package cn.zjzt.service.system;

import java.util.List;

import cn.zjzt.dao.system.WebNewsDao;
import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.system.WebNews;

public class WebNewsService {
	private WebNewsDao webNewsDao;

	public void setWebNewsDao(WebNewsDao webNewsDao) {
		this.webNewsDao = webNewsDao;
	}
	private ValidateInfo validateInfo;
	public void setValidateInfo(ValidateInfo validateInfo) {
		this.validateInfo = validateInfo;
	}

	/**
	 * 获取所有的新闻
	 * 
	 * @return
	 */
	public List<WebNews> getAllNews(String newsType) {
		return webNewsDao.queryAllNews(newsType);
	}

	/**
	 * 获取单条新闻
	 * 
	 * @return
	 */
	public WebNews getNewsByID(String id) {
		return webNewsDao.getNewsByID(id);
	}

	/**
	 * 增加或更新一条新闻数据
	 * 
	 * @param news
	 * @return
	 */
	public ValidateInfo addOrUpdateNews(WebNews news) {
		if (news.getId() > 0) {
			if( webNewsDao.updateNews(news) > 0 ){
				validateInfo.setStatus(200);
				validateInfo.setMessage("更新成功！");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("更新失败！");
			}
		} else {
			if(webNewsDao.insertNews(news) > 0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("新增成功！！");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("新增失败！！");
			}
		}
		return validateInfo;
	}
	/**
	 * 删除新闻
	 * @return
	 */
	public ValidateInfo deleteNews(int newsID){
		if(webNewsDao.deleteNews(newsID)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("删除成功！");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("删除失败！");
		}
		return validateInfo;
	}
}
