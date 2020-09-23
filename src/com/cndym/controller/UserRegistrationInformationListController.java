package com.cndym.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndym.entity.channel.Channel;
import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.service.IChannelService;
import com.cndym.service.IUserRegistrationInformationListService;
import com.cndym.util.PageView;

@Controller
@RequestMapping("/userRate")
public class UserRegistrationInformationListController extends BaseController {
	@Resource
	private IChannelService channelServiceImpl;
	@Resource
	private IUserRegistrationInformationListService userRegistrationInformationListServiceImpl;

	
	/**
	 * @Name: toUserRateListPage
	 * @Description: �������û�������ҳ��
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��12��29��10:18:12
	 * @Parameters:��
	 * @Return: /WEB-INF/pages/userRate/userRateList.jsp
	 */
	@RequestMapping("/toUserRateListPage")
	public String toUserRateListPage(HttpServletRequest request, Model model) {
		List<Channel> channelList= channelServiceImpl.getChannelList(null, null, null);
		model.addAttribute("channelList",channelList);
		return "/userRate/userRateList";
	}
	
	/**
	 * @Name: toUserRateListPage
	 * @Description: �����¼�û�������ҳ��
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��12��29��10:18:12
	 * @Parameters:��
	 * @Return: /WEB-INF/pages/userRate/userRateList.jsp
	 */
	@RequestMapping("/toUserLoginRateListPage")
	public String toUserLoginRateListPage(HttpServletRequest request, Model model) {
		List<Channel> channelList= channelServiceImpl.getChannelList(null, null, null);
		model.addAttribute("channelList",channelList);
		return "/userRate/userLoginRateList";
	}
	
	
	/**
	 * @Name: queryDataByUserRegistration
	 * @Description: ���û���ѯ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��4��13:49:18
	 * @Parameters:��
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	@RequestMapping("/queryDataByUserRegistration")
	public String queryDataByUserRegistration(HttpServletRequest request, UserRegistrationInformationList userRegistraList,
			Model model) {
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		
		userRegistraList.setIsRegistrStatus(1);
		String startTime=request.getParameter("startTime");
		String endTime= request.getParameter("endTime");
		PageView pageView = userRegistrationInformationListServiceImpl.queryPageList(userRegistraList, currentPage,startTime,endTime);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		model.addAttribute("userRegistraList", userRegistraList);
		model.addAttribute("pageView", pageView);
		List<Channel> channelList= channelServiceImpl.getChannelList(null, null, null);
		model.addAttribute("channelList",channelList);
		return "/userRate/userRateList";
	}

	
	/**
	 * @Name: queryDataByUserRegistrationLogin
	 * @Description: ��¼�û���ѯ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��4��13:49:18
	 * @Parameters:��
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	@RequestMapping("/queryDataByUserRegistrationLogin")
	public String queryDataByUserRegistrationLogin(HttpServletRequest request, UserRegistrationInformationList userRegistraList,
			Model model) {
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		userRegistraList.setIsRegistrStatus(2);
		String startTime=request.getParameter("startTime");
		String endTime= request.getParameter("endTime");
		PageView pageView = userRegistrationInformationListServiceImpl.queryPageList(userRegistraList, currentPage,startTime,endTime);
		model.addAttribute("userRegistraList", userRegistraList);
		model.addAttribute("pageView", pageView);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		List<Channel> channelList= channelServiceImpl.getChannelList(null, null, null);
		model.addAttribute("channelList",channelList);
		return "/userRate/userLoginRateList";
	}
}
