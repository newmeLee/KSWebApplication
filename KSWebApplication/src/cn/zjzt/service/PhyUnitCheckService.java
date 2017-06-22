package cn.zjzt.service;

import java.util.List;

import cn.zjzt.dao.PhyUnitCheckDao;
import cn.zjzt.entity.PhyAbnoResult;
import cn.zjzt.entity.PhyAcceptUnit;
import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.ViewPhyCheckMaster;

public class PhyUnitCheckService {
	private PhyUnitCheckDao unitCheckDao;

	public void setUnitCheckDao(PhyUnitCheckDao unitCheckDao) {
		this.unitCheckDao = unitCheckDao;
	}

	private ValidateInfo validateInfo;

	public void setValidateInfo(ValidateInfo validateInfo) {
		this.validateInfo = validateInfo;
	}

	/**
	 * 获取该单位下的人员体检信息
	 * 
	 * @param unitID
	 * @return
	 */
	public List<ViewPhyCheckMaster> getUnitEmplInfo(String unitID) {
		return unitCheckDao.queryUnitMaster(unitID);
	}

	/**
	 * 获取指定时间段内的该单位下的人员体检信息
	 * 
	 * @param unitID
	 * @return
	 */
	public List<ViewPhyCheckMaster> getUnitEmplInfo(String unitID,
			String startDate, String endDate) {
		List<ViewPhyCheckMaster> list = unitCheckDao.queryUnitMaster(unitID,
				startDate, endDate);
		List<PhyAbnoResult> abnoResult = unitCheckDao.getAbnoResult(unitID,
				startDate, endDate);
		for (ViewPhyCheckMaster master : list) {
			String masterAbno="";
			for (int i=0;i<abnoResult.size();i++) {
				if(master.getRegID().equals(abnoResult.get(i).getRegID())){
					masterAbno+=abnoResult.get(i).getDiagnose()+"<br>";
				}
			}
			master.setAbnoResult(masterAbno);
		}
		return list;
	}

	/**
	 * 获取单位信息
	 * 
	 * @param unitID
	 * @return
	 */
	public PhyAcceptUnit getUnitInfo(String unitID) {
		return unitCheckDao.queryUnitInfo(unitID);
	}

	/**
	 * 验证单位查询的输入信息完整性
	 * 
	 * @param unitID
	 * @param password
	 * @return
	 */
	public ValidateInfo validateUnitInfo(String unitID, String password) {
		if (unitCheckDao.queryUnitID(unitID) < 1) {
			validateInfo.setStatus(0);
			validateInfo.setMessage("不存在该单位，请检查单位编号是否正确！");
			return validateInfo;
		} else if (password.equals(unitCheckDao.queryUnitInfo(unitID)
				.getPhone())) {
			// 如果查询密码和单位联系电话相同则视为密码正确
			validateInfo.setStatus(2);
			validateInfo.setMessage("查询信息验证正确！");
			return validateInfo;
		} else {
			validateInfo.setStatus(1);
			validateInfo.setMessage("密码错误！");
			return validateInfo;
		}
	}
	/**
	 * 根据关键字查找单位列表
	 * @param keyword
	 * @return
	 */
	public List<PhyAcceptUnit> getUnitListByKeyword(String keyword){
		return unitCheckDao.getUnitInfo(keyword);
	}
	/**
	 * 根据单位编号查找单位的所有信息
	 * @param code
	 * @return
	 */
	public PhyAcceptUnit getUnitInfoByCode(String code){
		return unitCheckDao.getUnitInfoByCode(code);
	}
}
