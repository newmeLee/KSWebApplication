package cn.zjzt.service.system;

import java.util.List;

import cn.zjzt.dao.system.ExpertDao;
import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.system.Expert;

public class ExpertService {
	private ExpertDao expertDao;

	public void setExpertDao(ExpertDao expertDao) {
		this.expertDao = expertDao;
	}
	private ValidateInfo validateInfo;
	public void setValidateInfo(ValidateInfo validateInfo) {
		this.validateInfo = validateInfo;
	}
	/**
	 * 获取所有的专家信息
	 * 
	 * @return
	 */
	public List<Expert> getAllExpert() {
		return expertDao.queryAllExpert();
	}
	/**
	 * 增加一条专家信息
	 * @param expert
	 * @return
	 */
	public ValidateInfo addOrUpdateExpert(Expert expert) {
		if(expert.getId()>0){//如果是更新新闻信息
			if(expertDao.updateExpertInfo(expert)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("信息更新成功！");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("信息更新失败！");
			}
		}else{//如果是新增信息
			if(expertDao.insertExpert(expert)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("信息更新成功！");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("信息更新失败！");
			}
		}
		return validateInfo;
	}
	/**
	 * 获取单个套餐的信息
	 * @param id
	 * @return
	 */
	public Expert getExpertByID(String id) {
		return expertDao.getExpertByID(id);
	}
	/**
	 * 逻辑删除专家信息
	 * @param expertID
	 * @return
	 */
	public boolean deleteExpert(String expertID){
		return expertDao.deleteExpertByID(expertID)>0?true:false;
	}
}
