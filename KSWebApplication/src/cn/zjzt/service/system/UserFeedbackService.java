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
	 * 获取多有的
	 * 
	 * @return
	 */
	public List<UserFeedback> getAllFeedback() {
		return feedBackDao.getAllFeedback();
	}
	/**
	 * 新增一条
	 * @param userFeedback
	 * @return
	 */
	public ValidateInfo addUserFeedback(UserFeedback userFeedback) {
		if(feedBackDao.insertFeedBack(userFeedback)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("提交成功！");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("提交失败！");
		}
		return validateInfo;
	}
	/**
	 * 回复反馈信息
	 * @param id
	 * @param response
	 * @param resDate
	 * @return
	 */
	public ValidateInfo replyFeedbcak(String id,String response,String resDate){
		if(feedBackDao.updateResponse(id, response, resDate)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("回复成功");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("回复失败");
		}
		return validateInfo;
	}
	/**
	 * 删除用户反馈
	 * @param id
	 * @return
	 */
	public boolean deleteFeedback(String id){
		return feedBackDao.deleteFeedback(id)>0?true:false;
	}
}
