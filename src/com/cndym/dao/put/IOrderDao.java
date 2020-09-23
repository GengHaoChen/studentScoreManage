package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.UserBettors;
import com.cndym.entity.data.put.Order;

public interface IOrderDao extends BaseDao<Order> {

	//���ݷ���id��ѯ���� ���򲿷ֳɹ�
	List<UserBettors> queryHMByProgramsOrderId(Map<String, Object> paramMap);
	
	
	
	
	
	//����ȫ���ɹ� ���ݷ���id��ѯ����
    List<UserBettors> queryHMAllSuccessByProgramsOrderId(Map<String, Object> paramMap);  

}
