package com.cndym.entity.channel;

import java.io.Serializable;
import java.util.Date;

public class Channel implements Serializable {
	private static final long serialVersionUID = 7708962612733688322L;
	private Long id;
	private String companyCode;// ��˾��� rescourceAllocation type=1
	private String sid;// ������� ����һ���������ɵ�
	private String name;// ��������
	private String key; // ����ֵ �����ļ���ȡ����
	private String managerName; // ������
	private String modeCode; // ������ʽ��� rescourceAllocation type=2
	private String productCode;// ��Ʒ��� rescourceAllocation type=3
	private String billingCode;// ���㷽ʽ��� rescourceAllocation type=4
	private Integer status;// ״̬ 0 ���أ�1������
	private String note; // ��ע	
	private Integer delStatus;// ɾ��״̬ 0 �����ڣ�1���Ѿ�ɾ����
    private Date createTime;//����ʱ��
    private Date editTime;//�޸�ʱ��
	

	public Channel() {
	}

	public Channel(Long id, String companyCode, String sid, String name, String key, String managerName,
			String productCode, String modeCode, String billingCode, Integer status, String note,Integer delStatus,Date createTime,Date editTime) {
		this.id = id;
		this.companyCode = companyCode;
		this.sid = sid;
		this.name = name;
		this.key = key;
		this.managerName = managerName;
		this.productCode = productCode;
		this.modeCode = modeCode;
		this.billingCode = billingCode;
		this.status = status;
		this.note = note;
		this.delStatus=delStatus;
		this.createTime=createTime;
		this.editTime=editTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getModeCode() {
		return modeCode;
	}

	public void setModeCode(String modeCode) {
		this.modeCode = modeCode;
	}

	public String getBillingCode() {
		return billingCode;
	}

	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
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

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

}
