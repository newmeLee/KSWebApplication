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
	 * ��ȡ��λ��Ա��Ϣ
	 * 
	 * @param unitID
	 * @return
	 */
	public List<ViewPhyCheckMaster> queryUnitMaster(String unitID) {
		sql = "SELECT * FROM Phy_CheckUpMaster_Web WHERE acpUnitID=? ";
		return queryResult(sql, unitID);
	}

	/**
	 * ����ѡ���ʱ��β�ѯ��λ����û���Ϣ
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
				+ "' and FCStatus='���ܼ�' ORDER BY PhyDate";
		List<ViewPhyCheckMaster> masterList = jdbcTemplate.query(sql,
				new Object[] { unitID },
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return masterList;
	}

	/**
	 * ��ȡָ����λ�µ���Ա�������Ϣ
	 * 
	 * @param unitID
	 * @return
	 */

	/**
	 * ��ѯ����
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
	 * ��ȡ��쵥λ��Ϣ
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
	 * �ж��Ƿ���ڵ�λ���
	 * 
	 * @return
	 */
	public int queryUnitID(String unitID) {
		sql = "select count(*) from Phy_AcceptUnit where code=?";
		return jdbcTemplate.queryForInt(sql, unitID);
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public List<PhyAbnoResult> getAbnoResult(String unitID, String startDate,
			String endDate) {
		sql = "select regID,Diagnose from Phy_ChkAbnoResult where RegID in "
				+ "( SELECT  RegID FROM Phy_CheckUpMaster_Web WHERE acpUnitID =? "
				+ " and phyDate between '" + startDate + "' and '" + endDate
				+ "' and FCStatus='���ܼ�')";
		List<PhyAbnoResult> abnoList = jdbcTemplate.query(sql,
				new String[] { unitID },
				new BeanPropertyRowMapper<PhyAbnoResult>(PhyAbnoResult.class));
		return abnoList;
	}

	/**
	 * ���ݹؼ���ģ����ѯ��λ��Ϣ
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
	 * ���ݵ�λ��Ų�ѯ��λ��������Ϣ
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
