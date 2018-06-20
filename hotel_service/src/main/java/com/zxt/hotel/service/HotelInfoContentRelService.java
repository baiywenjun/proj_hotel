package com.zxt.hotel.service;

import com.zxt.hotel.entity.HotelInfoContentRel;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 酒店与描述关系 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-06-12
 */
public interface HotelInfoContentRelService extends IService<HotelInfoContentRel> {


    Boolean batchInsertRecord(Long hotelId, List<Long> contentIdList);

    Boolean deleteRecordWithContent(Long hotelId);
}
