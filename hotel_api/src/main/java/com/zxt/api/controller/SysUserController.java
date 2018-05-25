package com.zxt.api.controller;

import com.zxt.api.sersvice.AuthService;
import com.zxt.common.result.R;
import com.zxt.common.util.DateUtil;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Title: 用户信息
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/24 11:54
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class SysUserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/info")
    @ApiOperation(httpMethod = "GET", value = "个人信息")
    public R info(HttpServletRequest request){
        SysUser sysUser = authService.getUserInfoByReq(request);
        return R.ok("success",sysUser);
    }


    @PostMapping("/edit")
    @ApiOperation(httpMethod = "POST", value = "编辑个人信息")
    public R updateInfo(String userRealName,String sex,String birthday, HttpServletRequest request){
        SysUser sysUser = authService.getUserInfoByReq(request);
        if(StringUtils.isEmpty(userRealName)){
            return R.error(403,"姓名不能为空");
        }
        int sexInt = 1;
        if("女".equalsIgnoreCase(sex)){
            sexInt = 2;
        }
        Date birth = DateUtil.parseDate(birthday);
        sysUser.setName(userRealName);
        sysUser.setSex(sexInt);
        sysUser.setBirthday(birth);
        boolean flag = sysUserService.updateById(sysUser);
        return (flag)?R.ok():R.error();
    }
}
