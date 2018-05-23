package com.zxt.hotel.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 酒店描述相关
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@TableName("hotel_content")
public class HotelContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("content_id")
	private Long contentId;
	private String title;
	private String content;
	@TableField("sort_id")
	private Integer sortId;
	private String type;
	@TableField("create_time")
	private Date createTime;


	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "HotelContent{" +
			"contentId=" + contentId +
			", title=" + title +
			", content=" + content +
			", sortId=" + sortId +
			", type=" + type +
			", createTime=" + createTime +
			"}";
	}
}
