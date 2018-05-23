package com.zxt.hotel.service;

import com.baomidou.mybatisplus.service.IService;
import com.zxt.hotel.entity.SysWechat;

/**
 * <p>
 * 微信登录绑定 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-17
 */
public interface SysWechatService extends IService<SysWechat> {

    Boolean addRecord(SysWechat sysWechat);

    /**
     * 通过openid获取绑定记录
     * @param openid
     * @return
     */
    SysWechat findByOpenid(String openid);
}
