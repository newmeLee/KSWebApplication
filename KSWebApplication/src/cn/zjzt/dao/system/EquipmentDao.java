package cn.zjzt.dao.system;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.zjzt.entity.system.Equipment;

public class EquipmentDao {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String sql = null;
	/**
	 * 获取所有的设备
	 * 
	 * @return
	 */
	public List<Equipment> queryAllEquip() {
		sql = "select * from Equipment where is_delete=0";
		List<Equipment> equipList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Equipment>(Equipment.class));
		return equipList;
	}
	/**
	 * 插入一条设备信息
	 * @param depart
	 * @return
	 */
	public int insertEquip(Equipment equipment){
		sql="insert into Equipment(pic_url,name,intro) values(?,?,?)";
		return jdbcTemplate.update(sql,equipment.getPic_url(), equipment.getName(),
				equipment.getIntro());
	}
	/**
	 * 获取单个设备的信息
	 * @param departId
	 * @return
	 */
	public Equipment queryEquipById(String equipId){
		sql="select * from Equipment where id=?";
		List<Equipment> equipList = jdbcTemplate.query(sql, new Object[] { equipId },
				new BeanPropertyRowMapper<Equipment>(Equipment.class));
		return equipList.size() > 0 ? equipList.get(0) : null;
	}
	/**
	 * 逻辑删除一条设备信息
	 * @param departId
	 * @return
	 */
	public int deleteEquip(String equipId){
		sql = "update Equipment set is_delete=1 where id=?";
		return jdbcTemplate.update(sql, equipId);
	}
	/**
	 * 更新设备信息
	 * @param depart
	 * @return
	 */
	public int updateEquip(Equipment equipment){
		sql="update Equipment set pic_url=?, name=?,intro=? " +
				"where id=?";
		return jdbcTemplate.update(sql,equipment.getPic_url(), 
				equipment.getName(),equipment.getIntro(),equipment.getId());
	}
}
