package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 酒店信息
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
@TableName("hotel_info")
public class HotelInfo implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="hotel_id", type= IdType.AUTO)
	private Long hotelId;
	private String name;
	@TableField("hotel_no")
	private String hotelNo;
	@TableField("img_url")
	private String imgUrl;
	private Integer rank;
	private String province;
	private String city;
	private String county;
	private String address;
	private String lng;
	private String lat;
	private String description;
	private String phone;
	@TableField("contact_name")
	private String contactName;
	@TableField("create_time")
	private Date createTime;
	@TableField("create_id")
	private Long createId;
	@TableField("hotel_status")
	private String hotelStatus;
	private String remark;


	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHotelNo() {
		return hotelNo;
	}

	public void setHotelNo(String hotelNo) {
		this.hotelNo = hotelNo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public String getHotelStatus() {
		return hotelStatus;
	}

	public void setHotelStatus(String hotelStatus) {
		this.hotelStatus = hotelStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "HotelInfo{" +
			"hotelId=" + hotelId +
			", name=" + name +
			", hotelNo=" + hotelNo +
			", imgUrl=" + imgUrl +
			", rank=" + rank +
			", province=" + province +
			", city=" + city +
			", county=" + county +
			", address=" + address +
			", lng=" + lng +
			", lat=" + lat +
			", description=" + description +
			", phone=" + phone +
			", contactName=" + contactName +
			", createTime=" + createTime +
			", createId=" + createId +
			", hotelStatus=" + hotelStatus +
			", remark=" + remark +
			"}";
	}
}
