package com.cndym.dao.task.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.NumberUtils;

import com.cndym.dao.task.IDataTableDetailDao;
import com.cndym.entity.data.task.AchieveInfoData;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.util.export.Utils;

@Repository
public class AchieveInfoDataDetailDao implements IDataTableDetailDao {

	@Resource
	private JdbcTemplate taskJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DataDetail loadFromSource(Date start, Date end) {
		String sql = "SELECT \"count\"(*) count,t.title title FROM t_user_title ut LEFT JOIN t_title t ON t.\"id\" = ut.title_id WHERE create_time = ? GROUP BY t.\"id\"";
		List<Map<String,Object>> queryForList = taskJdbcTemplate.queryForList(sql,start);
		Map<String,Integer> data = new HashMap<String, Integer>();
		for(Map<String, Object> m : queryForList){
			String taskName = m.get("title").toString();
			Integer count = NumberUtils.parseNumber(m.get("count").toString(), Integer.class);
			data.put(taskName, count);
		}
		AchieveInfoData d = formatData(data);
		d.setCurrentDate(start);
		return d;
	}

	@Override
	public void saveToLocal(DataDetail data) {
		String sql = "insert into TASK_ACHIEVE_INFO_DATA\n" +
			"(id,c_date,title_count,title_name)\n" + 
			"values\n" +
			"(SEQ_TASK_ACHIEVE_INFO_DATA.NEXTVAL,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		AchieveInfoData d= (AchieveInfoData) data;
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuChuMaoLu(),"����é®");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXinShouShangLu(),"������·");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYangFanQiHang(),"�﷫��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getCaiDaQiCu(),"�ƴ�����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYouFuTongXiang(),"�и�ͬ��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getHuiYanShiZhu(),"����ʶ��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuXueZhaLian(),"��ѧէ��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShuNengShengQiao(),"��������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getDeXinYingShou(),"����Ӧ��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getJingYiQiuJing(),"������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLuHuoChunQing(),"¯����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuShenRuHua(),"�����뻯");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXiaoShiNiuDao(),"С��ţ��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShiBuKeDang(),"�Ʋ��ɵ�");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChengFengPoLang(),"�˷�����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getFengLeiYongDong(),"����ӿ��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLeiTingWanJun(),"�������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLongWeiHuZhen(),"��������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXiangLongFuHu(),"��������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getQiTunShanHe(),"����ɽ��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChiChaFengYun(),"߳�����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getGaiShiWuShuang(),"������˫");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getJingTianDongDi(),"���춯��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuLouFengMang(),"��¶��â");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShiLaiYunZhuan(),"ʱ����ת");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYunShiJianJia(),"���ƽ���");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getHaoYunLianLian(),"��������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShunFengShunShui(),"˳��˳ˮ");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getHongYunDangTou(),"���˵�ͷ");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getHongFuQiTian(),"�鸣����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXiaoYouChengJiu(),"С�гɾ�");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getZhanLuTouJiao(),"ո¶ͷ��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLueYouXiaoCheng(),"����С��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuLeiBaCui(),"�������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getBuTongFanXaing(),"��ͬ����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYiMingJingRen(),"һ������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getDaYouSuoWei(),"������Ϊ");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYiJuChengMing(),"һ�ٳ���");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShuoGuoLeiLei(),"˶������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getGongChengMingJiu(),"��������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getZhenGuShuoJin(),"���˸��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLeShanHaoShi(),"���ƺ�ʩ");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getFuBeiManYi(),"��������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getZhangYiShuCai(),"�������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getFuWeiJiKun(),"��Σ����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getGuangJieShanYuan(),"�����Ե");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getJiShanChengDe(),"���Ƴɵ�");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getMoMoWuWen(),"ĬĬ����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYinRenZhuMu(),"����עĿ");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXiaoYouMingQi(),"С������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYuanJinWenMing(),"Զ������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShengMingYuanYang(),"����Զ��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getDaMingDingDing(),"��������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getMingManTianXia(),"��������");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuLaiZhaDao(),"����է��");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getZaiJieZaiLi(),"�ٽ�����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getJianChiBuxie(),"��ֲ�и");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYiRuJiWang(),"һ�����");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChiZhiYiHeng(),"��֮�Ժ�");
		
	}

	@Override
	public List<DataDetail> loadFromLoacl(Date start, Date end) {
		String sql = "select * from task_achieve_info_data where c_date>=? and c_date<=? order by c_date desc";
		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql,start,end);
		Map<String,Integer> data = new HashMap<String, Integer>();
		List<DataDetail> result = new ArrayList<DataDetail>();
		String lastDate = "";
		AchieveInfoData d = null;
		for(Map<String,Object> map : queryForList){
			String date = map.get("C_DATE").toString();
			if(!date.equals(lastDate)&&!"".equals(lastDate)){
				d = formatData(data);
				d.setCurrentDate(Utils.getDate(lastDate));
				result.add(d);
				data.clear();
			}
			lastDate = date;
			String titleName = map.get("TITLE_NAME")==null?"":map.get("TITLE_NAME").toString();
			Integer count = map.get("TITLE_COUNT")==null?0:NumberUtils.parseNumber(map.get("TITLE_COUNT").toString(), Integer.class);
			data.put(titleName, count);
		}
		d = formatData(data);
		d.setCurrentDate(Utils.getDate(lastDate));
		result.add(d);
		return result;
	}
	
	private AchieveInfoData formatData(Map<String,Integer> data){
		AchieveInfoData d= new AchieveInfoData();
		d.setChuChuMaoLu(data.get("����é®")==null?0:data.get("����é®"));
		d.setXinShouShangLu(data.get("������·")==null?0:data.get("������·"));
		d.setYangFanQiHang(data.get("�﷫��")==null?0:data.get("�﷫��"));
		d.setCaiDaQiCu(data.get("�ƴ�����")==null?0:data.get("�ƴ�����"));
		d.setYouFuTongXiang(data.get("�и�ͬ��")==null?0:data.get("�и�ͬ��"));
		d.setHuiYanShiZhu(data.get("����ʶ��")==null?0:data.get("����ʶ��"));
		d.setChuXueZhaLian(data.get("��ѧէ��")==null?0:data.get("��ѧէ��"));
		d.setShuNengShengQiao(data.get("��������")==null?0:data.get("��������"));
		d.setDeXinYingShou(data.get("����Ӧ��")==null?0:data.get("����Ӧ��"));
		d.setJingYiQiuJing(data.get("������")==null?0:data.get("������"));
		d.setLuHuoChunQing(data.get("¯����")==null?0:data.get("¯����"));
		d.setChuShenRuHua(data.get("�����뻯")==null?0:data.get("�����뻯"));
		d.setXiaoShiNiuDao(data.get("С��ţ��")==null?0:data.get("С��ţ��"));
		d.setShiBuKeDang(data.get("�Ʋ��ɵ�")==null?0:data.get("�Ʋ��ɵ�"));
		d.setChengFengPoLang(data.get("�˷�����")==null?0:data.get("�˷�����"));
		d.setFengLeiYongDong(data.get("����ӿ��")==null?0:data.get("����ӿ��"));
		d.setLeiTingWanJun(data.get("�������")==null?0:data.get("�������"));
		d.setLongWeiHuZhen(data.get("��������")==null?0:data.get("��������"));
		d.setXiangLongFuHu(data.get("��������")==null?0:data.get("��������"));
		d.setQiTunShanHe(data.get("����ɽ��")==null?0:data.get("����ɽ��"));
		d.setChiChaFengYun(data.get("߳�����")==null?0:data.get("߳�����"));
		d.setGaiShiWuShuang(data.get("������˫")==null?0:data.get("������˫"));
		d.setJingTianDongDi(data.get("���춯��")==null?0:data.get("���춯��"));
		d.setChuLouFengMang(data.get("��¶��â")==null?0:data.get("��¶��â"));
		d.setShiLaiYunZhuan(data.get("ʱ����ת")==null?0:data.get("ʱ����ת"));
		d.setYunShiJianJia(data.get("���ƽ���")==null?0:data.get("���ƽ���"));
		d.setHaoYunLianLian(data.get("��������")==null?0:data.get("��������"));
		d.setShunFengShunShui(data.get("˳��˳ˮ")==null?0:data.get("˳��˳ˮ"));
		d.setHongYunDangTou(data.get("���˵�ͷ")==null?0:data.get("���˵�ͷ"));
		d.setHongFuQiTian(data.get("�鸣����")==null?0:data.get("�鸣����"));
		d.setXiaoYouChengJiu(data.get("С�гɾ�")==null?0:data.get("С�гɾ�"));
		d.setZhanLuTouJiao(data.get("ո¶ͷ��")==null?0:data.get("ո¶ͷ��"));
		d.setLueYouXiaoCheng(data.get("����С��")==null?0:data.get("����С��"));
		d.setChuLeiBaCui(data.get("�������")==null?0:data.get("�������"));
		d.setBuTongFanXaing(data.get("��ͬ����")==null?0:data.get("��ͬ����"));
		d.setYiMingJingRen(data.get("һ������")==null?0:data.get("һ������"));
		d.setDaYouSuoWei(data.get("������Ϊ")==null?0:data.get("������Ϊ"));
		d.setYiJuChengMing(data.get("һ�ٳ���")==null?0:data.get("һ�ٳ���"));
		d.setShuoGuoLeiLei(data.get("˶������")==null?0:data.get("˶������"));
		d.setGongChengMingJiu(data.get("��������")==null?0:data.get("��������"));
		d.setZhenGuShuoJin(data.get("���˸��")==null?0:data.get("���˸��"));
		d.setLeShanHaoShi(data.get("���ƺ�ʩ")==null?0:data.get("���ƺ�ʩ"));
		d.setFuBeiManYi(data.get("��������")==null?0:data.get("��������"));
		d.setZhangYiShuCai(data.get("�������")==null?0:data.get("�������"));
		d.setFuWeiJiKun(data.get("��Σ����")==null?0:data.get("��Σ����"));
		d.setGuangJieShanYuan(data.get("�����Ե")==null?0:data.get("�����Ե"));
		d.setJiShanChengDe(data.get("���Ƴɵ�")==null?0:data.get("���Ƴɵ�"));
		d.setMoMoWuWen(data.get("ĬĬ����")==null?0:data.get("ĬĬ����"));
		d.setYinRenZhuMu(data.get("����עĿ")==null?0:data.get("����עĿ"));
		d.setXiaoYouMingQi(data.get("С������")==null?0:data.get("С������"));
		d.setYuanJinWenMing(data.get("Զ������")==null?0:data.get("Զ������"));
		d.setShengMingYuanYang(data.get("����Զ��")==null?0:data.get("����Զ��"));
		d.setDaMingDingDing(data.get("��������")==null?0:data.get("��������"));
		d.setMingManTianXia(data.get("��������")==null?0:data.get("��������"));
		d.setChuLaiZhaDao(data.get("����է��")==null?0:data.get("����է��"));
		d.setZaiJieZaiLi(data.get("�ٽ�����")==null?0:data.get("�ٽ�����"));
		d.setJianChiBuxie(data.get("��ֲ�и")==null?0:data.get("��ֲ�и"));
		d.setYiRuJiWang(data.get("һ�����")==null?0:data.get("һ�����"));
		d.setChiZhiYiHeng(data.get("��֮�Ժ�")==null?0:data.get("��֮�Ժ�"));
		return d;
	}

	@Override
	public void deleteFromLocal(Date start, Date end) {
		String sql = "delete from TASK_ACHIEVE_INFO_DATA where c_date>=? and c_date<=? ";
		jdbcTemplate.update(sql,start,end);
	}

}
