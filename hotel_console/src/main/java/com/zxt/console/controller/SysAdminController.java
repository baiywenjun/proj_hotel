package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.console.pojo.SysAdminDTO;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.service.SysAdminService;
import com.zxt.hotel.service.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/21 9:32
 */
@Controller
@RequestMapping("/admin")
public class SysAdminController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping("/page")
    public String adminPage(){
        return "components/admin";
    }

    @RequestMapping("/add-page")
    public String adminAddPage(){
        return "components/admin-add";
    }

    @RequestMapping("update-page")
    public String adminUpdatePage(Model model, String id){
        model.addAttribute("operate","update");
        model.addAttribute("adminId",id);
        return "components/admin-add";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Rt sysAdminList(Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        return sysAdminService.querySysAdminByPage(handle.getPage(), handle.getLimit());
    }

    @PostMapping("/add")
    @ResponseBody
    public R addSysAdmin(@RequestBody SysAdminDTO sysAdmin){
        // 简化处理，后续进行优化
        Long adminId = sysAdminService.addRecord(sysAdmin);
        log.info(adminId.toString());
        List<Long> roleIdList = sysAdmin.getRoleIdList();
        sysUserRoleService.saveOrUpdate(adminId,roleIdList);
        return (adminId!=null)?R.ok():R.error();
    }
}
