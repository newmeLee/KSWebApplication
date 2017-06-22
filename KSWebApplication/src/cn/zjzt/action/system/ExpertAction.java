package cn.zjzt.action.system;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.system.Expert;
import cn.zjzt.service.system.ExpertService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ExpertAction extends ActionSupport {
	private String expertID;

	public String getExpertID() {
		return expertID;
	}

	public void setExpertID(String expertID) {
		this.expertID = expertID;
	}

	private Expert expert;

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	private ExpertService expertService;

	/**
	 * ���캯��
	 */
	public ExpertAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		expertService = (ExpertService) context.getBean("expertService");
	}

	/**
	 * ��ȡ���е�ר��
	 * 
	 * @return
	 */
	public String getAllExpert() {
		ActionContext context = ActionContext.getContext();
		context.put("expertList", expertService.getAllExpert());
		return SUCCESS;
	}

	/**
	 * ����һ��ר����Ϣ
	 * 
	 * @return
	 */
	public String addOrUpdateExpert() {
		//MyPrintUtil.printError(expert.getId());
		expertService.addOrUpdateExpert(expert);
		return SUCCESS;
	}

	/**
	 * ��ȡ��ϸ��ר����Ϣ
	 * 
	 * @return
	 */
	public String expertDetail() {
		ActionContext context = ActionContext.getContext();
		context.put("expert", expertService.getExpertByID(expertID));
		return SUCCESS;
	}

	/**
	 * �߼�ɾ��ר����Ϣ
	 * 
	 * @return
	 */
	public String deleteExpert() {
		expertService.deleteExpert(expertID);
		return SUCCESS;
	}
}
