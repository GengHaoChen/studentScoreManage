package com.cndym.service;

import java.util.List;

import com.cndym.entity.report.BookReport;
import com.cndym.util.PageView;



public interface IBookReportService {

	/**
	 * @Description: ���ݹ�˾code��ȡ��������
	 * @Author:��»Ԫ  2016.04.13
	 * @Return:  ��ȡ���з�����������������
	 */
	boolean updateBookReport(Long id); 
	
	PageView queryPageBookReportList(BookReport bookReport, int currentPage);

	//��Ӷ�����Ϣ
	void saveReport(BookReport bookReport);

	//���ݶ�������+�û�code��ѯ�Ƿ���
	int queryReportByCodeAndName(String name, String code);

	//�����û�
	List<BookReport> queryReport();
	//����Ա�û�
	List<BookReport> queryReportAdmin();
}
