package cn.zjzt.service.system;

import java.util.List;

import cn.zjzt.dao.system.UserFeedBackDao;
import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.system.UserFeedback;

public class UserFeedbackService {
	private UserFeedBackDao feedBackDao;
	private ValidateInfo validateInfo;
	public void setFeedBackDao(UserFeedBackDao feedBackDao) {
		this.feedBackDao = feedBackDao;
	}
	public void setValidateInfo(ValidateInfo validateInfo) {
		this.validateInfo = validateInfo;
	}

	/**
	 * ��ȡ���е�
	 * 
	 * @return
	 */
	public List<UserFeedback> getAllFeedback() {
		return feedBackDao.getAllFeedback();
	}
	/**
	 * ����һ��
	 * @param userFeedback
	 * @return
	 */
	public ValidateInfo addUserFeedback(UserFeedback userFeedback) {
		if(feedBackDao.insertFeedBack(userFeedback)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("�ύ�ɹ���");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("�ύʧ�ܣ�");
		}
		return validateInfo;
	}
	/**
	 * �ظ�������Ϣ
	 * @param id
	 * @param response
	 * @param resDate
	 * @return
	 */
	public ValidateInfo replyFeedbcak(String id,String response,String resDate){
		if(feedBackDao.updateResponse(id, response, resDate)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("�ظ��ɹ�");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("�ظ�ʧ��");
		}
		return validateInfo;
	}
	/**
	 * ɾ���û�����
	 * @param id
	 * @return
	 */
	public boolean deleteFeedback(String id){
		return feedBackDao.deleteFeedback(id)>0?true:false;
	}
}
