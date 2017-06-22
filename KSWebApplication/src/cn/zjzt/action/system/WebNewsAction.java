package cn.zjzt.action.system;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.system.WebNews;
import cn.zjzt.service.system.WebNewsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class WebNewsAction extends ActionSupport {
	private WebNewsService webNewsService;
	private String newsID;
	private String newsTitle;
	private String type; 
	private String keywords;
	private String newsContent;
	private String creator;
	private WebNews result;
	private ValidateInfo checkData;

	public ValidateInfo getCheckData() {
		return checkData;
	}

	public void setCheckData(ValidateInfo checkData) {
		this.checkData = checkData;
	}

	public void setResult(WebNews result) {
		this.result = result;
	}

	public WebNews getResult() {
		return result;
	}

	public String getNewsID() {
		return newsID;
	}

	public void setNewsID(String newsID) {
		this.newsID = newsID;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	//获取新闻的类型
	private static final String NEWS_TYPE_OFFICAL="1";
	private static final String NEWS_TYPE_SOCIAL="2";
	public WebNewsAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		webNewsService = (WebNewsService) context.getBean("webNewsService");
	}

	/**
	 * 获取所有新闻(新闻通知类)
	 * 
	 * @return
	 */
	public String getAllNews() {
		getNews(NEWS_TYPE_OFFICAL);
		return SUCCESS;
	}
	/**
	 * 获取所有的体检常识类新闻
	 * @return
	 */
	public String getAllSocialNews(){
		getNews(NEWS_TYPE_SOCIAL);
		return SUCCESS;
	}
	/**
	 * 获取新闻的通用方法
	 * @param newsType
	 */
	private void getNews(String newsType){
		ActionContext context = ActionContext.getContext();
		List<WebNews> newsList = webNewsService.getAllNews(newsType);//类型1表示新闻通知类
		context.put("newsList", newsList);
	}
	/**
	 * 增加一条新闻
	 * 
	 * @return
	 */
	public String addOrUpdateNews() {
		WebNews news = new WebNews();
		if ((newsID != null) && (newsID.trim().length() > 0)) {
			news.setId(Integer.parseInt(newsID));
		}
		news.setNews_title(newsTitle);
		news.setType(type);
		news.setNews_content(newsContent);
		news.setKey_words(keywords);
		news.setCreator(creator);
		news.setGmt_create(new Date());
		news.setGmt_modified(new Date());
		this.setCheckData(webNewsService.addOrUpdateNews(news));
		return SUCCESS;
	}

	/**
	 * 获取单条新闻信息
	 * 
	 * @return
	 */
	public String getNewsByID() {
		ActionContext context = ActionContext.getContext();
		context.put("news", webNewsService.getNewsByID(newsID));
		return SUCCESS;
	}

	/**
	 * 获取单条新闻信息(返回Json格式的字符串)
	 * 
	 * @return
	 */
	public String getNewsByIDJson() {
		this.setResult(webNewsService.getNewsByID(newsID));
		return SUCCESS;
	}
	/**
	 * 删除新闻（逻辑删除）
	 * @return
	 */
	public String deleteNewsByID() {
		if ((newsID != null) && (newsID.trim().length() > 0)) {
			this.setCheckData(webNewsService.deleteNews(Integer
					.parseInt(newsID)));
		}
		//获取所有的新闻列表(跳转到获取所有的新闻列表action)
		return SUCCESS;
	}
}
