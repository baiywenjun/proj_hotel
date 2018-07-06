package com.zxt.console.controller;

import com.zxt.common.constant.Constant;
import com.zxt.common.exception.RRException;
import com.zxt.common.result.R;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.entity.SysMenu;
import com.zxt.hotel.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import java.util.List;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/7/1 15:00
 */
@Controller
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/page")
    public String menuPage(){
        return "components/menu";
    }

    /**
     * 左侧菜单显示
     */
    @RequestMapping("/nav")
    @ResponseBody
    public R nav(){
        //  获取用户ID
        SysAdmin user = (SysAdmin) SecurityUtils.getSubject().getPrincipal();
        Long userId = user.getAdminId();
        List<SysMenu> menuList = sysMenuService.getUserMenuList(userId);
        return R.ok().put("menuList", menuList);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<SysMenu> list(){
        List<SysMenu> menuList = sysMenuService.selectList(null);
        for(SysMenu sysMenuEntity : menuList){
            SysMenu parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
            if(parentMenuEntity != null){
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }
        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    @ResponseBody
    public R select(){
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();
        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);
        return R.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    @ResponseBody
    public R info(@PathVariable("menuId") Long menuId){
        SysMenu menu = sysMenuService.selectById(menuId);
        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);
        sysMenuService.insert(menu);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);
        sysMenuService.updateById(menu);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(long menuId){
        if(menuId <= 31){
            return R.error("系统菜单，不能删除");
        }
        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
        if(menuList.size() > 0){
            return R.error("请先删除子菜单或按钮");
        }
        sysMenuService.delete(menuId);
        return R.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu){
        if(StringUtils.isBlank(menu.getName())){
            throw new RRException("菜单名称不能为空");
        }
        if(menu.getParentId() == null){
            throw new RRException("上级菜单不能为空");
        }
        //菜单
        if(menu.getType() == Constant.MenuType.MENU.getValue()){
            if(StringUtils.isBlank(menu.getUrl())){
                throw new RRException("菜单URL不能为空");
            }
        }
        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }
        //目录、菜单
        if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()){
            if(parentType != Constant.MenuType.CATALOG.getValue()){
                throw new RRException("上级菜单只能为目录类型");
            }
            return ;
        }
        //按钮
        if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
            if(parentType != Constant.MenuType.MENU.getValue()){
                throw new RRException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }


}
