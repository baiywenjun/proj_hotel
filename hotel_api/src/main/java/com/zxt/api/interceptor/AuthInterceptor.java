package com.zxt.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxt.api.pojo.TokenInfo;
import com.zxt.common.config.RedisUtils;
import com.zxt.common.result.R;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/24 16:32
 */
public class AuthInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            log.warn("TOKEN IS NULL!!");
            R error = R.error(401, "令牌为空");
            ObjectMapper mapper = new ObjectMapper();
            String errorStr = mapper.writeValueAsString(error);
            response.setHeader("Content-Type","application/json;charset=UTF-8");
            response.getWriter().print(errorStr);
            return false;
        }
        // 小程序一般不存在令牌过期的情况
        TokenInfo tokenInfo = redisUtils.get(token, TokenInfo.class);
        if(tokenInfo==null){
            log.warn("TOKEN INCORRECT!!");
            R error = R.error(401, "令牌错误");
            ObjectMapper mapper = new ObjectMapper();
            String errorStr = mapper.writeValueAsString(error);
            response.setHeader("Content-Type","application/json;charset=UTF-8");
            response.getWriter().print(errorStr);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //log.debug("AuthInterceptor test postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //log.debug("AuthInterceptor afterCompletion");
    }

}
