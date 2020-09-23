package com.cndym.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.dao.pick.IDataInfoDao;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.service.IChannelService;
import com.cndym.service.IDataInfoService;
import com.cndym.util.PageView;
import com.cndym.util.export.Utils;

@Service("dataInfoServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class DataInfoServiceImpl implements IDataInfoService {
	@Resource
	private IDataInfoDao dataInfoDaoImpl;
	@Resource
	private IChannelService channelServiceImpl;
	
	

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public PageView queryPageList(DataInfo dataInfo, int currentPage,
			String permissionType,String administratorType) {
		Map<String,String> sidMap= channelServiceImpl.sidMap();
		PageView pageView = null;
		// 1.�ж��û�Ȩ��
		if ("fa".equals(permissionType)) {
			// ȫ������
			pageView = dataInfoDaoImpl.queryPageViewAll(dataInfo,
					currentPage);
		} else if ("fb".equals(permissionType)) {
			// ��ֵ����
			pageView = dataInfoDaoImpl.queryPageViewChongzhi(dataInfo,
					currentPage,administratorType);
		} else if ("fc".equals(permissionType)) {
			// Ͷע����
			pageView = dataInfoDaoImpl.queryPageViewTouzhu(dataInfo,
					currentPage,administratorType);
		}
		List<DataInfo> dataInfoList= pageView.getItems();
		for (DataInfo da : dataInfoList) {
			if(Utils.isNotEmpty(sidMap.get(da.getSid()))){
				da.setChannelName(sidMap.get(da.getSid()));
			}else{
				da.setChannelName("�������,sid��"+da.getSid());
			}
		}
		return pageView;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public List<DataInfo> getExportDataList(DataInfo dataInfo,String permissionType,String stradmin) {
		List<DataInfo> list = null;
		if ("fa".equals(permissionType)) {
			// ȫ������
			list = dataInfoDaoImpl.queryListAll(dataInfo.getSid(), dataInfo.getStartTime(),dataInfo.getEndTime(), 1,stradmin);
		} else if ("fb".equals(permissionType)) {
			// ��ֵ����
			list = dataInfoDaoImpl.queryListChongzhi(dataInfo.getSid(), dataInfo.getStartTime(),dataInfo.getEndTime(),2,stradmin);
		} else if ("fc".equals(permissionType)) {
			// Ͷע����
			list = dataInfoDaoImpl.queryListTouzhu(dataInfo.getSid(), dataInfo.getStartTime(),dataInfo.getEndTime(), 2,stradmin);
		}
		Map<String,String> sidMap= channelServiceImpl.sidMap();
		for (DataInfo da : list) {
			if(Utils.isNotEmpty(sidMap.get(da.getSid()))){
				da.setChannelName(sidMap.get(da.getSid()));
			}else{
				da.setChannelName("�������,sid��"+da.getSid());
			}
		}
		return list;
	}

	

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public List<DataInfo> queryBySidAllTime(String sid, String reportTime,
			String permissionType,String administratorType) {
		Map<String,String> sidMap= channelServiceImpl.sidMap();
		List<DataInfo>  dataInfo=null;
		// 1.�ж��û�Ȩ��
		String endTime = Utils.today();
		if ("fa".equals(permissionType)) {
			// ȫ������
			dataInfo=dataInfoDaoImpl.queryListAll(sid, reportTime,endTime, 1,administratorType);
		} else if ("fb".equals(permissionType)) {
			// ��ֵ����
			dataInfo=dataInfoDaoImpl.queryListChongzhi(sid, reportTime,endTime,2,administratorType);
		} else if ("fc".equals(permissionType)) {
			// Ͷע����
			dataInfo=dataInfoDaoImpl.queryListTouzhu(sid, reportTime,endTime, 2,administratorType);
		}
		for (DataInfo data : dataInfo) {
			data.setChannelName(sidMap.get(data.getSid()));
		}
		return dataInfo;
	}
}
