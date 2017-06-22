package cn.zjzt.dao.system;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.zjzt.entity.system.Template;

/**
 * 体检套餐数据库应用层
 * 
 * @author Administrator
 * 
 */
public class TemplateDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String sql = null;

	/**
	 * 增加体检套餐记录
	 * 
	 * @param template
	 * @return
	 */
	public int insertTemplate(Template template) {
		sql = "insert into Template(name,apply_sex,intro,price,gmt_create,"
				+ "gmt_modified,pic_url) values(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, template.getName(),
				template.getApply_sex(), template.getIntro(),
				template.getPrice(), template.getGmt_create(),
				template.getGmt_modified(), template.getPic_url());
	}

	/**
	 * 获取所有的展示套餐
	 * 
	 * @return
	 */
	public List<Template> getAllTemplate() {
		sql = "select * from Template where is_delete=0";
		List<Template> templateList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Template>(Template.class));
		return templateList;
	}
	/**
	 * 获取单个套餐的信息
	 * @param id
	 * @return
	 */
	public Template getTemplateByID(String id) {
		sql = "select * from Template where id=?";
		List<Template> templateList = jdbcTemplate.query(sql,
				new Object[] { id }, new BeanPropertyRowMapper<Template>(
						Template.class));
		return templateList.size() > 0 ? templateList.get(0) : null;
	}
	/**
	 * 更新套餐信息
	 * @param template
	 * @return
	 */
	public int updateTemplate(Template template){
		sql="update Template set name=?,apply_sex=?,intro=?,price=?," +
				"pic_url=?,gmt_modified=? where id=?";
		return jdbcTemplate.update(sql, template.getName(),
				template.getApply_sex(), template.getIntro(),
				template.getPrice(), template.getPic_url(),
				template.getGmt_modified(),template.getId());
	}
	/**
	 * 逻辑删除体检套餐
	 * @param templateId
	 * @return
	 */
	public int deleteTemplate(String templateId){
		sql="update Template set is_delete=1 where id=?";
		return jdbcTemplate.update(sql,templateId);
	}
}
