package cn.zjzt.action.system;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.system.Depart;
import cn.zjzt.entity.system.Equipment;
import cn.zjzt.entity.system.Expert;
import cn.zjzt.entity.system.WebNews;
import cn.zjzt.service.system.DepartService;
import cn.zjzt.service.system.EquipmentService;
import cn.zjzt.service.system.ExpertService;
import cn.zjzt.service.system.LoginService;
import cn.zjzt.service.system.WebNewsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {

	private WebNewsService webNewsService;
	private ExpertService expertService;
	private DepartService departService;
	private EquipmentService equipmentService;
	private LoginService loginService;
	private String message;//action-ajax返回的信息
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	//获取新闻的类型
	private static final String NEWS_TYPE_OFFICAL="1";
	public IndexAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		webNewsService = (WebNewsService) context.getBean("webNewsService");
		expertService = (ExpertService) context.getBean("expertService");
		departService = (DepartService) context.getBean("departService");
		equipmentService=(EquipmentService) context.getBean("equipmentService");
		loginService=(LoginService) context.getBean("loginService");
	}

	/**
	 * 首页action
	 * 
	 * @return
	 */
	public String index() {
		ActionContext context = ActionContext.getContext();
		List<WebNews> newsList = webNewsService.getAllNews(NEWS_TYPE_OFFICAL);//类型1表示新闻通知类
		List<Expert> expertList = expertService.getAllExpert();
		List<Depart> departList=departService.getAllDepart();
		List<Equipment> equipList=equipmentService.getAllEquip();
		// MyPrintUtil.printError(newsList.size());
		context.put("newsList", newsList);
		context.put("expertList", expertList);
		context.put("departList", departList);
		context.put("equipList", equipList);
		return SUCCESS;
	}
	/**
	 * 后台管理登录
	 * @return
	 */
	public String adminLogin(){
		this.setMessage(loginService.validateUser(userName, password));
		return SUCCESS;
	}
}
