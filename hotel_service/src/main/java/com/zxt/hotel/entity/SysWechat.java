package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 微信登录绑定
 * </p>
 *
 * @author wenjun
 * @since 2018-05-17
 */
@TableName("sys_wechat")
public class SysWechat implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="bind_id", type= IdType.AUTO)
	private Long bindId;
	private String phone;
	@TableField("open_id")
	private String openId;
	@TableField("create_time")
	private Date createTime;
	@TableField("modify_time")
	private Date modifyTime;


	public Long getBindId() {
		return bindId;
	}

	public void setBindId(Long bindId) {
		this.bindId = bindId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "SysWechat{" +
			"bindId=" + bindId +
			", phone=" + phone +
			", openId=" + openId +
			", createTime=" + createTime +
			", modifyTime=" + modifyTime +
			"}";
	}
}
