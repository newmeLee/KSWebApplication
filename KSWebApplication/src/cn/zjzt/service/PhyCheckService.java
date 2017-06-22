package cn.zjzt.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.zjzt.dao.PhyCheckDao;
import cn.zjzt.entity.PhyCheckUpList;
import cn.zjzt.entity.ViewPhyCheckMaster;
import cn.zjzt.entity.ViewPhyCheckResult;

public class PhyCheckService {
	private PhyCheckDao phyCheckDao;

	public void setPhyCheckDao(PhyCheckDao phyCheckDao) {
		this.phyCheckDao = phyCheckDao;
	}

	//
	private String phyID = null;
	private static final String COLUMN_NAME_PHYID = "PhyID";
	private static final String COLUMN_NAME_IDCARD = "IDCard";
	private static final String COLUMN_NAME_FCSTATUS = "FCStatus";
	private static final String FCSTATUS_SUCCESS = "���ܼ�";

	/**
	 * ��ȡ�û��ļ����
	 * 
	 * @param phyID
	 * @return
	 */
	public List<PhyCheckUpList> getCheckListByRegID(String regID) {
		phyID = phyCheckDao.queryPropertyByRegID("PhyID", regID).toString();
		return phyCheckDao.queryCheckResult(phyID);
	}

	/**
	 * ������ͼ�е����еļ��������
	 * 
	 * @param phyID
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	public List<ViewPhyCheckResult> getAllViewResult(String regID) {
		List<ViewPhyCheckResult> resultList = phyCheckDao
				.queryAllViewCheckResult(regID);
		// ��ȡ��һ�����ĵǼǺźͽ��
		if (getLastRegID(regID) != null) {
			List<ViewPhyCheckResult> lastResultList = phyCheckDao
					.queryLastCheckResult(getLastRegID(regID));
			for (int i = 0; i < resultList.size(); i++) {
				for (int j = 0; j < lastResultList.size(); j++) {
					if (resultList.get(i).getTariffID()
							.equals(lastResultList.get(j).getTariffID())) {
						resultList.get(i).setLastChkResult(
								lastResultList.get(j).getChkResult());
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * ��ѯ������а����Ŀ���
	 * 
	 * @param phyID
	 * @return
	 */
	// public List<ViewPhyCheckResult> getDeptViewResult(String regID) {
	// return phyCheckDao.queryDeptViewCheckResult(regID);
	// }

	/**
	 * ������Ŀ��Ͻ��
	 * 
	 * @param phyID
	 * @return
	 */
	// public List<ViewPhyCheckResult> getGroupViewResult(String regID) {
	// return phyCheckDao.queryGroupViewCheckResult(regID);
	// }

	/**
	 * ��ȡ�û�����������¼
	 * 
	 * @param phyID
	 * @return
	 */
	public List<ViewPhyCheckMaster> getUserPhyRecords(String regID) {
		phyID = phyCheckDao.queryPropertyByRegID(COLUMN_NAME_PHYID, regID)
				.toString();
		String IDCard = phyCheckDao.queryPropertyByRegID(COLUMN_NAME_IDCARD,
				regID).toString();
		//return phyCheckDao.queryPhyResultRecords(phyID);
		return phyCheckDao.queryPhyResultRecordsByIDCard(IDCard);
	}

	/**
	 * �ж��û��Ƿ����ܼ�
	 * 
	 * @return
	 */
	public boolean getFCStatus(String regID) {
		String fcStatus = phyCheckDao.queryPropertyByRegID(
				COLUMN_NAME_FCSTATUS, regID).toString();
		return fcStatus.equals(FCSTATUS_SUCCESS) ? true : false;
	}

	/**
	 * �������֤�Ż�ȡ���µĵǼǺ�
	 * 
	 * @param IDCard
	 * @return
	 */
	public String getCurrentRegIDByIDCard(String IDCard) {
		return phyCheckDao.queryRegIDByIDCard(IDCard).size() > 0 ? phyCheckDao
				.queryRegIDByIDCard(IDCard).get(0).getRegID() : null;
	}

	/**
	 * ��������ֶλ�ȡ��������ǼǺ�
	 * 
	 * @param columnName
	 * @param value
	 * @return
	 */
	public String getCurrentRegIDByProp(String columnName, String value) {
		return phyCheckDao.queryRegIDByProp(columnName, value).size() > 0 ? phyCheckDao
				.queryRegIDByProp(columnName, value).get(0).getRegID()
				: null;
	}

	/**
	 * �������֤�Ż�ȡ��һ�ε����ǼǺ�
	 * 
	 * @param IDCard
	 * @return
	 */
	public String getLastRegID(String regID) {
		// 1.���ݵ�ǰ�ǼǺŻ�ȡ���֤��
		String IDCard = phyCheckDao.queryPropertyByRegID("IDCard", regID)
				.toString();
		// 2.�������֤�Ż�ȡ���е����ǼǺ�
		List<ViewPhyCheckMaster> list = phyCheckDao.queryRegIDByIDCard(IDCard);
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRegID().equals(regID)) {
				index = i;
				break;
			}
		}
		// MyPrintUtil.printError("����б�" + list.size());
		// MyPrintUtil.printError(index);
		// 4.��ȡǰһ�εĵǼǺ�
		return (index > -1) && (index < list.size() - 1) ? list.get(index + 1)
				.getRegID() : null;
	}

	/**
	 * �ӽ�����л�ȡ������Ϣ
	 * 
	 * @param list
	 * @return
	 */
	public List<ViewPhyCheckResult> getDeptOrGroupListResult(String columnName,
			List<ViewPhyCheckResult> list) {
		List<ViewPhyCheckResult> newList = new ArrayList<ViewPhyCheckResult>();
		if (list.size() > 0) {
			newList.add(list.get(0));
			for (int i = 1; i < list.size(); i++) {
				if (columnName.equals("deptID")) {
					if (ifNotExistResult(columnName, list.get(i).getDeptID(),
							newList)) {
						newList.add(list.get(i));
					}
				} else if (columnName.equals("groupID")) {
					if (ifNotExistResult(columnName, list.get(i).getGroupID(),
							newList)) {
						newList.add(list.get(i));
					}
				}
			}
		}
		return newList;
	}

	/**
	 * �ж��Ƿ��Ѿ�����
	 * 
	 * @param value
	 * @param list
	 * @return
	 */
	public boolean ifNotExistResult(String columnName, String value,
			List<ViewPhyCheckResult> list) {
		boolean flag = true;
		for (ViewPhyCheckResult viewPhyCheckResult : list) {
			if (columnName.equals("deptID")) {// ����ǶԿ��ҽ���ȥ��
				if (viewPhyCheckResult.getDeptID().equals(value)) {
					flag = false;
					break;
				}
			} else if (columnName.equals("groupID")) {
				if (viewPhyCheckResult.getGroupID().equals(value)) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * ��������ģ����ѯ
	 * 
	 * @param name
	 * @return
	 * @throws ParseException
	 */
	public List<ViewPhyCheckMaster> getMasterByName(String name)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<ViewPhyCheckMaster> masterList = phyCheckDao.getMasterByName(name);
		for (int i = 0; i < masterList.size(); i++) {
			masterList.get(i).setPhyDate(
					dateFormat.parse((masterList.get(i).getPhyDate())
							.toString()));
		}
		return masterList;
	}
}
