package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 酒店与描述关系
 * </p>
 *
 * @author wenjun
 * @since 2018-06-12
 */
@TableName("hotel_info_content_rel")
public class HotelInfoContentRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("rel_id")
	private Long relId;
	@TableField("hotel_id")
	private Long hotelId;
	@TableField("content_id")
	private Long contentId;


	public Long getRelId() {
		return relId;
	}

	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	@Override
	public String toString() {
		return "HotelInfoContentRel{" +
			"relId=" + relId +
			", hotelId=" + hotelId +
			", contentId=" + contentId +
			"}";
	}
}
