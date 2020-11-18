package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * development实体类
 * @author Administrator
 *
 */
@Table(name="tb_development")
public class Development implements Serializable{

	@Id
	private String id;//主键id


	

	private java.util.Date applyTime;//申请时间

	private String isActivist;//是否积极分子

	private String activistFileId;//积极分子申请文件id

	private String linkActivist;//入党积极分子通过书链接

	private java.util.Date activistTime;//成为积极分子时间

	private String isDevelop;//是否发展对象

	private String linkDevelop;//发展对象通过书链接

	private java.util.Date developTime;//成为发展对象时间

	private String developFileId;//发展对象申请文件id

	private String isPre;//是否预备党员

	private String linkPre;//预备党员通过书链接

	private String preFileId;//预备党员文件id

	private java.util.Date preMemberTime;//成为预备党员时间

	private String isMember;//是否党员

	private String linkMember;//党员申请书链接

	private java.util.Date memberTime;//成为党员时间

	private String isGraduate;//是否毕业

	private java.util.Date graduateTime;//毕业时间

	private String userId;//user表外键

	private String culture1Id;//培养人id1

	private String culture2Id;//培养人id2

	private Integer status;//当前状态

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public java.util.Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(java.util.Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getIsActivist() {
		return isActivist;
	}
	public void setIsActivist(String isActivist) {
		this.isActivist = isActivist;
	}

	public String getActivistFileId() {
		return activistFileId;
	}
	public void setActivistFileId(String activistFileId) {
		this.activistFileId = activistFileId;
	}

	public String getLinkActivist() {
		return linkActivist;
	}
	public void setLinkActivist(String linkActivist) {
		this.linkActivist = linkActivist;
	}

	public java.util.Date getActivistTime() {
		return activistTime;
	}
	public void setActivistTime(java.util.Date activistTime) {
		this.activistTime = activistTime;
	}

	public String getIsDevelop() {
		return isDevelop;
	}
	public void setIsDevelop(String isDevelop) {
		this.isDevelop = isDevelop;
	}

	public String getLinkDevelop() {
		return linkDevelop;
	}
	public void setLinkDevelop(String linkDevelop) {
		this.linkDevelop = linkDevelop;
	}

	public java.util.Date getDevelopTime() {
		return developTime;
	}
	public void setDevelopTime(java.util.Date developTime) {
		this.developTime = developTime;
	}

	public String getDevelopFileId() {
		return developFileId;
	}
	public void setDevelopFileId(String developFileId) {
		this.developFileId = developFileId;
	}

	public String getIsPre() {
		return isPre;
	}
	public void setIsPre(String isPre) {
		this.isPre = isPre;
	}

	public String getLinkPre() {
		return linkPre;
	}
	public void setLinkPre(String linkPre) {
		this.linkPre = linkPre;
	}

	public String getPreFileId() {
		return preFileId;
	}
	public void setPreFileId(String preFileId) {
		this.preFileId = preFileId;
	}

	public java.util.Date getPreMemberTime() {
		return preMemberTime;
	}
	public void setPreMemberTime(java.util.Date preMemberTime) {
		this.preMemberTime = preMemberTime;
	}

	public String getIsMember() {
		return isMember;
	}
	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}

	public String getLinkMember() {
		return linkMember;
	}
	public void setLinkMember(String linkMember) {
		this.linkMember = linkMember;
	}

	public java.util.Date getMemberTime() {
		return memberTime;
	}
	public void setMemberTime(java.util.Date memberTime) {
		this.memberTime = memberTime;
	}

	public String getIsGraduate() {
		return isGraduate;
	}
	public void setIsGraduate(String isGraduate) {
		this.isGraduate = isGraduate;
	}

	public java.util.Date getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(java.util.Date graduateTime) {
		this.graduateTime = graduateTime;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCulture1Id() {
		return culture1Id;
	}
	public void setCulture1Id(String culture1Id) {
		this.culture1Id = culture1Id;
	}

	public String getCulture2Id() {
		return culture2Id;
	}
	public void setCulture2Id(String culture2Id) {
		this.culture2Id = culture2Id;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	
}
