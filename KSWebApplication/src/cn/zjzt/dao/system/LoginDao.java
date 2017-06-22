package cn.zjzt.dao.system;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

public class LoginDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String sql = null;
	private String password = null;

	/**
	 * 查询是否存在该账号
	 * 
	 * @param userName
	 * @return
	 */
	public int queryUser(String userName) {
		sql = "select count(*) from Sys_Users where user_name=?";
		return jdbcTemplate.queryForInt(sql, userName);
	}
	/**
	 * 根据用户名获取密码
	 * @param userName
	 * @return
	 */
	public String getUserPassword(String userName) {
		sql = "select password from Sys_Users where user_name=?";
		jdbcTemplate.query(sql, new Object[] { userName },
				new RowCallbackHandler() {
					public void processRow(ResultSet res) throws SQLException {
						password = (res.getString("password"));
					}
				});
		return password;
	}
}
