package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.common.constant.shoConst;
import com.zxt.common.customs.CallBack;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelOrderRoom;
import com.zxt.hotel.entity.ServeHotelOrder;
import com.zxt.hotel.mapper.HotelOrderRoomMapper;
import com.zxt.hotel.mapper.ServeHotelOrderMapper;
import com.zxt.hotel.pojo.ServeHotelOrderFullVO;
import com.zxt.hotel.pojo.ServeHotelOrderQuery;
import com.zxt.hotel.service.ServeHotelOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 酒店服务 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@Service
public class ServeHotelOrderServiceImpl extends ServiceImpl<ServeHotelOrderMapper, ServeHotelOrder> implements ServeHotelOrderService {

    @Autowired
    private ServeHotelOrderMapper serveHotelOrderMapper;

    @Override
    /**
     * 查询用户自己的订单
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public Rt queryListByUser(Long userId, Integer page, Integer limit){
        Wrapper<ServeHotelOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("is_user_id",userId);
        //wrapper.ne("status", shoConst.CANCEL);
        int count = this.selectCount(wrapper);
        wrapper.orderBy("create_time",false);
        Page<ServeHotelOrder> serveHotelOrderPage = this.selectPage(new Page<>(page, limit), wrapper);
        List<ServeHotelOrder> records = serveHotelOrderPage.getRecords();
        return Rt.ok(count,records);
    }


    @Override
    public ServeHotelOrder findOneById(Long serveHotelId){
        return this.selectById(serveHotelId);
    }

    @Override
    public Boolean cancelOrder(Long serveHotelId){
        ServeHotelOrder order = new ServeHotelOrder();
        order.setServeHotelId(serveHotelId);
        order.setStatus(shoConst.CANCEL);
        return this.updateById(order);
    }

    @Override
    /**
     * 后台查询服务订单
     * @param query
     * @param page
     * @param limit
     * @return
     */
    public Rt queryListByPage(ServeHotelOrderQuery query, Integer page, Integer limit){
        Wrapper<ServeHotelOrder> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(query.getRoomNo())){
            wrapper.eq("room_no",query.getRoomNo());
        }
        if(query.getIsServeTypeId() != null){
            wrapper.eq("is_serve_type_id",query.getIsServeTypeId());
        }
        int count = this.selectCount(wrapper);
        wrapper.orderBy("create_time",false);
        List<ServeHotelOrderFullVO> serveHotelOrderFullVOList = serveHotelOrderMapper.queryListByPage(new Page(page, limit), wrapper);
        return  Rt.ok(count,serveHotelOrderFullVOList);
    }

    /**
     * 后台查询服务订单(根据 serve_hotel_id 查询)
     * @param query
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Rt queryListByPage2(ServeHotelOrderQuery query, Integer page, Integer limit) {
        Wrapper<ServeHotelOrder> wrapper = new EntityWrapper<>();
        if (null != query.getServeHotelId()) {
            wrapper.eq("serve_hotel_id", query.getServeHotelId());
        }

        if (null != query.getHotelName() && !query.getHotelName().trim().equalsIgnoreCase("")) {
            wrapper.eq("hotel_name", query.getHotelName());
        }
        int count = this.selectCount(wrapper);
        wrapper.orderBy("create_time", false);
        List<ServeHotelOrderFullVO> serveHotelOrderFullVOList = serveHotelOrderMapper.queryListByPage2(new Page(page, limit), wrapper);
        return Rt.ok(count, serveHotelOrderFullVOList);
    }

    @Override
    public Rt queryListByPageNCallback(ServeHotelOrderQuery query, Integer page, Integer limit, CallBack<ServeHotelOrderFullVO> callback) {
        Wrapper<ServeHotelOrder> wrapper = new EntityWrapper<>();
        if (null != query.getServeHotelId()) {
            wrapper.eq("serve_hotel_id", query.getServeHotelId());
        }
        int count = this.selectCount(wrapper);
        wrapper.orderBy("create_time", false);
        List<ServeHotelOrderFullVO> serveHotelOrderFullVOList = serveHotelOrderMapper.queryListByPage2(new Page(page, limit), wrapper);
        callback.dblist(serveHotelOrderFullVOList);
        Rt ret=Rt.ok(count, serveHotelOrderFullVOList);
        if( null != callback && null!=callback.other() && callback.other().size()!=0){
            ret.put("other",callback.other());
        }
        return ret;
    }


}
