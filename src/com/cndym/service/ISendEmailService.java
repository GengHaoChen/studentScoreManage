package com.cndym.service;

import java.util.List;

import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.email.UserEmail;
import com.cndym.entity.report.BookReport;

public interface ISendEmailService {

	/**
	 * �����ʼ�
	 * @param dataInfoSid 
	 * @param permissionType 
	 * @param email 
	 * @param Flag ͳ��flag  
	 */
	void sendEmail(List<DataInfo> dataInfoSid, UserEmail email, String permissionType, String countFlag);
	

}
