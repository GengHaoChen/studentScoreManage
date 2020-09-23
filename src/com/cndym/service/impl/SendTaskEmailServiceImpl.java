package com.cndym.service.impl;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.email.MailEngine;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.data.task.AchieveInfoData;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.data.task.DataTableType;
import com.cndym.entity.data.task.DayTaskInfoData;
import com.cndym.entity.data.task.ExportDataFileType;
import com.cndym.entity.data.task.PropAndFragMainData;
import com.cndym.entity.data.task.PropAndFragSourceData;
import com.cndym.entity.data.task.ScoreDetailData;
import com.cndym.entity.data.task.ScoreMainData;
import com.cndym.entity.data.task.TaskMainData;
import com.cndym.entity.email.UserEmail;
import com.cndym.service.ISendEmailService;
import com.cndym.service.ISendTaskEmailService;
import com.cndym.util.ConfigUtils;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.DataDetailUtils;
import com.cndym.util.export.Utils;

@Service("sendTaskEmailServiceImpl")
public class SendTaskEmailServiceImpl implements ISendTaskEmailService {
	private Logger logger = Logger.getLogger(getClass());
	// private String cellStyle = " style='border: 1px solid black;' ";
	private String thStyle = " style='background-color: #FFFFFF;border: 1px solid black;' ";
	private String td1Style = " style='background-color: #DDDDDD;border: 1px solid black;text-align: right;' ";
	private String td2Style = " style='background-color: #FFFFFF;border: 1px solid black;text-align: right;' ";
	private String tableStyle = " style='overflow: scroll;min-width:1300px;border-collapse:collapse;' ";
	private String tempStyle = "";

