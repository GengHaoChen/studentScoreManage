package com.cndym.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.data.task.DataTableType;
import com.cndym.entity.email.UserEmail;
import com.cndym.entity.report.BookReport;
import com.cndym.service.IBookReportService;
import com.cndym.service.IDataInfoService;
import com.cndym.service.IDataTableService;
import com.cndym.service.ISendEmailService;
import com.cndym.service.ISendTaskEmailService;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.Utils;

public class SendEmail extends QuartzJobBean {
	private Logger logger = Logger.getLogger(getClass());
	private ISendEmailService sendEmailServiceImpl;
	private IBookReportService bookReportServiceImpl;
	private IDataInfoService dataInfoServiceImpl;
	private ISendTaskEmailService sendTaskEmailServiceImpl;
	private IDataTableService dataTableService;
	private boolean isDebug = false;


	public void query() {
		logger.info("sendEmail start");
		if (sendEmailServiceImpl == null) {
			sendEmailServiceImpl = (ISendEmailService) SpringUtils
					.getBean("sendEmailServiceImpl");
			bookReportServiceImpl = (IBookReportService) SpringUtils
					.getBean("bookReportServiceImpl");
			dataInfoServiceImpl = (IDataInfoService) SpringUtils
					.getBean("dataInfoServiceImpl");

			// 1.�����û�
			// ��ѯ���ĵ���Ϣ delStatus=0 �� ��ǰʱ�䡷= reportTime+1day
			// ��ѯ�ʼ����ı������Ƕ���ʱ��+1<=��ǰʱ��
			List<BookReport> reportSidList = bookReportServiceImpl
					.queryReport();
			logger.info("�����û����ı����������" + reportSidList.size());


			Date nowTime=new Date();
			String reportTime = Utils.addDateTime(nowTime, "d", -30);

			// ������Ϣ
			for (BookReport bookSid : reportSidList) {
				String sid = bookSid.getSid();
				String permissionType = bookSid.getPermissionType();
				List<DataInfo> dataInfoSid = dataInfoServiceImpl
						.queryBySidAllTime(sid, reportTime, permissionType,"");
				UserEmail email = new UserEmail();
				email.setTitle(bookSid.getName());
				email.setReceiveEmail(bookSid.getEmailAddress());
				sendEmailServiceImpl.sendEmail(dataInfoSid, email,
						permissionType, "");
			}

			List<BookReport> reportAdminList = bookReportServiceImpl
					.queryReportAdmin();
			logger.info("����Ա�û����ı����������" + reportAdminList.size());

			for (BookReport bookAdmin : reportAdminList) {
				String permissionType = bookAdmin.getPermissionType();
				String sid = bookAdmin.getSid();
				UserEmail email = new UserEmail();
				email.setTitle(bookAdmin.getName());
				email.setReceiveEmail(bookAdmin.getEmailAddress());
				//������������ʼ�
				logger.debug(bookAdmin.getEmailAddress()+"   |    " +bookAdmin.getName());
				if(!"fa".equals(permissionType)&&!"fb".equals(permissionType)&&!"fc".equals(permissionType)){
					//TEST �������ʼ� start
					if(isDebug&&!bookAdmin.getUserCode().equals("1461837622684")){
						continue;
					}
					//TEST  end
					try{
//						logger.debug(bookAdmin.getName()+" | "+"�ƺŻ�������ֲ� | "+Boolean.toString(!bookAdmin.getName().equals("�ƺŻ�������ֲ�")));
						sendTaskMail(bookAdmin.getPermissionType(),email);
					}catch(Exception e){
						e.printStackTrace();
						logger.error("����������������ʼ�ʧ��  type:"+bookAdmin.getPermissionType()+"  to:"+bookAdmin.getName());
					}
					continue;
				}

				if(isDebug){//�����debug   ��ִ�з��������ʼ�
					continue;
				}

				//ԭ���ݷ����ʼ�
				List<DataInfo> dataInfoList =  dataInfoServiceImpl
						.queryBySidAllTime(sid, reportTime, permissionType,"admin");
				if (Utils.isEmpty(sid)) {
					// ͳ������
					sendEmailServiceImpl.sendEmail(dataInfoList, email,
							permissionType, "count");
				} else {
					// ����sid��ѯ��������
					sendEmailServiceImpl.sendEmail(dataInfoList, email,
							permissionType, "");
				}
			}
			logger.info("sendEmail end");
		} else {
			logger.info("sendEmail error");
		}
	}

	private void sendTaskMail(String type,UserEmail email){
		if(!type.equals(DataTableType.TASK_ACHIEVE_INFO_DATA)&&!type.equals(DataTableType.TASK_DAY_TASK_INFO_DATA)&&
				!type.equals(DataTableType.TASK_PROP_AND_FRAG_MAIN_DATA)&&!type.equals(DataTableType.TASK_PROP_AND_FRAG_SOURCE_DATA)&&
				!type.equals(DataTableType.TASK_SCORE_MAIN_DATA)&&!type.equals(DataTableType.TASK_SCORE_SOURSE_DATA)&&
				!type.equals(DataTableType.TASK_TASK_MAIN_DATA)){
			return ;
		}
		dataTableService = (IDataTableService) SpringUtils.getBean("dataTableService");
		sendTaskEmailServiceImpl = (ISendTaskEmailService) SpringUtils.getBean("sendTaskEmailServiceImpl");
		Date start = Utils.getDayStart(new Date(System.currentTimeMillis()-1000*60*60*24*7));
		Date end = Utils.getDayEnd(new Date(System.currentTimeMillis()-1000*60*60*24*1));
		DataTable loadDataTableFromLocal = dataTableService.loadDataTableFromLocal(type, start, end);
		email.setTitle("[�������] "+email.getTitle()+" ("+Utils.formatDate2Str(Utils.addDate(new Date(), "d", -1), "yyyyMMdd")+")");
		sendTaskEmailServiceImpl.sendEmail(loadDataTableFromLocal, email);
	}

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		query();
	}

	public static void main(String[] args) {
//		System.out.println(String.format("%.2f",7.0));
		new SendEmail().query();
	}
}
