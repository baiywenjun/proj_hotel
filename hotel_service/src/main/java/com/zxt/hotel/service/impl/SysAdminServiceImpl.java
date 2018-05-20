package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.mapper.SysAdminMapper;
import com.zxt.hotel.service.SysAdminService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    @Override
    public SysAdmin queryByLogin(String adminName){
        Wrapper<SysAdmin> wrapper = new EntityWrapper<>();
        wrapper.eq("admin_name",adminName);
        SysAdmin sysAdmin = this.selectOne(wrapper);
        return sysAdmin;
    }

}
