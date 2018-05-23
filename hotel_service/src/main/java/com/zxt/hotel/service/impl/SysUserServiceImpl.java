package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.mapper.SysUserMapper;
import com.zxt.hotel.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    /**
     * 通过用户名（手机号）获取用户信息
     * @param username username
     * @return SysUser
     */
    public SysUser findOneByUsername(String username){
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("username",username);
        return this.selectOne(wrapper);
    }

}
