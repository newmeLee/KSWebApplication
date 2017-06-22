package cn.zjzt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import cn.zjzt.entity.Patient;
import cn.zjzt.entity.ViewPhyCheckMaster;

public class PatientDao {

	private Patient patient;
	private JdbcTemplate jdbcTemplate;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//
	String sql = null;

	/*
	 * 获取人员信息
	 */
	public ViewPhyCheckMaster queryPatientById(String regId) {
		sql = "select regID,phyID,PtName,age,PtSex,Telephone,MobileTel,IDCard,UnitName,Category,"
				+ " MasterSales,SecondSales from Phy_CheckUpMaster_Web where RegID=?";
		List<ViewPhyCheckMaster> patientList = jdbcTemplate.query(sql,
				new Object[] { regId },
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return patientList.size() > 0 ? patientList.get(0) : null;
	}

	/**
	 * 查找是否存在体检编号
	 * 
	 * @param queryID
	 * @return
	 */
	public int queryPhyID(String phyID) {
		sql = "select count(*) from Phy_CheckUpMaster where phyID=?";
		return jdbcTemplate.queryForInt(sql, phyID);
	}

	/**
	 * 根据体检编号获取身份证号
	 * 
	 * @param phyID
	 * @return
	 */
	public String getIDcardbyRegID(String regID) {
		sql = "select IDCard from Phy_CheckUpMaster_Web where RegID=?";
		jdbcTemplate.query(sql, new Object[] { regID },
				new RowCallbackHandler() {
					public void processRow(ResultSet res) throws SQLException {
						patient.setIdCard(res.getString("IDCard"));
					}
				});
		return patient.getIdCard();
	}

	/**
	 * 查找是否存在该登记号
	 * 
	 * @param regID
	 * @return
	 */
	public int queryRegID(String regID) {
		sql = "select count(*) from Phy_CheckUpMaster_Web where RegID=?";
		return jdbcTemplate.queryForInt(sql, regID);
	}

	/**
	 * 是否存在该身份证号
	 * 
	 * @param IDCard
	 * @return
	 */
	public int queryIDCard(String IDCard) {
		sql = "select count(*) from Phy_CheckUpMaster_Web where IDCard=?";
		return jdbcTemplate.queryForInt(sql, IDCard);
	}

	/**
	 * 根据身份证号获取最新的登记号
	 * 
	 * @param IDCard
	 * @return
	 */
	public String getCurrentRegIDByIDCard(String IDCard) {
		sql = " select * from Phy_CheckUpMaster_Web where IDCard=? "
				+ "order by PhyDate desc";
		List<ViewPhyCheckMaster> regIDList = jdbcTemplate.query(sql,
				new Object[] { IDCard },
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return regIDList.size() > 0 ? regIDList.get(0).getRegID() : null;
	}

	/**
	 * 是否存在某一个字段的值
	 * 
	 * @param IDCard
	 * @return
	 */
	public int queryProperty(String columnName, String value) {
		sql = "select count(*) from Phy_CheckUpMaster_Web where " + columnName
				+ "=?";
		return jdbcTemplate.queryForInt(sql, value);
	}

}
