package com.cndym.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cndym.entity.Function;
import com.cndym.entity.user.User;
import com.cndym.util.ParsingPermissionUtil;
import com.cndym.util.export.Utils;

public class BaseController {
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * @Name: isSuper
	 * @Description: �Ƿ��ǹ���Ա,true:�ǹ���Ա,flase:�����û�
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date: 2016��4��21��16:03:28
	 * @Parameters:��
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	public boolean isAdministrator(String permission) {
		// 1.�ж����û��������û����ǳ�������Ա�û�
		// Ȩ�޲������еľ��������û�,�õ����е�Ȩ��ȥ�ȶԣ��鿴����ͨ�û����ǳ�������Ա�û�
		boolean falg = true;
		ParsingPermissionUtil parse = new ParsingPermissionUtil();
		Collection<Function> functions = parse.getIndexMenu(null);
		for (Function f : functions) {
			List<Function> childList = f.getChildFunctions();
			for (Function child : childList) {
				if (permission.indexOf("@" + child.getFunctionParentFlag()) == -1) {
					return false;
				}
			}
		}
		return falg;
	}

	// ����־��¼  2016-04-22 camily
	public void writeLog(HttpServletRequest request, String content) {
		User user = (User) request.getSession().getAttribute("LOGON_USER");
		if (Utils.isNotEmpty(user))
			logger.info("�û�:" + null == user.getUserName() ? null : user.getUserName() + "---> " + content);
		else
			logger.info("�û�:" + null + "---> " + content);
	}
}
