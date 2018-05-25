package com.zxt.hotel.service;

import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.ServeType;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类型 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface ServeTypeService extends IService<ServeType> {

    /**
     * 酒店服务列表
     * @return rt
     */
    Rt serveTypeList();
}
