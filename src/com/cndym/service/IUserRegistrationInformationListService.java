package com.cndym.service;

import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.util.PageView;

public interface IUserRegistrationInformationListService {
	/**
	 * 
	 * @param userRegistr
	 * @param currentPage
	 * @param retentionRateFlag �����ʱ�ʶ(1:��¼  2��Ͷע)
	 * @return
	 */
	PageView queryPageList(UserRegistrationInformationList userRegistr,int currentPage,String startTime,String endTime);
}
