package com.cndym.dao;

import java.io.Serializable;
import java.util.List;

import com.cndym.util.BuilderSql;
import com.cndym.util.PageView;

public interface BaseDao<T> {
	/**
	 * @Name:save
	 * @Description:���ݴ���Ķ���ʵ�����ӹ���
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:����Ķ���
	 * @Return:��
	 */
	void save(T t);
	/**
	 * @Name:delete
	 * @Description:����id��ʵ��ɾ������
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:�����id
	 * @Return:��
	 */
	void delete(Serializable id);

	/**
	 * @Name:update
	 * @Description:���ݶ���ʵ���޸Ĺ���
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:����
	 * @Return:��
	 */
	void update(T t);

	/**
	 * @Name:saveOrUpdate
	 * @Description:��������
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:����
	 * @Return:��
	 */
	void saveOrUpdate(T obj);

	/**
	 * @Name:getById
	 * @Description: ��ȡʵ��
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:�����id
	 * @Return:����id��ѯ�󷵻ص���������
	 */
	T getById(Serializable id);


	/**
	 * @Name:getByIdList
	 * @Description: ����id���ϣ���ѯ��Ӧ�Ķ���������Ϣ
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:id����
	 * @Return:���󼯺�
	 */
	List<T> getByIdList(Serializable[] idList);

	/**
	 * @Name:getAll
	 * @Description:��ѯ����
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:��
	 * @Return:���󼯺�
	 */
	List<T> getAll();

	/**
	 * @Name:getPageView
	 * @Description:����һϵ�еĲ��������ض�Ӧ��pageView����
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:��Service�㣬��ƴ�ӺõĲ�ѯ����װ��BuilderSql�У�ͬʱ������currentPage
	 * @Return:pageView����
	 * 
	 */
	PageView getPageView(BuilderSql builderSql, int currentPage);

	/**
	 * @Name:getList
	 * @Description:���ݲ�ѯ�������������ݿ⣬���������ļ��϶���
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:��Service�㣬��ƴ�ӺõĲ�ѯ����װ��BuilderSql��
	 * @Return:���ز�ѯ��ļ���
	 * 
	 */
	List<T> getList(BuilderSql builderSql);
	/**
	 * @Name:getListUseCache==========================ʹ�ö�������
	 * @Description:�ڶ��������з��������ļ��϶���
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:��Service�㣬��ƴ�ӺõĲ�ѯ����װ��BuilderSql��
	 * @Return:���ز�ѯ��ļ���
	 * 
	 */
	// ʹ�ö�������
	List<T> getListUseCache(BuilderSql builderSql);
	/**
	 * @Name:getListNoGen
	 * @Description:����hql���  ���в�ѯ    ���صĲ���ĳһ�����󣬶���һ��Object���͵ļ���
	 * @Author:LiNa
	 * @Version:v1.00(�汾��)
	 * @Create Date:2016��3��3��18:16:01
	 * @Parameters:��Service�㣬��ƴ�ӺõĲ�ѯ����װ��BuilderSql��
	 * @Return:  ���صĲ���ĳһ�����󣬶���һ��Object���͵ļ���
	 * 
	 */
	List getListNoGen(BuilderSql builderSql);
}
