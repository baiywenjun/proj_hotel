package com.zxt.hotel.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.pojo.HotelInfoExt;
import com.zxt.hotel.pojo.HotelInfoFullVOBack;
import com.zxt.hotel.pojo.HotelInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 酒店信息 Mapper 接口
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
public interface HotelInfoMapper extends BaseMapper<HotelInfo> {

    /**
     * 带距离显示的分页
     * @param page
     * @param query
     * @return
     */
    List<HotelInfoExt> queryHotelInfoExtByPage(Page<HotelInfoExt> page, @Param("query") HotelInfoQuery query);
}
