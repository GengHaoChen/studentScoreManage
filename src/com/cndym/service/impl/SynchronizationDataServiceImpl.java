package com.cndym.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.dao.pick.IDataInfoDao;
import com.cndym.dao.pick.IUserBettorsDao;
import com.cndym.dao.pick.IUserRegistrationConversionRateDao;
import com.cndym.dao.pick.IUserRegistrationInformationListDao;
import com.cndym.dao.put.IAccountLogDao;
import com.cndym.dao.put.IClientActiveDao;
import com.cndym.dao.put.IFillDao;
import com.cndym.dao.put.IMemberDao;
import com.cndym.dao.put.IMemberLoginHistoryDao;
import com.cndym.dao.put.IOrderDao;
import com.cndym.dao.put.IProgramsDao;
import com.cndym.dao.put.ITicketDao;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.data.pick.ProgramsPick;
import com.cndym.entity.data.pick.UserBettors;
import com.cndym.entity.data.pick.UserRegistrationConversionRate;
import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.service.ISynchronizationDataService;
import com.cndym.util.export.Utils;

@Service("synchronizationDataServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SynchronizationDataServiceImpl implements
		ISynchronizationDataService {
	private Logger logger = Logger.getLogger(getClass());

	@Resource
	private IMemberLoginHistoryDao memberLoginHistoryDao;
	@Resource
	private IFillDao fillDao;
	@Resource
	private IMemberDao memberDao;
	@Resource
	private ITicketDao ticketDao;
	@Resource
	private IProgramsDao programsDao;
	@Resource
	private IUserBettorsDao userBettorsDao;
	@Resource
	private IOrderDao orderDao;
	@Resource
	private IDataInfoDao dataInfoDao;
	@Resource
	private IClientActiveDao clientActiveDao;
	@Resource
	private IAccountLogDao accountLogDao;
	@Resource
	private IUserRegistrationConversionRateDao userRegistrationConversionRateDao;
	@Resource
	private IUserRegistrationInformationListDao userRegistrationInformationListDao;
	
	/** ע���û���¼ת���� */
	private static String INTERVAL = "2,3,4,5,6,7,10,15,20,25,30";

	// ��ԭʼ�����ݲ�ѯ�����뵽���ݿ���
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void getDataByOriginal(String start, String end) {
		// ������
		clientActiveDao.queryClientActive(start, end);
		logger.info("client active");
		// DAU
		memberLoginHistoryDao.queryMemberLoginHistoryList(start, end);
		logger.info("client login");
		// ע���û���
		memberDao.queryMemberList(start, end);
		logger.info("client register");
	}

	/**
	 * ���û���¼/Ͷע������
	 * @param start
	 * @param end
	 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void userMemberRetentionRate(String start, String end) {
		/** loginStatus/bettingStatus �м�����е���Ϣ */
		Integer loginStatus = 0;	
		Integer bettingStatus = 0;
		/** �����ʱ�ʶ(1:��¼ 2��Ͷע) */
		Integer retentionRateFlag = 1;
		/**ע���û�/��¼�û�(1:ע�� 2����¼)*/
		Integer isLoginFlag=1;
		//ע���û�
			// 2.���û���¼
			loginStatus = 1;
			bettingStatus = 0;
			retentionRateFlag = 1;
			userMemberOrLoginRate(start, end, loginStatus, bettingStatus, retentionRateFlag, isLoginFlag);
			
			// 2.���û�Ͷע
			loginStatus = 0;
			bettingStatus = 1;
			retentionRateFlag = 2;
			userMemberOrLoginRate(start, end, loginStatus, bettingStatus, retentionRateFlag, isLoginFlag);
			
		//��¼�û�
			//��¼
			 isLoginFlag=2;
			 
			 loginStatus = 1;
			 bettingStatus = 0;
			 retentionRateFlag = 1;
			 userMemberOrLoginRate(start, end, loginStatus, bettingStatus, retentionRateFlag, isLoginFlag);
		    //Ͷע
			 loginStatus = 0;
		     bettingStatus = 1;
			 retentionRateFlag = 2;
			 userMemberOrLoginRate(start, end, loginStatus, bettingStatus, retentionRateFlag, isLoginFlag);
	}
	
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateMemberNullRate(String start, String end) {
		//���ע��ʱ��
		List<UserRegistrationConversionRate> userRegistraList = userRegistrationConversionRateDao
				.queryUserRegistrationConversionRateList(0,0, 0);
		for (UserRegistrationConversionRate rate : userRegistraList) {
			String loginOrRegTime = Utils.formatDate2Str(
					rate.getRegistrationTime(), "yyyy-MM-dd");
			String endTime = Utils.addDateTime(Utils.getDate(loginOrRegTime),
					"d", +1);
			int dateInterval = Utils.dateInterval(start, loginOrRegTime) + 1;
			if (dateInterval >= 31) {
				continue;
			}
			if (dateInterval == 1) {
				continue;
			}
			Map<Integer,String> paramMap=new HashMap<Integer,String>();
			paramMap.put(2, "TWO_DAY");
			paramMap.put(3, "THREE_DAY");
			paramMap.put(4, "FOUR_DAY");
			paramMap.put(5, "FIVE_DAY");
			paramMap.put(6, "SIX_DAY");
			paramMap.put(7, "SEVEN_DAY");
			paramMap.put(10, "TEN_DAY");
			paramMap.put(15, "FIFTEEN_DAY");
			paramMap.put(20, "TWENTY_DAY");
			paramMap.put(25, "TWENTY_FIVE_DAY");
			paramMap.put(30, "THIRTY_DAY");
			if (INTERVAL.indexOf(dateInterval + "") != -1) {
				userRegistrationInformationListDao.updateUserRegList(loginOrRegTime, endTime, paramMap, dateInterval);
				paramMap.put(2, "TWO_DAY_RATE");
				paramMap.put(3, "THREE_DAY_RATE");
				paramMap.put(4, "FOUR_DAY_RATE");
				paramMap.put(5, "FIVE_DAY_RATE");
				paramMap.put(6, "SIX_DAY_RATE");
				paramMap.put(7, "SEVEN_DAY_RATE");
				paramMap.put(10, "TEN_DAY_RATE");
				paramMap.put(15, "FIFTEEN_DAY_RATE");
				paramMap.put(20, "TWENTY_DAY_RATE");
				paramMap.put(25, "TWENTY_FIVE_DAY_RATE");
				paramMap.put(30, "THIRTY_DAY_RATE");
				userRegistrationInformationListDao.updateUserRegList(loginOrRegTime, endTime, paramMap, dateInterval);
			}
		}
	}
	//ע���û�����¼�û�     ��¼/Ͷע������
	public void userMemberOrLoginRate(String start, String end, Integer loginStatus,
			Integer bettingStatus, Integer retentionRateFlag,
			Integer isLoginFlag) {
		// ���û�
		List<UserRegistrationConversionRate> userRegistraList = userRegistrationConversionRateDao
				.queryUserRegistrationConversionRateList(loginStatus,
						bettingStatus, isLoginFlag);
		logger.info("���û�/��¼�û�,��¼�м��������" + userRegistraList.size());
		for (UserRegistrationConversionRate rate : userRegistraList) {
			String loginOrRegTime = Utils.formatDate2Str(
					rate.getRegistrationTime(), "yyyy-MM-dd");
			String endTime = Utils.addDateTime(Utils.getDate(loginOrRegTime),
					"d", +1);
			int dateInterval = Utils.dateInterval(start, loginOrRegTime) + 1;
			if (dateInterval >= 31) {
				// ����¼״̬��Ϊ2
				rate.setLoginStatus(2);
				rate.setBettingStatus(2);
				userRegistrationConversionRateDao.update(rate);
				logger.info("ע��ʱ�䣺" + rate.getRegistrationTime()
						+ " 30��ͳ���Ѿ����,loginStatus�Ѿ��޸�Ϊ��2");
				continue;
			}
			if (dateInterval == 1) {
				logger.info("ע��ʱ�䣺" + rate.getRegistrationTime() + " ��ǰʱ�䣺"
						+ start + " û��ͳ��ʱ��");
				continue;
			}
			if (INTERVAL.indexOf(dateInterval + "") != -1) {
				// 3.��ѯ�����¼���ܵ��û���
				Map<String, Integer> memberSidCountMap = new HashMap<String, Integer>();
				Integer memberCount = 0;// ���ܵ�ע���û���
				List<Map<String, Object>> memberLoginListMap =null;
				if(isLoginFlag.equals(2)){
					memberLoginListMap= memberLoginHistoryDao
						.queryDistinctGroupBySId(loginOrRegTime, endTime);
				}
				if(isLoginFlag.equals(1)){
					memberLoginListMap= memberDao
						.queryDistinct(loginOrRegTime, endTime);
				}		
				if (memberLoginListMap != null && memberLoginListMap.size() > 0) {
					for (Map<String, Object> map : memberLoginListMap) {
						String sid = (String) map.get("SID");
						Integer loginUserCount = Integer.parseInt(map.get(
								"COUNT").toString());
						memberCount = memberCount + loginUserCount;
						memberSidCountMap.put(sid, loginUserCount);
					}
					logger.info("ע��ʱ��:" + loginOrRegTime + "�ܵ�ע���û���:"
							+ memberCount);
				}
				// 2.ȥƥ���¼�������Ϣ start��end֮��
				// ��¼/Ͷע���û���registrationTime��endTime���ʱ���ڵ�¼���û���
				List<Map<String, Object>> memberList = null;
				if(isLoginFlag.equals(1)){
					if (loginStatus.equals(1)) {
						memberList = memberDao.queryMemberLoginByTime(
								loginOrRegTime, endTime, start, end);
					}
					if (bettingStatus.equals(1)) {
						memberList = memberDao.queryMemberBettingByTime(
								loginOrRegTime, endTime, start, end);
					}
				}
				if(isLoginFlag.equals(2)){
					if (loginStatus.equals(1)) {
						memberList=memberLoginHistoryDao.queryMemberLoginByTime(loginOrRegTime, endTime, start, end);
					}
					if (bettingStatus.equals(1)) {
						memberList=memberLoginHistoryDao.queryMemberLoginBettingByTime(loginOrRegTime, endTime, start, end);
					}
				}
				logger.info("��ʼʱ��:" + start + " ����ʱ��:" + end + " ��¼���û�,��"
						+ memberList.size() + "����������ע��ʱ��:" + loginOrRegTime
						+ "����ע�� ");
				//��ͨ����
				Integer countIsNull=0;
				if(memberList.size()==0){
					countIsNull=1;
					if(isLoginFlag.equals(1)){
							memberList = memberDao.queryDistinct(loginOrRegTime, endTime);
					}
					if(isLoginFlag.equals(2)){
							memberList=memberLoginHistoryDao.queryDistinct(loginOrRegTime, endTime);
					}
				}
				if (memberList != null && memberList.size() > 0) {
					// ����������ͳ��userCode����
					Integer userCodeCountSum = userRegistrationRateSingleSave(
							loginOrRegTime, dateInterval, memberList,
							memberSidCountMap, retentionRateFlag,isLoginFlag,countIsNull);
					userRegistrationRateSave(loginOrRegTime, dateInterval,
							memberCount, userCodeCountSum, retentionRateFlag,isLoginFlag);

				}
				
				//��©�� �����ݿ⣻������ע��ʱ��
				//�õ���ǰʱ�䣻
				//����ʱ��6�죻
				//��һ�죻�ǿգ�������������ݣ�6�м�¼Ϊ0��
			}
		}
	}
	
	/**
	 * ��¼�û� ��¼/Ͷע������
	 * @param start
	 * @param end
	 * @param loginStatus
	 * @param bettingStatus
	 * @param retentionRateFlag
	 */
//	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
//	public void loginMemberRetentionRate(String start, String end,
//			Integer loginStatus, Integer bettingStatus,
//			Integer retentionRateFlag,Integer isLoginFlag){
//		//1.��ȡ��¼�û��м������
//		List<UserRegistrationConversionRate> userRegistraList = userRegistrationConversionRateDao
//				.queryUserRegistrationConversionRateList(loginStatus,
//						bettingStatus,isLoginFlag);
//		logger.info("��¼�û���¼�м��������" + userRegistraList.size());
//		for (UserRegistrationConversionRate rate : userRegistraList) {
//			String loginTime = Utils.formatDate2Str(
//					rate.getRegistrationTime(), "yyyy-MM-dd");
//			String endTime = Utils.addDateTime(Utils.getDate(loginTime),
//					"d", +1);
//			int dateInterval = Utils.dateInterval(start, loginTime) + 1;
//			if (dateInterval >= 31) {
//				// ����¼״̬��Ϊ2
//				rate.setLoginStatus(2);
//				rate.setBettingStatus(2);
//				userRegistrationConversionRateDao.update(rate);
//				logger.info("ע��ʱ�䣺" + rate.getRegistrationTime()
//						+ " 30��ͳ���Ѿ����,loginStatus�Ѿ��޸�Ϊ��2");
//				continue;
//			}
//			if (dateInterval == 1) {
//				logger.info("ע��ʱ�䣺" + rate.getRegistrationTime() + " ��ǰʱ�䣺"
//						+ start + " û��ͳ��ʱ��");
//				continue;
//			}
//			if (INTERVAL.indexOf(dateInterval + "") != -1) {
//				// 2.ȥƥ���¼�������Ϣ start��end֮��
//				// ��¼/Ͷע���û���registrationTime��endTime���ʱ���ڵ�¼���û���
//				
//				List<Map<String, Object>> memberList = null;
//				memberList=memberLoginHistoryDao.queryMemberLoginByTime(loginTime, endTime, start, endTime);
//				logger.info("��ʼʱ��:" + start + " ����ʱ��:" + end + " ��¼���û�,��"
//						+ memberList.size() + "����������ע��ʱ��:" + loginTime
//						+ "����ע�� ");
//				// 3.��ѯ�����¼���ܵ��û���
//				Map<String, Integer> memberSidCountMap = new HashMap<String, Integer>();
//				Integer memberCount = 0;// ���ܵ�ע���û���
//				List<Map<String, Object>> memberLoginListMap = memberLoginHistoryDao
//						.queryDistinctGroupBySId(loginTime, endTime);
//				if (memberLoginListMap != null && memberLoginListMap.size() > 0) {
//					for (Map<String, Object> map : memberLoginListMap) {
//						String sid = (String) map.get("SID");
//						Integer loginUserCount = Integer.parseInt(map.get(
//								"COUNT").toString());
//						memberCount = memberCount + loginUserCount;
//						memberSidCountMap.put(sid, loginUserCount);
//					}
//					logger.info("ע��ʱ��:" + loginTime + "�ܵ�ע���û���:"
//							+ memberCount);
//				}
//				if (memberList != null && memberList.size() > 0) {
//					// ����������ͳ��userCode����
//					Integer userCodeCountSum = userRegistrationRateSingleSave(
//							loginTime, dateInterval, memberLoginListMap,
//							memberSidCountMap, retentionRateFlag);
//					userRegistrationRateSave(loginTime, dateInterval,
//							memberCount, userCodeCountSum, retentionRateFlag);
//
//				}
//			}
//		}
//	}
	
	/**
	 * 
	 * @param start
	 *            ��ʼʱ��
	 * @param end
	 *            ����ʱ��
	 * @param loginStatus
	 *            ���û��м��������Ϣ(��¼��־)
	 * @param bettingStatus
	 *            ���û��м��������Ϣ(Ͷע��־)
	 * @param retentionRateFlag
	 *            0:��ͨ���ݣ�1��ͳ�ƺ������,������ͳ��
	 */
//	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
//	public void saveUserMemberRate(String start, String end,
//			Integer loginStatus, Integer bettingStatus,
//			Integer retentionRateFlag,Integer isLoginFlag) {
//		List<UserRegistrationConversionRate> userRegistraList = userRegistrationConversionRateDao
//				.queryUserRegistrationConversionRateList(loginStatus,
//							bettingStatus,isLoginFlag);
//			logger.info("���û���¼�м��������" + userRegistraList.size());
//		for (UserRegistrationConversionRate rate : userRegistraList) {
//			String registrationTime = Utils.formatDate2Str(
//					rate.getRegistrationTime(), "yyyy-MM-dd");
//			String endTime = Utils.addDateTime(Utils.getDate(registrationTime),
//					"d", +1);
//			int dateInterval = Utils.dateInterval(start, registrationTime) + 1;
//			if (dateInterval >= 31) {
//				// ����¼״̬��Ϊ2
//				rate.setLoginStatus(2);
//				rate.setBettingStatus(2);
//				userRegistrationConversionRateDao.update(rate);
//				logger.info("ע��ʱ�䣺" + rate.getRegistrationTime()
//						+ " 30��ͳ���Ѿ����,loginStatus�Ѿ��޸�Ϊ��2");
//				continue;
//			}
//			if (dateInterval == 1) {
//				logger.info("ע��ʱ�䣺" + rate.getRegistrationTime() + " ��ǰʱ�䣺"
//						+ start + " û��ͳ��ʱ��");
//				continue;
//			}
//			if (INTERVAL.indexOf(dateInterval + "") != -1) {
//				// 2.ȥƥ��ע��������Ϣ start��end֮��
//				// ��¼/Ͷע���û���registrationTime��endTime���ʱ����ע����û���
//				List<Map<String, Object>> memberList = null;
//				if (loginStatus.equals(1)) {
//					memberList = memberDao.queryMemberLoginByTime(
//							registrationTime, endTime, start, end);
//				}
//				if (bettingStatus.equals(1)) {
//					memberList = memberDao.queryMemberBettingByTime(
//							registrationTime, endTime, start, end);
//				}
//
//				logger.info("��ʼʱ��:" + start + " ����ʱ��:" + end + " ��¼���û�,��"
//						+ memberList.size() + "����������ע��ʱ��:" + registrationTime
//						+ "����ע�� ");
//				// 3.��ѯ����ע����ܵ��û���
//				Map<String, Integer> memberSidCountMap = new HashMap<String, Integer>();
//				Integer memberCount = 0;// ���ܵ�ע���û���
//				List<Map<String, Object>> memberListMap = memberDao
//						.queryDistinct(registrationTime, endTime);
//				if (memberListMap != null && memberListMap.size() > 0) {
//					for (Map<String, Object> map : memberListMap) {
//						String sid = (String) map.get("SID");
//						Integer registeredUserCount = Integer.parseInt(map.get(
//								"COUNT").toString());
//						memberCount = memberCount + registeredUserCount;
//						memberSidCountMap.put(sid, registeredUserCount);
//					}
//					logger.info("ע��ʱ��:" + registrationTime + "�ܵ�ע���û���:"
//							+ memberCount);
//				}
//				if (memberList != null && memberList.size() > 0) {
//					// ����������ͳ��userCode����
//					Integer userCodeCountSum = userRegistrationRateSingleSave(
//							registrationTime, dateInterval, memberListMap,
//							memberSidCountMap, retentionRateFlag);
//					userRegistrationRateSave(registrationTime, dateInterval,
//							memberCount, userCodeCountSum, retentionRateFlag);
//
//				}
//			}
//		}
//	}

	/**
	 * ע���û�����¼����������������
	 * 
	 * @param registrationTime
	 *            �м���ע��ʱ��
	 * @param dateInterval
	 *            ʱ�����ڣ�2��3��4��5��6��7��10��15��20��25��30������
	 * @param memberList
	 *            ȥƥ��ע��������Ϣ start��end֮��
	 *            ��¼���û���registrationTime��endTime���ʱ����ע����û���
	 *            ���õ����ǹ���sid�����ͳ�Ƽ���key:sid value��count
	 * @param memberSidCountMap
	 *            registrationTime��endTime���ʱ����ע����û�������sid����ͳ�Ƶ���Ϣ key:sid
	 *            value��count
	 * @param retentionRateFlag
	 *            0:��ͨ���ݣ�1��ͳ�ƺ������,������ͳ��
	 * @return ������registrationTime��endTime���ʱ���� �����ܸ��������ܵĵ�¼���û�����
	 */
	//countIsNull  : 0:��ͨ����  1����ѯ������Ϊ��
	public Integer userRegistrationRateSingleSave(String registrationTime,
			int dateInterval, List<Map<String, Object>> memberList,
			Map<String, Integer> memberSidCountMap, Integer retentionRateFlag,Integer isLoginFlag,Integer countIsNull) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("registrationTime", registrationTime);
		paramMap.put("isRegistrStatus", isLoginFlag);
		Integer userCodeCountSum = 0;
		for (Map<String, Object> member : memberList) {
			// ��������޸�
			String unionId = "";
			Integer sum = 0;
			if (Utils.isNotEmpty(member.get("SID"))) {
				unionId = (String) member.get("SID");
			}
			if (Utils.isNotEmpty(memberSidCountMap.get(unionId))) {
				sum = memberSidCountMap.get(unionId);
			}
			Integer userCodeCount = 0;
			if(countIsNull.equals(0)){
				if (Utils.isNotEmpty(member.get("COUNT"))) {
					userCodeCount = Integer
							.parseInt(member.get("COUNT").toString());
					userCodeCountSum = userCodeCountSum + userCodeCount;
				}
			}
			logger.info("����" + registrationTime + "ע����û�����" + dateInterval
					+ "���¼������" + unionId + " ��������¼/Ͷע���û�������" + userCodeCount
					+ " ��������������" + sum);
			paramMap.put("sid", unionId);
			paramMap.put("retentionRateFlag", retentionRateFlag);
			UserRegistrationInformationList userRegistrationSingle = userRegistrationInformationListDao
					.queryByQueryURIL(paramMap);
			if (userRegistrationSingle == null) {
				userRegistrationSingle = new UserRegistrationInformationList();
				userRegistrationSingle.setSid(unionId);
				userRegistrationSingle.setCreateTime(new Date());
				userRegistrationSingle.setRegistrationTime(Utils
						.getDate(registrationTime));
				userRegistrationSingle.setIsRegistrStatus(isLoginFlag);// ���û�
				userRegistrationSingle.setIsCount(0);// ��ͨ����
				userRegistrationSingle.setRetentionRateFlag(retentionRateFlag);// ��¼������
				setUril(dateInterval,userRegistrationSingle);
				userRegistrationSingle.setRegisterdCount(sum);// ���������ܵ�ע���û���
			}
			userRegistrationSingle = getColumnByKey(dateInterval, sum,
					userCodeCount, userRegistrationSingle);
			// �Ȳ���update
			// 3.�õ���Ϣд������
			userRegistrationInformationListDao
					.saveOrUpdate(userRegistrationSingle);
			logger.info("����Ϣд������ɹ�");
		}
		return userCodeCountSum;
	}

	private void setUril(Integer dateInterval,UserRegistrationInformationList uril) {
		switch (dateInterval) {
		case 3:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			break;
		case 4:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			break;
		case 5:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			break;
		case 6:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			break;
		case 7:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			break;
		case 10:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			break;
		case 15:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			uril.setTenDay(0);
			uril.setTenDayRate(0.00);
			break;
		case 20:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			uril.setTenDay(0);
			uril.setTenDayRate(0.00);
			uril.setFifteenDay(0);
			uril.setFifteenDayRate(0.00);
			break;
		case 25:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			uril.setTenDay(0);
			uril.setTenDayRate(0.00);
			uril.setFifteenDay(0);
			uril.setFifteenDayRate(0.00);
			uril.setTwentyDay(0);
			uril.setTwentyDayRate(0.00);
			break;
		case 30:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			uril.setTenDay(0);
			uril.setTenDayRate(0.00);
			uril.setFifteenDay(0);
			uril.setFifteenDayRate(0.00);
			uril.setTwentyDay(0);
			uril.setTwentyDayRate(0.00);
			uril.setThirtyDay(0);
			uril.setThirtyDayRate(0.00);
			break;
		}
	}

	/**
	 * ע���û�����½����������
	 * 
	 * @param registrationTime
	 *            �м���ע��ʱ��
	 * @param dateInterval
	 *            ʱ�����ڣ�2��3��4��5��6��7��10��15��20��25��30������
	 * @param memberCount
	 *            ���ܵ�ע���û���
	 * @param userCodeCountSum
	 *            ��registrationTime��endTime���ʱ���� �����������ܵĵ�¼���û�����
	 */
	public void userRegistrationRateSave(String registrationTime,
			int dateInterval, Integer memberCount, Integer userCodeCountSum,
			Integer retentionRateFlag,Integer isLoginFlag) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("registrationTime", registrationTime);
		paramMap.put("sid", "");
		paramMap.put("retentionRateFlag", retentionRateFlag);
		paramMap.put("isRegistrStatus", isLoginFlag);
		UserRegistrationInformationList userRegistrationSum = userRegistrationInformationListDao
				.queryByQueryURIL(paramMap);
		if (userRegistrationSum == null) {
			userRegistrationSum = new UserRegistrationInformationList();
			userRegistrationSum.setCreateTime(new Date());
			userRegistrationSum.setRegistrationTime(Utils
					.getDate(registrationTime));
			userRegistrationSum.setIsRegistrStatus(isLoginFlag);// ���û�
			userRegistrationSum.setIsCount(1);// ��ͨ����
			userRegistrationSum.setRetentionRateFlag(retentionRateFlag);// Ͷע������
			userRegistrationSum.setRegisterdCount(memberCount);// ���ܵ�ע���û���
			setUril(dateInterval,userRegistrationSum);
		}
		logger.info("����" + registrationTime + "ע����û�����" + dateInterval
				+ "�����Ͷע���û�������" + userCodeCountSum);
		userRegistrationSum = getColumnByKey(dateInterval, memberCount,
				userCodeCountSum, userRegistrationSum);
		// 3.�õ���Ϣд������
		userRegistrationInformationListDao.saveOrUpdate(userRegistrationSum);
		logger.info("��ͳ����Ϣд������ɹ�");

	}

	/** ���û� ��¼/Ͷע�м�� */
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveUserMemeber(String start, String end) {
		UserRegistrationConversionRate urcrRegistered = new UserRegistrationConversionRate();
		urcrRegistered.setLoginStatus(1);
		urcrRegistered.setBettingStatus(1);
		urcrRegistered.setRegistrationTime(Utils.getDate(start));
//		urcrRegistered.setEndTime(Utils.addDate(Utils.getDate(start), "d", -30));
		urcrRegistered.setCreateTime(new Date());
		urcrRegistered.setIsLoginFlag(1);
		userRegistrationConversionRateDao.save(urcrRegistered);
		logger.info("ע���û�  ��¼/Ͷע�м�� ���ɹ�");
		UserRegistrationConversionRate urcrLogin = new UserRegistrationConversionRate();
		urcrLogin.setLoginStatus(1);
		urcrLogin.setBettingStatus(1);
		urcrLogin.setRegistrationTime(Utils.getDate(start));
//		urcrLogin.setEndTime(Utils.addDate(Utils.getDate(start), "d", -30));
		urcrLogin.setCreateTime(new Date());
		urcrLogin.setIsLoginFlag(2);
		userRegistrationConversionRateDao.save(urcrLogin);
		logger.info("��½�û�  ��¼/Ͷע�м�� ���ɹ�");
	}

	/**
	 * 
	 * @param key
	 * @param userCodeCountMin
	 * @param userR
	 * @return
	 */
	public UserRegistrationInformationList getColumnByKey(Integer key,
			Integer sumMax, Integer userCodeCountMin,
			UserRegistrationInformationList userR) {
		switch (key) {
		case 2:
			userR.setTwoDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setTwoDayRate(0.00);
				break;
			}
			userR.setTwoDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 3:
			userR.setThreeDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setThreeDayRate(0.00);
				break;
			}
			userR.setThreeDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 4:
			userR.setFourDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setFourDayRate(0.00);
				break;
			}
			userR.setFourDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 5:
			userR.setFiveDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setFiveDayRate(0.00);
				break;
			}
			userR.setFiveDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 6:
			userR.setSixDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setSixDayRate(0.00);
				break;
			}
			userR.setSixDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 7:
			userR.setSevenDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setSevenDayRate(0.00);
				break;
			}
			userR.setSevenDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 10:
			userR.setTenDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setTenDayRate(0.00);
				break;
			}
			userR.setTenDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 15:
			userR.setFifteenDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setFifteenDayRate(0.00);
				break;
			}
			userR.setFifteenDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 20:
			userR.setTwentyDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setTwentyDayRate(0.00);
				break;
			}
			userR.setTwentyDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 25:
			userR.setTwentyFiveDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setTwentyFiveDayRate(0.00);
				break;
			}
			userR.setTwentyFiveDayRate(Utils.divideInteger(userCodeCountMin, sumMax,
					4) * 100);
			break;
		case 30:
			userR.setThirtyDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setThirtyDayRate(0.00);
				break;
			}
			userR.setThirtyDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		}
		return userR;
	}

	// ɾ�����������
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteDataInfo(String startDate, String endDate) {
		 // ɾ��������
		 clientActiveDao.deleteClientActive(startDate, endDate);
		 logger.info(" deleteClientActive");
		 // ɾ��DAU
		 memberLoginHistoryDao.deleteMemberLoginHistory(startDate, endDate);
		 logger.info(" deleteMemberLoginHistory");
		 // ɾ��ע���û�
		 memberDao.deleteMember(startDate, endDate);
		 logger.info(" deleteMember");
		 // ��ֵ�û���
		 fillDao.deleteFill(startDate, endDate);
		 logger.info(" deleteFill");
		 // ����ɾ��
		 accountLogDao.deleteAccountLog(startDate, endDate);
		 logger.info(" deleteAccountLog");
		 // �����м��ɾ��
		 programsDao.deletePrograms(startDate, endDate);
		 logger.info(" deletePrograms");
		 // Ͷע��ϸ��ɾ��
		 userBettorsDao.deleteUserBettors(startDate, endDate);
		 logger.info(" deleteUserBettors");
		 // ɾ�����������
		 dataInfoDao.deleteDataInfo(startDate,endDate);
		// ɾ�����ע���û���¼��Ϣ�м��
//		userRegistrationConversionRateDao.deleteUserRegRate(startDate, endDate);
		// ɾ�����û���¼ת������Ϣͳ�Ʊ�
//		userRegistrationInformationListDao
//				.deleteUserRegList(startDate, endDate);
		logger.info(" deleteDataInfo");
	}

	// ��½
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void queryDateLogin(String startDate, String endDate) {
		// ��ֵ�û���
		fillDao.queryFillList(startDate, endDate);
		// ����
		accountLogDao.queryAccountLog(startDate, endDate);
		// ����������е� ����--����½���� �����м��
		programsDao.queryAllDataByStatusLogin(startDate, endDate);
		// ��״̬ < 3�����ݣ�Ҳ���Ƿ���ȫ���ɹ�/���ֳɹ� ȫ����ѯ����
		List<ProgramsPick> programsList = programsDao.queryAllDataLogin(
				startDate, endDate);
		logger.info(" ����ȫ���ɹ�/���ֳɹ������� " + programsList.size());
		// �����ɹ�/���ֳɹ� ���� �ɹ�/���ֳɹ�
		List<UserBettors> lastList = new ArrayList<UserBettors>();
		if (programsList != null && programsList.size() > 0) {
			for (ProgramsPick ub : programsList) {
				// ��������
				Integer buyType = ub.getBuyType();
				if (buyType == 1) {
					// ����
					getDaiGou(lastList, ub);
				} else if (buyType == 2) {
					// ����
					getHM(lastList, ub);
				} else {
					// ׷��
					addNumber(lastList, ub);
				}
			}
			// ����ϸ����� ����ȫ���ɹ�/���ֳɹ� ����ȫ���ɹ�/���ֳɹ�
			logger.info(" �������Ľ��¼�뵽�����userBettors��    lastList.size():  "
					+ lastList.size());
			userBettorsDao.insertUserBettors(lastList);
		}
	}

	// ׷�ŵ�½����
	private void addNumber(List<UserBettors> lastList, ProgramsPick ub) {
		Integer buyType = ub.getBuyType();
		// �û�code
		String userCode = ub.getUserCode();
		String partnersCode = ub.getPartnersCode();
		// ��ִʱ��
		Date returnTime = ub.getReturnTime();
		// �ɹ����
		Integer orderStatus = ub.getOrderStatus();
		Double orderAmount = ub.getOrderAmount();
		// ׷�� ��ֱ������
		UserBettors betTemp = new UserBettors();
		// �û�Code
		betTemp.setUserCode(userCode);
		betTemp.setBuyType(buyType);
		betTemp.setOrderStatus(orderStatus);
		betTemp.setOrderAmount(orderAmount);
		betTemp.setReturnTime(returnTime);
		betTemp.setPartnersCode(partnersCode);
		betTemp.setRenGouSuccessAmount(orderAmount);
		lastList.add(betTemp);
	}

	/**
	 * �����ɹ��ʹ������ֳɹ�
	 * 
	 * @param lastList
	 * @param ub
	 */
	private void getDaiGou(List<UserBettors> lastList, ProgramsPick ub) {
		// �û�code
		String userCode = ub.getUserCode();
		String partnersCode = ub.getPartnersCode();
		// ��ִʱ��
		Date returnTime = ub.getReturnTime();
		Integer buyType = ub.getBuyType();
		// �ɹ����
		Integer orderStatus = ub.getOrderStatus();
		// ����ID
		String programsOrderId = ub.getProgramsOrderId();
		Double orderAmount = ub.getOrderAmount();
		UserBettors betTemp = new UserBettors();
		// �û�Code
		betTemp.setUserCode(userCode);
		betTemp.setBuyType(buyType);
		betTemp.setOrderStatus(orderStatus);
		betTemp.setOrderAmount(orderAmount);
		betTemp.setReturnTime(returnTime);
		betTemp.setPartnersCode(partnersCode);
		// ע��������û��д
		if (orderStatus == 1) {
			// ȫ���ɹ� ֱ�����
			betTemp.setRenGouSuccessAmount(orderAmount);
			lastList.add(betTemp);
		} else if (orderStatus == 2) {
			// ���ֳɹ� Ҫ��Ʊ��
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("programsOrderId", programsOrderId);
			Double renGouSuccessAmount = ticketDao
					.queryAmountByProgramsOrderId(paramMap);
			betTemp.setRenGouSuccessAmount(renGouSuccessAmount);
			lastList.add(betTemp);
		}
	}

	/**
	 * ����ɹ��ͺ��򲿷ֳɹ�
	 * 
	 * @param lastList
	 * @param ub
	 */
	private void getHM(List<UserBettors> lastList, ProgramsPick ub) {
		Integer buyType = ub.getBuyType();
		String partnersCode = ub.getPartnersCode();
		// ��ִʱ��
		Date returnTime = ub.getReturnTime();
		// �ɹ����
		Integer orderStatus = ub.getOrderStatus();
		// ����ID
		String programsOrderId = ub.getProgramsOrderId();
		// ����
		Double orderAmount = ub.getOrderAmount();
		if (orderStatus == 1) {
			// �鶩��������û��� �ɹ��� ��ѯ���� �ô� 1.ʱ�� 2.����id
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("programsOrderId", programsOrderId);
			List<UserBettors> userHMSuccesslist = orderDao
					.queryHMAllSuccessByProgramsOrderId(paramMap);
			for (UserBettors ubAfter : userHMSuccesslist) {
				ubAfter.setPartnersCode(partnersCode);
				ubAfter.setBuyType(buyType);
				ubAfter.setOrderStatus(orderStatus);
				ubAfter.setReturnTime(returnTime);
				ubAfter.setOrderAmount(orderAmount);
			}
			lastList.addAll(userHMSuccesslist);
		} else if (orderStatus == 2) {
			// ���򲿷ֳɹ�
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("programsOrderId", programsOrderId);
			List<UserBettors> userHMSuccesslist = orderDao
					.queryHMByProgramsOrderId(paramMap);
			// ��Ʊ��
			paramMap.put("ticketStatus", 3);
			Double successMoney = ticketDao
					.querySuccessTicketByProgramsOrderId(paramMap);
			for (UserBettors t : userHMSuccesslist) {
				Double renGou = t.getRenGouAmount();
				BigDecimal allUserAppu1 = new BigDecimal(renGou);
				BigDecimal allUserAppu2 = new BigDecimal(orderAmount);
				BigDecimal allUserAppu3 = allUserAppu1.divide(allUserAppu2, 2,
						BigDecimal.ROUND_DOWN);
				BigDecimal allUserAppu4 = new BigDecimal(successMoney);
				Double suceess = allUserAppu3.multiply(allUserAppu4)
						.doubleValue();
				t.setRenGouSuccessAmount(suceess);
				t.setRenGouAmount(renGou);
				t.setPartnersCode(partnersCode);
				t.setBuyType(buyType);
				t.setOrderStatus(orderStatus);
				t.setReturnTime(returnTime);
				t.setOrderAmount(orderAmount);
			}
			// ���û���
			lastList.addAll(userHMSuccesslist);
		}
	}

	// ע����������ؼ���
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void countDateReg(String startDate, String endDate) {
		try {
			Map<String, DataInfo> dataInfoMap = new HashMap<String, DataInfo>();
			List<Map<String, Object>> resultList = null;
			// dau
			resultList = memberLoginHistoryDao.queryDistinctGroupBySId(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "dau");
			// ������
			resultList = clientActiveDao.queryDistinct(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "jiHuo");
			// ע���û���
			resultList = memberDao.queryDistinct(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "registeredUserCount");
			// ��ֵ�û���
			resultList = fillDao.queryDistinctRegGroupBySid(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiUserCount");
			// �ܳ�ֵ���
			resultList = fillDao.queryAllMoneyReg(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiAllMoney");
			// ��ֵ���û�����
			resultList = fillDao.queryNewDistinctRegGroupBySid(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiNewUserCount");
			// ���û���ֵ����ǰ���ڣ�ע�ᵱ���ֹ24:00ǰ��ֵ�ɹ��Ľ��
			resultList = fillDao.queryNewMoneryReg(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiNewUserMonery");
			// Ͷע�û���
			resultList = userBettorsDao.queryDistinctRegGroupBySid(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuUserCount");
			// ��Ͷע���
			resultList = userBettorsDao.queryAllTouZhuMoneyReg(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuAllMoney");
			// Ͷע���û���
			resultList = userBettorsDao.queryTouZhuNewCountRegGroupBySid(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuNewUserCount");
			// ���û�Ͷע����ǰ���ڣ�ע�ᵱ���ֹ24:00ǰͶע�ɹ��Ľ��
			resultList = userBettorsDao.queryTouZhuNewMoneryCountReg(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuNewUserMonery");
			// ���𣺵�ǰ���ڣ����������ܽ��
			resultList = accountLogDao.queryGrantReg(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "grants");
			// �������ģ���ǰ���ڣ��������ĵ��ܽ��
			resultList = accountLogDao.queryGrantConsumeReg(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "grantsConsume");
			// ��DataInfo���뵽���ݿ�
			for (Entry<String, DataInfo> entry : dataInfoMap.entrySet()) {
				String sid = entry.getKey();
				DataInfo dataInfo = entry.getValue();
				// �ܳ�ֵ��� :chongZhiAllMoney ��ֵ�û���:chongZhiUserCount
				Double chongZhiAllMoney = dataInfo.getChongZhiAllMoney();
				Integer chongZhiUserCount = dataInfo.getChongZhiUserCount();
				// userAvgChongZhiMoney �˾���ֵ���ܳ�ֵ���/��ֵ�û���
				Double userAvgChongZhiMoney = Utils.divideDouble(
						chongZhiAllMoney, chongZhiUserCount, 2);
				// ���û���ֵ���:chongZhiNewUserMonery ��ֵ���û���:chongZhiNewUserCount
				Double chongZhiNewUserMonery = dataInfo
						.getChongZhiNewUserMonery();
				Integer chongZhiNewUserCount = dataInfo
						.getChongZhiNewUserCount();
				// chongZhiNewUserAvgMoney ���û��˾���ֵ�����û���ֵ���/��ֵ���û���
				Double chongZhiNewUserAvgMoney = Utils.divideDouble(
						chongZhiNewUserMonery, chongZhiNewUserCount, 2);
				// ��ֵ���û���:chongZhiNewUserCount ע���û���:registeredUserCount
				Integer registeredUserCount = dataInfo.getRegisteredUserCount();
				// chongZhiNewUserRate
				// ���û���ֵת���ʣ���ֵ���û���/ע���û���-------------------------
				Double chongZhiNewUserRate = Utils.divideInteger(
						chongZhiNewUserCount, registeredUserCount, 4);
				// Ͷע���û���:touZhuNewUserCount ע���û���:registeredUserCount
				// touZhuNewUserRate
				// ���û�Ͷעת���ʣ�Ͷע���û���/ע���û���-------------------------
				Integer touZhuNewUserCount = dataInfo.getTouZhuNewUserCount();
				Double touZhuNewUserRate = Utils.divideInteger(
						touZhuNewUserCount, registeredUserCount, 4);
				// ���û�Ͷע���:touZhuNewUserMonery Ͷע���û���:touZhuNewUserCount
				// touZhuNewUserArpu ���û�ͶעARPU�����û�Ͷע���/Ͷע���û���
				Double touZhuNewUserMonery = dataInfo.getTouZhuNewUserMonery();
				Double touZhuNewUserArpu = Utils.divideDouble(
						touZhuNewUserMonery, touZhuNewUserCount, 2);
				// ��Ͷע���:touZhuAllMoney Ͷע�û�����touZhuUserCount
				// touZhuAllUserArpu ȫ���û�ͶעARPU����Ͷע���/Ͷע�û���
				Double touZhuAllMoney = dataInfo.getTouZhuAllMoney();
				Integer touZhuUserCount = dataInfo.getTouZhuUserCount();
				Double touZhuAllUserArpu = Utils.divideDouble(touZhuAllMoney,
						touZhuUserCount, 2);
				dataInfo.setTouZhuAllUserArpu(touZhuAllUserArpu);
				dataInfo.setTouZhuNewUserArpu(touZhuNewUserArpu);
				dataInfo.setTouZhuNewUserRate(touZhuNewUserRate * 100);
				dataInfo.setChongZhiNewUserRate(chongZhiNewUserRate * 100);
				dataInfo.setChongZhiNewUserAvgMoney(chongZhiNewUserAvgMoney);
				dataInfo.setUserAvgChongZhiMoney(userAvgChongZhiMoney);
				dataInfo.setSid(sid);
				dataInfo.setCreateTime(Utils.getDate(endDate));
				dataInfo.setDataRecordTime(Utils.getDate(startDate));
				dataInfo.setSidStatus(2);
				dataInfo.setIsCount(0);
				dataInfoDao.save(dataInfo);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveDataCount(String startDate, String endDate) {
		try {
			Integer sidStatus = 1;
			Integer isCount = 1;
			List<DataInfo> allLoginList = dataInfoDao.queryCountListAll(
					startDate, endDate, sidStatus);
			// ��½�������ܼ���
			saveDataCount(startDate, endDate, sidStatus, isCount, allLoginList);
			sidStatus = 2;
			List<DataInfo> allRegList = dataInfoDao.queryCountListAll(
					startDate, endDate, sidStatus);
			saveDataCount(startDate, endDate, sidStatus, isCount, allRegList);
		} catch (Exception e) {
			logger.info("�������е�����ͳ��ʧ��", e);
		}
	}

	private void saveDataCount(String startDate, String endDate,
			Integer sidStatus, Integer isCount, List<DataInfo> allLoginList) {
		if (allLoginList != null && allLoginList.size() > 0) {
			for (DataInfo allLogin : allLoginList) {
				// DAU
				Integer dau = memberLoginHistoryDao.queryDistinctCount(
						startDate, endDate);
				// ��ֵ�û���
				Integer chongZhiUserCount = 0;
				// ���û���ֵ����
				Integer chongZhiNewUserCount = 0;
				// Ͷע�û���
				Integer touZhuUserCount = 0;
				// ���û�Ͷע����
				Integer touZhuNewUserCount = 0;
				if (sidStatus == 1) {
					// ��½
					chongZhiUserCount = fillDao.queryDistinctLoginCount(
							startDate, endDate);
					chongZhiNewUserCount = fillDao.queryNewDistinctLoginCount(
							startDate, endDate);
					touZhuUserCount = userBettorsDao.queryDistinctLoginCount(
							startDate, endDate);
					touZhuNewUserCount = userBettorsDao
							.queryTouZhuNewCountLoginCount(startDate, endDate);
				} else if (sidStatus == 2) {
					// ע��
					chongZhiUserCount = fillDao.queryDistinctRegCount(
							startDate, endDate);
					chongZhiNewUserCount = fillDao.queryNewDistinctRegCount(
							startDate, endDate);
					touZhuUserCount = userBettorsDao.queryDistinctRegCount(
							startDate, endDate);
					touZhuNewUserCount = userBettorsDao
							.queryTouZhuNewCountRegCount(startDate, endDate);
				}
				allLogin.setIsCount(isCount);
				allLogin.setSidStatus(sidStatus);
				allLogin.setCreateTime(Utils.getDate(endDate));
				// �˾���ֵ���ܳ�ֵ���/��ֵ�û���-------------------��� userAvgChongZhiMoney
				// chongZhiAllMoney / chongZhiUserCount
				Double chongZhiAllMoney = allLogin.getChongZhiAllMoney();
				Double userAvgChongZhiMoney = Utils.divideDouble(
						chongZhiAllMoney, chongZhiUserCount, 2);
				// ���û��˾���ֵ�����û���ֵ���/��ֵ���û���-------------------���
				// chongZhiNewUserAvgMoney
				// chongZhiNewUserMonery / chongZhiNewUserCount
				Double chongZhiNewUserMonery = allLogin
						.getChongZhiNewUserMonery();
				Double chongZhiNewUserAvgMoney = Utils.divideDouble(
						chongZhiNewUserMonery, chongZhiNewUserCount, 2);
				// ���û���ֵת���ʣ���ֵ���û���/ע���û���-------------------���
				// chongZhiNewUserRate
				// chongZhiNewUserCount/registeredUserCount
				Integer registeredUserCount = allLogin.getRegisteredUserCount();
				Double chongZhiNewUserRate = Utils.divideInteger(
						chongZhiNewUserCount, registeredUserCount, 4);
				// ���û�Ͷעת���ʣ�Ͷע���û���/ע���û���-------------------��� touZhuNewUserRate
				// touZhuNewUserCount/registeredUserCount
				Double touZhuNewUserRate = Utils.divideInteger(
						touZhuNewUserCount, registeredUserCount, 4);
				// ���û�ͶעARPU�����û�Ͷע���/Ͷע���û���-------------------���
				// touZhuNewUserArpu
				// touZhuNewUserMonery/touZhuNewUserCount
				Double touZhuNewUserMonery = allLogin.getTouZhuNewUserMonery();
				Double touZhuNewUserArpu = Utils.divideDouble(
						touZhuNewUserMonery, touZhuNewUserCount, 2);
				// ȫ���û�ͶעARPU����Ͷע���/Ͷע�û���-------------------��� touZhuAllUserArpu
				// touZhuAllMoney/touZhuUserCount
				Double touZhuAllMoney = allLogin.getTouZhuAllMoney();
				Double touZhuAllUserArpu = Utils.divideDouble(touZhuAllMoney,
						touZhuUserCount, 2);
				allLogin.setDau(dau);
				allLogin.setChongZhiUserCount(chongZhiUserCount);
				allLogin.setChongZhiNewUserCount(chongZhiNewUserCount);
				allLogin.setTouZhuNewUserCount(touZhuNewUserCount);
				allLogin.setTouZhuNewUserCount(touZhuNewUserCount);
				allLogin.setUserAvgChongZhiMoney(userAvgChongZhiMoney);
				allLogin.setChongZhiNewUserAvgMoney(chongZhiNewUserAvgMoney);
				allLogin.setChongZhiNewUserRate(chongZhiNewUserRate * 100);
				allLogin.setTouZhuNewUserRate(touZhuNewUserRate * 100);
				allLogin.setTouZhuNewUserArpu(touZhuNewUserArpu);
				allLogin.setTouZhuAllUserArpu(touZhuAllUserArpu);
				dataInfoDao.save(allLogin);
			}
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void countDateLogin(String startDate, String endDate) {
		try {
			Map<String, DataInfo> dataInfoMap = new HashMap<String, DataInfo>();
			List<Map<String, Object>> resultList = null;
			// dau
			resultList = memberLoginHistoryDao.queryDistinctGroupBySId(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "dau");
			// ������
			resultList = clientActiveDao.queryDistinct(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "jiHuo");
			// ע���û���
			resultList = memberDao.queryDistinct(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "registeredUserCount");
			// ��ֵ�û���
			resultList = fillDao.queryDistinctLoginGroupBySid(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiUserCount");
			// �ܳ�ֵ���
			resultList = fillDao.queryAllMoneyLogin(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiAllMoney");
			// ��ֵ���û�����
			resultList = fillDao.queryNewDistinctLoginGroupBySid(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiNewUserCount");
			// ���û���ֵ����ǰ���ڣ�ע�ᵱ���ֹ24:00ǰ��ֵ�ɹ��Ľ��
			resultList = fillDao.queryNewMoneryLogin(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiNewUserMonery");
			// Ͷע�û���
			resultList = userBettorsDao.queryDistinctLoginGroupBySId(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuUserCount");
			// ��Ͷע���
			resultList = userBettorsDao.queryAllTouZhuMoneyLogin(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuAllMoney");
			// Ͷע���û���
			resultList = userBettorsDao.queryTouZhuNewCountLoginGroupBySid(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuNewUserCount");
			// ���û�Ͷע����ǰ���ڣ�ע�ᵱ���ֹ24:00ǰͶע�ɹ��Ľ��
			resultList = userBettorsDao.queryTouZhuNewMoneryCountLogin(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuNewUserMonery");
			// ���𣺵�ǰ���ڣ����������ܽ��
			resultList = accountLogDao.queryGrantLogin(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "grants");
			// �������ģ���ǰ���ڣ��������ĵ��ܽ��
			resultList = accountLogDao.queryGrantConsumeLogin(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "grantsConsume");
			logger.info("¼�뵽dataInfo������Ϊ��dataInfoMap.entrySet().size()��"
					+ dataInfoMap.entrySet().size());
			// ��DataInfo���뵽���ݿ�
			for (Entry<String, DataInfo> entry : dataInfoMap.entrySet()) {
				String sid = entry.getKey();
				DataInfo dataInfo = entry.getValue();
				// �ܳ�ֵ��� :chongZhiAllMoney ��ֵ�û���:chongZhiUserCount
				Double chongZhiAllMoney = dataInfo.getChongZhiAllMoney();
				Integer chongZhiUserCount = dataInfo.getChongZhiUserCount();
				// userAvgChongZhiMoney �˾���ֵ���ܳ�ֵ���/��ֵ�û���
				Double userAvgChongZhiMoney = Utils.divideDouble(
						chongZhiAllMoney, chongZhiUserCount, 2);
				// ���û���ֵ���:chongZhiNewUserMonery ��ֵ���û���:chongZhiNewUserCount
				Double chongZhiNewUserMonery = dataInfo
						.getChongZhiNewUserMonery();
				Integer chongZhiNewUserCount = dataInfo
						.getChongZhiNewUserCount();
				// chongZhiNewUserAvgMoney ���û��˾���ֵ�����û���ֵ���/��ֵ���û���
				Double chongZhiNewUserAvgMoney = Utils.divideDouble(
						chongZhiNewUserMonery, chongZhiNewUserCount, 2);
				// ��ֵ���û���:chongZhiNewUserCount ע���û���:registeredUserCount
				Integer registeredUserCount = dataInfo.getRegisteredUserCount();
				// chongZhiNewUserRate ���û���ֵת���ʣ���ֵ���û���/ע���û���
				Double chongZhiNewUserRate = Utils.divideInteger(
						chongZhiNewUserCount, registeredUserCount, 4);
				// Ͷע���û���:touZhuNewUserCount ע���û���:registeredUserCount
				// touZhuNewUserRate ���û�Ͷעת���ʣ�Ͷע���û���/ע���û���
				Integer touZhuNewUserCount = dataInfo.getTouZhuNewUserCount();
				Double touZhuNewUserRate = Utils.divideInteger(
						touZhuNewUserCount, registeredUserCount, 4);
				// ���û�Ͷע���:touZhuNewUserMonery Ͷע���û���:touZhuNewUserCount
				// touZhuNewUserArpu ���û�ͶעARPU�����û�Ͷע���/Ͷע���û���
				Double touZhuNewUserMonery = dataInfo.getTouZhuNewUserMonery();
				Double touZhuNewUserArpu = Utils.divideDouble(
						touZhuNewUserMonery, touZhuNewUserCount, 2);
				// ��Ͷע���:touZhuAllMoney Ͷע�û�����touZhuUserCount
				// touZhuAllUserArpu ȫ���û�ͶעARPU����Ͷע���/Ͷע�û���
				Double touZhuAllMoney = dataInfo.getTouZhuAllMoney();
				Integer touZhuUserCount = dataInfo.getTouZhuUserCount();
				Double touZhuAllUserArpu = Utils.divideDouble(touZhuAllMoney,
						touZhuUserCount, 2);
				dataInfo.setTouZhuAllUserArpu(touZhuAllUserArpu);
				dataInfo.setTouZhuNewUserArpu(touZhuNewUserArpu);
				dataInfo.setTouZhuNewUserRate(touZhuNewUserRate * 100);
				dataInfo.setChongZhiNewUserRate(chongZhiNewUserRate * 100);
				dataInfo.setChongZhiNewUserAvgMoney(chongZhiNewUserAvgMoney);
				dataInfo.setUserAvgChongZhiMoney(userAvgChongZhiMoney);
				dataInfo.setSid(sid);
				dataInfo.setCreateTime(Utils.getDate(endDate));
				dataInfo.setDataRecordTime(Utils.getDate(startDate));
				dataInfo.setSidStatus(1);
				dataInfo.setIsCount(0);
				dataInfoDao.save(dataInfo);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void dealList(List<Map<String, Object>> list,
			List<Map<String, Object>> insertData, String mapKey, String dataType) {
		if (list != null && list.size() > 0) {
			for (Map<String, Object> registered : list) {
				String sid = (String) registered.get("SID");
				boolean flag = false;
				for (Map<String, Object> insert : insertData) {
					if (insert.containsValue(sid)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					// �������
					for (Map<String, Object> insert : insertData) {
						if (insert.containsValue(sid)) {
							Object ob = registered.get("COUNT");
							if (dataType.equals("double")) {
								Double countDouble = Double.parseDouble(ob
										.toString());
								insert.put(mapKey, countDouble);
							} else if (dataType.equals("integer")) {
								Integer countInteger = Integer.parseInt(ob
										.toString());
								insert.put(mapKey, countInteger);
							}
							break;
						}
					}
				} else {
					// ���������
					Map<String, Object> tem = new HashMap<String, Object>();
					Object ob = registered.get("COUNT");
					if (dataType.equals("double")) {
						Double countDouble = Double.parseDouble(ob.toString());
						tem.put(mapKey, countDouble);
					} else {
						Integer countInteger = Integer.parseInt(ob.toString());
						tem.put(mapKey, countInteger);
					}
					tem.put("SID", sid);
					insertData.add(tem);
				}
			}
		}
	}

	public void setDataInfo(Map<String, DataInfo> dataInfoMap,
			List<Map<String, Object>> resultList, String columnName) {
		if (resultList == null || resultList.size() == 0) {
			logger.info("-----��" + columnName);
			return;
		}
		DataInfo dataInfo = null;
		for (Map<String, Object> result : resultList) {
			String sid = (String) result.get("SID");
			dataInfo = new DataInfo();
			if (Utils.isNotEmpty(dataInfoMap.get(sid))) {
				dataInfo = dataInfoMap.get(sid);
			}
			if ("dau".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setDau(Integer.parseInt(result.get("COUNT")
							.toString()));
				} else {
					dataInfo.setDau(0);
				}
			}
			if ("jiHuo".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setJiHuo(Integer.parseInt(result.get("COUNT")
							.toString()));
				} else {
					dataInfo.setJiHuo(0);
				}
			}
			if ("registeredUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setRegisteredUserCount(Integer.parseInt(result
							.get("COUNT").toString()));
				} else {
					dataInfo.setRegisteredUserCount(0);
				}
			}
			if ("chongZhiUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setChongZhiUserCount(Integer.parseInt(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setChongZhiUserCount(0);
				}
			}
			if ("chongZhiAllMoney".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setChongZhiAllMoney(Double.parseDouble(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setChongZhiAllMoney(0d);
				}
			}
			if ("chongZhiNewUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setChongZhiNewUserCount(Integer.parseInt(result
							.get("COUNT").toString()));
				} else {
					dataInfo.setChongZhiNewUserCount(0);
				}
			}
			if ("chongZhiNewUserMonery".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setChongZhiNewUserMonery(Double.parseDouble(result
							.get("COUNT").toString()));
				} else {
					dataInfo.setChongZhiNewUserMonery(0d);
				}
			}
			if ("touZhuUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setTouZhuUserCount(Integer.parseInt(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setTouZhuUserCount(0);
				}
			}
			if ("touZhuAllMoney".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setTouZhuAllMoney(Double.parseDouble(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setTouZhuAllMoney(0d);
				}
			}
			if ("touZhuNewUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setTouZhuNewUserCount(Integer.parseInt(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setTouZhuNewUserCount(0);
				}
			}
			if ("touZhuNewUserMonery".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setTouZhuNewUserMonery(Double.parseDouble(result
							.get("COUNT").toString()));
				} else {
					dataInfo.setTouZhuNewUserMonery(0d);
				}
			}
			if ("grants".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setGrants(Double.parseDouble(result.get("COUNT")
							.toString()));
				} else {
					dataInfo.setGrants(0d);
				}
			}
			if ("grantsConsume".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setGrantsConsume(Double.parseDouble(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setGrantsConsume(0d);
				}
			}
			dataInfoMap.put(sid, dataInfo);
		}
	}
}
