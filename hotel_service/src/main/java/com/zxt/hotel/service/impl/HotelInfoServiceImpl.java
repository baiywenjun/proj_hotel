package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.mapper.HotelInfoMapper;
import com.zxt.hotel.pojo.HotelInfoQuery;
import com.zxt.hotel.service.HotelInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 酒店信息 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
@Service
public class HotelInfoServiceImpl extends ServiceImpl<HotelInfoMapper, HotelInfo> implements HotelInfoService {

    @Override
    /**
     * 查询酒店信息
     * @param page page
     * @param limit limit
     * @return rt
     */
    public Rt queryHotelInfoByPage(HotelInfoQuery query, Integer page, Integer limit){
        Wrapper<HotelInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(query.getName())){
            wrapper.like("name",query.getName());
        }
        int count = this.selectCount(wrapper);
        Page<HotelInfo> hotelInfoPage = this.selectPage(new Page<>(page, limit), wrapper);
        List<HotelInfo> records = hotelInfoPage.getRecords();
        return Rt.ok(count,records);
    }

    @Override
    public Boolean addHotelInfo(HotelInfo hotel) {
        hotel.setCreateTime(new Date());
        boolean insert = this.insert(hotel);
        return insert;

    }

    @Override
    public Boolean updateHotelInfo(HotelInfo hotel) {
        return this.updateById(hotel);
    }

}
