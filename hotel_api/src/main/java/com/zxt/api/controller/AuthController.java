package com.zxt.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxt.api.pojo.OpenidInfo;
import com.zxt.api.sersvice.AuthService;
import com.zxt.common.result.R;
import com.zxt.common.util.HttpClientUtil;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.entity.SysWechat;
import com.zxt.hotel.service.SysUserService;
import com.zxt.hotel.service.SysWechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: 小程序验证
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/21 14:42
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "登录")
public class AuthController {

    private static Logger log = LoggerFactory.getLogger(AuthController.class);

    //"https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
    private static final String OPEN_ID_URL = "https://api.weixin.qq.com/sns/jscode2session";
    @Value("${wx.appid}")
    private String wxAppid;
    @Value("${wx.secret}")
    private String wxSecret;

    @Autowired
    private AuthService authService;
    @Autowired
    private SysWechatService sysWechatService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 小程序登录
     * @param code
     * @return
     */
    @RequestMapping("/wxlogin")
    @ApiOperation(httpMethod = "GET", value="小程序登录")
    public R wxlogin(String code){
        Map<String,String> param = new HashMap<>();
        param.put("appid",wxAppid);
        param.put("secret",wxSecret);
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        String result = HttpClientUtil.doGet(OPEN_ID_URL,param);
        OpenidInfo openidInfo = JSON.parseObject(result, OpenidInfo.class);
        if(openidInfo==null || StringUtils.isEmpty(openidInfo.getOpenid())){
            return R.error(403,"获取openId异常");
        }
        // 判断openid是否有绑定过，如果没有，登录失败，小程序跳转至绑定页面
        SysWechat sysWechat = sysWechatService.findByOpenid(openidInfo.getOpenid());
        if(sysWechat == null){
            Map<String,Object> map = new HashMap<>(1);
            map.put("code",401);
            map.put("msg","没有绑定手机号码");
            map.put("openid",openidInfo.getOpenid());
            return R.error(map);
        }
        // redis 登录
        String tokenKey = authService.loginFromRedis(openidInfo,sysWechat.getPhone());
        Map<String,Object> map = new HashMap<>(1);
        map.put("token",tokenKey);
        return R.ok(map);
    }

    /**
     * 在系统获取openId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getopenid")
    @ApiOperation(httpMethod = "GET", value="在系统获取openId")
    public R getWxToken(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            return R.error(401,"未在系统登录");
        }
        OpenidInfo openidInfo = authService.getOpenidInfoByToken(token);
        if(openidInfo == null){
            return R.error(401,"token信息错误");
        }
        Map<String,Object> map = new HashMap<>(1);
        map.put("openid",openidInfo.getOpenid());
        return R.ok(map);
    }

    /**
     * 手机号码绑定系统
     * @param phone
     * @return
     */
    @RequestMapping("/bind")
    @ApiOperation(httpMethod = "GET", value="手机号码绑定系统")
    public R bindPhone(String phone, String openid){
        if(StringUtils.isEmpty(phone)){
            return R.error(403,"手机号码不能为空");
        }
        if(StringUtils.isEmpty(openid)){
            return R.error(403,"openid不能为空");
        }
        // 添加绑定记录
        SysWechat sysWechat = new SysWechat();
        sysWechat.setPhone(phone);
        sysWechat.setOpenId(openid);
        Boolean flag1 = sysWechatService.addRecord(sysWechat);
        // 用户初次使用，创建默认帐号
        // 如果用户修改过微信信息，导致重新绑定，无需再创建默认帐号
        // warning 如果用户故意输入的别人手机号，则会以别人手机号的账号形式登录
        SysUser findOne = sysUserService.findOneByUsername(phone);
        if(findOne == null){
            SysUser sysUser = new SysUser();
            sysUser.setUsername(phone);
            sysUser.setPhone(phone);
            sysUser.setCreateTime(new Date());
            boolean flag2 = sysUserService.insert(sysUser);
            return (flag1 && flag2)?R.ok():R.error();
        }
        return (flag1)?R.ok():R.error();
    }


    /**
     * 模拟登陆
     * @param username
     * @return
     */
    @RequestMapping("/testlogin")
    @ApiOperation(httpMethod = "GET", value = "模拟小程序登陆")
    public R testLogin(String  username){
        if(StringUtils.isEmpty(username)){
            return R.error(403,"用户名不能为空");
        }
        OpenidInfo openidInfo = new OpenidInfo();
        openidInfo.setOpenid("THIS_IS_TEST_OPENID");
        openidInfo.setSessionKey("THIS_IS_TEST_SESSION_KEY");
        // redis 登录
        String tokenKey = authService.loginFromRedis(openidInfo,username);
        Map<String,Object> map = new HashMap<>(1);
        map.put("token",tokenKey);
        return R.ok(map);
    }
}
