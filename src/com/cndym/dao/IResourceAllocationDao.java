package com.cndym.dao;

import java.util.Map;

import com.cndym.entity.company.ResourceAllocation;

public interface IResourceAllocationDao  extends BaseDao<ResourceAllocation>{

	/**
	 * @Name: queryByQuery
	 * @Description: ����������ȡ��˾��ϸ��Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��23��09:38:19
	 * @Parameters: ��
	 * @Return: �����Ĺ�˾����
	 */
	ResourceAllocation queryByQuery(Map<String, Object> groupMap);
	
}
