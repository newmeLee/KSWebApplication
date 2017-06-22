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
	 * 获取所有的科室信息
	 * 
	 * @return
	 */
	public List<Depart> getAllDepart() {
		return departDao.queryAllDepart();
	}

	/**
	 * 新增科室信息
	 * 
	 * @param depart
	 * @return
	 */
	public ValidateInfo addOrUpdateDepart(Depart depart) {
		if (depart.getId() > 0) {// 更新科室信息
			if(departDao.updateDepart(depart)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("更新成功！");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("更新失败！");
			}
		} else {
			if(departDao.insertDepart(depart)>0){
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
	 * 
	 * @param departId
	 * @return
	 */
	public boolean deleteDepart(String departId) {
		return departDao.deleteDepart(departId) > 0 ? true : false;
	}
	/**
	 * 获取科室信息
	 * @param departId
	 * @return
	 */
	public Depart getDepartById(String departId){
		return departDao.queryDepartById(departId);
	}
}
