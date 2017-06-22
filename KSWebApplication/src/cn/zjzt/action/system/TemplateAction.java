package cn.zjzt.action.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.system.Template;
import cn.zjzt.service.system.TemplateService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 体检套餐类Action
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class TemplateAction extends ActionSupport {
	private String templateID;

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}
	private Template template;
	public void setTemplate(Template template) {
		this.template = template;
	}
	public Template getTemplate() {
		return template;
	}
	
	private TemplateService templateService;
	private SimpleDateFormat sdf;
	public TemplateAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		templateService = (TemplateService) context.getBean("templateService");
		sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取套餐
	 * 
	 * @return
	 */
	public String template() {
		ActionContext context = ActionContext.getContext();
		context.put("templateList", templateService.getAllTemplate());
		return SUCCESS;
	}
	/**
	 *  获取所有的套餐信息，显示到后台管理页面
	 * @return
	 */
	public String getAllTemplate(){
		ActionContext context = ActionContext.getContext();
		context.put("templateList", templateService.getAllTemplate());
		return SUCCESS;
	}
	/**
	 * 获取套餐的信息
	 * 
	 * @return
	 */
	public String templateDetail() {
		ActionContext context = ActionContext.getContext();
		context.put("templateInfo", templateService.getTemplateByID(templateID));
		return SUCCESS;
	}
	/**
	 * 增加一条套餐数据
	 * @return
	 */
	public String addOrUpdateTemplate(){
		//MyPrintUtil.printError(template.getId());
		if(template.getId()<1){
			template.setGmt_create(sdf.format(new Date()));
		}
		template.setGmt_modified(sdf.format(new Date()));
		templateService.addOrUpdateTemplate(template);
		return SUCCESS;
	}
	/**
	 * 逻辑删除套餐信息
	 * @return
	 */
	public String deleteTemplate(){
		templateService.deleteTemplate(templateID);
		return SUCCESS;
	}
	
}
