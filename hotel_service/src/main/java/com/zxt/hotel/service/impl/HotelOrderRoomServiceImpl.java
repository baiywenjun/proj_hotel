package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zxt.common.constant.horConst;
import com.zxt.common.exception.RRException;
import com.zxt.common.util.DateUtil;
import com.zxt.hotel.entity.*;
import com.zxt.hotel.mapper.*;
import com.zxt.hotel.pojo.StayInfoVO;
import com.zxt.hotel.service.HotelOrderRoomService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 房间的订单历史信息 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@Service
public class HotelOrderRoomServiceImpl extends ServiceImpl<HotelOrderRoomMapper, HotelOrderRoom> implements HotelOrderRoomService {

    @Autowired
    private HotelOrderMapper hotelOrderMapper;

    @Autowired
    private HotelRoomMapper hotelRoomMapper;

    @Autowired
    private HotelRoomTypeMapper hotelRoomTypeMapper;

    @Autowired
    private HotelInfoMapper hotelInfoMapper;

    @Override
    /**
     * 用户通过扫码，新增房间订单
     * @param roomId
     * @param request
     * @return
     */
    public Boolean addRecordByUserScan(Long roomId, SysUser sysUser){
        HotelRoom hotelRoom = hotelRoomMapper.selectById(roomId);
        HotelRoomType hotelRoomType = hotelRoomTypeMapper.selectById(hotelRoom.getIsRoomTypeId());
        HotelOrderRoom hotelOrderRoom = new HotelOrderRoom();
        hotelOrderRoom.setIsRoomId(roomId);
        // 添加房型信息
        hotelOrderRoom.setTypeName(hotelRoomType.getTypeName());
        hotelOrderRoom.setTypeSpec(hotelRoomType.getTypeSpec());
        hotelOrderRoom.setTypePrice(hotelRoomType.getTypePrice());
        // 添加房间信息
        hotelOrderRoom.setStorey(hotelRoom.getStorey());
        hotelOrderRoom.setRoomNo(hotelRoom.getRoomNo());
        // 添加用户信息
        hotelOrderRoom.setUsername(sysUser.getUsername());
        hotelOrderRoom.setUserRealName(sysUser.getName());
        hotelOrderRoom.setUserPhone(sysUser.getPhone());
        // 添加入住信息
        // 通过扫码添加的入住信息，默认入住开始时间是扫码时间，结束时间是第二天12点
        Date now = new Date();
        Date tomorrow12 = this.getTomorrow12(now);
        hotelOrderRoom.setBeginDate(now);
        hotelOrderRoom.setEndDate(tomorrow12);
        hotelOrderRoom.setStayStatus(horConst.STAY);
        hotelOrderRoom.setConsumeType(horConst.OFFLINE);
        hotelOrderRoom.setCreateTime(new Date());
        // insert
        boolean insert = this.insert(hotelOrderRoom);
        // 因为走线下，不知道房间是否入住，只有在扫码时才知道已经有入住
        hotelRoom.setStayStatus(horConst.STAY);
        Integer flag = hotelRoomMapper.updateById(hotelRoom);
        return (insert && flag>0);
    }

    // 获取第二天12点的日期对象
    private Date getTomorrow12(Date now){
        DateUtil dateUtil = new DateUtil();
        Date tomorrow = dateUtil.afterNDay(now, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tomorrow);
        calendar.set(Calendar.HOUR_OF_DAY,12);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }


    @Override
    /**
     * 获取入住信息
     * @param sysUser user
     * @return StayInfoVO
     */
    public StayInfoVO getStayInfo(SysUser sysUser){
        Wrapper<HotelOrderRoom> wrapper = new EntityWrapper<>();
        // 获取 当前用户 正在入住的 未过期的
        wrapper.eq("username",sysUser.getUsername());
        wrapper.eq("stay_status",horConst.STAY);
        wrapper.gt("end_date",new Date());
        List<HotelOrderRoom> hotelOrderRoomList = this.selectList(wrapper);
        if(hotelOrderRoomList.size() < 1){
            return null;
        }
        if(hotelOrderRoomList.size() > 1){
            // to do something 订多个房间，后续处理
        }
        HotelOrderRoom hotelOrderRoom = hotelOrderRoomList.get(0);
        HotelRoom hotelRoom = hotelRoomMapper.selectById(hotelOrderRoom.getIsRoomId());
        HotelInfo hotelInfo = hotelInfoMapper.selectById(hotelRoom.getIsHotelId());
        StayInfoVO stayInfoVO = new StayInfoVO();
        stayInfoVO.setHotelInfo(hotelInfo);
        stayInfoVO.setHotelOrderRoom(hotelOrderRoom);
        stayInfoVO.setRoomNo(hotelOrderRoom.getRoomNo());
        stayInfoVO.setHotelOrderRoomId(hotelOrderRoom.getOrderRoomId());
        stayInfoVO.setHotelOrderId(hotelOrderRoom.getIsOrderId());
        return stayInfoVO;

    }


