package com.cndym.service;

import java.util.List;
import java.util.Map;

import com.cndym.entity.user.UserGroup;
import com.cndym.util.PageView;

public interface IUserGroupService {

	/**
	 * @Name: queryAllGroupList
	 * @Description: ��ȡ���е��û��鼯��
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��22��11:56:12
	 * @Parameters: ��
	 * @Return: ���е��û��鼯��
	 */
	List<UserGroup> queryAllGroupList();

	/**
	 * @Name: queryAllGroupList
	 * @Description: ��ȡ���е��û��鼯��
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��22��11:56:12
	 * @Parameters: ��
	 * @Return: ���е��û��鼯��
	 */
	PageView queryPageUserGroupList( int currentPage);

	/**
	 * @Name: saveOrUpdateUserGroup
	 * @Description: ��������޸��û�����Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��25��17:17:12
	 * @Parameters: Ҫ������û���Ϣ
	 * @Return: ��
	 */
	void saveOrUpdateUserGroup(UserGroup userGroup);

	/**
	 * @Name: queryByQuery
	 * @Description: ����paramMap������ѯ�û�����ϸ��Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��25��18:13:11
	 * @Parameters: paramMap ��ѯ����
	 * @Return: �������û���Ϣ
	 */
	UserGroup queryByQuery(Map<String, Object> paramMap);
	
	/**
	 * @Name: deleteGroupById
	 * @Description: ����idɾ���û�����Ϣ��ɾ��֮ǰҪ�жϸ�id�����Ƿ����û�
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��23��15:50:46
	 * @Parameters: ��
	 * @Return: �Ƿ�ɾ���ɹ�
	 */
	String deleteGroupById(Long id);
}
