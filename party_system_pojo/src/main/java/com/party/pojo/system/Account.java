package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * account实体类
 * @author Administrator
 *
 */
@Table(name="tb_account")
public class Account implements Serializable{

	@Id
	private String id;//id


	

	private String username;//用户

	private String password;//密码

	private Integer status;//1可用0不可用

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	
}
