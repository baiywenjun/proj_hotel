package com.zxt.hotel.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 房间的订单历史信息
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@TableName("hotel_order_room")
public class HotelOrderRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("order_room_id")
	private Long orderRoomId;
	@TableField("is_room_id")
	private Long isRoomId;
	@TableField("is_order_id")
	private Long isOrderId;
	@TableField("type_name")
	private String typeName;
	@TableField("type_spec")
	private String typeSpec;
	@TableField("type_price")
	private Integer typePrice;
	private String storey;
	@TableField("room_no")
	private String roomNo;
	private String username;
	@TableField("uer_real_name")
	private String uerRealName;
	@TableField("user_phone")
	private String userPhone;
	@TableField("begin_date")
	private Date beginDate;
	@TableField("end_date")
	private Date endDate;
	@TableField("total_data")
	private Integer totalData;
	@TableField("amount_price")
	private Integer amountPrice;
	@TableField("payment_type")
	private String paymentType;
	@TableField("pay_no")
	private String payNo;
	@TableField("create_time")
	private Date createTime;
	@TableField("stay_status")
	private String stayStatus;
	@TableField("consume_type")
	private String consumeType;
	private String remark;


	public Long getOrderRoomId() {
		return orderRoomId;
	}

	public void setOrderRoomId(Long orderRoomId) {
		this.orderRoomId = orderRoomId;
	}

	public Long getIsRoomId() {
		return isRoomId;
	}

	public void setIsRoomId(Long isRoomId) {
		this.isRoomId = isRoomId;
	}

	public Long getIsOrderId() {
		return isOrderId;
	}

	public void setIsOrderId(Long isOrderId) {
		this.isOrderId = isOrderId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeSpec() {
		return typeSpec;
	}

	public void setTypeSpec(String typeSpec) {
		this.typeSpec = typeSpec;
	}

	public Integer getTypePrice() {
		return typePrice;
	}

	public void setTypePrice(Integer typePrice) {
		this.typePrice = typePrice;
	}

	public String getStorey() {
		return storey;
	}

	public void setStorey(String storey) {
		this.storey = storey;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUerRealName() {
		return uerRealName;
	}

	public void setUerRealName(String uerRealName) {
		this.uerRealName = uerRealName;
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

	public Integer getTotalData() {
		return totalData;
	}

	public void setTotalData(Integer totalData) {
		this.totalData = totalData;
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

	public String getStayStatus() {
		return stayStatus;
	}

	public void setStayStatus(String stayStatus) {
		this.stayStatus = stayStatus;
	}

	public String getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "HotelOrderRoom{" +
			"orderRoomId=" + orderRoomId +
			", isRoomId=" + isRoomId +
			", isOrderId=" + isOrderId +
			", typeName=" + typeName +
			", typeSpec=" + typeSpec +
			", typePrice=" + typePrice +
			", storey=" + storey +
			", roomNo=" + roomNo +
			", username=" + username +
			", uerRealName=" + uerRealName +
			", userPhone=" + userPhone +
			", beginDate=" + beginDate +
			", endDate=" + endDate +
			", totalData=" + totalData +
			", amountPrice=" + amountPrice +
			", paymentType=" + paymentType +
			", payNo=" + payNo +
			", createTime=" + createTime +
			", stayStatus=" + stayStatus +
			", consumeType=" + consumeType +
			", remark=" + remark +
			"}";
	}
}
