package com.cndym.entity.data.pick;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ע���û����¼/Ͷע ת���ʽ����  ----ע������
 * 
 * @author Administrator
 * 
 */
public class UserRegistrationInformationList {
	private Long id;
	/**ע��ʱ��*/
	private Date registrationTime;
	private Date createTime;
	/**��¼�����ʵ�״̬(1:δ����  2������) û��*/
//	private Integer loginStatus;
	/**Ͷע�����ʵ�״̬(1:δ����  2������) û��*/
//	private Integer bettingStatus;
	/**�����ʱ�ʶ(1:��¼  2��Ͷע)*/
	private Integer retentionRateFlag;
	/**ע������*/
	private String sid;
	/**
	 * ���ܣ��ܵ�ע���û���
	 * �����������������ܵ�ע���û���
	 * */
	private Integer registerdCount;
	private Integer twoDay;
	private Double  twoDayRate;
	private Integer threeDay;
	private Double  threeDayRate;
	private Integer fourDay;
	private Double  fourDayRate;
	private Integer fiveDay;
	private Double  fiveDayRate;	
	private Integer sixDay;
	private Double  sixDayRate;
	private Integer sevenDay;
	private Double  sevenDayRate;	
	private Integer tenDay;
	private Double  tenDayRate;
	private Integer fifteenDay;
	private Double  fifteenDayRate;
	private Integer twentyDay;
	private Double  twentyDayRate;
	private Integer twentyFiveDay;
	private Double  twentyFiveDayRate;	
	private Integer thirtyDay;
	private Double  thirtyDayRate;
	/**1:���û� 2:��¼�û�*/
	private Integer isRegistrStatus;
	/**0:��ͨ���ݣ�1��ͳ�ƺ������,������ͳ��*/
	private Integer isCount;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public String getTimeFormat(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(registrationTime);
		return time;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Integer getTwoDay() {
		return twoDay;
	}
	public void setTwoDay(Integer twoDay) {
		this.twoDay = twoDay;
	}
	public Double getTwoDayRate() {
		return twoDayRate;
	}
	public void setTwoDayRate(Double twoDayRate) {
		this.twoDayRate = twoDayRate;
	}
	public Integer getThreeDay() {
		return threeDay;
	}
	public void setThreeDay(Integer threeDay) {
		this.threeDay = threeDay;
	}
	public Double getThreeDayRate() {
		return threeDayRate;
	}
	public void setThreeDayRate(Double threeDayRate) {
		this.threeDayRate = threeDayRate;
	}
	public Integer getFourDay() {
		return fourDay;
	}
	public void setFourDay(Integer fourDay) {
		this.fourDay = fourDay;
	}
	public Double getFourDayRate() {
		return fourDayRate;
	}
	public void setFourDayRate(Double fourDayRate) {
		this.fourDayRate = fourDayRate;
	}
	public Integer getFiveDay() {
		return fiveDay;
	}
	public void setFiveDay(Integer fiveDay) {
		this.fiveDay = fiveDay;
	}
	public Double getFiveDayRate() {
		return fiveDayRate;
	}
	public void setFiveDayRate(Double fiveDayRate) {
		this.fiveDayRate = fiveDayRate;
	}
	public Integer getSixDay() {
		return sixDay;
	}
	public void setSixDay(Integer sixDay) {
		this.sixDay = sixDay;
	}
	public Double getSixDayRate() {
		return sixDayRate;
	}
	public void setSixDayRate(Double sixDayRate) {
		this.sixDayRate = sixDayRate;
	}
	public Integer getSevenDay() {
		return sevenDay;
	}
	public void setSevenDay(Integer sevenDay) {
		this.sevenDay = sevenDay;
	}
	public Double getSevenDayRate() {
		return sevenDayRate;
	}
	public void setSevenDayRate(Double sevenDayRate) {
		this.sevenDayRate = sevenDayRate;
	}
	public Integer getTenDay() {
		return tenDay;
	}
	public void setTenDay(Integer tenDay) {
		this.tenDay = tenDay;
	}
	public Double getTenDayRate() {
		return tenDayRate;
	}
	public void setTenDayRate(Double tenDayRate) {
		this.tenDayRate = tenDayRate;
	}
	public Integer getFifteenDay() {
		return fifteenDay;
	}
	public void setFifteenDay(Integer fifteenDay) {
		this.fifteenDay = fifteenDay;
	}
	public Double getFifteenDayRate() {
		return fifteenDayRate;
	}
	public void setFifteenDayRate(Double fifteenDayRate) {
		this.fifteenDayRate = fifteenDayRate;
	}
	public Integer getTwentyDay() {
		return twentyDay;
	}
	public void setTwentyDay(Integer twentyDay) {
		this.twentyDay = twentyDay;
	}
	public Double getTwentyDayRate() {
		return twentyDayRate;
	}
	public void setTwentyDayRate(Double twentyDayRate) {
		this.twentyDayRate = twentyDayRate;
	}
	public Integer getTwentyFiveDay() {
		return twentyFiveDay;
	}
	public void setTwentyFiveDay(Integer twentyFiveDay) {
		this.twentyFiveDay = twentyFiveDay;
	}
	public Double getTwentyFiveDayRate() {
		return twentyFiveDayRate;
	}
	public void setTwentyFiveDayRate(Double twentyFiveDayRate) {
		this.twentyFiveDayRate = twentyFiveDayRate;
	}
	public Integer getThirtyDay() {
		return thirtyDay;
	}
	public void setThirtyDay(Integer thirtyDay) {
		this.thirtyDay = thirtyDay;
	}
	public Double getThirtyDayRate() {
		return thirtyDayRate;
	}
	public void setThirtyDayRate(Double thirtyDayRate) {
		this.thirtyDayRate = thirtyDayRate;
	}
	public Integer getIsRegistrStatus() {
		return isRegistrStatus;
	}
	public void setIsRegistrStatus(Integer isRegistrStatus) {
		this.isRegistrStatus = isRegistrStatus;
	}
	public Integer getIsCount() {
		return isCount;
	}
	public void setIsCount(Integer isCount) {
		this.isCount = isCount;
	}
	public Integer getRetentionRateFlag() {
		return retentionRateFlag;
	}
	public void setRetentionRateFlag(Integer retentionRateFlag) {
		this.retentionRateFlag = retentionRateFlag;
	}
	public Integer getRegisterdCount() {
		return registerdCount;
	}
	public void setRegisterdCount(Integer registerdCount) {
		this.registerdCount = registerdCount;
	}
}