    @Override
    /**
     * 用户扫码退房操作
     * @param roomId roomId
     * @param orderRoomId orderRoomId
     * @return flag
     */
    public Boolean quitRoom(Long roomId, Long orderRoomId){
        HotelOrderRoom orderRoom = new HotelOrderRoom();
        orderRoom.setOrderRoomId(orderRoomId);
        // 订单更新为离开状态
        orderRoom.setStayStatus(horConst.QUIT);
        boolean flag1 = this.updateById(orderRoom);
        // 房间信息更新为离开状态
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setRoomId(roomId);
        hotelRoom.setStayStatus(horConst.QUIT);
        Integer flag2 = hotelRoomMapper.updateById(hotelRoom);
        return (flag1 && flag2>0);
    }

    @Override
    /**
     * 走线上订单，已包含分房信息
     * @param hotelOrderId
     * @return
     */
    public Boolean addRecordByHotelOrder(Long hotelOrderId){
        HotelOrder hotelOrder = hotelOrderMapper.selectById(hotelOrderId);
        HotelRoomType hotelRoomType = hotelRoomTypeMapper.selectById(hotelOrder.getIsRoomTypeId());
        // 不可复用新增订单
        String roomIds = hotelOrder.getRoomIds();
        if(StringUtils.isEmpty(roomIds)){
            throw new RRException("roomIds是空");
        }
        String[] roomIdArr = roomIds.split(",");
        if(roomIdArr.length<1){
            throw new RRException("roomIds中没有房间主键");
        }
        int flag = 0;
        for (String roomId : roomIdArr) {
            HotelRoom hotelRoom = hotelRoomMapper.selectById(Long.parseLong(roomId));
            // create entity
            HotelOrderRoom hotelOrderRoom = new HotelOrderRoom();
            // 冗余hotelOrder信息
            hotelOrderRoom.setIsOrderId(hotelOrder.getOrderId());
            hotelOrderRoom.setIsRoomId(Long.parseLong(roomId));
            hotelOrderRoom.setUsername(hotelOrder.getUsername());
            hotelOrderRoom.setUserPhone(hotelOrder.getUserPhone());
            hotelOrderRoom.setUserRealName(hotelOrder.getUserRealName());
            hotelOrderRoom.setBeginDate(hotelOrder.getBeginDate());
            hotelOrderRoom.setEndDate(hotelOrder.getEndDate());
            hotelOrderRoom.setTotalDate(hotelOrder.getTotalDate());
            hotelOrderRoom.setAmountPrice(hotelOrder.getAmountPrice());
            hotelOrderRoom.setPaymentType(hotelOrder.getPaymentType());
            hotelOrderRoom.setPayNo(hotelOrder.getPayNo());
            // 冗余房间信息
            hotelOrderRoom.setTypeName(hotelRoomType.getTypeName());
            hotelOrderRoom.setTypeSpec(hotelRoomType.getTypeSpec());
            hotelOrderRoom.setStorey(hotelRoom.getStorey());
            hotelOrderRoom.setRoomNo(hotelRoom.getRoomNo());
            // other
            hotelOrderRoom.setCreateTime(new Date());
            hotelOrderRoom.setStayStatus(horConst.STAY);
            hotelOrderRoom.setConsumeType(horConst.ONLINE);
            boolean insert = this.insert(hotelOrderRoom);
            if(insert){
                flag ++;
            }
        }
        return (flag == roomIdArr.length);
    }


    @Override
    /**
     * 续住
     * @param hotelOrderId
     * @return
     */
    public Boolean continueOrder(Long hotelOrderId){
        return this.addRecordByHotelOrder(hotelOrderId);
    }


