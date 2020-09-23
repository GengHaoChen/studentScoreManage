package com.cndym.service;

import java.util.List;
import java.util.Map;

import com.cndym.entity.channel.Channel;

public interface IChannelService {

	/**
	 * @Description: ���ݹ�˾code��ȡ��������
	 * @Author:��»Ԫ  2016.04.13
	 * @Return:  ��ȡ���з�����������������
	 */
	List<Channel> queryChannelListByCompanyCode(String  code);
	
	/**
	 * @Name: getChannelBySid
	 * @Description: ����sid�õ�����
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��4��21��13:59:31
	 * @Parameters:��
	 * @Return: ��
	 */
	Channel getChannelBySid(String  sid);

	List<Channel> getChannelList(String name, String  code, String companyCode);
	
	
	
	 Map<String, String> sidMap();

}
