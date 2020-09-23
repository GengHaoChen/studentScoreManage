package com.cndym.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndym.entity.channel.Channel;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.user.User;
import com.cndym.entity.user.UserGroup;
import com.cndym.service.IChannelService;
import com.cndym.service.IDataInfoService;
import com.cndym.service.IUserService;
import com.cndym.util.PageView;
import com.cndym.util.export.Utils;

@Controller
@RequestMapping("/dataInfo")
public class DataInfoController extends BaseController {
	private Logger logger = Logger.getLogger(getClass());
	@Resource
	private IChannelService channelServiceImpl;
	@Resource
	private IDataInfoService dataInfoServiceImpl;
	@Resource
	private IUserService userServiceImpl;

	/**
	 * @Name: toLogonPage
	 * @Description:���ݴ���Ķ���ʵ�����ӹ���
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��4��13:49:18
	 * @Parameters:��
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	@RequestMapping("/toDataInfoListPage")
	public String toDataInfoListPage(HttpServletRequest request, Model model) {
		/**
		 * �����û�:<br/>
		 * 1.���ݸ��û��Ĺ�˾��Ϣ ȥ�����е�������Ϣ <br/>
		 * 2.������ֻ�ܲ������ӵ�е���������<br/>
		 * ����Ա�û�:<br/>
		 * �ܲ�ѯ���е�������Ϣ
		 */
		User user = (User) request.getSession().getAttribute("LOGON_USER");
		if (user != null) {
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userCode", user.getUserCode());
			User userInfo = userServiceImpl.queryByQuery(userMap);
			UserGroup userGroup = userInfo.getUserGroup();
			// Ȩ��
			String permission = userGroup.getGroupPermissions();
			// ���е���������Ȩ��
			List<String> permissionList = new ArrayList<String>();
			if (permission.indexOf("fa") != -1) {
				permissionList.add("fa");
			}
			if (permission.indexOf("fb") != -1) {
				permissionList.add("fb");
			}
			if (permission.indexOf("fc") != -1) {
				permissionList.add("fc");
			}
			request.getSession().setAttribute("permissionList", permissionList);
			model.addAttribute("userInfo", userInfo);
			if (isAdministrator(permission)) {
				// ��������Ա�û����ڲ�ѯ���ݵ�ʱ����Բ�ѯ���е�sid��Ϣ
				model.addAttribute("isAdministratorFlag", 1);
				return "/data/dataInfoAdministratorList";
			} else {
				// �����û�ֻ�ܲ�ѯ�ù�˾�µ�sid��Ϣ
				String companyCode = userInfo.getCompanyCode();
				List<Channel> channelList = channelServiceImpl
						.queryChannelListByCompanyCode(companyCode);
				model.addAttribute("channelList", channelList);
				model.addAttribute("isAdministratorFlag", 0);
				return "/data/dataInfoList";
			}
		}
		model.addAttribute("isAdministratorFlag", 0);
		return "/data/dataInfoList";
	}

	/**
	 * @Name: queryData
	 * @Description: ��ѯ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��4��13:49:18
	 * @Parameters:��
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	@RequestMapping("/queryData")
	public String queryData(HttpServletRequest request, DataInfo dataInfo,
			Model model) {
		String permissionType = request.getParameter("permissionType");
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		String administratorType = request.getParameter("administratorType");
		PageView pageView = dataInfoServiceImpl.queryPageList(dataInfo,
				currentPage, permissionType,administratorType);
		User user = (User) request.getSession().getAttribute("LOGON_USER");

		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("userCode", user.getUserCode());
		User userInfo = userServiceImpl.queryByQuery(userMap);

		String companyCode = userInfo.getCompanyCode();
		model.addAttribute("permissionType", permissionType);
		model.addAttribute("dataInfo", dataInfo);
		model.addAttribute("pageView", pageView);
		model.addAttribute("userInfo", userInfo);
		if (Utils.isEmpty(administratorType)) {
			List<Channel> channelList = channelServiceImpl
					.queryChannelListByCompanyCode(companyCode);
			model.addAttribute("channelList", channelList);
			model.addAttribute("isAdministratorFlag", 0);
			return "/data/dataInfoList";
		}
		model.addAttribute("isAdministratorFlag", 1);
		return "/data/dataInfoAdministratorList";
	}

	/**
	 * @Name: ajaxSubscribeNameBySid
	 * @Description: ����sid���������͵õ����ı��������
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��4��21��13:59:31
	 * @Parameters:��
	 * @Return: ��
	 */
	@RequestMapping("/ajaxSubscribeNameBySid")
	public void ajaxSubscribeNameBySid(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter printWriter = null;
		JSONObject resultObject = new JSONObject();
		try {
			String sid = request.getParameter("sid");
			Channel channel = channelServiceImpl.getChannelBySid(sid);
			String subscribeName = channel.getName();
			printWriter = response.getWriter();
			resultObject.put("subscribeName", subscribeName);
		} catch (Exception e) {
			resultObject.put("subscribeName", "error");
			logger.error("����sid���������͵õ����ı�������Ƴ���", e);
		}
		printWriter.print(resultObject.toString());
		printWriter.flush();
		printWriter.close();
	}
}
