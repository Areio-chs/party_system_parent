package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * study实体类
 * @author Administrator
 *
 */
@Table(name="tb_study")
public class Study implements Serializable{

	@Id
	private String id;//id

	private String studyName;//学习内容

	private java.util.Date studyTime;//学习时间

	private String integral;//学习获得积分

	private String studyNote;//备注

	private String studyMore;//空白字段

	private String studyPlace;

	private String userId;//外键用户id

	private Integer isFinish;

	@Transient
	private List<String> organization;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public List<String> getOrganization() {
		return organization;
	}

	public void setOrganization(List<String> organization) {
		this.organization = organization;
	}

	public String getStudyPlace() {
		return studyPlace;
	}

	public void setStudyPlace(String studyPlace) {
		this.studyPlace = studyPlace;
	}

	@Override
	public String toString() {
		return "Study{" +
				"id='" + id + '\'' +
				", studyName='" + studyName + '\'' +
				", studyTime=" + studyTime +
				", integral='" + integral + '\'' +
				", studyNote='" + studyNote + '\'' +
				", studyMore='" + studyMore + '\'' +
				", studyPlace='" + studyPlace + '\'' +
				", userId='" + userId + '\'' +
				", isFinish=" + isFinish +
				", organization=" + organization +
				'}';
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public java.util.Date getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(java.util.Date studyTime) {
		this.studyTime = studyTime;
	}

	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getStudyNote() {
		return studyNote;
	}
	public void setStudyNote(String studyNote) {
		this.studyNote = studyNote;
	}

	public String getStudyMore() {
		return studyMore;
	}
	public void setStudyMore(String studyMore) {
		this.studyMore = studyMore;
	}

}
