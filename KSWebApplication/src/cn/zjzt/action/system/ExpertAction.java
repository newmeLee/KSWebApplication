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
	 * 构造函数
	 */
	public ExpertAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		expertService = (ExpertService) context.getBean("expertService");
	}

	/**
	 * 获取所有的专家
	 * 
	 * @return
	 */
	public String getAllExpert() {
		ActionContext context = ActionContext.getContext();
		context.put("expertList", expertService.getAllExpert());
		return SUCCESS;
	}

	/**
	 * 增加一条专家信息
	 * 
	 * @return
	 */
	public String addOrUpdateExpert() {
		//MyPrintUtil.printError(expert.getId());
		expertService.addOrUpdateExpert(expert);
		return SUCCESS;
	}

	/**
	 * 获取详细的专家信息
	 * 
	 * @return
	 */
	public String expertDetail() {
		ActionContext context = ActionContext.getContext();
		context.put("expert", expertService.getExpertByID(expertID));
		return SUCCESS;
	}

	/**
	 * 逻辑删除专家信息
	 * 
	 * @return
	 */
	public String deleteExpert() {
		expertService.deleteExpert(expertID);
		return SUCCESS;
	}
}
