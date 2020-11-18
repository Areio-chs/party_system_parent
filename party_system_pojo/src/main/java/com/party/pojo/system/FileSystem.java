package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * fileSystem实体类
 * @author Administrator
 *
 */
@Table(name="tb_file_system")
public class FileSystem implements Serializable{

	@Id
	private String sysId;//主键id


	

	private String fileName;//文件名

	private Integer fileTypeId;//文件类型id 0表示图片1表示文件

	private java.util.Date uploadTime;//上传时间

	private String filePath;//上传路径

	private String fileNote;//备注

	private String userId;//user表外键

	private Integer photoId;//1是活动文件/2是任务文件

	private String typeName;//文件类型

	
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileTypeId() {
		return fileTypeId;
	}
	public void setFileTypeId(Integer fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	public java.util.Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(java.util.Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileNote() {
		return fileNote;
	}
	public void setFileNote(String fileNote) {
		this.fileNote = fileNote;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	
}
