package com.cndym.dao.pick;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.UserBettors;

public interface IUserBettorsDao extends BaseDao<UserBettors> {
	/**
	 * @Name: insertUserBettors
	 * @Description: ��userBettors������ӵ�Ͷע����
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: ��
	 */
	void insertUserBettors(List<UserBettors> insertPurchaseUB);
	/**
	 * @Name: queryDistinctLoginGroupBySId
	 * @Description: ȥ��Ͷע�û�����������----->��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ�� ȥ��Ͷע�û���
	 */
	List<Map<String, Object>> queryDistinctLoginGroupBySId(String startDate, String endDate);
	/**
	 * @Name: queryDistinctLoginCount
	 * @Description: ȥ��Ͷע�û�������������----->��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: ȥ��Ͷע�û���
	 */
	Integer queryDistinctLoginCount(String startDate, String endDate) ;
	/**
	 * @Name: queryAllTouZhuMoneyLogin
	 * @Description: Ͷע�ܽ�������----->��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ�� Ͷע�ܽ��
	 */
	List<Map<String, Object>> queryAllTouZhuMoneyLogin(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewCountLoginGroupBySid
	 * @Description: ȥ��Ͷע���û�����������----->��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ�� ȥ��Ͷע���û���
	 */
	List<Map<String, Object>> queryTouZhuNewCountLoginGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewCountLoginCount
	 * @Description: ȥ��Ͷע���û�������������----->��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  ȥ��Ͷע���û���
	 */
	Integer  queryTouZhuNewCountLoginCount(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewMoneryCountLogin
	 * @Description: ���û�Ͷע��������----->��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  sid ��Ӧ�� ���û�Ͷע���
	 */
	List<Map<String, Object>> queryTouZhuNewMoneryCountLogin(String startDate, String endDate);
	/**
	 * @Name: queryDistinctRegGroupBySid
	 * @Description: ȥ��Ͷע�û�����������----->ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ�� ȥ��Ͷע�û���
	 */
	List<Map<String, Object>> queryDistinctRegGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryDistinctRegCount
	 * @Description: ȥ��Ͷע�û�������������----->ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  ȥ��Ͷע�û���
	 */
	Integer  queryDistinctRegCount(String startDate, String endDate) ;
	/**
	 * @Name: queryAllTouZhuMoneyReg
	 * @Description: Ͷע�ܽ�������----->ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  sid ��Ӧ�� Ͷע�ܽ��
	 */
	List<Map<String, Object>> queryAllTouZhuMoneyReg(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewCountRegGroupBySid
	 * @Description: ȥ��Ͷע���û�����������----->ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ�� ȥ��Ͷע���û���
	 */
	List<Map<String, Object>> queryTouZhuNewCountRegGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewCountRegCount
	 * @Description: ȥ��Ͷע���û�������������----->ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: ȥ��Ͷע���û���
	 */
	Integer  queryTouZhuNewCountRegCount(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewMoneryCountReg
	 * @Description: ���û�Ͷע��������----->ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  sid ��Ӧ�� ���û�Ͷע���
	 */
	List<Map<String, Object>> queryTouZhuNewMoneryCountReg(String startDate, String endDate);
	/**
	 * @Name: deleteUserBettors
	 * @Description: ɾ����¼
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  ��
	 */
	void deleteUserBettors(String startDate, String endDate);

}
