package com.zxt.hotel.service;

import com.baomidou.mybatisplus.service.IService;
import com.zxt.hotel.entity.SysAdmin;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
public interface SysAdminService extends IService<SysAdmin> {

    SysAdmin queryByLogin(String adminName);
}
