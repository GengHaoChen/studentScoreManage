package com.cndym.dao.pick;


import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.util.PageView;

public interface IUserRegistrationInformationListDao extends BaseDao<UserRegistrationInformationList> {
	public PageView queryPageView(UserRegistrationInformationList userRegistr, int currentPage,String startTime,String endTime);
	/**
	 * ���ݲ�����ѯ,���ط�����Ϣ�ļ���
	 * @param userMap
	 * @return
	 */
	public List<UserRegistrationInformationList> queryByQuery(
			Map<String, Object> userMap);
	
	/**
	 * ���ݲ�����ѯ,���ط�����Ϣ
	 * @param userMap
	 * @return
	 */
	public UserRegistrationInformationList queryByQueryURIL(Map<String, Object> userMap);

	/**
	 * ɾ����������������
	 * @param startDate
	 * @param endDate
	 */
	public void deleteUserRegList(String startDate, String endDate);
	
	
	/**
	 * ��©����
	 * @param start
	 * @param end
	 * @param paramMap
	 * @param interval
	 */
	public void updateUserRegList(String start,String end,Map<Integer,String> paramMap,Integer interval);
	
}
