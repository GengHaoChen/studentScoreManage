package com.cndym.entity.data.pick;

import java.util.Date;

/**
 * ע���û����¼/Ͷע ת�����м��----ע������
 * 
 * @author Administrator
 * 
 */
public class UserRegistrationConversionRate {
	private Long id;
	/**��¼�����ʵ�״̬(1:δ����  2������)*/
	private Integer loginStatus;
	/**Ͷע�����ʵ�״̬(1:δ����  2������)*/
	private Integer bettingStatus;
	/**ע���û�/��¼�û�(1:ע�� 2����¼)*/
	private Integer isLoginFlag;
	/**ע��ʱ��*/
	private Date registrationTime;
	/**30��֮���ʱ��*/
	private Date endTime;
	/**����ʱ��*/
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Integer getBettingStatus() {
		return bettingStatus;
	}
	public void setBettingStatus(Integer bettingStatus) {
		this.bettingStatus = bettingStatus;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getIsLoginFlag() {
		return isLoginFlag;
	}
	public void setIsLoginFlag(Integer isLoginFlag) {
		this.isLoginFlag = isLoginFlag;
	}
}
