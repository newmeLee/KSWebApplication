package cn.zjzt.action;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.PhyCheckUpList;
import cn.zjzt.entity.ViewPhyCheckMaster;
import cn.zjzt.entity.ViewPhyCheckResult;
import cn.zjzt.service.PatientService;
import cn.zjzt.service.PhyCheckService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PhyCheckAction extends ActionSupport {

	private String IDCard;
	private String regID;
	private String type;// 根据什么类型查询
	private String typeValue;// 类型的值

	public String getRegID() {
		return regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String IDCard) {
		this.IDCard = IDCard;
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

	private PhyCheckService phyCheckService;
	private PatientService patientService;

	/**
	 * 无参构造函数
	 */
	public PhyCheckAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		phyCheckService = (PhyCheckService) context.getBean("phyCheckService");
		patientService = (PatientService) context.getBean("patientService");
	}

	/**
	 * 获取用户的体检报告结果
	 * 
	 * @param phyID
	 * @return
	 */
	public String getCheckResult() {
		if (regID == null) {
			this.setRegID(phyCheckService.getCurrentRegIDByIDCard(IDCard));
		}
		List<PhyCheckUpList> checkList = phyCheckService
				.getCheckListByRegID(regID);
		ActionContext context = ActionContext.getContext();
		// System.out.println("异常标识"+checkList.get(0).getChkRtPrompt());
		context.put("checkList", checkList);
		return SUCCESS;
	}

	/**
	 * 根据登记号获取用户的单次体检记录(根据登记编号regID)
	 * 
	 * @return
	 */
	public String getCheckRecord() {
		if (regID == null) {
			this.setRegID(phyCheckService.getCurrentRegIDByIDCard(IDCard));
		}
		// 判断是否总检
		if (phyCheckService.getFCStatus(regID)) {
			queryCheckResult();
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * 业务员根据体检登记号查询用户的报告(不需要判断是否总检)
	 * 
	 * @return
	 */
	public String getCheckRecordByAdmin() {
		if ("PtName".equals(this.type)) {// 如果是根据姓名查询
			return "name";
		} else {
			if (this.regID == null) {
				this.setRegID(phyCheckService.getCurrentRegIDByProp(type,
						typeValue));
			}
			queryCheckResult();
			return SUCCESS;
		}
	}

	/**
	 * 根据字段名和字段值获取用户体检信息
	 * 
	 * @param columnName
	 * @param value
	 */
	private void queryCheckResult() {
		if (regID == null) {
			this.setRegID(phyCheckService.getCurrentRegIDByIDCard(IDCard));
		}
		// 获取所有的体检结果
		List<ViewPhyCheckResult> checkList = phyCheckService
				.getAllViewResult(regID);
		//MyPrintUtil.printError(regID);
		// 获取所有的检查科室
		// List<ViewPhyCheckResult> deptList =
		// phyCheckService.getDeptViewResult(
		// regID);
		List<ViewPhyCheckResult> deptList = phyCheckService
				.getDeptOrGroupListResult("deptID", checkList);
		// 获取所有的项目组合
		// List<ViewPhyCheckResult> groupList = phyCheckService
		// .getGroupViewResult(regID);
		List<ViewPhyCheckResult> groupList = phyCheckService
				.getDeptOrGroupListResult("groupID", checkList);
		// 获取用户信息
		ViewPhyCheckMaster userInfo = patientService.qureyPatient(regID);
		List<ViewPhyCheckMaster> phyRecords = phyCheckService
				.getUserPhyRecords(regID);
		// 将结果存储到域对象中以供调用
		ActionContext context = ActionContext.getContext();
		context.put("checkList", checkList);// 检查结果
		context.put("deptList", deptList);
		context.put("groupList", groupList);
		context.put("userInfo", userInfo);
		context.put("phyRecords", phyRecords);
		//
	}

	/**
	 * 获取用户所有的体检记录
	 * 
	 * @return
	 */
	public String getPhyicalList() {
		if (regID == null) {
			this.setRegID(phyCheckService.getCurrentRegIDByIDCard(IDCard));
		}
		List<ViewPhyCheckMaster> phyRecords = phyCheckService
				.getUserPhyRecords(regID);
		// 将结果存储到域对象中以供调用
		ActionContext context = ActionContext.getContext();
		context.put("phyRecords", phyRecords);
		return SUCCESS;
	}

	/**
	 * 根据姓名查找用户体检信息
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public String getMasterByName() throws ParseException {
		List<ViewPhyCheckMaster> masterlist = phyCheckService
				.getMasterByName(typeValue);
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().get("request");
		request.put("masterList", masterlist);
		return SUCCESS;
	}
}
