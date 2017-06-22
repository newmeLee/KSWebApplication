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
	 * ��ȡ���е�ר����Ϣ
	 * 
	 * @return
	 */
	public List<Expert> getAllExpert() {
		return expertDao.queryAllExpert();
	}
	/**
	 * ����һ��ר����Ϣ
	 * @param expert
	 * @return
	 */
	public ValidateInfo addOrUpdateExpert(Expert expert) {
		if(expert.getId()>0){//����Ǹ���������Ϣ
			if(expertDao.updateExpertInfo(expert)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("��Ϣ���³ɹ���");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("��Ϣ����ʧ�ܣ�");
			}
		}else{//�����������Ϣ
			if(expertDao.insertExpert(expert)>0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("��Ϣ���³ɹ���");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("��Ϣ����ʧ�ܣ�");
			}
		}
		return validateInfo;
	}
	/**
	 * ��ȡ�����ײ͵���Ϣ
	 * @param id
	 * @return
	 */
	public Expert getExpertByID(String id) {
		return expertDao.getExpertByID(id);
	}
	/**
	 * �߼�ɾ��ר����Ϣ
	 * @param expertID
	 * @return
	 */
	public boolean deleteExpert(String expertID){
		return expertDao.deleteExpertByID(expertID)>0?true:false;
	}
}
