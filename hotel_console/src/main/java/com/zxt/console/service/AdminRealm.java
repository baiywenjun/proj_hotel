package com.zxt.console.service;

import com.zxt.common.constant.Constant;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.entity.SysMenu;
import com.zxt.hotel.mapper.SysAdminMapper;
import com.zxt.hotel.mapper.SysMenuMapper;
import com.zxt.hotel.service.SysAdminService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class AdminRealm extends AuthorizingRealm{
	private static Logger log = LoggerFactory.getLogger(AdminRealm.class);

	@Autowired
	private SysAdminService sysAdminService;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysAdminMapper sysAdminMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysAdmin admin = (SysAdmin)principals.getPrimaryPrincipal();
		Long adminId = admin.getAdminId();
		// 权限列表
		List<String> permsList;
		//系统管理员，拥有最高权限
		if(adminId == Constant.SUPER_ADMIN){
			List<SysMenu> menuList = sysMenuMapper.selectList(null);
			permsList = new ArrayList<>(menuList.size());
			for(SysMenu menu : menuList){
				permsList.add(menu.getPerms());
			}
		}else{
			permsList = sysAdminMapper.queryAllPerms(adminId);
		}
		//用户权限标识符列表
		Set<String> permsSet = new HashSet<>();
		for(String perms : permsList){
			if(StringUtils.isBlank(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String usercode = (String) token.getPrincipal();
		SysAdmin sysAdmin = sysAdminService.queryByLogin(usercode);

		if(sysAdmin == null || sysAdmin.getAdminName() == null){
			//返回null，认证器接收到null，抛出异常UnknownAccountException
			return null;
		}
		// 执行到这里说明账号存在
		// 根据账号从数据库查询正确的密码 
		String pwd = sysAdmin.getPassword();
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(sysAdmin, pwd, "adminRealm");
		return simpleAuthenticationInfo;
	}


	
}
