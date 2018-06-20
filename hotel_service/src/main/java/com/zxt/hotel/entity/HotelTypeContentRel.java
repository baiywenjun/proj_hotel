package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 房型与描述关系
 * </p>
 *
 * @author wenjun
 * @since 2018-06-12
 */
@TableName("hotel_type_content_rel")
public class HotelTypeContentRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("rel_id")
	private Long relId;
	@TableField("room_type_id")
	private Long roomTypeId;
	@TableField("content_id")
	private Long contentId;


	public Long getRelId() {
		return relId;
	}

	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	@Override
	public String toString() {
		return "HotelTypeContentRel{" +
			"relId=" + relId +
			", roomTypeId=" + roomTypeId +
			", contentId=" + contentId +
			"}";
	}
}
