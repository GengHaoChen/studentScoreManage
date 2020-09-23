package com.cndym.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cndym.service.ISynchronizationDataService;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.Utils;

public class SynchronizationData extends QuartzJobBean {
	private Logger logger = Logger.getLogger(getClass());
	private ISynchronizationDataService synchronizationDataServiceImpl;
	public void query() {
		logger.info("synchronizationData start");
		if (synchronizationDataServiceImpl == null) {
			String start = Utils.yesterday();
			String end = Utils.today();
			synchronizationDataServiceImpl = (ISynchronizationDataService) SpringUtils
					.getBean("synchronizationDataServiceImpl");
			/**ͬ��֮ǰ��ɾ������*/
			synchronizationDataServiceImpl.deleteDataInfo(start, end);
			/**���ԭʼ����*/
			synchronizationDataServiceImpl.getDataByOriginal(start, end);
//			/**��ѯ��¼��������*/
			synchronizationDataServiceImpl.queryDateLogin(start, end);
//			/**ͳ�Ƶ�¼��������*/
			synchronizationDataServiceImpl.countDateLogin(start, end);
//			/**ͳ��ע����������*/
			synchronizationDataServiceImpl.countDateReg(start, end);
//			/**ͳ�Ƶ�¼����/ע������ �ܵ�����*/
			synchronizationDataServiceImpl.saveDataCount(start,end);
			/**������û�And��¼��   ��¼/Ͷע     �м�� */
			synchronizationDataServiceImpl.saveUserMemeber(start, end);
			/**���û�AndͶע�û�     ��¼/Ͷעת������Ϣ����*/
			synchronizationDataServiceImpl.userMemberRetentionRate(start, end);
			/**����Ϊ�յ�����*/
			synchronizationDataServiceImpl.updateMemberNullRate(start,end);
			logger.info("synchronizationData end");
		} else {
			logger.info("synchronizationData error");
		}
	}
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		query();
	}
	
	
}
