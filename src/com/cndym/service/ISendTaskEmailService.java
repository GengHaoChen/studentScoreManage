package com.cndym.service;

import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.email.UserEmail;

public interface ISendTaskEmailService {

	/**
	 * �����ʼ�
	 * @param dataInfoSid 
	 * @param permissionType 
	 * @param email 
	 * @param Flag ͳ��flag  
	 */
	void sendEmail(DataTable dataInfoSid, UserEmail email);
	

}
