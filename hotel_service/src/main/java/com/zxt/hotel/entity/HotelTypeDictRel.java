package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 房型与字典关系表
 * </p>
 *
 * @author wenjun
 * @since 2018-05-26
 */
@TableName("hotel_type_dict_rel")
public class HotelTypeDictRel implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value = "rel_id", type= IdType.AUTO)
	private Long relId;
	@TableField("room_type_id")
	private Long roomTypeId;
	@TableField("dict_id")
	private Long dictId;


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

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	@Override
	public String toString() {
		return "HotelTypeDictRel{" +
			"relId=" + relId +
			", roomTypeId=" + roomTypeId +
			", dictId=" + dictId +
			"}";
	}
}
