package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.mapper.SysUserMapper;
import com.zxt.hotel.pojo.SysUserQuery;
import com.zxt.hotel.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public Rt queryUserByPage(SysUserQuery query,Integer page, Integer limit){
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(query.getUsername())){
            wrapper.like("username",query.getUsername());
        }
        if(StringUtils.isNotEmpty(query.getName())){
            wrapper.like("name",query.getName());
        }
        if(StringUtils.isNotEmpty(query.getPhone())){
            wrapper.like("phone",query.getPhone());
        }
        int count = this.selectCount(wrapper);
        wrapper.orderBy("create_time",false);
        Page<SysUser> sysUserPage = this.selectPage(new Page<>(page, limit), wrapper);
        List<SysUser> records = sysUserPage.getRecords();
        return Rt.ok(count,records);
    }
}
