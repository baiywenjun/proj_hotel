package com.zxt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 管理员
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
@TableName("sys_admin")
public class SysAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="admin_id", type= IdType.AUTO)
	private Long adminId;
	@TableField("admin_name")
	private String adminName;
	private String password;
	private String name;
	private String image;
	private Integer sex;
	private Date birthday;
	private String phone;
	private String type;
	private String status;
	@TableField("create_time")
	private Date createTime;
	private String remark;


	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SysAdmin{" +
			"adminId=" + adminId +
			", adminName=" + adminName +
			", password=" + password +
			", name=" + name +
			", image=" + image +
			", sex=" + sex +
			", birthday=" + birthday +
			", phone=" + phone +
			", type=" + type +
			", status=" + status +
			", createTime=" + createTime +
			", remark=" + remark +
			"}";
	}
}
