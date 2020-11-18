package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * party实体类
 * @author Administrator
 *
 */
@Table(name="tb_party")
public class Party implements Serializable{

	@Id
	private String id;//主键id


	

	private String partyName;//党支部名字

	private String partyPhone;//党支部电话

	private String partyAddress;//党支部地址

	private java.util.Date partyTime;//创建时间

	private String partyNote;//备注

	private String generalId;//党总支id

	private String accountId;//账户id

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyPhone() {
		return partyPhone;
	}
	public void setPartyPhone(String partyPhone) {
		this.partyPhone = partyPhone;
	}

	public String getPartyAddress() {
		return partyAddress;
	}
	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}

	public java.util.Date getPartyTime() {
		return partyTime;
	}
	public void setPartyTime(java.util.Date partyTime) {
		this.partyTime = partyTime;
	}

	public String getPartyNote() {
		return partyNote;
	}
	public void setPartyNote(String partyNote) {
		this.partyNote = partyNote;
	}

	public String getGeneralId() {
		return generalId;
	}
	public void setGeneralId(String generalId) {
		this.generalId = generalId;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	
}
