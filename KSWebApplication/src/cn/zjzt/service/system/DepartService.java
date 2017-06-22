package cn.zjzt.service.system;

import java.util.List;

import cn.zjzt.dao.system.DepartDao;
import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.system.Depart;

public class DepartService {
	private DepartDao departDao;
	private ValidateInfo validateInfo;
	public void setDepartDao(DepartDao departDao) {
		this.departDao = departDao;
	}
	
	public void setValidateInfo(ValidateInfo validateInfo) {
		this.validateInfo = validateInfo;
	}

	/**
	 * ��ȡ���еĿ�����Ϣ
	 * 
	 * @return
	 */
	public List<Depart> getAllDepart() {
		return departDao.queryAllDepart();
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param depart
	 * @return
	 */
	public ValidateInfo addOrUpdateDepart(Depart depart) {
		if (depart.getId() > 0) {// ���¿�����Ϣ
			if(departDao.updateDepart(depart)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("���³ɹ���");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("����ʧ�ܣ�");
			}
		} else {
			if(departDao.insertDepart(depart)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("�����ɹ���");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("����ʧ�ܣ�");
			}
		}
		return validateInfo;
	}

	/**
	 * 
	 * @param departId
	 * @return
	 */
	public boolean deleteDepart(String departId) {
		return departDao.deleteDepart(departId) > 0 ? true : false;
	}
	/**
	 * ��ȡ������Ϣ
	 * @param departId
	 * @return
	 */
	public Depart getDepartById(String departId){
		return departDao.queryDepartById(departId);
	}
}
