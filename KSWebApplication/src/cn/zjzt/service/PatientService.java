package cn.zjzt.service;

import org.springframework.stereotype.Service;

import cn.zjzt.dao.PatientDao;
import cn.zjzt.dao.PhyUnitCheckDao;
import cn.zjzt.dao.UserPasswordDao;
import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.ViewPhyCheckMaster;

@Service
public class PatientService {

	private PatientDao patientDao;

	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	private ValidateInfo validateInfo;

	public void setValidateInfo(ValidateInfo validateInfo) {
		this.validateInfo = validateInfo;
	}

	private UserPasswordDao userPasswordDao;

	public void setUserPasswordDao(UserPasswordDao userPasswordDao) {
		this.userPasswordDao = userPasswordDao;
	}
	private PhyUnitCheckDao unitCheckDao;
	public void setUnitCheckDao(PhyUnitCheckDao unitCheckDao) {
		this.unitCheckDao = unitCheckDao;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param phyId
	 * @return
	 */
	public ViewPhyCheckMaster qureyPatient(String regID) {
		return patientDao.queryPatientById(regID);
	}

	/**
	 * 验证用户查询信息是否正确
	 * 
	 * @param phyID
	 * @param IDcard
	 * @return
	 */
	public ValidateInfo validateCheckInfo(String IDCard, String password) {
		if (patientDao.queryIDCard(IDCard) < 1) {
			validateInfo.setStatus(0);
			validateInfo.setMessage("不存在该身份证号！");
			return validateInfo;
		} else {
			String regID = patientDao.getCurrentRegIDByIDCard(IDCard);
			return checkByPassword(regID, password);
		}
	}

	/**
	 * 密码验证
	 * 
	 * @param regID
	 * @param password
	 * @return
	 */
	public  ValidateInfo checkByPassword(String regID, String password) {
		// 如果用户已经用密码查询过
		if (password.equals(userPasswordDao.queryPassword(regID))) {
			validateInfo.setStatus(2);
			validateInfo.setMessage("查询信息验证正确！");
		} else {
			validateInfo.setStatus(3);
			validateInfo.setMessage("查询密码错误！");
		}
		return validateInfo;
	}

	
	
	/**
	 * 修改密码
	 * 
	 * @param regID
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public ValidateInfo updatePassword(String regID, String oldPassword,
			String newPassword) {
		if (!oldPassword.equals(userPasswordDao.queryPassword(regID))) {
			validateInfo.setStatus(100);
			validateInfo.setMessage("原密码错误");
			return validateInfo;
		} else {
			if (userPasswordDao.updateUserPassword(regID, newPassword) > 0) {
				validateInfo.setStatus(200);
				validateInfo.setMessage("密码修改成功！");
				return validateInfo;
			} else {
				validateInfo.setStatus(500);
				validateInfo.setMessage("密码修改失败！");
				return validateInfo;
			}
		}
	}
	/**
	 * 修改单位查询的密码(联系电话)
	 * @param unitCode
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public ValidateInfo updateUnitPassword(String unitCode,String oldPassword,String newPassword){
		if(!oldPassword.equals(unitCheckDao.queryUnitInfo(unitCode).getPhone())){
			validateInfo.setStatus(100);
			validateInfo.setMessage("原密码错误");
			return validateInfo;
		}else {
			if (userPasswordDao.updateUnitPassword(unitCode, newPassword) > 0) {
				validateInfo.setStatus(200);
				validateInfo.setMessage("密码修改成功！");
				return validateInfo;
			} else {
				validateInfo.setStatus(500);
				validateInfo.setMessage("密码修改失败！");
				return validateInfo;
			}
		}
	}
	/**
	 * 是否存在指定的身份证号
	 * 
	 * @param IDCard
	 * @return
	 */
	public boolean ifExistIDCard(String IDCard) {
		return patientDao.queryIDCard(IDCard) > 0 ? true : false;
	}
	/**
	 * 判断某一个字段的值是否存在
	 * @param columnName
	 * @param value
	 * @return
	 */
	public ValidateInfo ifExistProperty(String columnName,String value){
		if( patientDao.queryProperty(columnName, value)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("信息正确！");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("信息错误！");
		}
		return validateInfo;
	}
	/**
	 * 验证信息验证身份证号码
	 * @param IDCard
	 * @return
	 */
	public ValidateInfo validateIDCard(String IDCard){
		if(patientDao.queryIDCard(IDCard)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("身份证正确！");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("身份证不存在！");
		}
		return validateInfo;
	}
	/**
	 * 获取用户的密码
	 * @param regID
	 * @return
	 */
	public String getUserPassword(String regID){
		return userPasswordDao.queryPassword(regID);
	}
	/**
	 * 验证是否存在体检登记号
	 * @param regID
	 * @return
	 */
	public ValidateInfo ifExistRegID(String regID){
		if(patientDao.queryRegID(regID)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("体检登记号正确");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("体检登记号不存在");
		}
		return validateInfo;
	}
}
