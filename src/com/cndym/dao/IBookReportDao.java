package com.cndym.dao;

import java.util.List;

import com.cndym.entity.report.BookReport;
import com.cndym.util.PageView;


public interface IBookReportDao  extends BaseDao<BookReport>{

	/**
	 * @Author:chengluyuan
	 * @Create Date: 2016��4��13��
	 * @Return: ����������ѯ
	 */
	boolean updateBookReport(Long id, int delStatus);
	
	//��ҳ��ѯ
	PageView queryPageBookReportList(BookReport bookReport, int currentPage);

	////���ݶ�������+�û�code��ѯ�Ƿ���
	int queryReportByCodeAndName(String name, String code);
	
	//��ѯ���ĵ�����
	List<BookReport> queryReport(Integer isAdmin);
}
