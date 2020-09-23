package com.cndym.service;

public interface ISynchronizationDataService {
	/** ����ͬ��֮ǰ����ɾ����*/
	void deleteDataInfo(String startDate, String endDate);

	/** ͬ����������DAU��ע���û���������*/
	void getDataByOriginal(String start, String end);

	/** ͬ����ֵ������Ͷע������*/
	void queryDateLogin(String startDate, String endDate);

	/** ��½���������*/
	void countDateLogin(String startDate, String endDate);

	/** ע���������� */
	void countDateReg(String startDate, String endDate);
	
	/**ͳ�Ƶ���������������*/
	void saveDataCount(String startDate,String endDate);
	
	/**���û�AndͶע�û�   ��¼/Ͷע�м�� */
	public void saveUserMemeber(String start, String end);
	
	/**���û�AndͶע�û�     ��¼/Ͷעת������Ϣ����*/
	public void userMemberRetentionRate(String start, String end) ;
	
	public void updateMemberNullRate(String start, String end);	
}
