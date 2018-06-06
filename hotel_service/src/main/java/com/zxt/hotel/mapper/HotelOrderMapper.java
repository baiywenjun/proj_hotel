package com.zxt.hotel.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.hotel.entity.HotelOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zxt.hotel.pojo.HotelOrderFullVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单信息 Mapper 接口
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface HotelOrderMapper extends BaseMapper<HotelOrder> {

    List<HotelOrderFullVO> queryHotelOrderFullByPage(Page page, @Param("ew") Wrapper wrapper);
}
