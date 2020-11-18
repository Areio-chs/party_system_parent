package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * baseUser实体类
 * @author Administrator
 *
 */
@Table(name="tb_base_user")
public class BaseUser implements Serializable{

	@Id
	private String id;//id


	

	private String sid;//学号

	private String name;//姓名

	private String sex;//性别

	private java.util.Date birth;//生日（年月日）

	private String grade;//年级

	private String classNum;//班级

	private String room;//宿舍号

	private String iamge;//个人照片

	private String idCard;//身份证

	private String phone;//电话号码

	private String email;//邮箱

	private String address;//家庭住址

	private String identity;//个人身份（学生）

	private String nativePlace;//籍贯

	private String residence;//户籍所在地

	private String nation;//民族

	private String duty;//职务（副部长）

	private String title;//职称

	private String aducation;//学历

	private String graInstitution;//毕业院校

	private java.util.Date workTime;//参加工作时间

	private java.util.Date joinTime;//入团时间

	private java.util.Date petitionConfirm;//落款时间

	private java.util.Date firstTalkTime;//首次谈话时间

	private String rewardPunishInfo;//奖惩信息

	private String accountId;//账户id

	private Integer typeId;//0积极分子1发展对象2预备党员

	private String integral;//累计积分

	private String qq;//QQ

	private String wechat;//微信

	private String leagueBranchId;//团支部id

	private String generalId;//党总支id

	private String partyId;//党支部id

	private String groupId;//党小组id

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public java.util.Date getBirth() {
		return birth;
	}
	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}

	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}

	public String getIamge() {
		return iamge;
	}
	public void setIamge(String iamge) {
		this.iamge = iamge;
	}

	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAducation() {
		return aducation;
	}
	public void setAducation(String aducation) {
		this.aducation = aducation;
	}

	public String getGraInstitution() {
		return graInstitution;
	}
	public void setGraInstitution(String graInstitution) {
		this.graInstitution = graInstitution;
	}

	public java.util.Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(java.util.Date workTime) {
		this.workTime = workTime;
	}

	public java.util.Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(java.util.Date joinTime) {
		this.joinTime = joinTime;
	}

	public java.util.Date getPetitionConfirm() {
		return petitionConfirm;
	}
	public void setPetitionConfirm(java.util.Date petitionConfirm) {
		this.petitionConfirm = petitionConfirm;
	}

	public java.util.Date getFirstTalkTime() {
		return firstTalkTime;
	}
	public void setFirstTalkTime(java.util.Date firstTalkTime) {
		this.firstTalkTime = firstTalkTime;
	}

	public String getRewardPunishInfo() {
		return rewardPunishInfo;
	}
	public void setRewardPunishInfo(String rewardPunishInfo) {
		this.rewardPunishInfo = rewardPunishInfo;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getLeagueBranchId() {
		return leagueBranchId;
	}
	public void setLeagueBranchId(String leagueBranchId) {
		this.leagueBranchId = leagueBranchId;
	}

	public String getGeneralId() {
		return generalId;
	}
	public void setGeneralId(String generalId) {
		this.generalId = generalId;
	}

	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	
}
