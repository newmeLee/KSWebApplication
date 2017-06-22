package cn.zjzt.action.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.system.UserFeedback;
import cn.zjzt.service.system.UserFeedbackService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserFeedbackAction extends ActionSupport {
	private String id;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String message;
	private String response;
	private ValidateInfo result;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ValidateInfo getResult() {
		return result;
	}

	public void setResult(ValidateInfo result) {
		this.result = result;
	}

	private UserFeedbackService userFeedbackService;
	private SimpleDateFormat dateFormat;

	public UserFeedbackAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		userFeedbackService = (UserFeedbackService) context
				.getBean("userFeedbackService");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 获取所有的反馈信息(显示到前台)
	 * @return
	 */
	public String contact_us() {
		ActionContext context = ActionContext.getContext();
		context.put("feedbackList", userFeedbackService.getAllFeedback());
		return SUCCESS;
	}
	
	/**
	 * 获取所有的反馈信息(用于后台回复)
	 * @return
	 */
	public String feedback() {
		ActionContext context = ActionContext.getContext();
		context.put("feedbackList", userFeedbackService.getAllFeedback());
		return SUCCESS;
	}

	/**
	 * 增加一条反馈
	 * 
	 * @return
	 */
	public String addUserFeedback() {
		UserFeedback feedback = new UserFeedback();
		feedback.setUser_name(userName);
		feedback.setUser_email(userEmail);
		feedback.setUser_phone(userPhone);
		feedback.setMessage(message);
		feedback.setGmt_create(dateFormat.format(new Date()));
		this.setResult(userFeedbackService.addUserFeedback(feedback));
		return SUCCESS;
	}

	/**
	 * 回复反馈信息
	 * @return
	 */
	public String replyFeedback(){
		this.setResult(userFeedbackService.replyFeedbcak(id, 
				response, dateFormat.format(new Date())));
		return SUCCESS;
	}
	/**
	 * 删除一条反馈信息
	 * @return
	 */
	public String deleteFeedback(){
		if(userFeedbackService.deleteFeedback(id)){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
}
