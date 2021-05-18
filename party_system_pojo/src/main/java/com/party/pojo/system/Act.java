package com.party.pojo.system;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * act实体类
 * @author Administrator
 *
 */
@Data
@Table(name="tb_act")
public class Act implements Serializable{

	@Id
	private String id;//id


	private String name;//活动名称

	private java.util.Date time;//活动时间

	private String note;//活动备注

	private String address;//活动地点

	private String publisher;//发布人

	private Integer joinNum;//参与人数

	private String typeName;//活动类型的名称

	private String taskId;//活动文件

	private String status;//活动是否结束

	@Transient
	private List<String> organization;

}
