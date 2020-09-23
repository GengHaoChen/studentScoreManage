package com.cndym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/link")
public class AllLinksController extends BaseController {
	/**
	 * @Name: toLogonPage
	 * @Description:���ݴ���Ķ���ʵ�����ӹ���
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��4��13:49:18
	 * @Parameters:��
	 * @Return: �����û���¼ҳ��
	 */
	@RequestMapping("/toLinkPage")
	public String toLogonPage(){
		return "/linkPage/link";
	}
}
 