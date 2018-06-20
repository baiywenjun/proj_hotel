package com.zxt.hotel.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.pojo.*;
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


    // 过渡
    List<HotelInfoFullDistanceVO> queryHotelInfoFullDistanceByPageOver(@Param("query") HotelInfoQuery query,
                                                                       @Param("ew") Wrapper wrapper,
                                                                       @Param("index") Integer index,
                                                                       @Param("size") Integer size);

    /**
     * 酒店列表-带距离
     * @param query
     * @param wrapper
     * @return
     */
    List<HotelInfoFullVO> queryHotelInfoFullDistanceByPage(@Param("query") HotelInfoQuery query,
                                                           @Param("ew") Wrapper wrapper,
                                                           @Param("index") Integer index,
                                                           @Param("size") Integer size);

    /**
     * 酒店列表
     * @param wrapper
     * @return
     */
    List<HotelInfoFullVO> queryHotelInfoFullByPage( @Param("ew") Wrapper wrapper, @Param("index") Integer index, @Param("size") Integer size);
}
