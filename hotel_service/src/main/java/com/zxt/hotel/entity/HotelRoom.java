package com.zxt.hotel.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 酒店房间信息
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@TableName("hotel_room")
public class HotelRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("room_id")
	private Long roomId;
	@TableField("is_room_type_id")
	private Long isRoomTypeId;
	@TableField("is_hotel_id")
	private Long isHotelId;
	@TableField("room_no")
	private String roomNo;
	@TableField("hotel_name")
	private String hotelName;
	private String storey;
	@TableField("qr_code")
	private String qrCode;
	@TableField("qr_code_content")
	private String qrCodeContent;
	@TableField("dev_no")
	private String devNo;
	@TableField("stay_status")
	private String stayStatus;
	@TableField("create_time")
	private Date createTime;


	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getIsRoomTypeId() {
		return isRoomTypeId;
	}

	public void setIsRoomTypeId(Long isRoomTypeId) {
		this.isRoomTypeId = isRoomTypeId;
	}

	public Long getIsHotelId() {
		return isHotelId;
	}

	public void setIsHotelId(Long isHotelId) {
		this.isHotelId = isHotelId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getStorey() {
		return storey;
	}

	public void setStorey(String storey) {
		this.storey = storey;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getQrCodeContent() {
		return qrCodeContent;
	}

	public void setQrCodeContent(String qrCodeContent) {
		this.qrCodeContent = qrCodeContent;
	}

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
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

	@Override
	public String toString() {
		return "HotelRoom{" +
			"roomId=" + roomId +
			", isRoomTypeId=" + isRoomTypeId +
			", isHotelId=" + isHotelId +
			", roomNo=" + roomNo +
			", hotelName=" + hotelName +
			", storey=" + storey +
			", qrCode=" + qrCode +
			", qrCodeContent=" + qrCodeContent +
			", devNo=" + devNo +
			", stayStatus=" + stayStatus +
			", createTime=" + createTime +
			"}";
	}
}
