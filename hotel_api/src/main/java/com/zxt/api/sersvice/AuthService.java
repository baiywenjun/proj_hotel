package com.zxt.api.sersvice;

import com.zxt.api.pojo.OpenidInfo;
import com.zxt.api.pojo.TokenInfo;
import com.zxt.common.config.RedisUtils;
import com.zxt.common.exception.RRException;
import com.zxt.common.util.DateUtil;
import com.zxt.common.util.MD5Utils;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Title: 与小程序登录相关
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/22 14:12
 */
@Service
public class AuthService {
    private static Logger log = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录在本系统中，并注册openId与用户信息
     * @param openidInfo
     * @param phone
     * @return
     */
    public String loginFromRedis(OpenidInfo openidInfo, String phone){
        String openid = openidInfo.getOpenid();
        log.info("openid--->"+openid);
        log.info("phone--->"+phone);
        SysUser sysUser = sysUserService.findOneByUsername(phone);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setOpenidInfo(openidInfo);
        tokenInfo.setSysUser(sysUser);
        try {
            String tokenKey = MD5Utils.MD5_32bit1(openid + DateUtil.formatDateByFormat(new Date(), "yyyyMMddHHmmss"));
            // token存在12小时 12*60*60
            redisUtils.set(tokenKey,tokenInfo,43200);
            return tokenKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取token-value
    public TokenInfo getTokenInfo(String token) {
        return redisUtils.get(token, TokenInfo.class);
    }

    // 获取openid
    public OpenidInfo getOpenidInfoByToken(String token){
        TokenInfo tokenInfo = this.getTokenInfo(token);
        return tokenInfo.getOpenidInfo();
    }

    // 获取用户信息
    public SysUser getUserInfoByToken(String token){
        TokenInfo tokenInfo = this.getTokenInfo(token);
        if(tokenInfo == null){
            throw new RRException("token错误",403);
        }
        return tokenInfo.getSysUser();
    }

    // 通过request 获取用户信息
    public SysUser getUserInfoByReq(HttpServletRequest request){
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            throw new RRException("token是空",403);
        }
        SysUser sysUser = this.getUserInfoByToken(token);
        if(sysUser == null){
            throw new RRException("token错误",403);
        }
        return sysUser;
    }

    // 通过request 获取openid
    public OpenidInfo getOpenidInfoByReq(HttpServletRequest request){
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            throw new RRException("token是空",403);
        }
        OpenidInfo openidInfo = this.getOpenidInfoByToken(token);
        if(openidInfo == null){
            throw new RRException("token错误",403);
        }
        return openidInfo;
    }
}
