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
	 * 增加一条套餐数据
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
	 * 获取所有的套餐
	 * @return
	 */
	public List<Template> getAllTemplate(){
		return templateDao.getAllTemplate();
	}
	/**
	 * 获取单个套餐的信息
	 * @param id
	 * @return
	 */
	public Template getTemplateByID(String id) {
		return templateDao.getTemplateByID(id);
	}
	/**
	 * 逻辑删除体检套餐
	 * @param templateId
	 * @return
	 */
	public int deleteTemplate(String templateId){
		return templateDao.deleteTemplate(templateId);
	}
		
}
