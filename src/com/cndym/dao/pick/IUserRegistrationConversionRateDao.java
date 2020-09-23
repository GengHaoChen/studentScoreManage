package com.cndym.dao.pick;


import java.util.List;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.UserRegistrationConversionRate;

public interface IUserRegistrationConversionRateDao extends BaseDao<UserRegistrationConversionRate> {
	/**
	 * ��loginStatus=1ʱ����ȡ�������û���¼ת������ʱ��Ϣ
	 * ��bettingStatus=1ʱ����ȡ�������û�Ͷעת������ʱ��Ϣ
	 * @param loginStatus  1:δ����  2������
	 * @param bettingStatus 1:δ����  2������
	 * @return
	 */
	public List<UserRegistrationConversionRate> queryUserRegistrationConversionRateList(Integer loginStatus,Integer bettingStatus,Integer isLoginFlag);
	
	/**
	 * ɾ���м�����������
	 * @param start
	 * @param endDate
	 */
	public void deleteUserRegRate(String start, String endDate);
}
