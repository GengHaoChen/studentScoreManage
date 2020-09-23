package com.cndym.entity.report;

import java.util.Date;

public class BookReport {

	private Long id;
	private String userCode;
	private String name;
	private String numType;
	private Integer delStatus=0;//0:���� 1��ɾ��
	private Date createTime=new Date();

	// ����ʱ��
	private String reportTime;
	// �ռ���ַ
	private String emailAddress;
	//����id
	private String sid;
	//Ȩ������ ȫ������:fa   ��ֵ����:fb  Ͷע���ݣ�fc
	private String permissionType;
	//0:�����û�  1:����Ա�û�
	private Integer isAdmin;
	
	
	
	
	
	

	public BookReport() {

	}

	public BookReport(Long id, String userCode, String name, String numType,
			Integer delStatus, Date createTime, String reportTime,
			String emailAddress,String sid,String permissionType,Integer isAdmin) {
		this.id = id;
		this.userCode = userCode;
		this.name = name;
		this.numType = numType;
		this.delStatus = delStatus;
		this.createTime = createTime;
		this.reportTime = reportTime;
		this.emailAddress = emailAddress;
		this.sid=sid;
		this.permissionType=permissionType;
		this.isAdmin=isAdmin;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumType() {
		return numType;
	}

	public void setNumType(String numType) {
		this.numType = numType;
	}

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
}
