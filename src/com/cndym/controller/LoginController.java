package com.cndym.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndym.entity.Function;
import com.cndym.entity.user.User;
import com.cndym.service.IUserService;
import com.cndym.util.Md5;
import com.cndym.util.ParsingPermissionUtil;
import com.cndym.util.export.Utils;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@Resource
	private IUserService userServiceImpl;
	

	/**
	 * @Name: toLogonPage
	 * @Description:���ݴ���Ķ���ʵ�����ӹ���
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��4��13:49:18
	 * @Parameters:��
	 * @Return: �����û���¼ҳ��
	 */
	@RequestMapping("/toHome")
	public String toHome(HttpServletRequest request) {
		User userOld = (User) request.getSession().getAttribute("LOGON_USER");
		if (Utils.isNotEmpty(userOld)) {
			splitrules(request, userOld);
			return "/layout/index";
		}
		return "/login/login";
	}

	/**
	 * @Name: loginOut
	 * @Description: �˳�
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��4��13:49:18
	 * @Parameters:��
	 * @Return:
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request) {
		request.getSession().getAttribute("LOGON_USER");
		request.getSession().invalidate();
		request.setAttribute("ctx", request.getContextPath());
		return "/login/login";
	}

	/**
	 * @Name: login
	 * @Description: �û���¼����֤�û��������룬���û�����Ӧ��Ȩ��
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��3��28��17:10:35
	 * @Parameters: �û���¼��Ϣ
	 * @Return: ��½�ɹ����ҳ�� /WEB-INF/pages/layout/index.jsp
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,User user,String code,Model model) {
		
	
		User userOld = (User) request.getSession().getAttribute("LOGON_USER");
		if (Utils.isNotEmpty(userOld)) {
			splitrules(request, userOld);
			return "/layout/index";
		}
		
		
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		userPassword=Md5.Md5(userPassword);
		if(request.getSession().getAttribute("rCode")==null || !request.getSession().getAttribute("rCode").equals(code)){
			return "/login/login";
		}
		userMap.put("userName", userName);
		userMap.put("userPassword", userPassword);
		User getUser = userServiceImpl.queryByQuery(userMap);
		
		if(Utils.isNotEmpty(getUser)){
		 splitrules(request, getUser);
		 request.getSession().setAttribute("LOGON_USER", getUser);
			return "/layout/index";
		}
		
		
		
		model.addAttribute("userName", userName);
		model.addAttribute("userPassword", userPassword);
		model.addAttribute("mes","�û������������");
		return "/login/login";	
	}

	public String splitrules(HttpServletRequest request, User user) {
		// ���Ȩ�ޱ�ʶ
		String permissions = user.getUserGroup().getGroupPermissions();
		String[] permissionsArray = permissions.split("@");
		Set<String> permissionSet = new HashSet<String>();
		for (String string : permissionsArray) {
			permissionSet.add(string);
		}
		ParsingPermissionUtil parsing = new ParsingPermissionUtil();
		Collection<Function> functions = parsing.getIndexMenu(permissionSet);
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("functions", functions);
		return permissions;
	}
}
