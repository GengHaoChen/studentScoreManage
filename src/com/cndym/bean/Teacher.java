package com.cndym.bean;

import java.io.Serializable;

import com.cndym.entity.user.UserGroup;

/**
*
* ��ʦ��
* @author lina
* @time	2020/09/02 10��46
*/
public class Teacher implements Serializable {
	private static final long serialVersionUID = 8690739672847914175L;
	private int 	teaId	;//	��ʦId��������������
	private String	teaNo	;//		��ʦְ����,ΨһT+��ְ���+01
	private String	teaPassword	;//	��ʦ����(��ʼֵ����ƴ��+@+123)
	private int 	teaSex	;//		��ʦ�Ա�
	private String	teaName	;//		��ʦ����
	private String	teaTelephone;//		��ʦ�绰
	private String	teaSubject	;//		���̿γ�
	private String	teaAdmissionTime	;//		��ְʱ��xxxx-xx-xx
	private String	teaLeaveTime	;//		   ��ְʱ��xxxx-xx-xx
	private int 	teaState	;//	��ְ״̬: 0(��ְ) 1(��ְ)
	// ������
	private UserGroup userGroup;
	//��id
	private int groupId;
	public int getTeaId() {
		return teaId;
	}
	public void setTeaId(int teaId) {
		this.teaId = teaId;
	}
	public String getTeaNo() {
		return teaNo;
	}
	public void setTeaNo(String teaNo) {
		this.teaNo = teaNo;
	}
	public String getTeaPassword() {
		return teaPassword;
	}
	public void setTeaPassword(String teaPassword) {
		this.teaPassword = teaPassword;
	}
	public int getTeaSex() {
		return teaSex;
	}
	public void setTeaSex(int teaSex) {
		this.teaSex = teaSex;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getTeaTelephone() {
		return teaTelephone;
	}
	public void setTeaTelephone(String teaTelephone) {
		this.teaTelephone = teaTelephone;
	}
	public String getTeaSubject() {
		return teaSubject;
	}
	public void setTeaSubject(String teaSubject) {
		this.teaSubject = teaSubject;
	}
	public String getTeaAdmissionTime() {
		return teaAdmissionTime;
	}
	public void setTeaAdmissionTime(String teaAdmissionTime) {
		this.teaAdmissionTime = teaAdmissionTime;
	}
	public String getTeaLeaveTime() {
		return teaLeaveTime;
	}
	public void setTeaLeaveTime(String teaLeaveTime) {
		this.teaLeaveTime = teaLeaveTime;
	}
	public int getTeaState() {
		return teaState;
	}
	public void setTeaState(int teaState) {
		this.teaState = teaState;
	}
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
