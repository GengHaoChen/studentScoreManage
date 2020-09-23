package com.cndym.dao.put;


import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.Member;

public  interface IMemberDao extends BaseDao<Member> {
	
	//ɾ��ע���û���
	void deleteMember(String startDate, String endDate) ;
	
	/**
	 * @param endDate 
	 * @param startDate 
	 * @Name: queryMemberList
	 * @Description: �����û�code ��union_Id�������з����
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��4��5��13:40:51
	 * @Parameters: ��
	 * @Return:  �������ʱ���ڵ�����ȥ�ص�¼�û�����
	 */
	boolean queryMemberList(String startDate, String endDate);

	
	List<Map<String,Object>>  queryDistinct(String startDate, String endDate);
	
	/**
	 * @param endDate 
	 * @param startDate 
	 * @param userCodeList  
	 * @Name: queryMemberByTime ,List<Map<String,Object>> userCodeList
	 * @Description: ��¼�û��ļ�����ĳ��ʱ����ע��ı��� 
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��4��5��13:40:51
	 * @Parameters: ��
	 * @Return:  ����ĳ��ʱ���ڣ���¼�û�ע����Ϣ�ļ���
	 */
	public List<Map<String,Object>> queryMemberLoginByTime(String startTime, String endTime,String start, String end);

	/**
	 * 
	 * @param registrationTime  ע��ʱ��
	 * @param endTime			ע�����
	 * @param start				��ʼʱ��
	 * @param end				����ʱ��
	 * @return
	 */
	public List<Map<String,Object>> queryMemberBettingByTime(String registrationTime, String endTime,String start, String end);
	
}
