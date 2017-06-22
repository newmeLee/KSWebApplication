package cn.zjzt.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.*;
import cn.zjzt.service.PatientService;
import cn.zjzt.service.PhyCheckService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PatientAction extends ActionSupport {
	private String regID;
	private String IDCard;
	private String unitCode;
	private String checkPassword;
	private String oldPassword;
	private String newPassword;
	private ValidateInfo result;
	private String userPassword;
	private String type;
	private String typeValue;
	public String getRegID() {
		return regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public ValidateInfo getResult() {
		return result;
	}

	public void setResult(ValidateInfo result) {
		this.result = result;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	//
	PatientService patientService;
	PhyCheckService phyCheckService;

	public PatientAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		patientService = (PatientService) context.getBean("patientService");
		phyCheckService = (PhyCheckService) context.getBean("phyCheckService");
	}

	/**
	 * 查找用户信息
	 * 
	 * @return
	 */
	public String queryPatient() {
		// System.out.println(phyID);
		String regID = phyCheckService.getCurrentRegIDByIDCard(IDCard);
		ViewPhyCheckMaster patient = patientService.qureyPatient(regID);
		// System.out.println(patient.getRegID()+patient.getPhyID()+patient.getName());
		ActionContext context = ActionContext.getContext();
		context.put("regID", patient.getRegID());
		context.put("phyID", patient.getPhyID());
		context.put("name", patient.getPtName());
		context.put("patient", patient);
		return "success";
	}

	/**
	 * 验证用户信息
	 * 
	 * @return
	 */
	public String validateInfo() {
		ValidateInfo validate = patientService.validateCheckInfo(IDCard,
				checkPassword);
		this.setResult(validate);
		return "success";
	}

	/**
	 * 修改密码(个人查询)
	 * 
	 * @return
	 */
	public String updatePassword() {
		// regID="1202130031";
		ValidateInfo validate = patientService.updatePassword(regID,
				oldPassword, newPassword);
		this.setResult(validate);
		// MyPrintUtil.printError(validate.getMessage());
		return "success";
	}

	public String getPassword() {
		userPassword = patientService.getUserPassword(regID);
		this.setUserPassword(userPassword);
		return "success";
	}

	/**
	 * 验证登记号正确性
	 * 
	 * @return
	 */
	public String validateRegID() {
		this.setResult(patientService.ifExistRegID(regID));
		return SUCCESS;
	}

	
	/**
	 * 验证身份证号的正确性
	 * 
	 * @return
	 */
	public String validateIDCard() {
		this.setResult(patientService.validateIDCard(IDCard));
		return SUCCESS;
	}
	
	/**
	 * 根据登记号和密码查询密码的正确性
	 * 
	 * @return
	 */
	public String validateRegIDAndPsd() {
		// 判断总检与否
		if (!phyCheckService.getFCStatus(regID)) {
			ValidateInfo info = new ValidateInfo();
			info.setMessage("该体检报告尚未完成，请稍后再查询！");
			info.setStatus(404);
			this.setResult(info);
		} else if (phyCheckService.getFCStatus(regID)) {
			this.setResult(patientService.checkByPassword(regID, checkPassword));
		}
		return SUCCESS;
	}

	/**
	 * 修改单位查询的密码
	 * 
	 * @return
	 */
	public String updateUnitPassword() {
		ValidateInfo validate = patientService.updateUnitPassword(unitCode,
				oldPassword, newPassword);
		this.setResult(validate);
		return "success";
	}
	/**
	 * 判断某个字段的值是否存在
	 * @return
	 */
	public String validateProperty(){
		this.setResult(patientService.ifExistProperty(type, typeValue));
		return SUCCESS;
	}
}
