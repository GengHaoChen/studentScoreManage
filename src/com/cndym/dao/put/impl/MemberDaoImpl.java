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
import com.cndym.dao.put.IMemberDao;
import com.cndym.entity.data.put.Member;

@Repository
@SuppressWarnings("unchecked")
public class MemberDaoImpl extends BaseDaoImpl<Member> implements IMemberDao {
	private Logger logger = Logger.getLogger(getClass());
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void deleteMember(String startDate, String endDate) {
		try {
			String sql = "delete from user_member_pick t where t.create_time  >= to_date('"+startDate+"','yyyy-MM-dd')  AND  t.create_time  < to_date('"+endDate+"','yyyy-MM-dd')";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			logger.error("ͬ��֮ǰ��ɾ����" + e);
		}
	}
	
	@Override
	public boolean queryMemberList(String startDate, String endDate) {
		boolean flag=false;
		try {
			String sql = "insert into USER_MEMBER_PICK(ID,USER_CODE,STATUS ,UNION_ID,TRACK_UNION,CREATE_TIME,SOFT_VER,REGISTER_APP)";
			sql = sql + " SELECT SEQ_USER_MEMBER_PICK.NEXTVAL, t.user_code ,t.status,t.union_id, t.track_union,t.CREATE_TIME , t.soft_ver ,t.REGISTER_APP from user_member t";
			sql = sql + " WHERE t.create_time  >= to_date('"+startDate+"','yyyy-MM-dd')  AND  t.create_time  < to_date('"+endDate+"','yyyy-MM-dd')";
			jdbcTemplate.update(sql);
			flag=true;
		} catch (Exception e) {
			flag=false;
			logger.error("ע���û��������ע��������û����������뵽�½��ı�user_member_pick�г���", e);
		}
		return flag;
	}
	@Override
	public List<Map<String,Object>> queryDistinct(String startDate, String endDate) {
		List<Map<String,Object>> list = null;
		try {
			String sql = " select count(1) as count,UNION_ID as sid from USER_MEMBER_PICK ";
			sql = sql + " WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') AND CREATE_TIME < to_date(?,'yyyy-MM-dd') group by UNION_ID ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
			logger.info("ȥ��ע���û���:"+list);
		} catch (Exception e) {
			logger.error("ȥ��ע���û���", e);
		}
		return list;
	}
	/**
	 * ��ȡ�����ע���û����� ,List<Map<String,Object>> userCodeList
	 */
	@Override
	public List<Map<String,Object>> queryMemberLoginByTime(String startTime, String endTime,String start, String end){
		List<Map<String,Object>> list = null;
		try {
			StringBuffer sb=new StringBuffer(" select count(1) as count,UNION_ID as sid from USER_MEMBER_PICK ");
			sb.append(" WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') AND CREATE_TIME < to_date(?,'yyyy-MM-dd')");
			sb.append(" AND user_code in ( select DISTINCT(USER_CODE) as userCode  from user_member_login_history_pick");
			sb.append(" WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') and CREATE_TIME < to_date(?,'yyyy-MM-dd'))");
			sb.append(" group by UNION_ID");
			Query query = this.getSession().createSQLQuery(sb.toString());
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setString(2, start);
			query.setString(3, end);
			logger.info("sql��"+sb.toString()+" startTime:"+startTime+" endTime:"+endTime);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
			logger.info("��ȡ��ʼʱ��:"+startTime+"����ʱ��:"+endTime+"��ע���û�����:"+list);
		} catch (Exception e) {
			logger.error("��ȡ�����ע���û�����", e);
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryMemberBettingByTime(
			String registrationTime, String endTime, String start, String end) {
		List<Map<String,Object>> list = null;
		try {
			StringBuffer sb=new StringBuffer(" select count(1) as count,UNION_ID as sid from USER_MEMBER_PICK ");
			sb.append(" WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') AND CREATE_TIME < to_date(?,'yyyy-MM-dd')");
			sb.append(" AND user_code in (select distinct(a.user_code) from USER_BETTORS a");
			sb.append(" where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd'))");
			sb.append(" group by UNION_ID");
			Query query = this.getSession().createSQLQuery(sb.toString());
			query.setString(0, registrationTime);
			query.setString(1, endTime);
			query.setString(2, start);
			query.setString(3, end);
			logger.info("sql��"+sb.toString()+" startTime:"+registrationTime+" endTime:"+endTime);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
			logger.info("��ȡ��ʼʱ��:"+registrationTime+"����ʱ��:"+endTime+"�ĵ�½�û�����:"+list+" start:"+start+" end:"+end);
		} catch (Exception e) {
			logger.error("��ȡ��ʼʱ��:"+registrationTime+"����ʱ��:"+endTime+"�ĵ�½�û�����:"+list+" start:"+start+" end:"+end, e);
		}
		return list;
	}
}
