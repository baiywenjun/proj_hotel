package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.common.constant.hoConst;
import com.zxt.common.exception.RRException;
import com.zxt.common.result.Rt;
import com.zxt.common.util.GenNo;
import com.zxt.hotel.entity.HotelOrder;
import com.zxt.hotel.mapper.HotelOrderMapper;
import com.zxt.hotel.pojo.HotelOrderFullVO;
import com.zxt.hotel.pojo.HotelOrderQuery;
import com.zxt.hotel.service.HotelOrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    private static Logger log = LoggerFactory.getLogger(HotelOrderServiceImpl.class);

    @Autowired
    private HotelOrderMapper hotelOrderMapper;


    @Override
    public Rt queryHotelOrderList(HotelOrderQuery query, Integer page, Integer limit) {
        Wrapper<HotelOrder> wrapper = new EntityWrapper<>();
        if(query.getOrderId() != null){
            wrapper.eq("order_id",query.getOrderId());
        }
        if(StringUtils.isNotEmpty(query.getOrderNo())){
            wrapper.like("order_no",query.getOrderNo());
        }
        if(query.getIsUserId() != null){
            wrapper.eq("is_user_id",query.getIsUserId());
        }
        if(StringUtils.isNotEmpty(query.getPaymentStatus())){
            wrapper.eq("payment_status",query.getPaymentStatus());
        }
        if(StringUtils.isNotEmpty(query.getUserRealName())){
            wrapper.like("user_real_name",query.getUserRealName());
        }
        if(StringUtils.isNotEmpty(query.getUserPhone())){
            wrapper.like("user_phone",query.getUserPhone());
        }
        int count = this.selectCount(wrapper);
        wrapper.orderBy("ho.create_time",false);
        //Page<HotelOrder> hotelInfoPage = this.selectPage(new Page<>(page, limit), wrapper);
        //List<HotelOrder> records = hotelInfoPage.getRecords();
        List<HotelOrderFullVO> hotelOrderFullVOList = hotelOrderMapper.queryHotelOrderFullByPage(new Page(page, limit), wrapper);
        return Rt.ok(count,hotelOrderFullVOList);
    }


    @Override
    public Map<String,Object> addHotelOrder(HotelOrder hotelOrder) {
        if(hotelOrder.getHoldTime() == null){
            // 如果是续租新增的订单，生成一个保留时间
            Date holdTime = hotelOrder.getBeginDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(holdTime);
            calendar.set(Calendar.HOUR,21);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            Date convertHoldTime = calendar.getTime();
            hotelOrder.setHoldTime(convertHoldTime);
        }
        // 创建订单编号
        //String orderNo = UUID.randomUUID().toString().replace("-","").toUpperCase().substring(0,20);
        String orderNo = GenNo.hotelOrderNo(hotelOrder.getUsername());
        hotelOrder.setOrderNo(orderNo);
        hotelOrder.setCreateTime(new Date());
        hotelOrder.setPaymentStatus(hoConst.WAIT);
        boolean insert = this.insert(hotelOrder);
        if(! insert){
            throw new RRException("增加订单失败");
        }
        Long orderId = hotelOrder.getOrderId();
        // 返回订单ID和订单编号
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("orderId",orderId);
        resultMap.put("orderNo",orderNo);
        return resultMap;
    }

    @Override
    public Boolean updateHotelOrder(HotelOrder hotelOrder){
        return this.updateById(hotelOrder);
    }

    @Override
    /**
     * 支付成功后，更新订单
     * @param orderNo
     * @param paymentType
     * @param payNo
     * @return
     */
    public void updateOrderByPaid(String paymentType, String orderNo, String payNo){
        Wrapper<HotelOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("order_no",orderNo);
        HotelOrder hotelOrder = this.selectOne(wrapper);
        if(hotelOrder == null){
            log.error("订单编号["+orderNo+"]:查询不在该平台中,回调失败");
            throw new RRException("err");
        }
        hotelOrder.setPayNo(payNo);
        hotelOrder.setPaymentStatus(hoConst.PAID);
        hotelOrder.setPaymentType(paymentType);
        boolean flag = this.updateById(hotelOrder);
        if(! flag){
            log.error("订单编号["+orderNo+"]:更新订单失败");
            throw new RRException("err");
        }
    }


}
