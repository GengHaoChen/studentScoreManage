package com.cndym.bean;

import java.io.Serializable;

import com.cndym.entity.user.UserGroup;

/**
 *
 * ѧ����
 * @author lina
 * @time	2020/09/02 10��46
 */
public class Student  implements Serializable {
	private static final long serialVersionUID = -7646170472179329865L;
	private int stuId; //ID
	private String stuNo;//ѧ��,Ψһ,�༶+ѧ��(S201901001,S201902001
	private int stuSex;//ѧ���Ա�  0�� 1Ů
	private String stuPassword;//ѧ������(��ʼֵ����ƴ��+@+123)
	private String stuCardNo;//���֤��
	private String stuAddress;//ѧ����ַ
	private String stuBirthday;//��������xxxx-xx-xx
	private String stuAdmissionTime;//��ѧʱ��xxxx-xx-xx
	private String stuGraduationTime;//��ҵʱ��xxxx-xx-xx
	private int stuState;//ѧ��״̬: 0(δ��ҵ) 1(�ѱ�ҵ)2(��ѧ)
	// ������
	private UserGroup userGroup;
	//��id
	private int groupId;
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public int getStuSex() {
		return stuSex;
	}
	public void setStuSex(int stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuPassword() {
		return stuPassword;
	}
	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword;
	}
	public String getStuCardNo() {
		return stuCardNo;
	}
	public void setStuCardNo(String stuCardNo) {
		this.stuCardNo = stuCardNo;
	}
	public String getStuAddress() {
		return stuAddress;
	}
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
	public String getStuBirthday() {
		return stuBirthday;
	}
	public void setStuBirthday(String stuBirthday) {
		this.stuBirthday = stuBirthday;
	}
	public String getStuAdmissionTime() {
		return stuAdmissionTime;
	}
	public void setStuAdmissionTime(String stuAdmissionTime) {
		this.stuAdmissionTime = stuAdmissionTime;
	}
	public String getStuGraduationTime() {
		return stuGraduationTime;
	}
	public void setStuGraduationTime(String stuGraduationTime) {
		this.stuGraduationTime = stuGraduationTime;
	}
	public int getStuState() {
		return stuState;
	}
	public void setStuState(int stuState) {
		this.stuState = stuState;
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
