package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.mapper.SysAdminMapper;
import com.zxt.hotel.service.SysAdminService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


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

    @Override
    public Rt querySysAdminByPage(Integer page, Integer limit) {
        // todo 欠缺封装QueryDomain
        Wrapper<SysAdmin> wrapper = new EntityWrapper<>();
        int count = this.selectCount(wrapper);
        Page<SysAdmin> hotelInfoPage = this.selectPage(new Page<>(page, limit), wrapper);
        List<SysAdmin> records = hotelInfoPage.getRecords();
        return Rt.ok(count,records);
    }

    @Override
    public Boolean addSysAdmin(SysAdmin sysAdmin) {
        // todo 设置default值，如默认密码等
        sysAdmin.setCreateTime(new Date());
        boolean insert = this.insert(sysAdmin);
        return insert;
    }

}
