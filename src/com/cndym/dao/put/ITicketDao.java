package com.cndym.dao.put;


import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.Ticket;

public interface ITicketDao extends BaseDao<Ticket> {

	/**
	 * ���������ֳɹ�����Ʊ�� ���ݷ���ID��ѯƱ������ܵ� Ͷע�ɹ���Ǯ
	 * @param returnTime   ��ִʱ��
	 */
	Double queryAmountByProgramsOrderId(Map<String,Object> paramMap);

	/**
	 * �����򲿷ֳɹ�����Ʊ�����ݷ���id����ѯ��������û��ܳɹ��Ľ�Ҳ���ǳ�ȥʧ��Ʊ�ļ۸�
	 * @param paramMap
	 * @return
	 */
	Double querySuccessTicketByProgramsOrderId(Map<String, Object> paramMap);

	
	
	
}