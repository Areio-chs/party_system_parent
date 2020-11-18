package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * general实体类
 * @author Administrator
 *
 */
@Table(name="tb_general")
public class General implements Serializable{

	@Id
	private String id;//主键id


	

	private String generalName;//党总支名字

	private String generalPhone;//党总支电话

	private String generalAddress;//党总支地址

	private java.util.Date generalTime;//创建时间

	private String generalNote;//备注

	private String accountId;//账户id

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getGeneralName() {
		return generalName;
	}
	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}

	public String getGeneralPhone() {
		return generalPhone;
	}
	public void setGeneralPhone(String generalPhone) {
		this.generalPhone = generalPhone;
	}

	public String getGeneralAddress() {
		return generalAddress;
	}
	public void setGeneralAddress(String generalAddress) {
		this.generalAddress = generalAddress;
	}

	public java.util.Date getGeneralTime() {
		return generalTime;
	}
	public void setGeneralTime(java.util.Date generalTime) {
		this.generalTime = generalTime;
	}

	public String getGeneralNote() {
		return generalNote;
	}
	public void setGeneralNote(String generalNote) {
		this.generalNote = generalNote;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	
}
