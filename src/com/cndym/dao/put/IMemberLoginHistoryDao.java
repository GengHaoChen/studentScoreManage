package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.MemberLoginHistory;

public interface IMemberLoginHistoryDao extends BaseDao<MemberLoginHistory> {
	/**
	 * @Name: deleteMemberLoginHistory
	 * @Description: ÿ��ͬ��֮ǰ,ɾ��DAU
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: ��
	 */
	 void deleteMemberLoginHistory(String startDate, String endDate);

	/**
	 * @Name: queryMemberLoginHistory
	 * @Description: �����û�code ���������з���Ĳ�ѯǰһ��00:00 ��23:59:59������<br/>
	 *               eg: 2016-04-05 00:00:00 �� 2016-04-05 23:59:59<br/>
	 *               ��ѯ���ʱ����DAUȥ�ص�¼�û����ļ�¼��Ȼ����뵽�½��ı���
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��4��6��15:06:58
	 * @Parameters: ��
	 * @Return: true  ����ɹ���false ����ʧ��
	 */
	boolean queryMemberLoginHistoryList(String startDate,String endDate);

	/**
	 * @Name: queryDistinctGroupBySId
	 * @Description: ����sid����õ�ȥ�غ��½�û��� DAU
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: key�� sid   value��ȥ�ص�½�û���
	 */
	List<Map<String,Object>>  queryDistinctGroupBySId(String startDate, String endDate);
	
	/**
	 * @Name: queryDistinctCount
	 * @Description: DAU ȥ�ص�½�û������ܣ���������ֱ��ͳ��ȥ����
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: ȥ�ص�½�û���
	 */
	Integer  queryDistinctCount(String startDate, String endDate);
	
	/**
	 * @Name: queryDistinctUserCode
	 * @Description: �õ�ȥ�ص�¼userCode����
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��12��23��18:54:36
	 * @Parameters: startTime:��ʼʱ��   endTime������ʱ��
	 * @Return: �õ�ȥ�ص�¼userCode����
	 */
	public List<Map<String,Object>> queryDistinctUserCode(String startTime,String endTime);
	
	/**
	 * @param endDate 
	 * @param startDate 
	 * @param userCodeList  
	 * @Name: queryMemberByTime ,List<Map<String,Object>> userCodeList
	 * @Description: ��¼�û��ļ�����ĳ��ʱ���ڵ�¼�ı��� 
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��4��5��13:40:51
	 * @Parameters: ��
	 * @Return:  ����ĳ��ʱ���ڣ���¼�û���¼��Ϣ�ļ���
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
	public List<Map<String, Object>> queryMemberLoginBettingByTime(
			String startTime, String endTime, String start, String end);
	
	
	public List<Map<String, Object>> queryDistinct(String startDate, String endDate);
}
