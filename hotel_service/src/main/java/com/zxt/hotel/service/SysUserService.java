package com.zxt.hotel.service;

import com.baomidou.mybatisplus.service.IService;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.pojo.SysUserQuery;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-17
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过用户名（手机号）获取用户信息
     * @param username username
     * @return SysUser
     */
    SysUser findOneByUsername(String username);

    Rt queryUserByPage(SysUserQuery query, Integer page, Integer limit);
}
