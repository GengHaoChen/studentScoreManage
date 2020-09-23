package com.cndym.service;

import java.util.List;

import com.cndym.entity.data.pick.DataInfo;
import com.cndym.util.PageView;

public interface IDataInfoService {
	/**
	 * ��ҳ��ѯ����
	 * 
	 * @param dataInfo
	 * @param currentPage
	 * @param permissionType
	 * @return
	 */
	PageView queryPageList(DataInfo dataInfo, int currentPage,
			String permissionType, String administratorType);

	// �����ʼ����ͣ�����sid,����ʱ�� ,����ȡ�Ӷ���ʱ�䵽��ǰʱ�����������
	List<DataInfo> queryBySidAllTime(String sid, String reportTime,
			String permissionType, String administratorType);


	List<DataInfo> getExportDataList(DataInfo dataInfo, String permissionType,
			String strAdmin);
}
