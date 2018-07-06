package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.common.util.PageUtils;
import com.zxt.common.util.ValidatorUtils;
import com.zxt.hotel.entity.SysRole;
import com.zxt.hotel.service.SysRoleDeptService;
import com.zxt.hotel.service.SysRoleMenuService;
import com.zxt.hotel.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/7/1 14:29
 */
@Controller
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;


    @RequestMapping("/page")
    public String rolePage(){
        return "components/role";
    }

   /* @RequestMapping("/list")
    @ResponseBody
    public Rt roleList(Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        return sysRoleService.queryListByPage(handle.getPage(), handle.getLimit());
    }*/



    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysRoleService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @ResponseBody
    public R select(){
        List<SysRole> list = sysRoleService.selectList(null);
        return R.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @ResponseBody
    public R info(@PathVariable("roleId") Long roleId){
        SysRole role = sysRoleService.selectById(roleId);
        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        //查询角色对应的部门
        List<Long> deptIdList = sysRoleDeptService.queryDeptIdList(new Long[]{roleId});
        role.setDeptIdList(deptIdList);
        return R.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody SysRole role){
        //ValidatorUtils.validateEntity(role);
        sysRoleService.save(role);
        return R.ok();
    }

    /**
     * 修改角色
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody SysRole role){
        //ValidatorUtils.validateEntity(role);
        sysRoleService.update(role);
        return R.ok();
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Long[] roleIds){
        sysRoleService.deleteBatch(roleIds);
        return R.ok();
    }
}
