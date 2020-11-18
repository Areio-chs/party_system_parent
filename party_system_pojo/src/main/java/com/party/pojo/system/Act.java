package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * act实体类
 * @author Administrator
 *
 */
@Table(name="tb_act")
public class Act implements Serializable{

	@Id
	private String id;//id


	

	private String name;//活动名称

	private java.util.Date time;//活动时间

	private Integer typeId;//活动类型id

	private String note;//活动备注

	private String address;//活动地点

	private String publisher;//发布人

	private Integer joinNum;//参与人数

	private String typeName;//活动类型的名称

	private String taskId;//活动文件

	
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

	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	
}
