package com.cndym.dao.pick;

import java.util.List;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.util.PageView;

public interface IDataInfoDao extends BaseDao<DataInfo> {
	// ɾ����������������
	void deleteDataInfo(String start, String endDate);

	/**
	 * ��ҳ��ѯ��ֵ����------ע������
	 * @param administratorType 
	 * 
	 * @return
	 */
	PageView queryPageViewChongzhi(DataInfo dataInfo, int currentPage, String administratorType);
	/**
	 * ��ҳ��ѯȫ������------��½����
	 * 
	 * @return
	 */
	PageView queryPageViewAll(DataInfo dataInfo, int currentPage);
	/**
	 * ��ҳ��ѯȫ��Ͷע����------ע������
	 * @param administratorType 
	 * 
	 * @return
	 */
	PageView queryPageViewTouzhu(DataInfo dataInfo, int currentPage, String administratorType);
	/**
	 * ��ѯ�������ݲ���ҳ��ֵ����------ע������
	 * @param administratorType 
	 * 
	 * @return
	 */
	List<DataInfo> queryListChongzhi(String sid, String startTime,String endTime,
			Integer sidStatus, String administratorType);
	/**
	 * ��ѯ�������ݲ���ҳͶע����------ע������
	 * @param administratorType 
	 * 
	 * @return
	 */
	List<DataInfo> queryListTouzhu(String sid, String startTime,String endTime,
			Integer sidStatus, String administratorType);
	/**
	 * ��ѯ�������ݲ���ҳȫ������------��½����
	 * @param administratorType 
	 * 
	 * @return
	 */
	List<DataInfo> queryListAll(String sid, String startTime,String endTime,
			Integer sidStatus, String administratorType);
	
	/**
	 * ��½������ȫ������ͳ��
	 * 
	 * @return
	 */
	List<DataInfo> queryCountListAll(String reportTime,String endTime, Integer sidStatus);
	/**
	 * ע����������ֵ����ͳ��
	 * @param endTime 
	 * 
	 * @return
	 */
	List<DataInfo> queryCountListChongzhi(String reportTime, String endTime, Integer sidStatus);
	/**
	 * ע��������Ͷע����ͳ��
	 * @param endTime 
	 * 
	 * @return
	 */
	List<DataInfo> queryCountListTouzhu(String reportTime, String endTime, Integer sidStatus);
}
