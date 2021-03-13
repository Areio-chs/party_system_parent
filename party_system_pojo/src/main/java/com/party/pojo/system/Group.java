package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
/**
 * group实体类
 * @author Administrator
 *
 */
@Table(name="tb_group")
public class Group implements Serializable{

	@Id
	private String id;//主键id


	

	private String groupName;//党小组名字

	private String groupPhone;//党小组电话

	private String groupAddress;//党小组地址

	private java.util.Date groupTime;//创建时间

	private String groupNote;//备注

	private String partyId;//党支部id

	//处理不在数据库的字段
	@Transient
	private String partyName;//党支部名

	private String accountId;//账户id

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupPhone() {
		return groupPhone;
	}
	public void setGroupPhone(String groupPhone) {
		this.groupPhone = groupPhone;
	}

	public String getGroupAddress() {
		return groupAddress;
	}
	public void setGroupAddress(String groupAddress) {
		this.groupAddress = groupAddress;
	}

	public java.util.Date getGroupTime() {
		return groupTime;
	}
	public void setGroupTime(java.util.Date groupTime) {
		this.groupTime = groupTime;
	}

	public String getGroupNote() {
		return groupNote;
	}
	public void setGroupNote(String groupNote) {
		this.groupNote = groupNote;
	}

	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	
}
