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
	 * ��ȡ���е�����
	 * 
	 * @return
	 */
	public List<WebNews> getAllNews(String newsType) {
		return webNewsDao.queryAllNews(newsType);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public WebNews getNewsByID(String id) {
		return webNewsDao.getNewsByID(id);
	}

	/**
	 * ���ӻ����һ����������
	 * 
	 * @param news
	 * @return
	 */
	public ValidateInfo addOrUpdateNews(WebNews news) {
		if (news.getId() > 0) {
			if( webNewsDao.updateNews(news) > 0 ){
				validateInfo.setStatus(200);
				validateInfo.setMessage("���³ɹ���");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("����ʧ�ܣ�");
			}
		} else {
			if(webNewsDao.insertNews(news) > 0){
				validateInfo.setStatus(200);
				validateInfo.setMessage("�����ɹ�����");
			}else{
				validateInfo.setStatus(500);
				validateInfo.setMessage("����ʧ�ܣ���");
			}
		}
		return validateInfo;
	}
	/**
	 * ɾ������
	 * @return
	 */
	public ValidateInfo deleteNews(int newsID){
		if(webNewsDao.deleteNews(newsID)>0){
			validateInfo.setStatus(200);
			validateInfo.setMessage("ɾ���ɹ���");
		}else{
			validateInfo.setStatus(500);
			validateInfo.setMessage("ɾ��ʧ�ܣ�");
		}
		return validateInfo;
	}
}
