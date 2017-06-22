package cn.zjzt.service.system;

import java.util.List;

import cn.zjzt.dao.system.TemplateDao;
import cn.zjzt.entity.system.Template;

public class TemplateService {
	
	private TemplateDao templateDao;
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}
	/**
	 * ����һ���ײ�����
	 * @param template
	 * @return
	 */
	public boolean addOrUpdateTemplate(Template template){
		if(template.getId()>0){
			return templateDao.updateTemplate(template)>0?true:false;
		}else{
			return templateDao.insertTemplate(template)>0?true:false;
		} 
	}
	/**
	 * ��ȡ���е��ײ�
	 * @return
	 */
	public List<Template> getAllTemplate(){
		return templateDao.getAllTemplate();
	}
	/**
	 * ��ȡ�����ײ͵���Ϣ
	 * @param id
	 * @return
	 */
	public Template getTemplateByID(String id) {
		return templateDao.getTemplateByID(id);
	}
	/**
	 * �߼�ɾ������ײ�
	 * @param templateId
	 * @return
	 */
	public int deleteTemplate(String templateId){
		return templateDao.deleteTemplate(templateId);
	}
		
}
