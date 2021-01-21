package com.party.pojo.system;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * account实体类
 * @author Administrator
 *
 */
@Data
@Table(name="tb_account")
public class Account implements Serializable{

	@Id
	private String id;//id


	

	private String username;//用户

	private String password;//密码

	private Integer status;//1可用0不可用


	
}
