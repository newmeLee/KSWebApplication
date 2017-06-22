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
	 * ��ȡ�û���Ϣ
	 * 
	 * @param phyId
	 * @return
	 */
	public ViewPhyCheckMaster qureyPatient(String regID) {
		return patientDao.queryPatientById(regID);
	}

	/**
	 * ��֤�û���ѯ��Ϣ�Ƿ���ȷ
	 * 
	 * @param phyID
	 * @param IDcard
	 * @return
	 */
	public ValidateInfo validateCheckInfo(String IDCard, String password) {
		if (patientDao.queryIDCard(IDCard) < 1) {
			validateInfo.setStatus(0);
			validateInfo.setMessage("�����ڸ����֤�ţ�");
			return validateInfo;
		} else {
			String regID = patientDao.getCurrentRegIDByIDCard(IDCard);
			return checkByPassword(regID, password);
		}
	}

	/**
	 * ������֤
	 * 
	 * @param regID
	 * @param password
	 * @return
	 */
	public  ValidateInfo checkByPassword(String regID, String password) {
		// ����û��Ѿ��������ѯ��
		if (password.equals(userPasswordDao.queryPassword(regID))) {
			validateInfo.setStatus(2);
			validateInfo.setMessage("��ѯ��Ϣ��֤��ȷ��");
		} else {
			validateInfo.setStatus(3);
			validateInfo.setMessage("��ѯ�������");
		}
		return validateInfo;
	}

	
	
	/**
	 * �޸�����
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
			validateInfo.setMessage("ԭ�������");
			return validateInfo;
		} else {
			if (userPasswordDao.updateUserPassword(regID, newPassword) > 0) {
				validateInfo.setStatus(200);
				validateInfo.setMessage("�����޸ĳɹ���");
				return validateInfo;
			} else {
				validateInfo.setStatus(500);
				validateInfo.setMessage("�����޸�ʧ�ܣ�");
				return validateInfo;
			}
		}
	}
	/**
	 * �޸ĵ�λ��ѯ������(��ϵ�绰)
	 * @param unitCode
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public ValidateInfo updateUnitPassword(String unitCode,String oldPassword,String newPassword){
		if(!oldPassword.equals(unitCheckDao.queryUnitInfo(unitCode).getPhone())){
			validateInfo.setStatus(100);
			validateInfo.setMessage("ԭ�������");
			return validateInfo;
		}else {
			if (userPasswordDao.updateUnitPassword(unitCode, newPassword) > 0) {
				validateInfo.setStatus(200);
				validateInfo.setMessage("�����޸ĳɹ���");
				return validateInfo;
			} else {
				validateInfo.setStatus(500);
				validateInfo.setMessage("�����޸�ʧ�ܣ�");
				return validateInfo;
			}
		}
	}
	/**
	 * �Ƿ����ָ�������֤��
	 * 
	 * @param IDCard
	 * @return
	 */
	public boolean ifExistIDCard(String IDCard) {
		return patientDao.queryIDCard(IDCard) > 0 ? true : false;
	}
	/**
	 * �ж�ĳһ���ֶε�ֵ�Ƿ����
	 * @param columnName
	 * @param value
	 * @return
	 */
	public ValidateInfo ifExistProperty(String columnName,String value){
		if( patientDao.queryProperty(columnName, value)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("��Ϣ��ȷ��");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("��Ϣ����");
		}
		return validateInfo;
	}
	/**
	 * ��֤��Ϣ��֤���֤����
	 * @param IDCard
	 * @return
	 */
	public ValidateInfo validateIDCard(String IDCard){
		if(patientDao.queryIDCard(IDCard)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("���֤��ȷ��");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("���֤�����ڣ�");
		}
		return validateInfo;
	}
	/**
	 * ��ȡ�û�������
	 * @param regID
	 * @return
	 */
	public String getUserPassword(String regID){
		return userPasswordDao.queryPassword(regID);
	}
	/**
	 * ��֤�Ƿ�������ǼǺ�
	 * @param regID
	 * @return
	 */
	public ValidateInfo ifExistRegID(String regID){
		if(patientDao.queryRegID(regID)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("���ǼǺ���ȷ");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("���ǼǺŲ�����");
		}
		return validateInfo;
	}
}
