package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 酒店与字典关系表
 * </p>
 *
 * @author wenjun
 * @since 2018-06-12
 */
@TableName("hotel_info_dict_rel")
public class HotelInfoDictRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("rel_id")
	private Long relId;
	@TableField("hotel_id")
	private Long hotelId;
	@TableField("dict_id")
	private Long dictId;


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

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	@Override
	public String toString() {
		return "HotelInfoDictRel{" +
			"relId=" + relId +
			", hotelId=" + hotelId +
			", dictId=" + dictId +
			"}";
	}
}
