package com.cndym.dao;

import java.util.Map;

import com.cndym.entity.user.UserGroup;
import com.cndym.util.PageView;

public interface IUserGroupDao extends BaseDao<UserGroup> {

	/**
	 * @Name: queryByQuery
	 * @Description: ����������ȡ�û�����ϸ��Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��23��09:38:19
	 * @Parameters: ��
	 * @Return: �������û�����Ϣ
	 */
	UserGroup queryByQuery(Map<String, Object> groupMap);

	/**
	 * @Name: queryByQuery
	 * @Description: ����������ȡ�û�����ϸ��Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��23��09:38:19
	 * @Parameters: ��
	 * @Return: �������û�����Ϣ
	 */
	PageView queryPageUserGroupList( int currentPage);
}
