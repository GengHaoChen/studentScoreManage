package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.AccountLog;

public interface IAccountLogDao extends BaseDao<AccountLog> {

	//����---ɾ��
	void deleteAccountLog(String startDate, String endDate); 
	
	// ��ԭʼ���ݾ�ɸѡ���� ��½����
	boolean queryAccountLog(String startDate, String endDate);

	// ������--->��½
	List<Map<String, Object>> queryGrantLogin(String startDate, String endDate);

	// ������--->ע��
	List<Map<String, Object>> queryGrantReg(String startDate, String endDate);

	// ��������--->��½
	List<Map<String, Object>> queryGrantConsumeLogin(String startDate, String endDate);

	// ��������--->ע��
	List<Map<String, Object>> queryGrantConsumeReg(String startDate,
			String endDate);
}
