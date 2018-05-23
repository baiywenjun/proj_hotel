package com.zxt.hotel.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 酒店服务
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@TableName("serve_hotel_order")
public class ServeHotelOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("serve_hotel_id")
	private Long serveHotelId;
	@TableField("is_serve_type_id")
	private Long isServeTypeId;
	@TableField("is_order_id")
	private Long isOrderId;
	@TableField("is_order_room_id")
	private Long isOrderRoomId;
	private String phone;
	private String status;
	@TableField("create_time")
	private Date createTime;


	public Long getServeHotelId() {
		return serveHotelId;
	}

	public void setServeHotelId(Long serveHotelId) {
		this.serveHotelId = serveHotelId;
	}

	public Long getIsServeTypeId() {
		return isServeTypeId;
	}

	public void setIsServeTypeId(Long isServeTypeId) {
		this.isServeTypeId = isServeTypeId;
	}

	public Long getIsOrderId() {
		return isOrderId;
	}

	public void setIsOrderId(Long isOrderId) {
		this.isOrderId = isOrderId;
	}

	public Long getIsOrderRoomId() {
		return isOrderRoomId;
	}

	public void setIsOrderRoomId(Long isOrderRoomId) {
		this.isOrderRoomId = isOrderRoomId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ServeHotelOrder{" +
			"serveHotelId=" + serveHotelId +
			", isServeTypeId=" + isServeTypeId +
			", isOrderId=" + isOrderId +
			", isOrderRoomId=" + isOrderRoomId +
			", phone=" + phone +
			", status=" + status +
			", createTime=" + createTime +
			"}";
	}
}