    @Override
    /**
     * 走线上的分房
     * @param hotelOrderId 订单id
     * @param roomIds 房间Id数组
     * @return Boolean
     */
    public Boolean addRecordByAllotRoom(Long hotelOrderId, String[] roomIds){
        HotelOrder hotelOrder = hotelOrderMapper.selectById(hotelOrderId);
        String roomIdsStr = StringUtils.join(roomIds, ",");
        hotelOrder.setRoomIds(roomIdsStr);
        // 订单表更新选择房号
        Integer result1 = hotelOrderMapper.updateById(hotelOrder);
        // 选择了多少房间，添加房间订单信息
        int result2 = 0;
        for(int i=0; i<roomIds.length; i++){
            String roomIdStr = roomIds[i];
            Long roomId = Long.parseLong(roomIdStr);
            HotelRoom hotelRoom = hotelRoomMapper.selectById(roomId);
            HotelRoomType hotelRoomType = hotelRoomTypeMapper.selectById(hotelRoom.getIsRoomTypeId());
            // 房间历史订单
            HotelOrderRoom hotelOrderRoom = new HotelOrderRoom();
            hotelOrderRoom.setIsOrderId(hotelOrderId);
            hotelOrderRoom.setIsRoomId(roomId);
            // 添加房型信息
            hotelOrderRoom.setTypeName(hotelRoomType.getTypeName());
            hotelOrderRoom.setTypeSpec(hotelRoomType.getTypeSpec());
            hotelOrderRoom.setTypePrice(hotelRoomType.getTypePrice());
            // 添加房间信息
            hotelOrderRoom.setStorey(hotelRoom.getStorey());
            hotelOrderRoom.setRoomNo(hotelRoom.getRoomNo());
            // 添加订单的信息
            hotelOrderRoom.setUserRealName(hotelOrder.getUserRealName());
            hotelOrderRoom.setUserPhone(hotelOrder.getUserPhone());
            hotelOrderRoom.setBeginDate(hotelOrder.getBeginDate());
            hotelOrderRoom.setEndDate(hotelOrder.getEndDate());
            hotelOrderRoom.setTotalDate(hotelOrder.getTotalDate());
            hotelOrderRoom.setAmountPrice(hotelOrder.getAmountPrice());
            hotelOrderRoom.setPaymentType(hotelOrder.getPaymentType());
            hotelOrderRoom.setPayNo(hotelOrder.getPayNo());
            // 添加入住信息
            hotelOrderRoom.setStayStatus(horConst.STAY);
            hotelOrderRoom.setConsumeType(horConst.ONLINE);
            hotelOrderRoom.setCreateTime(new Date());
            // insert
            boolean insert = this.insert(hotelOrderRoom);
            if(insert){
                result2 ++;
            }
        }
        // 判断操作结果
        return (result1>0 && result2==roomIds.length);
    }

    /**
     * 走线下分房
     * @param roomIds 房间Id数组
     * @return
     */
    public Boolean addRecordByAllotRoom(String[] roomIds){
        // 选择了多少房间，添加房间订单信息
        int result2 = 0;
        for(int i=0; i<roomIds.length; i++){
            String roomIdStr = roomIds[i];
            Long roomId = Long.parseLong(roomIdStr);
            HotelRoom hotelRoom = hotelRoomMapper.selectById(roomId);
            HotelRoomType hotelRoomType = hotelRoomTypeMapper.selectById(hotelRoom.getIsRoomTypeId());
            // 房间历史订单
            HotelOrderRoom hotelOrderRoom = new HotelOrderRoom();
            hotelOrderRoom.setIsRoomId(roomId);
            // 添加房型信息
            hotelOrderRoom.setTypeName(hotelRoomType.getTypeName());
            hotelOrderRoom.setTypeSpec(hotelRoomType.getTypeSpec());
            hotelOrderRoom.setTypePrice(hotelRoomType.getTypePrice());
            // 添加房间信息
            hotelOrderRoom.setStorey(hotelRoom.getStorey());
            hotelOrderRoom.setRoomNo(hotelRoom.getRoomNo());
            // 走线下，没有订单的信息
            // 添加入住信息
            hotelOrderRoom.setStayStatus(horConst.STAY);
            hotelOrderRoom.setConsumeType(horConst.OFFLINE);
            hotelOrderRoom.setCreateTime(new Date());
            // insert
            boolean insert = this.insert(hotelOrderRoom);
            if(insert){
                result2 ++;
            }
        }
        return (result2==roomIds.length);
    }
}
