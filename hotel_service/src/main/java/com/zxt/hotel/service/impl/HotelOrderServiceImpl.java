package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.common.constant.hoConst;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelOrder;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.mapper.HotelOrderMapper;
import com.zxt.hotel.service.HotelOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单信息 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@Service
public class HotelOrderServiceImpl extends ServiceImpl<HotelOrderMapper, HotelOrder> implements HotelOrderService {

    @Override
    public Rt queryHotelOrderList(Integer page, Integer limit) {
        // todo 欠缺封装QueryDomain
        Wrapper<HotelOrder> wrapper = new EntityWrapper<>();
        int count = this.selectCount(wrapper);
        Page<HotelOrder> hotelInfoPage = this.selectPage(new Page<>(page, limit), wrapper);
        List<HotelOrder> records = hotelInfoPage.getRecords();
        return Rt.ok(count,records);
    }

    @Override
    public Boolean addHotelOrder(HotelOrder hotelOrder) {
        hotelOrder.setCreateTime(new Date());
        hotelOrder.setPaymentStatus(hoConst.WAIT);
        return this.insert(hotelOrder);
    }

    @Override
    public Boolean updateHotelOrder(HotelOrder hotelOrder){
        return this.updateById(hotelOrder);
    }
}
