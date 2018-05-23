package com.zxt.hotel.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 订单信息
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@TableName("hotel_order")
public class HotelOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("order_id")
	private Long orderId;
	@TableField("order_no")
	private String orderNo;
	@TableField("is_hotel_id")
	private Long isHotelId;
	@TableField("is_room_type_id")
	private Long isRoomTypeId;
	@TableField("is_user_id")
	private Long isUserId;
	private String username;
	@TableField("user_real_name")
	private String userRealName;
	@TableField("user_phone")
	private String userPhone;
	@TableField("begin_date")
	private Date beginDate;
	@TableField("end_date")
	private Date endDate;
	@TableField("total_date")
	private Integer totalDate;
	@TableField("room_count")
	private Integer roomCount;
	@TableField("hold_time")
	private Date holdTime;
	@TableField("is_coupon_id")
	private Long isCouponId;
	@TableField("coupon_money")
	private Integer couponMoney;
	@TableField("amount_price")
	private Integer amountPrice;
	@TableField("payment_type")
	private String paymentType;
	@TableField("payment_status")
	private String paymentStatus;
	@TableField("pay_no")
	private String payNo;
	@TableField("create_time")
	private Date createTime;
	@TableField("order_status")
	private String orderStatus;
	@TableField("room_ids")
	private String roomIds;
	private String remark;


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getIsHotelId() {
		return isHotelId;
	}

	public void setIsHotelId(Long isHotelId) {
		this.isHotelId = isHotelId;
	}

	public Long getIsRoomTypeId() {
		return isRoomTypeId;
	}

	public void setIsRoomTypeId(Long isRoomTypeId) {
		this.isRoomTypeId = isRoomTypeId;
	}

	public Long getIsUserId() {
		return isUserId;
	}

	public void setIsUserId(Long isUserId) {
		this.isUserId = isUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getTotalDate() {
		return totalDate;
	}

	public void setTotalDate(Integer totalDate) {
		this.totalDate = totalDate;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public Date getHoldTime() {
		return holdTime;
	}

	public void setHoldTime(Date holdTime) {
		this.holdTime = holdTime;
	}

	public Long getIsCouponId() {
		return isCouponId;
	}

	public void setIsCouponId(Long isCouponId) {
		this.isCouponId = isCouponId;
	}

	public Integer getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Integer couponMoney) {
		this.couponMoney = couponMoney;
	}

	public Integer getAmountPrice() {
		return amountPrice;
	}

	public void setAmountPrice(Integer amountPrice) {
		this.amountPrice = amountPrice;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(String roomIds) {
		this.roomIds = roomIds;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "HotelOrder{" +
			"orderId=" + orderId +
			", orderNo=" + orderNo +
			", isHotelId=" + isHotelId +
			", isRoomTypeId=" + isRoomTypeId +
			", isUserId=" + isUserId +
			", username=" + username +
			", userRealName=" + userRealName +
			", userPhone=" + userPhone +
			", beginDate=" + beginDate +
			", endDate=" + endDate +
			", totalDate=" + totalDate +
			", roomCount=" + roomCount +
			", holdTime=" + holdTime +
			", isCouponId=" + isCouponId +
			", couponMoney=" + couponMoney +
			", amountPrice=" + amountPrice +
			", paymentType=" + paymentType +
			", paymentStatus=" + paymentStatus +
			", payNo=" + payNo +
			", createTime=" + createTime +
			", orderStatus=" + orderStatus +
			", roomIds=" + roomIds +
			", remark=" + remark +
			"}";
	}
}
