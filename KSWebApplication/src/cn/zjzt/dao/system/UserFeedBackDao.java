package cn.zjzt.dao.system;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import cn.zjzt.entity.system.UserFeedback;

/**
 * 
 * @author Administrator
 * 
 */
public class UserFeedBackDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String sql = null;

	/**
	 * 增加一条反馈信息
	 * 
	 * @param feedback
	 * @return
	 */
	public int insertFeedBack(UserFeedback feedback){
		sql="insert into User_Feedback(user_name,user_email,user_phone,message,gmt_create) values" +
				"(?,?,?,?,?)";
		return jdbcTemplate.update(sql, feedback.getUser_name(),feedback.getUser_email(),
				feedback.getUser_phone(),feedback.getMessage(),feedback.getGmt_create());
	}
	/**
	 * 获取所有的反馈
	 * @return
	 */
	public List<UserFeedback> getAllFeedback(){
		sql="select * from User_feedback order by gmt_create desc";
		List<UserFeedback> feedList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<UserFeedback>(UserFeedback.class));
		return feedList;
	}
	/**
	 * 更新反馈的回复
	 * @param id
	 * @param response
	 * @param resDate
	 * @return
	 */
	public int updateResponse(String id,String response,String resDate){
		sql="update User_Feedback set response=?,gmt_response=? where id=?";
		return jdbcTemplate.update(sql, response,resDate,id);
	}
	/**
	 * 删除一条反馈信息
	 * @param id
	 * @return
	 */
	public int deleteFeedback(String id){
		sql="delete from User_Feedback where id=?";
		return jdbcTemplate.update(sql,id);
	}
}
