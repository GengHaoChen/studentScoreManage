package com.cndym.bean;

import java.io.Serializable;

/**
*
* ��ɫ��
* @author lina
* @time	2020/09/02 10��46
*/
public class UserGroup  implements Serializable {
	private static final long serialVersionUID = 1697539970349488459L;
	private int  groupId	;//������������
	private String gourName	;//	��ɫ����
	private String groupPermissions	;//	ӵ�е�Ȩ��aa@ab@ac@ad
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGourName() {
		return gourName;
	}
	public void setGourName(String gourName) {
		this.gourName = gourName;
	}
	public String getGroupPermissions() {
		return groupPermissions;
	}
	public void setGroupPermissions(String groupPermissions) {
		this.groupPermissions = groupPermissions;
	}
}
