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
	 * ��ѯ�Ƿ���ڸ��˺�
	 * 
	 * @param userName
	 * @return
	 */
	public int queryUser(String userName) {
		sql = "select count(*) from Sys_Users where user_name=?";
		return jdbcTemplate.queryForInt(sql, userName);
	}
	/**
	 * �����û�����ȡ����
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
