package com.zxt.hotel.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.hotel.entity.ServeHotelOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zxt.hotel.pojo.ServeHotelOrderFullVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 酒店服务 Mapper 接口
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface ServeHotelOrderMapper extends BaseMapper<ServeHotelOrder> {

    List<ServeHotelOrderFullVO> queryListByPage(Page page,@Param("ew") Wrapper wrapper);

    List<ServeHotelOrderFullVO> queryListByPage2(Page page,@Param("ew") Wrapper<ServeHotelOrder> wrapper);
}
