package com.party.pojo.system;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * development实体类
 * @author Administrator
 *
 */
@Data
@Table(name="tb_development")
public class Development implements Serializable{

	@Id
	private String id;//主键id

	private java.util.Date applyTime;//申请时间

	private String isActivist;//是否积极分子

	private String activistFileId;//积极分子申请文件id

	private String linkActivist;//入党积极分子通过书链接

	private java.util.Date activistTime;//成为积极分子时间

	private String isDevelop;//是否发展对象

	private String linkDevelop;//发展对象通过书链接

	private java.util.Date developTime;//成为发展对象时间

	private String developFileId;//发展对象申请文件id

	private String isPre;//是否预备党员

	private String linkPre;//预备党员通过书链接

	private String preFileId;//预备党员文件id

	private java.util.Date preMemberTime;//成为预备党员时间

	private String isMember;//是否党员

	private String linkMember;//党员申请书链接

	private java.util.Date memberTime;//成为党员时间

	private String isGraduate;//是否毕业

	private java.util.Date graduateTime;//毕业时间

	private String userId;//user表外键

	private String culture1Sid;//培养人id1

	private String culture1Id;//培养人id1

	private String culture1Name;//培养人id1

	private String culture2Sid;//培养人id2

	private String culture2Id;//培养人id2

	private String culture2Name;

	private Integer status;//当前状态


}
