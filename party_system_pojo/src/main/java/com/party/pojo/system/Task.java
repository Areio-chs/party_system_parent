package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * task实体类
 * @author Administrator
 *
 */
@Table(name="tb_task")
public class Task implements Serializable{

	@Id
	private String id;//id


	

	private String taskName;//任务名称

	private String taskDetail;//任务详情

	private String taskId;//任务文件

	private Integer status;//1审核完成任务0失败

	private String publisher;//发布人

	private String reviewer;//审核人

	private String integral;//任务积分

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}


	
}
