package cn.zjzt.dao.system;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import cn.zjzt.entity.system.WebNews;

public class WebNewsDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//
	private String sql = null;

	/**
	 * ��ȡ���е�����
	 * 
	 * @return
	 */
	public List<WebNews> queryAllNews(String newsType) {
		// û�б��߼�ɾ��������
		sql = "select * from Web_News where type =? and is_delete = 0 " +
				"order by gmt_create desc";
		List<WebNews> newsList = jdbcTemplate.query(sql,
				new Object[] { newsType }, new BeanPropertyRowMapper<WebNews>(
						WebNews.class));
		return newsList;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public WebNews getNewsByID(String id) {
		sql = "select * from Web_News where id=?";
		List<WebNews> newsList = jdbcTemplate.query(sql, new Object[] { id },
				new BeanPropertyRowMapper<WebNews>(WebNews.class));
		return newsList.size() > 0 ? newsList.get(0) : null;
	}

	/**
	 * ����һ����������
	 * 
	 * @param news
	 * @return
	 */
	public int insertNews(WebNews news) {
		sql = "insert into Web_News(news_title,news_content,key_words,creator,gmt_create,"
				+ "gmt_modified,type) values(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, news.getNews_title(),
				news.getNews_content(), news.getKey_words(), news.getCreator(),
				news.getGmt_create(), news.getGmt_modified(), news.getType());
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param news
	 * @return
	 */
	public int updateNews(WebNews news) {
		sql = "update Web_News set news_title=?,news_content=?,key_words=?,"
				+ "creator=?,gmt_modified=?,type=? where id=?";
		return jdbcTemplate.update(sql, news.getNews_title(),
				news.getNews_content(), news.getKey_words(), news.getCreator(),
				news.getGmt_modified(), news.getType(), news.getId());
	}

	/**
	 * ɾ��������Ϣ(�߼�ɾ��)
	 * 
	 * @param newID
	 * @return
	 */
	public int deleteNews(int newsID) {
		sql = "update Web_News set is_delete=1 where id=?";
		return jdbcTemplate.update(sql, newsID);
	}
}
