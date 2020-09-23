package com.cndym.entity.data.pick;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * @author Administrator
 */
public class DataInfo implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	// ����ID
	
	private String sid;
	// ���ݼ�¼��ʱ��[���ݵ�ʱ��]
	private Date dataRecordTime;
	// ��������ȥ�ؼ����豸��(��Ҫ������ṩ���ݣ�
	private Integer jiHuo=0;
	// DAU����ǰ���ڣ�ȥ�ص�¼�û������Ե�¼��ԴΪ׼��
	private Integer dau=0;
	// ע���û�������ǰ���ڣ����ע��������û�����
	private Integer registeredUserCount=0;
	// ��ֵ�û�������ǰ���ڣ���ֵ�ɹ���ȥ���û�����
	private Integer chongZhiUserCount=0;
	// Ͷע�û�������ǰ���ڣ�Ͷע�ɹ���ȥ���û�����������Ǻ����Ϲ����û���ÿ���û�����Ϊ1
	private Integer touZhuUserCount=0;
	// �ܳ�ֵ����ǰ����,��ֵ�ɹ����ܽ��
	private Double chongZhiAllMoney=0.00d;
	// �˾���ֵ���ܳ�ֵ���/��ֵ�û���-------------------���
	private Double userAvgChongZhiMoney=0.00d;
	// ��ֵ���û�������ǰ���ڣ�ע�ᵱ���ֹ24:00ǰ��ֵ�ɹ���ȥ���û���
	private Integer chongZhiNewUserCount=0;
	// ���û���ֵ����ǰ���ڣ�ע�ᵱ���ֹ24:00ǰ��ֵ�ɹ��Ľ��
	private Double chongZhiNewUserMonery=0.00d;
	// ���û��˾���ֵ�����û���ֵ���/��ֵ���û���-------------------���
	private Double chongZhiNewUserAvgMoney=0.00d;
	// ��Ͷע����ǰ���ڣ�Ͷע�ɹ����ܽ��
	private Double touZhuAllMoney=0.00d;
	// Ͷע���û�������ǰ���ڣ�ע�ᵱ���ֹ24:00ǰͶע�ɹ���ȥ���û���
	private Integer touZhuNewUserCount=0;
	// ���û�Ͷע����ǰ���ڣ�ע�ᵱ���ֹ24:00ǰͶע�ɹ��Ľ��
	private Double touZhuNewUserMonery=0.00d;
	// ���û���ֵת���ʣ���ֵ���û���/ע���û���-------------------���
	private Double chongZhiNewUserRate=0.00d;
	// ���û�Ͷעת���ʣ�Ͷע���û���/ע���û���-------------------���
	private Double touZhuNewUserRate=0.00d;
	// ���û�ͶעARPU�����û�Ͷע���/Ͷע���û���-------------------���
	private Double touZhuNewUserArpu=0.00d;
	// ȫ���û�ͶעARPU����Ͷע���/Ͷע�û���-------------------���
	private Double touZhuAllUserArpu=0.00d;
	// ���𣺵�ǰ���ڣ����������ܽ��
	private Double grants=0.00d;
	// �������ģ���ǰ���ڣ��������ĵ��ܽ��
	private Double grantsConsume=0.00d;
	// ������¼������ʱ��
	private Date createTime;
	// Ԥ���ֶΣ��ж��ǵ�½1 sid ����ע��2 sid
	private Integer sidStatus;
	//0:��ͨ���ݣ�1��ͳ�ƺ������,������ͳ��
	private Integer isCount; 
	
	
	
    //�����ֶΣ���ӳ�䵽���ݿ�
	// ��ʼʱ�� 
	private String startTime;
	// ����ʱ��
	private String endTime;
	//�������ƣ�����������ʹ�õ��ֶ�
	private String channelName;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Date getDataRecordTime() {
		return dataRecordTime;
	}

	public Integer getJiHuo() {
		return jiHuo;
	}

	public void setJiHuo(Integer jiHuo) {
		this.jiHuo = jiHuo;
	}

	public void setDataRecordTime(Date dataRecordTime) {
		this.dataRecordTime = dataRecordTime;
	}

	public Date getCreateTime() {
		return createTime;
	}
	
	
	public String getTimeFormat(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(dataRecordTime);
		return time;
	}
	
	
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

	
	public Integer getDau() {
		return dau;
	}

	public void setDau(Integer dau) {
		this.dau = dau;
	}

	public Integer getRegisteredUserCount() {
		return registeredUserCount;
	}

	public void setRegisteredUserCount(Integer registeredUserCount) {
		this.registeredUserCount = registeredUserCount;
	}

	public Integer getChongZhiUserCount() {
		return chongZhiUserCount;
	}

	public void setChongZhiUserCount(Integer chongZhiUserCount) {
		this.chongZhiUserCount = chongZhiUserCount;
	}

	public Integer getTouZhuUserCount() {
		return touZhuUserCount;
	}

	public void setTouZhuUserCount(Integer touZhuUserCount) {
		this.touZhuUserCount = touZhuUserCount;
	}

	public Double getChongZhiAllMoney() {
		
		return chongZhiAllMoney;
	}

	public void setChongZhiAllMoney(Double chongZhiAllMoney) {
		this.chongZhiAllMoney = chongZhiAllMoney;
	}

	public Double getUserAvgChongZhiMoney() {
		return userAvgChongZhiMoney;
	}

	public void setUserAvgChongZhiMoney(Double userAvgChongZhiMoney) {
		this.userAvgChongZhiMoney = userAvgChongZhiMoney;
	}

	public Double getTouZhuAllMoney() {
		return touZhuAllMoney;
	}

	public void setTouZhuAllMoney(Double touZhuAllMoney) {
		this.touZhuAllMoney = touZhuAllMoney;
	}

	public Integer getChongZhiNewUserCount() {
		return chongZhiNewUserCount;
	}

	public void setChongZhiNewUserCount(Integer chongZhiNewUserCount) {
		this.chongZhiNewUserCount = chongZhiNewUserCount;
	}

	public Integer getTouZhuNewUserCount() {
		return touZhuNewUserCount;
	}

	public void setTouZhuNewUserCount(Integer touZhuNewUserCount) {
		this.touZhuNewUserCount = touZhuNewUserCount;
	}

	public Double getChongZhiNewUserMonery() {
		return chongZhiNewUserMonery;
	}

	public void setChongZhiNewUserMonery(Double chongZhiNewUserMonery) {
		this.chongZhiNewUserMonery = chongZhiNewUserMonery;
	}

	public Double getChongZhiNewUserAvgMoney() {
		return chongZhiNewUserAvgMoney;
	}

	public void setChongZhiNewUserAvgMoney(Double chongZhiNewUserAvgMoney) {
		this.chongZhiNewUserAvgMoney = chongZhiNewUserAvgMoney;
	}

	public Double getTouZhuNewUserMonery() {
		return touZhuNewUserMonery;
	}

	public void setTouZhuNewUserMonery(Double touZhuNewUserMonery) {
		this.touZhuNewUserMonery = touZhuNewUserMonery;
	}

	public Double getChongZhiNewUserRate() {
		return chongZhiNewUserRate;
	}

	public void setChongZhiNewUserRate(Double chongZhiNewUserRate) {
		this.chongZhiNewUserRate = chongZhiNewUserRate;
	}

	public Double getTouZhuNewUserRate() {
		return touZhuNewUserRate;
	}

	public void setTouZhuNewUserRate(Double touZhuNewUserRate) {
		this.touZhuNewUserRate = touZhuNewUserRate;
	}

	public Double getTouZhuNewUserArpu() {
		return touZhuNewUserArpu;
	}

	public void setTouZhuNewUserArpu(Double touZhuNewUserArpu) {
		this.touZhuNewUserArpu = touZhuNewUserArpu;
	}

	public Double getTouZhuAllUserArpu() {
		return touZhuAllUserArpu;
	}

	public void setTouZhuAllUserArpu(Double touZhuAllUserArpu) {
		this.touZhuAllUserArpu = touZhuAllUserArpu;
	}

	public Double getGrants() {
		return grants;
	}

	public void setGrants(Double grants) {
		this.grants = grants;
	}

	public Double getGrantsConsume() {
		return grantsConsume;
	}

	public void setGrantsConsume(Double grantsConsume) {
		this.grantsConsume = grantsConsume;
	}

	public Integer getSidStatus() {
		return sidStatus;
	}

	public void setSidStatus(Integer sidStatus) {
		this.sidStatus = sidStatus;
	}

	 
	@Override
	public String toString() {
		String s="jihuo��"+jiHuo+"dau��"+dau+"registeredUserCount��"+registeredUserCount+"chongZhiUserCount��"+chongZhiUserCount
				+"touZhuUserCount��"+touZhuUserCount+"chongZhiAllMoney��"+chongZhiAllMoney+"userAvgChongZhiMoney��"+userAvgChongZhiMoney+"chongZhiNewUserCount��"+chongZhiNewUserCount
				+"chongZhiNewUserMonery��"+chongZhiNewUserMonery+"chongZhiNewUserAvgMoney��"+chongZhiNewUserAvgMoney+"touZhuAllMoney��"+touZhuAllMoney
				+"touZhuNewUserCount��"+touZhuNewUserCount+"touZhuNewUserMonery��"+touZhuNewUserMonery+"chongZhiNewUserRate��"+chongZhiNewUserRate
				+"touZhuNewUserRate��"+touZhuNewUserRate+"touZhuNewUserArpu��"+touZhuNewUserArpu
				+"touZhuAllUserArpu��"+touZhuAllUserArpu+"grants��"+grants+"grantsConsume��"+grantsConsume+"sidStatus��"+sidStatus
				;
		return s;
	}
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getIsCount() {
		return isCount;
	}

	public void setIsCount(Integer isCount) {
		this.isCount = isCount;
	}
}
