package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.ClientActive;

public interface IClientActiveDao extends BaseDao<ClientActive> {

	//����������ϸ
	boolean queryClientActive(String startDate, String endDate);

	//ȥ�ؼ�����
	List<Map<String, Object>> queryDistinct(String startDate, String endDate);
	//ɾ����
	boolean deleteClientActive(String start, String end);
}
