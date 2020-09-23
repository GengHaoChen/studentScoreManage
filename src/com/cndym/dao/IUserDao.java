package com.cndym.dao;

import java.util.List;
import java.util.Map;

import com.cndym.entity.user.User;
import com.cndym.util.PageView;

public interface IUserDao extends BaseDao<User> {

	/**
	 * @Name: queryByQuery
	 * @Description: ����������ȡ�û���ϸ��Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��22��11:56:12
	 * @Parameters: ��
	 * @Return: �������û���Ϣ
	 */
	List<User> queryByQuery(Map<String, Object> userMap);

	/**
	 * @Name: queryPageUserList
	 * @Description: ��ҳ��ʾ�û���Ϣ����
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��22��11:56:12
	 * @Parameters: ��
	 * @Return: ��ҳ��ʾ�û���Ϣ����
	 */
	PageView queryPageUserList(User user, int currentPage);

}
