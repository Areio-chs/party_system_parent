package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * change实体类
 * @author Administrator
 *
 */
@Table(name="tb_change")
public class Change implements Serializable{

	@Id
	private String id;//id


	

	private String fromName;//转出支部

	private java.util.Date fromTime;//转出时间

	private String toName;//转入支部

	private java.util.Date toTime;//转入时间

	private String auditor;//审核人

	private String status;//审核状态

	private String userId;//外键用户id

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public java.util.Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(java.util.Date fromTime) {
		this.fromTime = fromTime;
	}

	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}

	public java.util.Date getToTime() {
		return toTime;
	}
	public void setToTime(java.util.Date toTime) {
		this.toTime = toTime;
	}

	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	
}
