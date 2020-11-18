package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * power实体类
 * @author Administrator
 *
 */
@Table(name="tb_power")
public class Power implements Serializable{

	@Id
	private Integer id;//id


	

	private String pid;//pid

	private String name;//name

	private String url;//url

	private Integer status;//status

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	
}
