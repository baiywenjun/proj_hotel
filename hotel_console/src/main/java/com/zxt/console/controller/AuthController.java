package com.zxt.console.controller;

import com.google.code.kaptcha.Producer;
import com.zxt.common.result.R;
import com.zxt.hotel.entity.SysAdmin;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/4/7 21:53
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private static Logger log = LoggerFactory.getLogger(AuthController.class);

    private static final String LOGIN_CAPTCHA_KEY = "LOGIN_CAPTCHA_KEY";
    private static final String REGISTER_CAPTCHA_KEY = "REGISTER_CAPTCHA_KEY";

    @Autowired
    private Producer producer;

    @RequestMapping("/login_page")
    public String viewLogin(Model model){
        return "login";
    }

    @RequestMapping("/register_page")
    public String viewRegister(Model model){
        return "register";
    }

    /**
     * 获取注册验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/login_captcha.png")
    public void captcha(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        log.debug("验证码======> " + text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute(LOGIN_CAPTCHA_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
    }

    /**
     * 登录验证
     * @param adminName
     * @param password
     * @param captchaCode
     * @param request
     * @return r
     */
    @PostMapping(value="/login")
    @ResponseBody
    public R authorization(String adminName, String password, String captchaCode, HttpServletRequest request){
        // 先验证验证码
        //String contextCode = (String) request.getSession().getAttribute(LOGIN_CAPTCHA_KEY);
        // FIXME 开发阶段，关闭验证码
//		if(false == contextCode.equals(captchaCode)){
//			return R.ok("验证码错误");
//		}
        // 获取当前请求线程的用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(adminName, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            token.clear();
            if(e instanceof UnknownAccountException || e instanceof IncorrectCredentialsException){
                return R.error(1,"用户名或密码不正确");
            }else {
                e.printStackTrace();
            }
        }
        // 登录成功后，取用户session信息
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        if(savedRequest != null){
            String beforeUrl = savedRequest.getRequestUrl();
            log.debug("beforeUrl:====> " + beforeUrl + "");
        }
        return R.ok();
    }


    /**
     * 退出操作系统
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        SysAdmin sysAdmin = (SysAdmin) subject.getPrincipal();
        log.info(sysAdmin.getAdminName() + "退出了系统");
        subject.logout();
        return "redirect:login_page";
    }

    /**
     * 用户注册
     * @param userName
     * @param pwd
     * @param repeatPwd
     * @param code
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public R accountRegister(String userName, String pwd, String repeatPwd, String code,
                          HttpServletRequest request){
        R r = this.validateRegister(userName, pwd, repeatPwd, code, request);
        if(r!=null){
            return r;
        }
        // TODO
        Boolean flag = true;
        return (flag)?R.ok():R.error();
    }

    /**
     * 注册表单验证
     * @param userName
     * @param pwd
     * @param repeatPwd
     * @param code
     * @param request
     * @return
     */
    private R validateRegister(String userName, String pwd, String repeatPwd,String code,
                               HttpServletRequest request){
        if(StringUtils.isBlank(userName)){
            return R.error(1,"用户名不能为空");
        }
        if(StringUtils.isBlank(pwd)){
            return R.error(1,"密码不能为空");
        }
        if(StringUtils.isBlank(repeatPwd)){
            return R.error(1,"确认密码不能为空");
        }
        if(StringUtils.isBlank(code)){
            return R.error(1,"验证码不能为空");
        }
        if(!pwd.equals(repeatPwd)){
            return R.error(1,"两次密码不一致");
        }
        String phoneRegex = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        boolean phoneMatch = Pattern.matches(phoneRegex, userName);
//        if(!phoneMatch){
//            return R.error(1,"用户名要输入手机号码");
//        }
        String captchaCode = (String) request.getSession().getAttribute(REGISTER_CAPTCHA_KEY);
        if(!code.equals(captchaCode)){
            return R.error(1,"验证码不正确");
        }
        return null;
    }

}
