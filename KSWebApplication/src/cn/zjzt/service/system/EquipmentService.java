package cn.zjzt.service.system;

import java.util.List;

import cn.zjzt.dao.system.EquipmentDao;
import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.system.Equipment;

public class EquipmentService {
	private EquipmentDao equipmentDao;
	public void setEquipmentDao(EquipmentDao equipmentDao) {
		this.equipmentDao = equipmentDao;
	}
	private ValidateInfo validateInfo;
	public void setValidateInfo(ValidateInfo validateInfo) {
		this.validateInfo = validateInfo;
	}
	/**
	 * ��ȡ���е��豸��Ϣ
	 * 
	 * @return
	 */
	public List<Equipment> getAllEquip() {
		return equipmentDao.queryAllEquip();
	}

	/**
	 * �������޸��豸��Ϣ
	 * 
	 * @param depart
	 * @return
	 */
	public ValidateInfo addOrUpdateEquip(Equipment equipment) {
		if (equipment.getId() > 0) {// ���¿�����Ϣ
			if(equipmentDao.updateEquip(equipment)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("���³ɹ���");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("����ʧ�ܣ�");
			}
		} else {
			if(equipmentDao.insertEquip(equipment)>0){
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
	 * �߼�ɾ���豸��Ϣ
	 * @param departId
	 * @return
	 */
	public boolean deleteEquip(String equipId) {
		return equipmentDao.deleteEquip(equipId) > 0 ? true : false;
	}
	/**
	 * ��ȡ�豸��Ϣ
	 * @param departId
	 * @return
	 */
	public Equipment getEquipById(String equipId){
		return equipmentDao.queryEquipById(equipId);
	}
}
