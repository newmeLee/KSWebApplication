package cn.zjzt.action.system;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.service.system.WebNewsService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GuideAction extends ActionSupport {

	private WebNewsService newsService;
	// ������ŷ���(2Ϊ��쳣ʶ)
	private static final String NEWS_TYPE_SOCIAL = "2";

	public GuideAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		newsService = (WebNewsService) context.getBean("webNewsService");
	}

	/**
	 * ��ȡ��콡��֪ʶ����
	 * 
	 * @return
	 */
	public String getAllPhyicalNews() {
		ActionContext context = ActionContext.getContext();
		context.put("socialNews", newsService.getAllNews(NEWS_TYPE_SOCIAL));
		return SUCCESS;
	}
}
