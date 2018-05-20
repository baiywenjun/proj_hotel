package com.zxt.console.service;

import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.service.SysAdminService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRealm extends AuthorizingRealm{
	private static Logger log = LoggerFactory.getLogger(AdminRealm.class);

	@Autowired
	private SysAdminService sysAdminService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
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
