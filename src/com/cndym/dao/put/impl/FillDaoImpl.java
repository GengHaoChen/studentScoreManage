package com.cndym.dao.put.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.put.IFillDao;
import com.cndym.entity.data.put.Fill;

@Repository
public class FillDaoImpl extends BaseDaoImpl<Fill> implements IFillDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	private Logger logger = Logger.getLogger(getClass());
	
	
	@Override
	public void deleteFill(String startDate, String endDate) {
		try {
			String sql = "delete from user_fill_pick t where t.create_time  >= to_date('"+startDate+"','yyyy-MM-dd')  AND  t.create_time  < to_date('"+endDate+"','yyyy-MM-dd')";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			logger.error("ͬ��֮ǰ��ɾ����" + e);
		}
	}
	
	@Override
//	��ֵ�û���-------��½����
	public boolean queryFillList(String startDate, String endDate){
//		��ԭʼ���ݾ�ɸѡ�������ŵ���ֵ��½������
		boolean flag=false;
		try {
			String sql= "insert into USER_FILL_PICK(id,user_code,order_id ,fill_resources,amount,real_amount,create_time,accept_time,status,sid,platform,soft_ver)";
		    sql = sql + " SELECT SEQ_USER_FILL_PICK.NEXTVAL, t.user_code,t.order_id ,t.fill_resources,t.amount,t.real_amount,t.create_time,t.accept_time , t.status,t.sid,t.platform,t.soft_ver";
		    sql = sql + " from user_fill t WHERE t.STATUS=1 and t.create_time >= to_date('"+startDate+"','yyyy-MM-dd')  AND   t.create_time < to_date('"+endDate+"','yyyy-MM-dd') " ;
			jdbcTemplate.update(sql);
			flag=true;
		} catch (Exception e) {
			flag=false;
			logger.error("��ֵ�û�-->֮��½���������뵽�½��ı�user_fill_pick�г���", e);
		}
		return flag;
	}

	@Override
	public List<Map<String,Object>> queryDistinctLoginGroupBySid(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select count(DISTINCT(user_code)) as count, sid from USER_FILL_PICK ";
			sql = sql + " WHERE ACCEPT_TIME >= to_date(?,'yyyy-MM-dd') AND ACCEPT_TIME < to_date(?,'yyyy-MM-dd') GROUP BY SID ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
			logger.info("ȥ�س�ֵ�û�����½����---->��½����"+list);
		} catch (Exception e) {
			logger.error("ȥ�س�ֵ�û�����½����---->��½����", e);
		}
		return list;
	}
	@Override
	//��ֵ�û�������������ֱ�ӽ���ȥ��ͳ����
	public Integer queryDistinctLoginCount(String startDate, String endDate){
		Integer result=0;
		try {
			String sql = "select count(DISTINCT(user_code)) as count from USER_FILL_PICK ";
			sql = sql + " WHERE ACCEPT_TIME >= to_date(?,'yyyy-MM-dd') AND ACCEPT_TIME < to_date(?,'yyyy-MM-dd')";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>> list=query.list();
			if(list!=null && list.size()>0){
				result=Integer.valueOf(list.get(0).get("COUNT")+"");
			}
		} catch (Exception e) {
			logger.error("ȥ�س�ֵ�û�����½����---->��½����", e);
		}
		return result;
	}
	
	
	@Override
	public List<Map<String,Object>> queryDistinctRegGroupBySid(String startDate, String endDate){
		List<Map<String,Object>> list=null;
		try {
			String sql = "select b.union_id as sid, count(DISTINCT(a.user_code)) as count from user_fill_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd')";
			sql += "group by b.union_id ";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("ȥ�س�ֵ�û���ע������---->ע������", e);
		}
		return list;
	}
	
	@Override
	public Integer queryDistinctRegCount(String startDate, String endDate){
		Integer result=0;
		try {
			String sql = "select count(DISTINCT(a.user_code)) as count from user_fill_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd')";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>> list=query.list();
			if(list!=null && list.size()>0){
				result=Integer.valueOf(list.get(0).get("COUNT")+"");
			}
		} catch (Exception e) {
			logger.error("ȥ�س�ֵ�û���ע������---->ע������", e);
		}
		return result;
	}
	
	
	@Override
	public List<Map<String,Object>> queryAllMoneyLogin(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = " select SUM(REAL_AMOUNT) as count,sid from USER_FILL_PICK t ";
			sql = sql + " WHERE ACCEPT_TIME >= to_date(?,'yyyy-MM-dd') AND ACCEPT_TIME <  to_date(?,'yyyy-MM-dd') GROUP BY sid ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("�ܳ�ֵ���---->��½����", e);
		}
		return list;
	}
	
	@Override
	public List<Map<String,Object>> queryAllMoneyReg(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select b.union_id as sid, sum(a.real_amount) as count from user_fill_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd')";
			sql += "group by b.union_id ";
			
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("�ܳ�ֵ���----->ע������", e);
		}
		return list;
	}
	
	@Override
	public List<Map<String,Object>> queryNewDistinctLoginGroupBySid(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select a.sid, count(DISTINCT(a.user_code)) as count from user_fill_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by a.sid ";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("��ֵ���û���---->��½���� ", e);
		}
		return list;
	}
	
	@Override
	public  Integer queryNewDistinctLoginCount(String startDate, String endDate) {
		Integer  result=0;
		try {
			String sql = "select count(DISTINCT(a.user_code)) as count from user_fill_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>> list=query.list();
			if(list!=null && list.size()>0){
				result=Integer.valueOf(list.get(0).get("COUNT")+"");
			}
		} catch (Exception e) {
			logger.error("��ֵ���û���---->��½���� ", e);
		}
		return result;
	}
	
	
	
	@Override
	public List<Map<String,Object>> queryNewDistinctRegGroupBySid(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select b.union_id as sid, count(DISTINCT(a.user_code)) as count from user_fill_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by b.union_id ";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("��ֵ���û���----->ע������  ", e);
		}
		return list;
	}
	
	
	@Override
	public Integer queryNewDistinctRegCount(String startDate, String endDate) {
		Integer result=0;
		try {
			String sql = "select  count(DISTINCT(a.user_code)) as count from user_fill_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>> list=query.list();
			if(list!=null && list.size()>0){
				result=Integer.valueOf(list.get(0).get("COUNT")+"");
			}
		} catch (Exception e) {
			logger.error("��ֵ���û���----->ע������  ", e);
		}
		return result;
	}
	
	
	
	
	@Override
	public List<Map<String,Object>> queryNewMoneryLogin(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select a.sid, sum(a.real_amount) as count from user_fill_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by a.sid ";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
			logger.info("���û���ֵ���---->��½���� "+list);
		} catch (Exception e) {
			logger.error("���û���ֵ���---->��½���� ", e);
		}
		return list;
	}
	
	@Override
	public List<Map<String,Object>> queryNewMoneryReg(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select b.union_id as sid, sum(a.real_amount) as count from user_fill_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by b.union_id ";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate); 
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
			logger.info("���û���ֵ���--->ע������  "+list);
		} catch (Exception e) {
			logger.error("���û���ֵ���--->ע������  ", e);
		}
		return list;
	}
}
