package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 酒店sku
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
@TableName("hotel_room_type")
public class HotelRoomType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("room_type_id")
	private Long roomTypeId;
	@TableField("is_hotel_id")
	private Long isHotelId;
	@TableField("type_code")
	private String typeCode;
	@TableField("type_name")
	private String typeName;
	@TableField("type_spec")
	private String typeSpec;
	@TableField("type_price")
	private Integer typePrice;
	@TableField("type_img")
	private String typeImg;
	@TableField("type_content")
	private String typeContent;
	@TableField("type_status")
	private String typeStatus;


	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Long getIsHotelId() {
		return isHotelId;
	}

	public void setIsHotelId(Long isHotelId) {
		this.isHotelId = isHotelId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	public String getTypeImg() {
		return typeImg;
	}

	public void setTypeImg(String typeImg) {
		this.typeImg = typeImg;
	}

	public String getTypeContent() {
		return typeContent;
	}

	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}

	public String getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(String typeStatus) {
		this.typeStatus = typeStatus;
	}

	@Override
	public String toString() {
		return "HotelRoomType{" +
			"roomTypeId=" + roomTypeId +
			", isHotelId=" + isHotelId +
			", typeCode=" + typeCode +
			", typeName=" + typeName +
			", typeSpec=" + typeSpec +
			", typePrice=" + typePrice +
			", typeImg=" + typeImg +
			", typeContent=" + typeContent +
			", typeStatus=" + typeStatus +
			"}";
	}
}
