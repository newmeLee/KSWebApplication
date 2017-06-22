package cn.zjzt.dao.system;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.zjzt.entity.system.Depart;

public class DepartDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String sql = null;

	/**
	 * 获取所有的科室
	 * 
	 * @return
	 */
	public List<Depart> queryAllDepart() {
		sql = "select * from Depart where is_delete=0";
		List<Depart> departList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Depart>(Depart.class));
		return departList;
	}
	/**
	 * 插入一条科室信息
	 * @param depart
	 * @return
	 */
	public int insertDepart(Depart depart){
		sql="insert into Depart(name,intro) values(?,?)";
		return jdbcTemplate.update(sql, depart.getName(),
				depart.getIntro());
	}
	/**
	 * 获取单个科室的信息
	 * @param departId
	 * @return
	 */
	public Depart queryDepartById(String departId){
		sql="select * from Depart where id=?";
		List<Depart> departList = jdbcTemplate.query(sql, new Object[] { departId },
				new BeanPropertyRowMapper<Depart>(Depart.class));
		return departList.size() > 0 ? departList.get(0) : null;
	}
	/**
	 * 逻辑删除一条科室信息
	 * @param departId
	 * @return
	 */
	public int deleteDepart(String departId){
		sql = "update Depart set is_delete=1 where id=?";
		return jdbcTemplate.update(sql, departId);
	}
	/**
	 * 更新科室信息
	 * @param depart
	 * @return
	 */
	public int updateDepart(Depart depart){
		sql="update Depart set name=?,intro=? " +
				"where id=?";
		return jdbcTemplate.update(sql, depart.getName(),depart.getIntro(),depart.getId());
	}
}
