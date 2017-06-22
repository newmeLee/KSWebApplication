package cn.zjzt.dao.system;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.zjzt.entity.system.Expert;

public class ExpertDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String sql = null;

	/**
	 * ��ȡ���е�ר����Ϣ
	 * 
	 * @return
	 */
	public List<Expert> queryAllExpert() {
		sql = "select * from Expert where is_delete=0";
		List<Expert> expertList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Expert>(Expert.class));
		return expertList;
	}

	/**
	 * ����һ��ר����Ϣ
	 * 
	 * @param expert
	 * @return
	 */
	public int insertExpert(Expert expert) {
		sql = "insert into Expert(name,profer_title,depart,duty,intro,pic_url)"
				+ " values(?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, expert.getName(),
				expert.getProfer_title(), expert.getDepart(), expert.getDuty(),
				expert.getIntro(), expert.getPic_url());
	}

	/**
	 * ��ȡ�����ײ͵���Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Expert getExpertByID(String id) {
		sql = "select * from Expert where id=?";
		List<Expert> expertList = jdbcTemplate.query(sql, new Object[] { id },
				new BeanPropertyRowMapper<Expert>(Expert.class));
		return expertList.size() > 0 ? expertList.get(0) : null;
	}
	/**
	 * ɾ��ר����Ϣ
	 * @param expertId
	 * @return
	 */
	public int deleteExpertByID(String expertId) {
		sql = "update Expert set is_delete=1 where id=?";
		return jdbcTemplate.update(sql, expertId);
	}
	/**
	 * ����ר����Ϣ
	 * @param expert
	 * @return
	 */
	public int updateExpertInfo(Expert expert){
		sql="update Expert set name=?,profer_title=?,depart=?,duty=?,intro=?,pic_url=? " +
				"where id=?";
		return jdbcTemplate.update(sql, expert.getName(),expert.getProfer_title(),
				expert.getDepart(),expert.getDuty(),expert.getIntro(),
				expert.getPic_url(),expert.getId());
	}
}
