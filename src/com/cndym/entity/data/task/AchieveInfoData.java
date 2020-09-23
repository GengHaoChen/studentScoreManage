package com.cndym.entity.data.task;

import java.util.Date;

import com.cndym.entity.data.task.annotation.ExportDataConfig;
import com.cndym.entity.data.task.annotation.Format;
import com.cndym.util.export.Utils;

public class AchieveInfoData extends DataDetail {

	@ExportDataConfig(formats={@Format("yyyy-MM-dd")})
	private Date currentDate;
	
	//����é®
	private Integer chuChuMaoLu;
	//������·
	private Integer xinShouShangLu;
	//�﷫��
	private Integer yangFanQiHang;
	//�ƴ�����
	private Integer caiDaQiCu;
	//�и�ͬ��
	private Integer youFuTongXiang;
	//����ʶ��
	private Integer huiYanShiZhu;
	//��ѧէ��
	private Integer chuXueZhaLian;
	//��������
	private Integer shuNengShengQiao;
	//����Ӧ��
	private Integer deXinYingShou;
	//������
	private Integer jingYiQiuJing;
	//¯����
	private Integer luHuoChunQing;
	//�����뻯
	private Integer chuShenRuHua;
	//С��ţ��
	private Integer xiaoShiNiuDao;
	//�Ʋ��ɵ�
	private Integer shiBuKeDang;
	//�˷�����
	private Integer chengFengPoLang;
	//����ӿ��
	private Integer fengLeiYongDong;
	//�������   
	private Integer leiTingWanJun;
	//��������
	private Integer longWeiHuZhen;
	//��������
	private Integer xiangLongFuHu;
	//����ɽ��
	private Integer qiTunShanHe;
	//߳�����
	private Integer chiChaFengYun;
	//������˫
	private Integer gaiShiWuShuang;
	//���춯��
	private Integer jingTianDongDi;
	//��¶��â
	private Integer chuLouFengMang;
	//ʱ����ת
	private Integer shiLaiYunZhuan;
	//���ƽ���
	private Integer yunShiJianJia;
	//��������
	private Integer haoYunLianLian;
	//˳��˳ˮ
	private Integer ShunFengShunShui;
	//���˵�ͷ
	private Integer hongYunDangTou;
	//�鸣����
	private Integer hongFuQiTian;
	//С�гɾ�
	private Integer xiaoYouChengJiu;
	//ո¶ͷ��
	private Integer zhanLuTouJiao;
	//����С��
	private Integer lueYouXiaoCheng;
	//�������
	private Integer chuLeiBaCui;
	//��ͬ����
	private Integer buTongFanXaing;
	//һ������
	private Integer yiMingJingRen;
	//������Ϊ
	private Integer daYouSuoWei;
	//һ�ٳ���
	private Integer yiJuChengMing;
	//˶������
	private Integer shuoGuoLeiLei;
	//��������
	private Integer gongChengMingJiu;
	//���˸��
	private Integer zhenGuShuoJin;
	//���ƺ�ʩ
	private Integer leShanHaoShi;
	//��������
	private Integer fuBeiManYi;
	//�������
	private Integer zhangYiShuCai;
	//��Σ����
	private Integer fuWeiJiKun;
	//�����Ե
	private Integer guangJieShanYuan;
	//���Ƴɵ�
	private Integer jiShanChengDe;
	//ĬĬ����
	private Integer moMoWuWen;
	//����עĿ
	private Integer yinRenZhuMu;
	//С������
	private Integer xiaoYouMingQi;
	//Զ������
	private Integer yuanJinWenMing;
	//����Զ��
	private Integer shengMingYuanYang;
	//��������
	private Integer daMingDingDing;
	//��������
	private Integer mingManTianXia;
	//����է��
	private Integer chuLaiZhaDao;
	//�ٽ�����
	private Integer zaiJieZaiLi;
	//��ֲ�и
	private Integer jianChiBuxie;
	//һ�����
	private Integer yiRuJiWang;
	//��֮�Ժ�
	private Integer chiZhiYiHeng;
	
	@Override
	public void putCurrentDate(Date date) {
		setCurrentDate(date);
	}

	@Override
	public Date loadCurrentDate() {
		return getCurrentDate();
	}
	
