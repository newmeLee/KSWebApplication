package cn.zjzt.action;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.PhyAcceptUnit;
import cn.zjzt.entity.ViewPhyCheckMaster;
import cn.zjzt.service.PhyUnitCheckService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ���屨���ѯaction
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class PhyUnitCheckAction extends ActionSupport {
	private String unitID;
	private String startDate;
	private String endDate;
	private String keyword;

	public String getUnitID() {
		return unitID;
	}

	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	private PhyUnitCheckService unitCheckService;

	/**
	 * ���캯��
	 */
	public PhyUnitCheckAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		unitCheckService = (PhyUnitCheckService) context
				.getBean("phyUnitCheckService");
	}

	/**
	 * ��ȡָ����λ��Ϣ
	 * 
	 * @return
	 */
	public String getUnitEmpInfo() {
		PhyAcceptUnit acceptUnit = null;
		ActionContext context = ActionContext.getContext();
		if (null != unitID) {
			acceptUnit = unitCheckService.getUnitInfo(unitID);
		}
		context.put("unitInfo", acceptUnit);
		context.put("unitID", unitID);
		// System.out.println("��Ա��Ŀ"+empList.size());
		return SUCCESS;
	}

	/**
	 * ����ʱ��β�ѯ��λ�����Ա��Ϣ
	 * 
	 * @return
	 */
	public String getUnitEmpInfoByDate() {
		PhyAcceptUnit acceptUnit = null;
		List<ViewPhyCheckMaster> empList = null;
		ActionContext context = ActionContext.getContext();
		if (null != unitID) {
			acceptUnit = unitCheckService.getUnitInfo(unitID);
		}
		if (startDate != null && endDate != null) {
			empList = unitCheckService.getUnitEmplInfo(unitID, startDate,
					endDate);
		}
		// System.out.println("��λ����"+empResultList.size());
		context.put("unitID", unitID);
		context.put("unitInfo", acceptUnit);
		context.put("empList", empList);
		context.put("startDate", this.getStartDate());
		context.put("endDate", this.getEndDate());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getUnitList() {
		List<PhyAcceptUnit> unitList = unitCheckService
				.getUnitListByKeyword(keyword);
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().get("request");
		request.put("unitList", unitList);
		return SUCCESS;
	}
}
