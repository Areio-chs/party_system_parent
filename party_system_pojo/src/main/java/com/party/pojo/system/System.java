package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * system实体类
 * @author Administrator
 *
 */
@Table(name="tb_system")
public class System implements Serializable{

	@Id
	private String id;//id


	

	private java.util.Date openTime;//系统开放时间

	private java.util.Date closeTime;//close_time

	private Integer identityId;//身份id 1是申请人2是积极分子

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public java.util.Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(java.util.Date openTime) {
		this.openTime = openTime;
	}

	public java.util.Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(java.util.Date closeTime) {
		this.closeTime = closeTime;
	}

	public Integer getIdentityId() {
		return identityId;
	}
	public void setIdentityId(Integer identityId) {
		this.identityId = identityId;
	}


	
}
