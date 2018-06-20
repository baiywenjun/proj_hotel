package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.pojo.SysUserQuery;
import com.zxt.hotel.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/8 14:19
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/page")
    public String SysUserListPage(){
        return "components/customer";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Rt queryUserListByPage(String phone,Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        SysUserQuery query = new SysUserQuery();
        query.setPhone(phone);
        Rt rt = sysUserService.queryUserByPage(query, handle.getPage(), handle.getLimit());
        return rt;
    }


    public R queryUserCount(){

        return null;
    }

}
