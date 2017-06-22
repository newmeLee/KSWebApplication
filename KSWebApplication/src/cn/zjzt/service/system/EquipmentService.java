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
	 * 获取所有的设备信息
	 * 
	 * @return
	 */
	public List<Equipment> getAllEquip() {
		return equipmentDao.queryAllEquip();
	}

	/**
	 * 新增或修改设备信息
	 * 
	 * @param depart
	 * @return
	 */
	public ValidateInfo addOrUpdateEquip(Equipment equipment) {
		if (equipment.getId() > 0) {// 更新科室信息
			if(equipmentDao.updateEquip(equipment)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("更新成功！");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("更新失败！");
			}
		} else {
			if(equipmentDao.insertEquip(equipment)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("新增成功！");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("新增失败！");
			}
		}
		return validateInfo;
	}

	/**
	 * 逻辑删除设备信息
	 * @param departId
	 * @return
	 */
	public boolean deleteEquip(String equipId) {
		return equipmentDao.deleteEquip(equipId) > 0 ? true : false;
	}
	/**
	 * 获取设备信息
	 * @param departId
	 * @return
	 */
	public Equipment getEquipById(String equipId){
		return equipmentDao.queryEquipById(equipId);
	}
}
