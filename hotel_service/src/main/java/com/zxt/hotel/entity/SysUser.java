package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author wenjun
 * @since 2018-05-17
 */
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="user_id", type= IdType.AUTO)
	private Long userId;
	private String username;
	private String password;
	private String name;
	private String image;
	private Integer sex;
	private Date birthday;
	private String phone;
	private String status;
	@TableField("create_time")
	private Date createTime;
	private String remark;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SysUser{" +
			"userId=" + userId +
			", username=" + username +
			", password=" + password +
			", name=" + name +
			", image=" + image +
			", sex=" + sex +
			", birthday=" + birthday +
			", phone=" + phone +
			", status=" + status +
			", createTime=" + createTime +
			", remark=" + remark +
			"}";
	}
}
