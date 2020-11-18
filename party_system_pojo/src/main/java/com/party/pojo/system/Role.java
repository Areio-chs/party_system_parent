package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * role实体类
 * @author Administrator
 *
 */
@Table(name="tb_role")
public class Role implements Serializable{

	@Id
	private Integer id;//id


	

	private String name;//角色名

	private String remark;//备注

	private String status;//是否使用

	private java.util.Date createTime;//创建时间

	private java.util.Date modifiedTime;//修改时间

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(java.util.Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}


	
}
