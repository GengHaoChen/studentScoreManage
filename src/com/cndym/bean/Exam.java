package com.cndym.bean;

import java.io.Serializable;

/**
*
* ������
* @author lina
* @time	2020/09/02 10��46
*/
public class Exam implements Serializable {
	private static final long serialVersionUID = 5571688029901861349L;
	private int examId	;//������������
	private String examNo	;//���Ա�ţ�Ψһ
	private String examName	;//	��������
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public String getExamNo() {
		return examNo;
	}
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
}