	@Override
	public String toString() {
		return "AchieveInfoData [currentDate=" + Utils.formatDate2Str(currentDate, "yyyy-MM-dd HH:mm:ss") + ", chuChuMaoLu=" + chuChuMaoLu + ", xinShouShangLu="
				+ xinShouShangLu + ", yangFanQiHang=" + yangFanQiHang + ", caiDaQiCu=" + caiDaQiCu + ", youFuTongXiang="
				+ youFuTongXiang + ", huiYanShiZhu=" + huiYanShiZhu + ", chuXueZhaLian=" + chuXueZhaLian
				+ ", shuNengShengQiao=" + shuNengShengQiao + ", deXinYingShou=" + deXinYingShou + ", jingYiQiuJing="
				+ jingYiQiuJing + ", luHuoChunQing=" + luHuoChunQing + ", chuShenRuHua=" + chuShenRuHua
				+ ", xiaoShiNiuDao=" + xiaoShiNiuDao + ", shiBuKeDang=" + shiBuKeDang + ", chengFengPoLang="
				+ chengFengPoLang + ", fengLeiYongDong=" + fengLeiYongDong + ", leiTingWanJun=" + leiTingWanJun
				+ ", longWeiHuZhen=" + longWeiHuZhen + ", xiangLongFuHu=" + xiangLongFuHu + ", qiTunShanHe="
				+ qiTunShanHe + ", chiChaFengYun=" + chiChaFengYun + ", gaiShiWuShuang=" + gaiShiWuShuang
				+ ", jingTianDongDi=" + jingTianDongDi + ", chuLouFengMang=" + chuLouFengMang + ", shiLaiYunZhuan="
				+ shiLaiYunZhuan + ", yunShiJianJia=" + yunShiJianJia + ", haoYunLianLian=" + haoYunLianLian
				+ ", ShunFengShunShui=" + ShunFengShunShui + ", hongYunDangTou=" + hongYunDangTou + ", hongFuQiTian="
				+ hongFuQiTian + ", xiaoYouChengJiu=" + xiaoYouChengJiu + ", zhanLuTouJiao=" + zhanLuTouJiao
				+ ", lueYouXiaoCheng=" + lueYouXiaoCheng + ", chuLeiBaCui=" + chuLeiBaCui + ", buTongFanXaing="
				+ buTongFanXaing + ", yiMingJingRen=" + yiMingJingRen + ", daYouSuoWei=" + daYouSuoWei
				+ ", yiJuChengMing=" + yiJuChengMing + ", shuoGuoLeiLei=" + shuoGuoLeiLei + ", gongChengMingJiu="
				+ gongChengMingJiu + ", zhenGuShuoJin=" + zhenGuShuoJin + ", leShanHaoShi=" + leShanHaoShi
				+ ", fuBeiManYi=" + fuBeiManYi + ", zhangYiShuCai=" + zhangYiShuCai + ", fuWeiJiKun=" + fuWeiJiKun
				+ ", guangJieShanYuan=" + guangJieShanYuan + ", jiShanChengDe=" + jiShanChengDe + ", moMoWuWen="
				+ moMoWuWen + ", yinRenZhuMu=" + yinRenZhuMu + ", xiaoYouMingQi=" + xiaoYouMingQi + ", yuanJinWenMing="
				+ yuanJinWenMing + ", shengMingYuanYang=" + shengMingYuanYang + ", daMingDingDing=" + daMingDingDing
				+ ", mingManTianXia=" + mingManTianXia + ", chuLaiZhaDao=" + chuLaiZhaDao + ", zaiJieZaiLi="
				+ zaiJieZaiLi + ", jianChiBuxie=" + jianChiBuxie + ", yiRuJiWang=" + yiRuJiWang + ", chiZhiYiHeng="
				+ chiZhiYiHeng + "]";
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getChuChuMaoLu() {
		return chuChuMaoLu;
	}

	public void setChuChuMaoLu(Integer chuChuMaoLu) {
		this.chuChuMaoLu = Math.abs(chuChuMaoLu);
	}

	public Integer getXinShouShangLu() {
		return xinShouShangLu;
	}

	public void setXinShouShangLu(Integer xinShouShangLu) {
		this.xinShouShangLu = Math.abs(xinShouShangLu);
	}

	public Integer getYangFanQiHang() {
		return yangFanQiHang;
	}

	public void setYangFanQiHang(Integer yangFanQiHang) {
		this.yangFanQiHang = Math.abs(yangFanQiHang);
	}

	public Integer getCaiDaQiCu() {
		return caiDaQiCu;
	}

	public void setCaiDaQiCu(Integer caiDaQiCu) {
		this.caiDaQiCu = Math.abs(caiDaQiCu);
	}

	public Integer getYouFuTongXiang() {
		return youFuTongXiang;
	}

	public void setYouFuTongXiang(Integer youFuTongXiang) {
		this.youFuTongXiang = Math.abs(youFuTongXiang);
	}

	public Integer getHuiYanShiZhu() {
		return huiYanShiZhu;
	}

	public void setHuiYanShiZhu(Integer huiYanShiZhu) {
		this.huiYanShiZhu = Math.abs(huiYanShiZhu);
	}

	public Integer getChuXueZhaLian() {
		return chuXueZhaLian;
	}

	public void setChuXueZhaLian(Integer chuXueZhaLian) {
		this.chuXueZhaLian = Math.abs(chuXueZhaLian);
	}

	public Integer getShuNengShengQiao() {
		return shuNengShengQiao;
	}

	public void setShuNengShengQiao(Integer shuNengShengQiao) {
		this.shuNengShengQiao = Math.abs(shuNengShengQiao);
	}

	public Integer getDeXinYingShou() {
		return deXinYingShou;
	}

	public void setDeXinYingShou(Integer deXinYingShou) {
		this.deXinYingShou = Math.abs(deXinYingShou);
	}

	public Integer getJingYiQiuJing() {
		return jingYiQiuJing;
	}

	public void setJingYiQiuJing(Integer jingYiQiuJing) {
		this.jingYiQiuJing = Math.abs(jingYiQiuJing);
	}

	public Integer getLuHuoChunQing() {
		return luHuoChunQing;
	}

	public void setLuHuoChunQing(Integer luHuoChunQing) {
		this.luHuoChunQing = Math.abs(luHuoChunQing);
	}

	public Integer getChuShenRuHua() {
		return chuShenRuHua;
	}

	public void setChuShenRuHua(Integer chuShenRuHua) {
		this.chuShenRuHua = Math.abs(chuShenRuHua);
	}

	public Integer getXiaoShiNiuDao() {
		return xiaoShiNiuDao;
	}

	public void setXiaoShiNiuDao(Integer xiaoShiNiuDao) {
		this.xiaoShiNiuDao = Math.abs(xiaoShiNiuDao);
	}

	public Integer getShiBuKeDang() {
		return shiBuKeDang;
	}

	public void setShiBuKeDang(Integer shiBuKeDang) {
		this.shiBuKeDang = Math.abs(shiBuKeDang);
	}

	public Integer getChengFengPoLang() {
		return chengFengPoLang;
	}

	public void setChengFengPoLang(Integer chengFengPoLang) {
		this.chengFengPoLang = Math.abs(chengFengPoLang);
	}

	public Integer getFengLeiYongDong() {
		return fengLeiYongDong;
	}

	public void setFengLeiYongDong(Integer fengLeiYongDong) {
		this.fengLeiYongDong = Math.abs(fengLeiYongDong);
	}

	public Integer getLeiTingWanJun() {
		return leiTingWanJun;
	}

	public void setLeiTingWanJun(Integer leiTingWanJun) {
		this.leiTingWanJun = Math.abs(leiTingWanJun);
	}

	public Integer getLongWeiHuZhen() {
		return longWeiHuZhen;
	}

	public void setLongWeiHuZhen(Integer longWeiHuZhen) {
		this.longWeiHuZhen = Math.abs(longWeiHuZhen);
	}

	public Integer getXiangLongFuHu() {
		return xiangLongFuHu;
	}

	public void setXiangLongFuHu(Integer xiangLongFuHu) {
		this.xiangLongFuHu = Math.abs(xiangLongFuHu);
	}

	public Integer getQiTunShanHe() {
		return qiTunShanHe;
	}

	public void setQiTunShanHe(Integer qiTunShanHe) {
		this.qiTunShanHe = Math.abs(qiTunShanHe);
	}

	public Integer getChiChaFengYun() {
		return chiChaFengYun;
	}

	public void setChiChaFengYun(Integer chiChaFengYun) {
		this.chiChaFengYun = Math.abs(chiChaFengYun);
	}

	public Integer getGaiShiWuShuang() {
		return gaiShiWuShuang;
	}

	public void setGaiShiWuShuang(Integer gaiShiWuShuang) {
		this.gaiShiWuShuang = Math.abs(gaiShiWuShuang);
	}

	public Integer getJingTianDongDi() {
		return jingTianDongDi;
	}

	public void setJingTianDongDi(Integer jingTianDongDi) {
		this.jingTianDongDi = Math.abs(jingTianDongDi);
	}

	public Integer getChuLouFengMang() {
		return chuLouFengMang;
	}

	public void setChuLouFengMang(Integer chuLouFengMang) {
		this.chuLouFengMang = Math.abs(chuLouFengMang);
	}

	public Integer getShiLaiYunZhuan() {
		return shiLaiYunZhuan;
	}

	public void setShiLaiYunZhuan(Integer shiLaiYunZhuan) {
		this.shiLaiYunZhuan = Math.abs(shiLaiYunZhuan);
	}

	public Integer getYunShiJianJia() {
		return yunShiJianJia;
	}

	public void setYunShiJianJia(Integer yunShiJianJia) {
		this.yunShiJianJia = Math.abs(yunShiJianJia);
	}

	public Integer getHaoYunLianLian() {
		return haoYunLianLian;
	}

	public void setHaoYunLianLian(Integer haoYunLianLian) {
		this.haoYunLianLian = Math.abs(haoYunLianLian);
	}

	public Integer getShunFengShunShui() {
		return ShunFengShunShui;
	}

	public void setShunFengShunShui(Integer shunFengShunShui) {
		ShunFengShunShui = shunFengShunShui;
	}

	public Integer getHongYunDangTou() {
		return hongYunDangTou;
	}

	public void setHongYunDangTou(Integer hongYunDangTou) {
		this.hongYunDangTou = Math.abs(hongYunDangTou);
	}

	public Integer getHongFuQiTian() {
		return hongFuQiTian;
	}

	public void setHongFuQiTian(Integer hongFuQiTian) {
		this.hongFuQiTian = Math.abs(hongFuQiTian);
	}

	public Integer getXiaoYouChengJiu() {
		return xiaoYouChengJiu;
	}

	public void setXiaoYouChengJiu(Integer xiaoYouChengJiu) {
		this.xiaoYouChengJiu = Math.abs(xiaoYouChengJiu);
	}

	public Integer getZhanLuTouJiao() {
		return zhanLuTouJiao;
	}

	public void setZhanLuTouJiao(Integer zhanLuTouJiao) {
		this.zhanLuTouJiao = Math.abs(zhanLuTouJiao);
	}

	public Integer getLueYouXiaoCheng() {
		return lueYouXiaoCheng;
	}

	public void setLueYouXiaoCheng(Integer lueYouXiaoCheng) {
		this.lueYouXiaoCheng = Math.abs(lueYouXiaoCheng);
	}

	public Integer getChuLeiBaCui() {
		return chuLeiBaCui;
	}

	public void setChuLeiBaCui(Integer chuLeiBaCui) {
		this.chuLeiBaCui = Math.abs(chuLeiBaCui);
	}

	public Integer getBuTongFanXaing() {
		return buTongFanXaing;
	}

	public void setBuTongFanXaing(Integer buTongFanXaing) {
		this.buTongFanXaing = Math.abs(buTongFanXaing);
	}

	public Integer getYiMingJingRen() {
		return yiMingJingRen;
	}

	public void setYiMingJingRen(Integer yiMingJingRen) {
		this.yiMingJingRen = Math.abs(yiMingJingRen);
	}

	public Integer getDaYouSuoWei() {
		return daYouSuoWei;
	}

	public void setDaYouSuoWei(Integer daYouSuoWei) {
		this.daYouSuoWei = Math.abs(daYouSuoWei);
	}

	public Integer getYiJuChengMing() {
		return yiJuChengMing;
	}

	public void setYiJuChengMing(Integer yiJuChengMing) {
		this.yiJuChengMing = Math.abs(yiJuChengMing);
	}

	public Integer getShuoGuoLeiLei() {
		return shuoGuoLeiLei;
	}

	public void setShuoGuoLeiLei(Integer shuoGuoLeiLei) {
		this.shuoGuoLeiLei = Math.abs(shuoGuoLeiLei);
	}

	public Integer getGongChengMingJiu() {
		return gongChengMingJiu;
	}

	public void setGongChengMingJiu(Integer gongChengMingJiu) {
		this.gongChengMingJiu = Math.abs(gongChengMingJiu);
	}

	public Integer getZhenGuShuoJin() {
		return zhenGuShuoJin;
	}

	public void setZhenGuShuoJin(Integer zhenGuShuoJin) {
		this.zhenGuShuoJin = Math.abs(zhenGuShuoJin);
	}

	public Integer getLeShanHaoShi() {
		return leShanHaoShi;
	}

	public void setLeShanHaoShi(Integer leShanHaoShi) {
		this.leShanHaoShi = Math.abs(leShanHaoShi);
	}

	public Integer getFuBeiManYi() {
		return fuBeiManYi;
	}

	public void setFuBeiManYi(Integer fuBeiManYi) {
		this.fuBeiManYi = Math.abs(fuBeiManYi);
	}

	public Integer getZhangYiShuCai() {
		return zhangYiShuCai;
	}

	public void setZhangYiShuCai(Integer zhangYiShuCai) {
		this.zhangYiShuCai = Math.abs(zhangYiShuCai);
	}

	public Integer getFuWeiJiKun() {
		return fuWeiJiKun;
	}

	public void setFuWeiJiKun(Integer fuWeiJiKun) {
		this.fuWeiJiKun = Math.abs(fuWeiJiKun);
	}

	public Integer getGuangJieShanYuan() {
		return guangJieShanYuan;
	}

	public void setGuangJieShanYuan(Integer guangJieShanYuan) {
		this.guangJieShanYuan = Math.abs(guangJieShanYuan);
	}

	public Integer getJiShanChengDe() {
		return jiShanChengDe;
	}

	public void setJiShanChengDe(Integer jiShanChengDe) {
		this.jiShanChengDe = Math.abs(jiShanChengDe);
	}

	public Integer getMoMoWuWen() {
		return moMoWuWen;
	}

	public void setMoMoWuWen(Integer moMoWuWen) {
		this.moMoWuWen = Math.abs(moMoWuWen);
	}

	public Integer getYinRenZhuMu() {
		return yinRenZhuMu;
	}

	public void setYinRenZhuMu(Integer yinRenZhuMu) {
		this.yinRenZhuMu = Math.abs(yinRenZhuMu);
	}

	public Integer getXiaoYouMingQi() {
		return xiaoYouMingQi;
	}

	public void setXiaoYouMingQi(Integer xiaoYouMingQi) {
		this.xiaoYouMingQi = Math.abs(xiaoYouMingQi);
	}

	public Integer getYuanJinWenMing() {
		return yuanJinWenMing;
	}

	public void setYuanJinWenMing(Integer yuanJinWenMing) {
		this.yuanJinWenMing = Math.abs(yuanJinWenMing);
	}

	public Integer getShengMingYuanYang() {
		return shengMingYuanYang;
	}

	public void setShengMingYuanYang(Integer shengMingYuanYang) {
		this.shengMingYuanYang = Math.abs(shengMingYuanYang);
	}

	public Integer getDaMingDingDing() {
		return daMingDingDing;
	}

	public void setDaMingDingDing(Integer daMingDingDing) {
		this.daMingDingDing = Math.abs(daMingDingDing);
	}

	public Integer getMingManTianXia() {
		return mingManTianXia;
	}

	public void setMingManTianXia(Integer mingManTianXia) {
		this.mingManTianXia = Math.abs(mingManTianXia);
	}

	public Integer getChuLaiZhaDao() {
		return chuLaiZhaDao;
	}

	public void setChuLaiZhaDao(Integer chuLaiZhaDao) {
		this.chuLaiZhaDao = Math.abs(chuLaiZhaDao);
	}

	public Integer getZaiJieZaiLi() {
		return zaiJieZaiLi;
	}

	public void setZaiJieZaiLi(Integer zaiJieZaiLi) {
		this.zaiJieZaiLi = Math.abs(zaiJieZaiLi);
	}

	public Integer getJianChiBuxie() {
		return jianChiBuxie;
	}

	public void setJianChiBuxie(Integer jianChiBuxie) {
		this.jianChiBuxie = Math.abs(jianChiBuxie);
	}

	public Integer getYiRuJiWang() {
		return yiRuJiWang;
	}

	public void setYiRuJiWang(Integer yiRuJiWang) {
		this.yiRuJiWang = Math.abs(yiRuJiWang);
	}

	public Integer getChiZhiYiHeng() {
		return chiZhiYiHeng;
	}

	public void setChiZhiYiHeng(Integer chiZhiYiHeng) {
		this.chiZhiYiHeng = Math.abs(chiZhiYiHeng);
	}

	

}
