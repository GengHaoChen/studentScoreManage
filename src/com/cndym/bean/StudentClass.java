package com.cndym.bean;

import java.io.Serializable;

/**
 * �༶��
 * @author LiNa
 * @time	2020/09/02 10��46
 *
 */
public class StudentClass implements Serializable {
	private static final long serialVersionUID = -701580149443848629L;
	private int	classId	;//	�༶Id��������������
	private String classNo	;//	�༶���2016+01��,Ψһ�������޸�
	private String className	;//	�༶����,�޸ĸ�һ,�߶�
	private int classStudentNumber	;//	�༶ѧ������
	private int classStatus	;//	0(δ��ҵ) 1(�ѱ�ҵ)
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
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
	public int getClassStudentNumber() {
		return classStudentNumber;
	}
	public void setClassStudentNumber(int classStudentNumber) {
		this.classStudentNumber = classStudentNumber;
	}
	public int getClassStatus() {
		return classStatus;
	}
	public void setClassStatus(int classStatus) {
		this.classStatus = classStatus;
	}
}
