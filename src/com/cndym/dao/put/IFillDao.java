package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.Fill;

/**
 * 
 * @author Administrator
 * 
 */
public interface IFillDao extends BaseDao<Fill> {
	/**
	 * @Name: queryFillList
	 * @Description: ��ԭʼ������ɸѡ��������������
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: ��
	 */
	boolean queryFillList(String startDate, String endDate);
	/**
	 * @Name: deleteFill
	 * @Description: ɾ�����������
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: ��
	 */
	void deleteFill(String startDate, String endDate);
	/**
	 * @Name: queryDistinctLoginGroupBySid
	 * @Description: ȥ�س�ֵ�û�����������----->��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ�� ȥ�س�ֵ�û���
	 */
	List<Map<String, Object>> queryDistinctLoginGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryDistinctLoginCount
	 * @Description: ȥ�س�ֵ�û�������������----->��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: ȥ�س�ֵ�û���
	 */
	Integer queryDistinctLoginCount(String startDate, String endDate);
	/**
	 * @Name: queryDistinctRegGroupBySid
	 * @Description: ȥ�س�ֵ�û�����������----->ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ��ȥ�س�ֵ��
	 */
	List<Map<String, Object>> queryDistinctRegGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryDistinctRegCount
	 * @Description: ȥ�س�ֵ�û�������������----->ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: ȥ�س�ֵ�û���
	 */
	Integer queryDistinctRegCount(String startDate, String endDate);
	/**
	 * @Name: queryAllMoneyLogin
	 * @Description: �ܳ�ֵ���----->��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ�ĳ�ֵ���
	 */
	List<Map<String, Object>> queryAllMoneyLogin(String startDate, String endDate);
	/**
	 * @Name: queryAllMoneyReg
	 * @Description: �ܳ�ֵ���----->ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  sid ��Ӧ�ĳ�ֵ���
	 */
	List<Map<String, Object>> queryAllMoneyReg(String startDate, String endDate);
	/**
	 * @Name: queryNewDistinctLoginGroupBySid
	 * @Description: ��ֵ���û����� ������--------��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ��  ��ֵ���û���
	 */
	List<Map<String, Object>> queryNewDistinctLoginGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryNewDistinctLoginCount
	 * @Description: ��ֵ���û����� ��������--------��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  ��ֵ���û���
	 */
	Integer queryNewDistinctLoginCount(String startDate, String endDate) ;
	/**
	 * @Name: queryNewDistinctRegGroupBySid
	 * @Description: ��ֵ���û����� ������--------ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return: sid ��Ӧ��  ��ֵ���û���
	 */
	List<Map<String, Object>> queryNewDistinctRegGroupBySid(String startDate,String endDate);
	/**
	 * @Name: queryNewDistinctRegCount
	 * @Description: ��ֵ���û����� ��������--------ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  ��ֵ���û���
	 */
	Integer queryNewDistinctRegCount(String startDate, String endDate);
	/**
	 * @Name: queryNewMoneryLogin
	 * @Description: ���û���ֵ���--------��½
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  sid ��Ӧ�����û���ֵ���
	 */
	List<Map<String, Object>> queryNewMoneryLogin(String startDate, String endDate);
	/**
	 * @Name: queryNewMoneryReg
	 * @Description:  ���û���ֵ��� ---------ע��
	 * @Author: LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��5��11��14:39:33
	 * @Parameters: startDate:��ʼʱ��   endDate������ʱ��
	 * @Return:  sid ��Ӧ�����û���ֵ���
	 */
	List<Map<String,Object>> queryNewMoneryReg(String startDate, String endDate);

}
