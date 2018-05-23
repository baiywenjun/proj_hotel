package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 酒店字典
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@TableName("hotel_dict")
public class HotelDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("dict_id")
	private Long dictId;
	private String code;
	private String value;
	private String name;
	@TableField("sort_id")
	private Integer sortId;
	@TableField("parent_id")
	private Long parentId;
	private Integer level;
	private String type;
	private String remark;


	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "HotelDict{" +
			"dictId=" + dictId +
			", code=" + code +
			", value=" + value +
			", name=" + name +
			", sortId=" + sortId +
			", parentId=" + parentId +
			", level=" + level +
			", type=" + type +
			", remark=" + remark +
			"}";
	}
}
