package com.cndym.bean;

import java.io.Serializable;

/**
*
* �û�
* @author lina
* @time	2020/09/02 10��46
*/
public class User  implements Serializable {
	private static final long serialVersionUID = 2237782137665137163L;
	private int userId	;//������������
	private String userNo	;//	�û����
	private String userName	;//	�û���
	private String userPassword	;//	����
	private String userTelephone	;//�绰
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}
}
