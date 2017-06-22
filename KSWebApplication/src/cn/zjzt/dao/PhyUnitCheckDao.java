package cn.zjzt.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.zjzt.entity.PhyAbnoResult;
import cn.zjzt.entity.PhyAcceptUnit;
import cn.zjzt.entity.ViewPhyCheckMaster;

public class PhyUnitCheckDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String sql = null;

	/**
	 * 获取单位人员信息
	 * 
	 * @param unitID
	 * @return
	 */
	public List<ViewPhyCheckMaster> queryUnitMaster(String unitID) {
		sql = "SELECT * FROM Phy_CheckUpMaster_Web WHERE acpUnitID=? ";
		return queryResult(sql, unitID);
	}

	/**
	 * 根据选择的时间段查询单位体检用户信息
	 * 
	 * @param unitID
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<ViewPhyCheckMaster> queryUnitMaster(String unitID,
			String startDate, String endDate) {
		sql = "SELECT RegID,PhyID,PtName,age,PtSex,PhyTimes,IDCard,AcpUnitID,PhyDate,FCStatus, "
				+ " chkSummary,chkAdvice FROM Phy_CheckUpMaster_Web WHERE acpUnitID=? "
				+ "and phyDate between '"
				+ startDate
				+ "' and '"
				+ endDate
				+ "' and FCStatus='已总检' ORDER BY PhyDate";
		List<ViewPhyCheckMaster> masterList = jdbcTemplate.query(sql,
				new Object[] { unitID },
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return masterList;
	}

	/**
	 * 获取指定单位下的人员的体检信息
	 * 
	 * @param unitID
	 * @return
	 */

	/**
	 * 查询数据
	 * 
	 * @param sql
	 * @return
	 */
	private List<ViewPhyCheckMaster> queryResult(String sql, String unitID) {
		List<ViewPhyCheckMaster> masterList = jdbcTemplate.query(sql,
				new Object[] { unitID },
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return masterList;
	}

	/**
	 * 获取体检单位信息
	 * 
	 * @param unitID
	 * @return
	 */
	public PhyAcceptUnit queryUnitInfo(String unitID) {
		sql = "select Code,Name,Principal,Address,Phone,MobileTel,"
				+ "EMail,Property,SalesForce,UpdateUser,SecondSales "
				+ "from Phy_AcceptUnit where Code=?";
		List<PhyAcceptUnit> unitList = jdbcTemplate.query(sql,
				new String[] { unitID },
				new BeanPropertyRowMapper<PhyAcceptUnit>(PhyAcceptUnit.class));
		return unitList.size() > 0 ? unitList.get(0) : null;
	}

	/**
	 * 判断是否存在单位编号
	 * 
	 * @return
	 */
	public int queryUnitID(String unitID) {
		sql = "select count(*) from Phy_AcceptUnit where code=?";
		return jdbcTemplate.queryForInt(sql, unitID);
	}

	/**
	 * 返回
	 * 
	 * @return
	 */
	public List<PhyAbnoResult> getAbnoResult(String unitID, String startDate,
			String endDate) {
		sql = "select regID,Diagnose from Phy_ChkAbnoResult where RegID in "
				+ "( SELECT  RegID FROM Phy_CheckUpMaster_Web WHERE acpUnitID =? "
				+ " and phyDate between '" + startDate + "' and '" + endDate
				+ "' and FCStatus='已总检')";
		List<PhyAbnoResult> abnoList = jdbcTemplate.query(sql,
				new String[] { unitID },
				new BeanPropertyRowMapper<PhyAbnoResult>(PhyAbnoResult.class));
		return abnoList;
	}

	/**
	 * 根据关键字模糊查询单位信息
	 * 
	 * @param keyword
	 * @return
	 */
	public List<PhyAcceptUnit> getUnitInfo(String keyword) {
		sql = "select * from Phy_AcceptUnit where Name like '%"+keyword+"%'";
		List<PhyAcceptUnit> unitList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<PhyAcceptUnit>(PhyAcceptUnit.class));
		return unitList;
	}

	/**
	 * 根据单位编号查询单位的所有信息
	 * 
	 * @param unitCode
	 * @return
	 */
	public PhyAcceptUnit getUnitInfoByCode(String unitCode) {
		sql = "select * from Phy_AcceptUnit where Code=?";
		List<PhyAcceptUnit> unitList = jdbcTemplate.query(sql,
				new String[] { unitCode },
				new BeanPropertyRowMapper<PhyAcceptUnit>(PhyAcceptUnit.class));
		return unitList.size() > 0 ? unitList.get(0) : null;
	}
}
