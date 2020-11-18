package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * baseResume实体类
 * @author Administrator
 *
 */
@Table(name="tb_base_resume")
public class BaseResume implements Serializable{

	@Id
	private String id;//id


	

	private java.util.Date fromTime;//自何年何月

	private java.util.Date toTime;//至何年何月

	private String where;//在何地、何单位工作（学习），任何职

	private String witness;//证明人

	private String userId;//user_id

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public java.util.Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(java.util.Date fromTime) {
		this.fromTime = fromTime;
	}

	public java.util.Date getToTime() {
		return toTime;
	}
	public void setToTime(java.util.Date toTime) {
		this.toTime = toTime;
	}

	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}

	public String getWitness() {
		return witness;
	}
	public void setWitness(String witness) {
		this.witness = witness;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	
}
