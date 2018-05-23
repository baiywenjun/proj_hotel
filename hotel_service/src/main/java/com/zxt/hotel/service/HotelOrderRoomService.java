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
     * 走线上的分房
     * @param hotelOrderId 订单id
     * @param roomIds 房间Id数组
     * @return Boolean
     */
    Boolean addRecordByAllotRoom(Long hotelOrderId, String[] roomIds);
}
