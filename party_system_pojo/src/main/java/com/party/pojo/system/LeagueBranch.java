package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * leagueBranch实体类
 * @author Administrator
 *
 */
@Table(name="tb_league_branch")
public class LeagueBranch implements Serializable{

	@Id
	private String id;//id


	

	private String name;//name

	private String phone;//phone

	private String address;//address

	private String note;//note

	private java.util.Date createTime;//create_time

	private String groupId;//group_id

	private String accountId;//account_id

	private String partyId;//party_id

	private String memberId;//党员id,在base_user中

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	
}
