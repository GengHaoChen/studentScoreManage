package com.cndym.service;

import java.util.Map;

import com.cndym.entity.user.User;
import com.cndym.util.PageView;

public interface IUserService {

	/**
	 * @Name: getFunctionXml
	 * @Description: ��ȡ�û���ӵ�е�Ȩ��
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��22��11:56:12
	 * @Parameters: ��
	 * @Return: ��
	 */
	void getFunctionXml();

	/**
	 * @Name: queryByQuery
	 * @Description: ����������ȡ�û���ϸ��Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��22��11:56:12
	 * @Parameters: ��
	 * @Return: �������û���Ϣ
	 */
	User queryByQuery(Map<String,Object> userMap);

	/**
	 * @Name: queryPageUserList
	 * @Description: ����������ҳ��ѯ�û���Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��22��11:56:12
	 * @Parameters: ��
	 * @Return: �������û���Ϣ
	 */
	PageView queryPageUserList(User user, int currentPage);

	/**
	 * @Name: deleteById
	 * @Description: ����idɾ���û���Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��23��15:50:46
	 * @Parameters: ��
	 * @Return: �Ƿ�ɾ���ɹ�
	 */
	boolean deleteById(Long id);

	/**
	 * @Name: saveOrUpdateUser
	 * @Description: ��������޸��û���Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��23��15:50:46
	 * @Parameters: ��
	 * @Return: �Ƿ񱣴�����޸ĳɹ�
	 */
	void saveOrUpdateUser(User u);
}
