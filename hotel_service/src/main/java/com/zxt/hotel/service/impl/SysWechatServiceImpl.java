package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.hotel.entity.SysWechat;
import com.zxt.hotel.mapper.SysWechatMapper;
import com.zxt.hotel.service.SysWechatService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 微信登录绑定 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-17
 */
@Service
public class SysWechatServiceImpl extends ServiceImpl<SysWechatMapper, SysWechat> implements SysWechatService {

    @Override
    public Boolean addRecord(SysWechat sysWechat){
        sysWechat.setCreateTime(new Date());
        return this.insert(sysWechat);
    }

    @Override
    /**
     * 通过openid获取绑定记录
     * @param openid
     * @return
     */
    public SysWechat findByOpenid(String openid){
        Wrapper<SysWechat> wrapper = new EntityWrapper();
        wrapper.eq("open_id",openid);
        return this.selectOne(wrapper);
    }
}
