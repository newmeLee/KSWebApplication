package cn.zjzt.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.service.PhyUnitCheckService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UnitValidateAction extends ActionSupport{
	private String unitID;
	private String unitPassword;
	private ValidateInfo result;//查询信息完整性验证类
	public String getUnitID() {
		return unitID;
	}
	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}
	public String getUnitPassword() {
		return unitPassword;
	}
	public void setUnitPassword(String unitPassword) {
		this.unitPassword = unitPassword;
	}
	public ValidateInfo getResult() {
		return result;
	}
	public void setResult(ValidateInfo result) {
		this.result = result;
	}

	private PhyUnitCheckService unitCheckService;
	private static final String ACTION_RESULT_SUCCESS="success";
	public UnitValidateAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		unitCheckService = (PhyUnitCheckService) context
				.getBean("phyUnitCheckService");
	}
	/**
	 * 单位查询信息完整性验证
	 * @return
	 */
	public String validateUnitInfo() {
		this.setResult(unitCheckService.validateUnitInfo(unitID,
				unitPassword));
		return ACTION_RESULT_SUCCESS;
	}
}
