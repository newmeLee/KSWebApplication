package cn.zjzt.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.zjzt.entity.system.UserPassword;

public class UserPasswordDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String sql = null;

	/**
	 * 根据登记号获取查询密码
	 * 
	 * @param regID
	 * @return
	 */
	public String queryPassword(String regID) {
		sql = "select password from Phy_CheckUpMaster where regID=?";
		List<UserPassword> resultList = jdbcTemplate.query(sql,
				new String[] { regID },
				new BeanPropertyRowMapper<UserPassword>(UserPassword.class));
		return resultList.size() > 0 ? resultList.get(0).getPassword() : null;
	}

	/**
	 * 用户修改查询密码
	 * 
	 * @param userPassword
	 * @return
	 */
	public int updateUserPassword(String regID, String newPassword) {
		sql = "update Phy_CheckUpMaster set password=? where regID=?";
		return jdbcTemplate.update(sql, new Object[] { newPassword, regID });
	}

	/**
	 * 修改单位查询的密码(联系电话)
	 * 
	 * @param unitCode
	 * @param newPassword
	 * @return
	 */
	public int updateUnitPassword(String unitCode, String newPassword) {
		sql = "update Phy_AcceptUnit set Phone=? where Code=?";
		return jdbcTemplate.update(sql, new Object[] { newPassword, unitCode });
	}
}
