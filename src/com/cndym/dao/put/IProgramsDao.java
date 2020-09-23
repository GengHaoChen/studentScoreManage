package com.cndym.dao.put;

import java.util.List;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.ProgramsPick;
import com.cndym.entity.data.pick.ProgramsPickRegistered;
import com.cndym.entity.data.put.Programs;

public interface IProgramsDao extends BaseDao<Programs> {
	//����ɾ��
	void deletePrograms(String startDate, String endDate);
	// ��ѯ���гɹ��Ͳ��ֳɹ������ִ��� ������׷�ŵ����� ----��½����
	public List<ProgramsPick> queryAllDataLogin(String startDate, String endDate);

	// ��ѯ���гɹ��Ͳ��ֳɹ������ִ��� ������׷�ŵ����� ----ע������
	public List<ProgramsPickRegistered> queryAllDataRegistered(String startDate, String endDate);

	// ��ԭʼ���ݾ�ɸѡ�������ŵ�Ͷע��½������
	boolean queryAllDataByStatusLogin(String startDate, String endDate);

	// ��ԭʼ���ݾ�ɸѡ�������ŵ�Ͷעע��������
	boolean queryAllDataByStatusRegistered(String startDate, String endDate);
}
