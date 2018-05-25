package com.zxt.hotel.service;

import com.zxt.hotel.entity.HotelOrderRoom;
import com.baomidou.mybatisplus.service.IService;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.pojo.StayInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 房间的订单历史信息 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface HotelOrderRoomService extends IService<HotelOrderRoom> {

    /**
     * 用户通过扫码，新增房间订单
     * @param roomId
     * @param sysUser
     * @return
     */
    Boolean addRecordByUserScan(Long roomId, SysUser sysUser);

    /**
     * 获取入住信息
     * @param sysUser user
     * @return StayInfoVO
     */
    StayInfoVO getStayInfo(SysUser sysUser);

    /**
     * 用户扫码退房操作
     * @param roomId roomId
     * @param orderRoomId orderRoomId
     * @return flag
     */
    Boolean quitRoom(Long roomId, Long orderRoomId);

    /**
     * 走线上订单，已包含分房信息
     * @param hotelOrderId
     * @return
     */
    Boolean addRecordByHotelOrder(Long hotelOrderId);

    /**
     * 续住
     * @param hotelOrderId
     * @return
     */
    Boolean continueOrder(Long hotelOrderId);

    /**
     * 走线上的分房
     * @param hotelOrderId 订单id
     * @param roomIds 房间Id数组
     * @return Boolean
     */
    Boolean addRecordByAllotRoom(Long hotelOrderId, String[] roomIds);
}
