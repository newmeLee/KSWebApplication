package cn.zjzt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import cn.zjzt.entity.PhyCheckUpList;
import cn.zjzt.entity.ViewPhyCheckMaster;
import cn.zjzt.entity.ViewPhyCheckResult;

public class PhyCheckDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setCheckUpList(PhyCheckUpList checkUpList) {
	}

	private String sql = null;
	private String colName = null;
	private Object result = null;

	/**
	 * 
	 * @param phyID
	 * @return
	 */
	public List<PhyCheckUpList> queryCheckResult(String phyID) {
		sql = "SELECT RegID,PhyID,PtSex,TemplateName,GroupID,TariffID,TariffName,NaturalResult,ChkResult,"
				+ "AbnormityResult,ChkRtColor,ChkRtPrompt,ChkDoctor,ChkDate,ChkGrpResult,ChkSummary,ChkDAdvice,ChkOPName,"
				+ "ChkOPDate,TradeType FROM Phy_CheckUpList WHERE PhyID=?";
		List<PhyCheckUpList> list = jdbcTemplate
				.query(sql, new Object[] { phyID },
						new BeanPropertyRowMapper<PhyCheckUpList>(
								PhyCheckUpList.class));
		return list;
	}

	/**
	 * ��ȡ��ͼ�е���챨������
	 * 
	 * @param phyID
	 * @return
	 */
	public List<ViewPhyCheckResult> queryAllViewCheckResult(String regID) {
		sql = "SELECT * FROM Phy_CheckUpList_Web " + "WHERE regID=?  "
				+ "and DeptID not in ('42','51') "
				+ " ORDER BY deptRowNum,groupRowNum,tariffRowNum";
		return queryResult(sql, regID);
	}

	/**
	 * �������ǼǺŻ�ȡ�����Ŀ�źͽ��(����������Ա�)
	 * 
	 * @param regID
	 * @return
	 */
	public List<ViewPhyCheckResult> queryLastCheckResult(String regID) {
		sql = "SELECT TariffID,ChkResult FROM Phy_CheckUpList_Web "
				+ "WHERE regID=? "
				+ "ORDER BY deptRowNum,groupRowNum,tariffRowNum";
		return queryResult(sql, regID);
	}

	/**
	 * ��ѯ����
	 * 
	 * @param sql
	 * @return
	 */
	private List<ViewPhyCheckResult> queryResult(String sql, String regID) {
		List<ViewPhyCheckResult> resultList = jdbcTemplate.query(sql,
				new String[] { regID },
				new BeanPropertyRowMapper<ViewPhyCheckResult>(
						ViewPhyCheckResult.class));
		return resultList;
	}

	/**
	 * ��ȡ�û���������¼
	 * 
	 * @return
	 */
	public List<ViewPhyCheckMaster> queryPhyResultRecords(String phyID) {
		sql = "select  RegID,PhyID,PhyTimes,"
				+ "PhyDate from Phy_CheckUpMaster_Web where PhyID=? "
				+ "order by PhyDate desc";
		List<ViewPhyCheckMaster> masterList = jdbcTemplate.query(sql,
				new Object[] { phyID },
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return masterList;
	}
	/**
	 * ��ȡ�û���������¼(�������֤��)
	 * @param IDCard
	 * @return
	 */
	public List<ViewPhyCheckMaster> queryPhyResultRecordsByIDCard(String IDCard) {
		sql = "select  RegID,PhyID,PhyTimes,"
				+ "PhyDate from Phy_CheckUpMaster_Web where IDCard=? "
				+ "order by PhyDate desc";
		List<ViewPhyCheckMaster> masterList = jdbcTemplate.query(sql,
				new Object[] { IDCard },
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return masterList;
	}
	
	/**
	 * ���ݵǼǺŻ�ȡĳһ���ֶ�ֵ
	 * 
	 * @return
	 */
	public Object queryPropertyByRegID(String columnName, String regID) {
		colName = columnName;
		sql = "select " + columnName + " from Phy_CheckUpMaster_Web"
				+ " where RegID=?";
		jdbcTemplate.query(sql, new Object[] { regID },
				new RowCallbackHandler() {
					public void processRow(ResultSet res) throws SQLException {
						result = res.getString(colName);
					}
				});
		return result;
	}

	/**
	 * ���ݵǼǺŲ��ҵ��������Ϣ
	 * 
	 * @return
	 */
	public ViewPhyCheckResult queryResultByRegID(String regID) {
		sql = "select top 1 regID,ChkSummary,ChkDAdvice "
				+ "from Phy_CheckUpList_Web where RegID=?";
		List<ViewPhyCheckResult> resultList = jdbcTemplate.query(sql,
				new Object[] { regID },
				new BeanPropertyRowMapper<ViewPhyCheckResult>(
						ViewPhyCheckResult.class));
		return resultList.size() > 0 ? resultList.get(0) : null;
	}

	/**
	 * �������֤�Ż�ȡ���еĵǼǺ�
	 * 
	 * @param IDCard
	 * @return
	 */
	public List<ViewPhyCheckMaster> queryRegIDByIDCard(String IDCard) {
		sql = " select RegID from Phy_CheckUpMaster_Web" + " where IDCard=? "
				+ "order by PhyDate desc";
		List<ViewPhyCheckMaster> regIDList = jdbcTemplate.query(sql,
				new Object[] { IDCard },
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return regIDList;
	}
	/**
	 * ��������ֶβ��ҵǼǺ�
	 * @param columnName
	 * @param value
	 * @return
	 */
	public List<ViewPhyCheckMaster> queryRegIDByProp(String columnName,
			String value) {
		sql = " select RegID from Phy_CheckUpMaster_Web  where " + columnName
				+ "=? order by PhyDate desc";
		List<ViewPhyCheckMaster> regIDList = jdbcTemplate.query(sql,
				new Object[] { value },
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return regIDList;
	}

	/**
	 * ��������ź���������ȡ��һ�����ĵǼǺ�
	 * 
	 * @param phyID
	 * @param phyTimes
	 * @return
	 */
	public String getLastRegID(String phyID, int phyTimes) {
		sql = "select RegID from Phy_CheckUpMaster_Web where phyID=? And PhyTimes=?";
		jdbcTemplate.query(sql, new Object[] { phyID, phyTimes },
				new RowCallbackHandler() {
					public void processRow(ResultSet res) throws SQLException {
						result = (res.getString("RegID"));
					}
				});
		return result.toString();
	}

	/**
	 * ��ȡ�û����ܼ��־
	 * 
	 * @param regID
	 * @return
	 */
	public String queryFCStatusByRegID(String regID) {
		sql = "select FCStatus from Phy_CheckUpMaster_Web " + "where RegID=?";
		jdbcTemplate.query(sql, new Object[] { regID },
				new RowCallbackHandler() {
					public void processRow(ResultSet res) throws SQLException {
						result = (res.getString("FCStatus"));
					}
				});
		return result.toString();
	}

	/**
	 * ��ȡ�û��������ͽ���
	 * 
	 * @param regID
	 * @return
	 */
	public List<ViewPhyCheckResult> getSummaryAndAdvice(String regID) {
		sql = "SELECT distinct(ChkSummary) ,ChkDAdvice FROM Phy_CheckUpList where RegID=?";
		List<ViewPhyCheckResult> summaryList = jdbcTemplate.query(sql,
				new String[] { regID },
				new BeanPropertyRowMapper<ViewPhyCheckResult>(
						ViewPhyCheckResult.class));
		return summaryList;
	}
	/**
	 * ����������ѯ�ͻ������Ϣ
	 * @param name
	 * @return
	 */
	public List<ViewPhyCheckMaster> getMasterByName(String name) {
		sql = " select * from Phy_CheckUpMaster_Web  where PtName like '%"
				+ name + "%' order by PhyDate desc";
		List<ViewPhyCheckMaster> masterList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ViewPhyCheckMaster>(
						ViewPhyCheckMaster.class));
		return masterList;
	}
}
