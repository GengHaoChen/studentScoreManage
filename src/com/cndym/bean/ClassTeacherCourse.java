package com.cndym.bean;

import java.io.Serializable;

/**
 * �༶��ʦ�γ̱�
 * @author LiNa
 * @time	2020/09/02 10��46
 */
public class ClassTeacherCourse implements Serializable {
	private static final long serialVersionUID = 2188923231590301208L;
	private int  ctcId	;//������������
	private String classNo	;//	�༶���Ψһ  ���
	private String 	className	;//�༶����
	private String 	teaNo	;//	��ʦְ����,Ψһ T+��ְ���+01.....  ���
	private String 	teaName	;//	��ʦ����
	private String 	courseNo	;//	�γ̱��Ψһ ���
	private String 	courseName	;//	�γ�����
	public int getCtcId() {
		return ctcId;
	}
	public void setCtcId(int ctcId) {
		this.ctcId = ctcId;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTeaNo() {
		return teaNo;
	}
	public void setTeaNo(String teaNo) {
		this.teaNo = teaNo;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
