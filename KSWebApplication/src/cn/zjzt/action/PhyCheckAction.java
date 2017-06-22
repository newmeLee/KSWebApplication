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
	private String type;// ����ʲô���Ͳ�ѯ
	private String typeValue;// ���͵�ֵ

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
	 * �޲ι��캯��
	 */
	public PhyCheckAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		phyCheckService = (PhyCheckService) context.getBean("phyCheckService");
		patientService = (PatientService) context.getBean("patientService");
	}

	/**
	 * ��ȡ�û�����챨����
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
		// System.out.println("�쳣��ʶ"+checkList.get(0).getChkRtPrompt());
		context.put("checkList", checkList);
		return SUCCESS;
	}

	/**
	 * ���ݵǼǺŻ�ȡ�û��ĵ�������¼(���ݵǼǱ��regID)
	 * 
	 * @return
	 */
	public String getCheckRecord() {
		if (regID == null) {
			this.setRegID(phyCheckService.getCurrentRegIDByIDCard(IDCard));
		}
		// �ж��Ƿ��ܼ�
		if (phyCheckService.getFCStatus(regID)) {
			queryCheckResult();
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * ҵ��Ա�������ǼǺŲ�ѯ�û��ı���(����Ҫ�ж��Ƿ��ܼ�)
	 * 
	 * @return
	 */
	public String getCheckRecordByAdmin() {
		if ("PtName".equals(this.type)) {// ����Ǹ���������ѯ
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
	 * �����ֶ������ֶ�ֵ��ȡ�û������Ϣ
	 * 
	 * @param columnName
	 * @param value
	 */
	private void queryCheckResult() {
		if (regID == null) {
			this.setRegID(phyCheckService.getCurrentRegIDByIDCard(IDCard));
		}
		// ��ȡ���е������
		List<ViewPhyCheckResult> checkList = phyCheckService
				.getAllViewResult(regID);
		//MyPrintUtil.printError(regID);
		// ��ȡ���еļ�����
		// List<ViewPhyCheckResult> deptList =
		// phyCheckService.getDeptViewResult(
		// regID);
		List<ViewPhyCheckResult> deptList = phyCheckService
				.getDeptOrGroupListResult("deptID", checkList);
		// ��ȡ���е���Ŀ���
		// List<ViewPhyCheckResult> groupList = phyCheckService
		// .getGroupViewResult(regID);
		List<ViewPhyCheckResult> groupList = phyCheckService
				.getDeptOrGroupListResult("groupID", checkList);
		// ��ȡ�û���Ϣ
		ViewPhyCheckMaster userInfo = patientService.qureyPatient(regID);
		List<ViewPhyCheckMaster> phyRecords = phyCheckService
				.getUserPhyRecords(regID);
		// ������洢����������Թ�����
		ActionContext context = ActionContext.getContext();
		context.put("checkList", checkList);// �����
		context.put("deptList", deptList);
		context.put("groupList", groupList);
		context.put("userInfo", userInfo);
		context.put("phyRecords", phyRecords);
		//
	}

	/**
	 * ��ȡ�û����е�����¼
	 * 
	 * @return
	 */
	public String getPhyicalList() {
		if (regID == null) {
			this.setRegID(phyCheckService.getCurrentRegIDByIDCard(IDCard));
		}
		List<ViewPhyCheckMaster> phyRecords = phyCheckService
				.getUserPhyRecords(regID);
		// ������洢����������Թ�����
		ActionContext context = ActionContext.getContext();
		context.put("phyRecords", phyRecords);
		return SUCCESS;
	}

	/**
	 * �������������û������Ϣ
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
