package com.cndym.dao;

import java.util.List;
import java.util.Map;

import com.cndym.entity.channel.Channel;

public interface IChannelDao  extends BaseDao<Channel>{

	/**
	 * @Author:chengluyuan
	 * @Create Date: 2016��4��13��
	 * @Return: ����������ѯchannel
	 */
	List<Channel> queryByQuery(Channel channel);


	List<Channel> searchChannelList(Channel channel);
	
	/**
	 * @Name: getChannelBySid
	 * @Description: ����sid�õ�����
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��4��21��13:59:31
	 * @Parameters:��
	 * @Return: ��
	 */
	Channel getChannelBySid(String sid);


	/**
	 * @Name: sidMap
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��4��21��13:59:31
	 * @Parameters:��
	 * @Return: ��
	 */
	Map<String, String> sidMap();
}
