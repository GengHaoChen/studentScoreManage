package com.cndym.service;

import java.util.List;

import com.cndym.entity.company.ResourceAllocation;

public interface IResourceAllocationService {

	/**
	 * @Name: queryAllResourceList
	 * @param type   1-������˾��2-������ʽ��3-������Ʒ��4-���㷽ʽ
	 * @Description: ��ȡ���е���Դ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��22��11:56:12
	 * @Parameters: ��
	 * @Return:  ��ȡ���е���Դ����
	 */
	List<ResourceAllocation> queryAllResourceList(Integer type);

}
