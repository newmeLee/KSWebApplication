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
	private static final String FCSTATUS_SUCCESS = "已总检";

	/**
	 * 获取用户的检查结果
	 * 
	 * @param phyID
	 * @return
	 */
	public List<PhyCheckUpList> getCheckListByRegID(String regID) {
		phyID = phyCheckDao.queryPropertyByRegID("PhyID", regID).toString();
		return phyCheckDao.queryCheckResult(phyID);
	}

	/**
	 * 返回视图中的所有的检查结果数据
	 * 
	 * @param phyID
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	public List<ViewPhyCheckResult> getAllViewResult(String regID) {
		List<ViewPhyCheckResult> resultList = phyCheckDao
				.queryAllViewCheckResult(regID);
		// 获取上一次体检的登记号和结果
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
	 * 查询检查结果中包含的科室
	 * 
	 * @param phyID
	 * @return
	 */
	// public List<ViewPhyCheckResult> getDeptViewResult(String regID) {
	// return phyCheckDao.queryDeptViewCheckResult(regID);
	// }

	/**
	 * 查找项目组合结果
	 * 
	 * @param phyID
	 * @return
	 */
	// public List<ViewPhyCheckResult> getGroupViewResult(String regID) {
	// return phyCheckDao.queryGroupViewCheckResult(regID);
	// }

	/**
	 * 获取用户的体检次数记录
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
	 * 判断用户是否已总检
	 * 
	 * @return
	 */
	public boolean getFCStatus(String regID) {
		String fcStatus = phyCheckDao.queryPropertyByRegID(
				COLUMN_NAME_FCSTATUS, regID).toString();
		return fcStatus.equals(FCSTATUS_SUCCESS) ? true : false;
	}

	/**
	 * 根据身份证号获取最新的登记号
	 * 
	 * @param IDCard
	 * @return
	 */
	public String getCurrentRegIDByIDCard(String IDCard) {
		return phyCheckDao.queryRegIDByIDCard(IDCard).size() > 0 ? phyCheckDao
				.queryRegIDByIDCard(IDCard).get(0).getRegID() : null;
	}

	/**
	 * 根据相关字段获取最近的体检登记号
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
	 * 根据身份证号获取上一次的体检登记号
	 * 
	 * @param IDCard
	 * @return
	 */
	public String getLastRegID(String regID) {
		// 1.根据当前登记号获取身份证号
		String IDCard = phyCheckDao.queryPropertyByRegID("IDCard", regID)
				.toString();
		// 2.根据身份证号获取所有的体检登记号
		List<ViewPhyCheckMaster> list = phyCheckDao.queryRegIDByIDCard(IDCard);
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRegID().equals(regID)) {
				index = i;
				break;
			}
		}
		// MyPrintUtil.printError("结果列表" + list.size());
		// MyPrintUtil.printError(index);
		// 4.获取前一次的登记号
		return (index > -1) && (index < list.size() - 1) ? list.get(index + 1)
				.getRegID() : null;
	}

	/**
	 * 从结果集中获取科室信息
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
	 * 判断是否已经存在
	 * 
	 * @param value
	 * @param list
	 * @return
	 */
	public boolean ifNotExistResult(String columnName, String value,
			List<ViewPhyCheckResult> list) {
		boolean flag = true;
		for (ViewPhyCheckResult viewPhyCheckResult : list) {
			if (columnName.equals("deptID")) {// 如果是对科室进行去重
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
	 * 根据姓名模糊查询
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
