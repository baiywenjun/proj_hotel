package com.zxt.hotel.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 服务类型
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@TableName("serve_type")
public class ServeType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("serve_type_id")
	private Long serveTypeId;
	@TableField("type_code")
	private String typeCode;
	@TableField("serve_name")
	private String serveName;
	@TableField("serve_content")
	private String serveContent;
	@TableField("serve_timeout")
	private Date serveTimeout;
	@TableField("sort_id")
	private Integer sortId;
	private String remark;


	public Long getServeTypeId() {
		return serveTypeId;
	}

	public void setServeTypeId(Long serveTypeId) {
		this.serveTypeId = serveTypeId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getServeName() {
		return serveName;
	}

	public void setServeName(String serveName) {
		this.serveName = serveName;
	}

	public String getServeContent() {
		return serveContent;
	}

	public void setServeContent(String serveContent) {
		this.serveContent = serveContent;
	}

	public Date getServeTimeout() {
		return serveTimeout;
	}

	public void setServeTimeout(Date serveTimeout) {
		this.serveTimeout = serveTimeout;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ServeType{" +
			"serveTypeId=" + serveTypeId +
			", typeCode=" + typeCode +
			", serveName=" + serveName +
			", serveContent=" + serveContent +
			", serveTimeout=" + serveTimeout +
			", sortId=" + sortId +
			", remark=" + remark +
			"}";
	}
}
