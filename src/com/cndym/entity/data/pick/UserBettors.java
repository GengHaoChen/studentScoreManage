package com.cndym.entity.data.pick;

import java.util.Date;

/**
 * ��ֵ�û���
 * 
 * @author Administrator
 * 
 */
public class UserBettors {
	private Long id;
	private String userCode;
	// ��½����
	private String partnersCode;
	// �����Ļ�ִʱ��
	private Date returnTime;
	// ��������
	private Integer buyType;
	// 1. �ɹ��� 2 ���ֳɹ�
	private Integer orderStatus;
	// �������
	private Double orderAmount;
	// �Ϲ����
	private Double renGouAmount;
	// �Ϲ��ɹ����
	private Double renGouSuccessAmount;

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

	public String getPartnersCode() {
		return partnersCode;
	}

	public void setPartnersCode(String partnersCode) {
		this.partnersCode = partnersCode;
	}


	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Double getRenGouAmount() {
		return renGouAmount;
	}

	public void setRenGouAmount(Double renGouAmount) {
		this.renGouAmount = renGouAmount;
	}

	public Double getRenGouSuccessAmount() {
		return renGouSuccessAmount;
	}

	public void setRenGouSuccessAmount(Double renGouSuccessAmount) {
		this.renGouSuccessAmount = renGouSuccessAmount;
	}

}