	@Override
	public void sendEmail(DataTable data, UserEmail email) {
		MailEngine mailEngine = (MailEngine) SpringUtils.getBean("mailEngine");
		String content = formatDataToEmailContent(data);
//		System.out.println(content);
		try {
			mailEngine.sendMessage(new String[] { email.getReceiveEmail() }, ConfigUtils.getValue("MAIL.DEFAULT.FROM"),
					null, content, email.getTitle(), null);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String formatDataToEmailContent(DataTable data) {
		String type = data.getType();
		String result = "";
		try {
			List<Map<String, Object>> formatDataToMaps = DataDetailUtils.formatDataToMaps(data,ExportDataFileType.HTML);
			
			if (DataTableType.TASK_ACHIEVE_INFO_DATA.equals(type)) {
				result = getAchieveInfoDataContent(formatDataToMaps);
			} else if (DataTableType.TASK_DAY_TASK_INFO_DATA.equals(type)) {
				result = getDayTaskInfoData(formatDataToMaps);
			} else if (DataTableType.TASK_PROP_AND_FRAG_MAIN_DATA.equals(type)) {
				result = getPAFMainData(formatDataToMaps);
			} else if (DataTableType.TASK_PROP_AND_FRAG_SOURCE_DATA.equals(type)) {
				result = getPAFSourceData(formatDataToMaps);
			} else if (DataTableType.TASK_SCORE_MAIN_DATA.equals(type)) {
				result = getScoreMainData(formatDataToMaps);
			} else if (DataTableType.TASK_SCORE_SOURSE_DATA.equals(type)) {
				result = getSocreSourceData(formatDataToMaps);
			} else if (DataTableType.TASK_TASK_MAIN_DATA.equals(type)) {
				result = getTaskMainData(formatDataToMaps);
			}
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			result = "ERROR:�����ʼ����Ĵ���   �������ݡ�";
		}
		return result;
	}

	/*
	 * List<DataDetail> d = data.getData(); StringBuilder content = new
	 * StringBuilder(); for(DataDetail detail : d){ } return content.toString();
	 */

	private String getTaskMainData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table " + tableStyle + ">");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='21'>��������ձ�</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>���� </th>");
		content.append("		<th" + thStyle + " colspan='6'>�������������� </th>");
		content.append("		<th" + thStyle + " colspan='6'>������������������ </th>");
		content.append("		<th" + thStyle + " colspan='8'>�ɾ��������������� </th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">������� </th>");
		content.append("		<th" + thStyle + ">��������� </th>");
		content.append("		<th" + thStyle + ">��ȡ���������� </th>");
		content.append("		<th" + thStyle + ">���������� </th>");
		content.append("		<th" + thStyle + ">������ȡ���� </th>");
		content.append("		<th" + thStyle + ">�˾��������� </th>");
		content.append("		<th" + thStyle + ">������� </th>");
		content.append("		<th" + thStyle + ">��������� </th>");
		content.append("		<th" + thStyle + ">��ȡ���������� </th>");
		content.append("		<th" + thStyle + ">���������� </th>");
		content.append("		<th" + thStyle + ">������ȡ���� </th>");
		content.append("		<th" + thStyle + ">�˾��������� </th>");
		content.append("		<th" + thStyle + ">������� </th>");
		content.append("		<th" + thStyle + ">��������� </th>");
		content.append("		<th" + thStyle + ">��ȡ���������� </th>");
		content.append("		<th" + thStyle + ">���������� </th>");
		content.append("		<th" + thStyle + ">������ȡ���� </th>");
		content.append("		<th" + thStyle + ">�˾��������� </th>");
		content.append("		<th" + thStyle + ">��óƺ����� </th>");
		content.append("		<th" + thStyle + ">��óƺ��� </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskRecoverTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskRecoverPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskRecoverTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskRecoverPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskRecoverTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskRecoverPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("catchTitlePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("catchTitleTitleCount").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getSocreSourceData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table " +tableStyle+ ">");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='33'>���������ձ�</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>���� </th>");
		content.append("		<th" + thStyle + " colspan='3'>����������� </th>");
		content.append("		<th" + thStyle + " colspan='3'>���ֳ齱���Ļ��� </th>");
		content.append("		<th" + thStyle + " colspan='3'>�һ��������Ļ��� </th>");
		content.append("		<th" + thStyle + " colspan='6'>����ݵ��������� </th>");
		content.append("		<th" + thStyle + " colspan='3'>����ݵ����Ļ��� </th>");
		content.append("		<th" + thStyle + " colspan='1'>����ݵ����� </th>");
		content.append("		<th" + thStyle + " colspan='3'>���߻��ղ������� </th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">���������� </th>");
		content.append("		<th" + thStyle + ">������ȡ���� </th>");
		content.append("		<th" + thStyle + ">�˾�������� </th>");
		content.append("		<th" + thStyle + ">�齱���� </th>");
		content.append("		<th" + thStyle + ">���Ļ����� </th>");
		content.append("		<th" + thStyle + ">�˾��齱���� </th>");
		content.append("		<th" + thStyle + ">�һ����� </th>");
		content.append("		<th" + thStyle + ">���Ļ����� </th>");
		content.append("		<th" + thStyle + ">�˾��һ����� </th>");
		content.append("		<th" + thStyle + ">�һ����� </th>");
		content.append("		<th" + thStyle + ">���Ļ��� </th>");
		content.append("		<th" + thStyle + ">�˾����Ļ��� </th>");
		content.append("		<th" + thStyle + ">�������� </th>");
		content.append("		<th" + thStyle + ">���������� </th>");
		content.append("		<th" + thStyle + ">�˾��������� </th>");
		content.append("		<th" + thStyle + ">�������ֵ���� </th>");
		content.append("		<th" + thStyle + ">��������� </th>");
		content.append("		<th" + thStyle + ">�˾���������� </th>");
		content.append("		<th" + thStyle + ">�������� </th>");
		content.append("		<th" + thStyle + ">���Ļ����� </th>");
		content.append("		<th" + thStyle + ">�˾����»��� </th>");
		content.append("		<th" + thStyle + ">���ջ����� </th>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">���������� </th>");
		content.append("		<th" + thStyle + ">�˾������� </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskCompleteRewardScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskCompleteRecivePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskCompleteAverageTaskScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costDrawPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costDrawCostScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costDrawAverageDrawScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropCostScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropAverageCostScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyInventedPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyInventedPropScoreCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyInventedPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodMakeSuccessPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodMakeSocre").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodMakeAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyLuckySocrePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyLuckyScoreScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyLuckyScoreAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodCostPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodCostSocre").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodCostAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodReciveScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimPeople").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimAverageScore").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getScoreMainData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table " + tableStyle + "  >");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='13'>���ֻ����ձ�</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>����</th>");
		content.append("		<th" + thStyle + " colspan='3'>����������</th>");
		content.append("		<th" + thStyle + " colspan='3'>���Ļ�����</th>");
		content.append("		<th" + thStyle + " rowspan='2'>�����¼�û���������</th>");
		content.append("		<th" + thStyle + " rowspan='2'>������ִ���</th>");
		content.append("		<th" + thStyle + " rowspan='2'>���ִ���</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">������            </th>");
		content.append("		<th" + thStyle + ">�˾���������            </th>");
		content.append("		<th" + thStyle + ">��������          </th>");
		content.append("		<th" + thStyle + ">���Ļ�����          </th>");
		content.append("		<th" + thStyle + ">�˾����Ļ���          </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");

		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate") + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("makeScorePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("makeScoreTotalScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("makeScoreAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costScorePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costScoreTotalScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costScoreAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("loginScoreTotalScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("changeScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("totalScoreInPool").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getPAFSourceData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table" +tableStyle+ " >");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='16'>�������߼���Ƭ��Դ�ձ�</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>����</th>");
		content.append("		<th" + thStyle + " colspan='3'>���ֶһ�����</th>");
		content.append("		<th" + thStyle + " colspan='6'>���ֳ齱����</th>");
		content.append("		<th" + thStyle + " colspan='3'>��Ƭ�ϳɵ���</th>");
		content.append("		<th" + thStyle + " colspan='3'>����ͣ�����</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">������ </th>");
		content.append("		<th" + thStyle + ">�˾��һ��� </th>");
		content.append("		<th" + thStyle + ">�������� </th>");
		content.append("		<th" + thStyle + ">������ </th>");
		content.append("		<th" + thStyle + ">�˾������� </th>");
		content.append("		<th" + thStyle + ">��Ƭ���� </th>");
		content.append("		<th" + thStyle + ">��Ƭ�� </th>");
		content.append("		<th" + thStyle + ">�˾���Ƭ�� </th>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">������ </th>");
		content.append("		<th" + thStyle + ">�˾��ϳ��� </th>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">������ </th>");
		content.append("		<th" + thStyle + ">�˾������� </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawFragPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawFragCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawFragAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hechengPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hechengPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hechengAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiangbutingPeopleCont").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiangbutingPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiangbutingAverageCount").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getPAFMainData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table " +tableStyle+ "  >");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='33'>���߼���Ƭ�����ձ�</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>���� </th>");
		content.append("		<th" + thStyle + " colspan='3'>�������� </th>");
		content.append("		<th" + thStyle + " colspan='3'>ʹ�õ��� </th>");
		content.append("		<th" + thStyle + " colspan='3'>������ʹ����� </th>");
		content.append("		<th" + thStyle + " colspan='3'>���׿�ʹ����� </th>");
		content.append("		<th" + thStyle + " colspan='3'>�ӽ���ʹ����� </th>");
		content.append("		<th" + thStyle + " colspan='3'>�����ʹ����� </th>");
		content.append("		<th" + thStyle + " colspan='3'>���߻������</th>");
		content.append("		<th" + thStyle + " colspan='2'>���ڵ��� </th>");
		content.append("		<th" + thStyle + " colspan='2'>ʣ����� </th>");
		content.append("		<th" + thStyle + " colspan='3'>������Ƭ </th>");
		content.append("		<th" + thStyle + " colspan='3'>��Ƭ�������</th>");
		content.append("		<th" + thStyle + " >ʣ����Ƭ </th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">������ </th>");
		content.append("		<th" + thStyle + ">�˾������� </th>");
		content.append("		<th" + thStyle + ">ʹ������ </th>");
		content.append("		<th" + thStyle + ">ʹ�õ����� </th>");
		content.append("		<th" + thStyle + ">�˾�ʹ���� </th>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">������ </th>");
		content.append("		<th" + thStyle + ">�˾�ʹ���� </th>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">������ </th>");
		content.append("		<th" + thStyle + ">�˾�ʹ���� </th>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">������ </th>");
		content.append("		<th" + thStyle + ">�˾�ʹ���� </th>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">������ </th>");
		content.append("		<th" + thStyle + ">�˾�ʹ���� </th>");
		content.append("		<th" + thStyle + ">�������� </th>");
		content.append("		<th" + thStyle + ">���յ����� </th>");
		content.append("		<th" + thStyle + ">�˾����յ����� </th>");
		content.append("		<th" + thStyle + ">���ڵ����� </th>");
		content.append("		<th" + thStyle + ">�ۼƹ��ڵ����� </th>");
		content.append("		<th" + thStyle + ">����ʣ������� </th>");
		content.append("		<th" + thStyle + ">�ۼ�ʣ������� </th>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">��Ƭ�� </th>");
		content.append("		<th" + thStyle + ">�˾������� </th>");
		content.append("		<th" + thStyle + ">�������� </th>");
		content.append("		<th" + thStyle + ">������Ƭ�� </th>");
		content.append("		<th" + thStyle + ">�˾�������Ƭ�� </th>");
		;
		content.append("		<th" + thStyle + ">�ۼ�ʣ����Ƭ�� </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newPropPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("usedPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("usedPropPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("usedPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fanliPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fanliPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fanliAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("baodiPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("baodiPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("baodiAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiajiangPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiajiangPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiajiangAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("huodongPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("huodongPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("huodongAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimPropPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guoqiDayPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guoqiTotalPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shengyuDayPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shengyuTotalPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newFragPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newFragFragCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newFragAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimFragPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimFragCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimAverageFragCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shengyuFragCount").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getDayTaskInfoData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append(
				"<table class='table table-bordered table-hover'" +tableStyle+ "  id='chongZhiTableId' >");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='19'>����������������ֲ�</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">���� </th>");
		content.append("		<th" + thStyle + ">���յ�¼ </th>");
		content.append("		<th" + thStyle + ">����1��Ͷע��¼ </th>");
		content.append("		<th" + thStyle + ">���ճɹ���ֵ10Ԫ </th>");
		content.append("		<th" + thStyle + ">���ճɹ���ֵ20Ԫ </th>");
		content.append("		<th" + thStyle + ">���ճɹ���ֵ50Ԫ </th>");
		content.append("		<th" + thStyle + ">���ճɹ���ֵ100Ԫ </th>");
		content.append("		<th" + thStyle + ">���ճɹ���ֵ200Ԫ </th>");
		content.append("		<th" + thStyle + ">���ճɹ���ֵ500Ԫ </th>");
		content.append("		<th" + thStyle + ">����Ͷע��10Ԫ </th>");
		content.append("		<th" + thStyle + ">����Ͷע��30Ԫ </th>");
		content.append("		<th" + thStyle + ">����Ͷע��50Ԫ </th>");
		content.append("		<th" + thStyle + ">����Ͷע��100Ԫ </th>");
		content.append("		<th" + thStyle + ">����Ͷע��300Ԫ </th>");
		content.append("		<th" + thStyle + ">����Ͷע��500Ԫ </th>");
		content.append("		<th" + thStyle + ">����Ͷע��1000Ԫ </th>");
		content.append("		<th" + thStyle + ">���ճɹ�Ͷע1�� </th>");
		content.append("		<th" + thStyle + ">���ճɹ�Ͷע5�� </th>");
		content.append("		<th" + thStyle + ">���ճɹ�Ͷע10�� </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("login").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shareBetting1").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge10").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge20").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge50").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge100").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge200").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge500").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting10").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting30").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting50").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting100").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting300").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting500").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting1000").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("bettingTimes1").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("bettingTimes5").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("bettingTimes10").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getAchieveInfoDataContent(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table" +tableStyle+ "");
		content.append("	<thead>");
		content.append("		<tr>");
		content.append("			<th" + thStyle + " colspan='60'>�ƺŻ�������ֲ�</th>");
		content.append("		</tr>");
		content.append("		<tr>");
		content.append("			<th" + thStyle + ">���� </th>");
		content.append("			<th" + thStyle + ">����é® </th>");
		content.append("			<th" + thStyle + ">������· </th>");
		content.append("			<th" + thStyle + ">�﷫�� </th>");
		content.append("			<th" + thStyle + ">�ƴ����� </th>");
		content.append("			<th" + thStyle + ">�и�ͬ�� </th>");
		content.append("			<th" + thStyle + ">����ʶ�� </th>");
		content.append("			<th" + thStyle + ">��ѧէ�� </th>");
		content.append("			<th" + thStyle + ">�������� </th>");
		content.append("			<th" + thStyle + ">����Ӧ�� </th>");
		content.append("			<th" + thStyle + ">������ </th>");
		content.append("			<th" + thStyle + ">¯���� </th>");
		content.append("			<th" + thStyle + ">�����뻯 </th>");
		content.append("			<th" + thStyle + ">С��ţ�� </th>");
		content.append("			<th" + thStyle + ">�Ʋ��ɵ� </th>");
		content.append("			<th" + thStyle + ">�˷����� </th>");
		content.append("			<th" + thStyle + ">����ӿ�� </th>");
		content.append("			<th" + thStyle + ">������� </th>   ");
		content.append("			<th" + thStyle + ">�������� </th>");
		content.append("			<th" + thStyle + ">�������� </th>");
		content.append("			<th" + thStyle + ">����ɽ�� </th>");
		content.append("			<th" + thStyle + ">߳����� </th>");
		content.append("			<th" + thStyle + ">������˫ </th>");
		content.append("			<th" + thStyle + ">���춯�� </th>");
		content.append("			<th" + thStyle + ">��¶��â </th>");
		content.append("			<th" + thStyle + ">ʱ����ת </th>");
		content.append("			<th" + thStyle + ">���ƽ��� </th>");
		content.append("			<th" + thStyle + ">�������� </th>");
		content.append("			<th" + thStyle + ">˳��˳ˮ </th>");
		content.append("			<th" + thStyle + ">���˵�ͷ </th>");
		content.append("			<th" + thStyle + ">�鸣���� </th>");
		content.append("			<th" + thStyle + ">С�гɾ� </th>");
		content.append("			<th" + thStyle + ">ո¶ͷ�� </th>");
		content.append("			<th" + thStyle + ">����С�� </th>");
		content.append("			<th" + thStyle + ">������� </th>");
		content.append("			<th" + thStyle + ">��ͬ���� </th>");
		content.append("			<th" + thStyle + ">һ������ </th>");
		content.append("			<th" + thStyle + ">������Ϊ </th>");
		content.append("			<th" + thStyle + ">һ�ٳ��� </th>");
		content.append("			<th" + thStyle + ">˶������ </th>");
		content.append("			<th" + thStyle + ">�������� </th>");
		content.append("			<th" + thStyle + ">���˸�� </th>");
		content.append("			<th" + thStyle + ">���ƺ�ʩ </th>");
		content.append("			<th" + thStyle + ">�������� </th>");
		content.append("			<th" + thStyle + ">������� </th>");
		content.append("			<th" + thStyle + ">��Σ���� </th>");
		content.append("			<th" + thStyle + ">�����Ե </th>");
		content.append("			<th" + thStyle + ">���Ƴɵ� </th>");
		content.append("			<th" + thStyle + ">ĬĬ���� </th>");
		content.append("			<th" + thStyle + ">����עĿ </th>");
		content.append("			<th" + thStyle + ">С������ </th>");
		content.append("			<th" + thStyle + ">Զ������ </th>");
		content.append("			<th" + thStyle + ">����Զ�� </th>");
		content.append("			<th" + thStyle + ">�������� </th>");
		content.append("			<th" + thStyle + ">�������� </th>");
		content.append("			<th" + thStyle + ">����է�� </th>");
		content.append("			<th" + thStyle + ">�ٽ����� </th>");
		content.append("			<th" + thStyle + ">��ֲ�и </th>");
		content.append("			<th" + thStyle + ">һ����� </th>");
		content.append("			<th" + thStyle + ">��֮�Ժ� </th>");
		content.append("		</tr>");
		content.append("	</thead>");
		content.append("	<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("		<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuChuMaoLu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xinShouShangLu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yangFanQiHang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("caiDaQiCu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("youFuTongXiang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("huiYanShiZhu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuXueZhaLian").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shuNengShengQiao").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("deXinYingShou").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jingYiQiuJing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("luHuoChunQing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuShenRuHua").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xiaoShiNiuDao").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shiBuKeDang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chengFengPoLang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fengLeiYongDong").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("leiTingWanJun").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("longWeiHuZhen").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xiangLongFuHu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("qiTunShanHe").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chiChaFengYun").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("gaiShiWuShuang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jingTianDongDi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuLouFengMang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shiLaiYunZhuan").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yunShiJianJia").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("haoYunLianLian").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("ShunFengShunShui").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hongYunDangTou").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hongFuQiTian").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xiaoYouChengJiu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("zhanLuTouJiao").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("lueYouXiaoCheng").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuLeiBaCui").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buTongFanXaing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yiMingJingRen").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("daYouSuoWei").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yiJuChengMing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shuoGuoLeiLei").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("gongChengMingJiu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("zhenGuShuoJin").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("leShanHaoShi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fuBeiManYi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("zhangYiShuCai").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fuWeiJiKun").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guangJieShanYuan").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiShanChengDe").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("moMoWuWen").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yinRenZhuMu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xiaoYouMingQi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yuanJinWenMing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shengMingYuanYang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("daMingDingDing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("mingManTianXia").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuLaiZhaDao").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("zaiJieZaiLi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jianChiBuxie").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yiRuJiWang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chiZhiYiHeng").toString() + "</td>");
			content.append("		</tr>");
		}
		content.append("	</tbody>");
		content.append("</table>");
		return content.toString();
	}

}
