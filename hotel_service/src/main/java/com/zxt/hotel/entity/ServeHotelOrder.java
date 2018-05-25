package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2018-05-24
 */
@TableName("serve_hotel_order")
public class ServeHotelOrder implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="serve_hotel_id", type= IdType.AUTO)
	private Long serveHotelId;
	@TableField("is_serve_type_id")
	private Long isServeTypeId;
	@TableField("is_order_id")
	private Long isOrderId;
	@TableField("is_order_room_id")
	private Long isOrderRoomId;
	private String phone;
    /**
     * 服务超时时间
     */
	@TableField("time_out")
	private Date timeOut;
	private String status;
    /**
     * 服务说明
     */
	private String remark;
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

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
			", timeOut=" + timeOut +
			", status=" + status +
			", remark=" + remark +
			", createTime=" + createTime +
			"}";
	}
}
