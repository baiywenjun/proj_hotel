package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.entity.HotelRoomType;
import com.zxt.hotel.mapper.HotelRoomTypeMapper;
import com.zxt.hotel.service.HotelRoomTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 酒店sku 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
@Service
public class HotelRoomTypeServiceImpl extends ServiceImpl<HotelRoomTypeMapper, HotelRoomType> implements HotelRoomTypeService {

    @Override
    public Rt queryHotelRoomTypeByPage(Integer page, Integer limit){
        // todo 欠缺封装QueryDomain
        Wrapper<HotelRoomType> wrapper = new EntityWrapper<>();
        int count = this.selectCount(wrapper);
        Page<HotelRoomType> hotelInfoPage = this.selectPage(new Page<>(page, limit), wrapper);
        List<HotelRoomType> records = hotelInfoPage.getRecords();
        return Rt.ok(count,records);
    }

}
