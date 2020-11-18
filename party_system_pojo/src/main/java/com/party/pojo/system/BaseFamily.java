package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * baseFamily实体类
 * @author Administrator
 *
 */
@Table(name="tb_base_family")
public class BaseFamily implements Serializable{

	@Id
	private String id;//id


	

	private String appellation;//父亲/母亲/妹妹

	private String name;//name

	private Integer age;//age

	private String politicalStatus;//群众/共青团员/党员

	private String title;//副部长

	private String userId;//user_id

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getAppellation() {
		return appellation;
	}
	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	
}
