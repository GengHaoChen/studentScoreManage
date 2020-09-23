package com.cndym.dao.task.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.NumberUtils;

import com.cndym.dao.task.IDataTableDetailDao;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.DayTaskInfoData;

@Repository
public class DayTaskInfoDataDetailDao implements IDataTableDetailDao {

	@Resource
	private JdbcTemplate taskJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DataDetail loadFromSource(Date start, Date end) {
		String sql = "select \"count\"(*) count,c.task_name \"name\" FROM t_task_conf c LEFT JOIN t_user_task_rec r ON r.task_id = c.\"id\" WHERE c.task_type = '0' AND r.finish_date=? GROUP BY c.\"id\"";
		List<Map<String, Object>> q = taskJdbcTemplate.queryForList(sql,start);
		Map<String,Integer> data = new HashMap<String, Integer>();
		for(Map<String, Object> m : q){
			String taskName = m.get("name").toString();
			Integer count = NumberUtils.parseNumber(m.get("count").toString(), Integer.class);
			data.put(taskName, count);
		}
		DayTaskInfoData d= new DayTaskInfoData();
		d.setCurrentDate(start);
		d.setLogin(data.get("���յ�¼")==null?0:data.get("���յ�¼"));
		d.setShareBetting1(data.get("����1��Ͷע��¼")==null?0:data.get("����1��Ͷע��¼"));
		d.setRecharge10(data.get("���ճɹ���ֵ10Ԫ")==null?0:data.get("���ճɹ���ֵ10Ԫ"));
		d.setRecharge20(data.get("���ճɹ���ֵ20Ԫ")==null?0:data.get("���ճɹ���ֵ20Ԫ"));
		d.setRecharge50(data.get("���ճɹ���ֵ50Ԫ")==null?0:data.get("���ճɹ���ֵ50Ԫ"));
		d.setRecharge100(data.get("���ճɹ���ֵ100Ԫ")==null?0:data.get("���ճɹ���ֵ100Ԫ"));
		d.setRecharge200(data.get("���ճɹ���ֵ200Ԫ")==null?0:data.get("���ճɹ���ֵ200Ԫ"));
		d.setRecharge500(data.get("���ճɹ���ֵ500Ԫ")==null?0:data.get("���ճɹ���ֵ500Ԫ"));
		d.setBetting10(data.get("����Ͷע��10Ԫ")==null?0:data.get("����Ͷע��10Ԫ"));
		d.setBetting30(data.get("����Ͷע��30Ԫ")==null?0:data.get("����Ͷע��30Ԫ"));
		d.setBetting50(data.get("����Ͷע��50Ԫ")==null?0:data.get("����Ͷע��50Ԫ"));
		d.setBetting100(data.get("����Ͷע��100Ԫ")==null?0:data.get("����Ͷע��100Ԫ"));
		d.setBetting300(data.get("����Ͷע��300Ԫ")==null?0:data.get("����Ͷע��300Ԫ"));
		d.setBetting500(data.get("����Ͷע��500Ԫ")==null?0:data.get("����Ͷע��500Ԫ"));
		d.setBetting1000(data.get("����Ͷע��1000Ԫ")==null?0:data.get("����Ͷע��1000Ԫ"));
		d.setBettingTimes1(data.get("���ճɹ�Ͷע1��")==null?0:data.get("���ճɹ�Ͷע1��"));
		d.setBettingTimes5(data.get("���ճɹ�Ͷע5��")==null?0:data.get("���ճɹ�Ͷע5��"));
		d.setBettingTimes10(data.get("���ճɹ�Ͷע10��")==null?0:data.get("���ճɹ�Ͷע10��"));
		return d;
	}

	@Override
	public void saveToLocal(DataDetail data) {
		String sql = "insert into TASK_DAY_TASK_INFO\n" +
			"(id,c_date,login,share_betting1,\n" + 
			"recharge10,recharge20,recharge50,recharge100,recharge200,recharge500,\n" + 
			"betting10,betting30,betting50,betting100,betting300,betting500,betting1000,\n" + 
			"betting_times1,betting_times5,betting_times10)\n" + 
			"values\n" + 
			"(SEQ_TASK_TASK_MAIN_DATA.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DayTaskInfoData d = (DayTaskInfoData) data;
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLogin(),d.getShareBetting1()
				,d.getRecharge10(),d.getRecharge20(),d.getRecharge50(),d.getRecharge100(),d.getRecharge200(),d.getRecharge500()
				,d.getBetting10(),d.getBetting30(),d.getBetting50(),d.getBetting100(),d.getBetting300(),d.getBetting500(),d.getBetting1000()
				,d.getBettingTimes1(),d.getBettingTimes5(),d.getBettingTimes10());
	}

	@Override
	public List<DataDetail> loadFromLoacl(Date start, Date end) {
		String sql = "select c_date currentDate,login login,share_betting1 shareBetting1,\n" +
			"      recharge10,recharge20,recharge50,recharge100,recharge200,recharge500,\n" + 
			"      betting10,betting30,betting50,betting100,betting300,betting500,betting1000,\n" + 
			"      betting_times1 bettingTimes1,betting_times5 bettingTimes5,betting_times10 bettingTimes10\n" + 
			"      from TASK_DAY_TASK_INFO\n" + 
			"      where c_date>=? and c_date<=?  order by c_date desc";
		List query = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DayTaskInfoData.class),start,end);
		return query;
	}

	@Override
	public void deleteFromLocal(Date start, Date end) {
		String sql = "delete from TASK_DAY_TASK_INFO where c_date>=? and c_date<=?";
		jdbcTemplate.update(sql,start,end);
	}

}
