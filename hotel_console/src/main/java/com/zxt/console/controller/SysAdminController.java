package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/21 9:32
 */
@Controller
@RequestMapping("/admin")
public class SysAdminController {

    @Autowired
    private SysAdminService sysAdminService;

    @RequestMapping("/list")
    public Rt sysAdminList(Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        return sysAdminService.querySysAdminByPage(handle.getPage(), handle.getLimit());
    }

    @PostMapping("/add")
    public R addSysAdmin(@RequestBody SysAdmin sysAdmin){
        Boolean flag = sysAdminService.addSysAdmin(sysAdmin);
        return (flag)?R.ok():R.error();
    }
}
