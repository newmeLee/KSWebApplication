package cn.zjzt.service;

import java.util.List;

import cn.zjzt.dao.PhyUnitCheckDao;
import cn.zjzt.entity.PhyAbnoResult;
import cn.zjzt.entity.PhyAcceptUnit;
import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.ViewPhyCheckMaster;

public class PhyUnitCheckService {
	private PhyUnitCheckDao unitCheckDao;

	public void setUnitCheckDao(PhyUnitCheckDao unitCheckDao) {
		this.unitCheckDao = unitCheckDao;
	}

	private ValidateInfo validateInfo;

	public void setValidateInfo(ValidateInfo validateInfo) {
		this.validateInfo = validateInfo;
	}

	/**
	 * ��ȡ�õ�λ�µ���Ա�����Ϣ
	 * 
	 * @param unitID
	 * @return
	 */
	public List<ViewPhyCheckMaster> getUnitEmplInfo(String unitID) {
		return unitCheckDao.queryUnitMaster(unitID);
	}

	/**
	 * ��ȡָ��ʱ����ڵĸõ�λ�µ���Ա�����Ϣ
	 * 
	 * @param unitID
	 * @return
	 */
	public List<ViewPhyCheckMaster> getUnitEmplInfo(String unitID,
			String startDate, String endDate) {
		List<ViewPhyCheckMaster> list = unitCheckDao.queryUnitMaster(unitID,
				startDate, endDate);
		List<PhyAbnoResult> abnoResult = unitCheckDao.getAbnoResult(unitID,
				startDate, endDate);
		for (ViewPhyCheckMaster master : list) {
			String masterAbno="";
			for (int i=0;i<abnoResult.size();i++) {
				if(master.getRegID().equals(abnoResult.get(i).getRegID())){
					masterAbno+=abnoResult.get(i).getDiagnose()+"<br>";
				}
			}
			master.setAbnoResult(masterAbno);
		}
		return list;
	}

	/**
	 * ��ȡ��λ��Ϣ
	 * 
	 * @param unitID
	 * @return
	 */
	public PhyAcceptUnit getUnitInfo(String unitID) {
		return unitCheckDao.queryUnitInfo(unitID);
	}

	/**
	 * ��֤��λ��ѯ��������Ϣ������
	 * 
	 * @param unitID
	 * @param password
	 * @return
	 */
	public ValidateInfo validateUnitInfo(String unitID, String password) {
		if (unitCheckDao.queryUnitID(unitID) < 1) {
			validateInfo.setStatus(0);
			validateInfo.setMessage("�����ڸõ�λ�����鵥λ����Ƿ���ȷ��");
			return validateInfo;
		} else if (password.equals(unitCheckDao.queryUnitInfo(unitID)
				.getPhone())) {
			// �����ѯ����͵�λ��ϵ�绰��ͬ����Ϊ������ȷ
			validateInfo.setStatus(2);
			validateInfo.setMessage("��ѯ��Ϣ��֤��ȷ��");
			return validateInfo;
		} else {
			validateInfo.setStatus(1);
			validateInfo.setMessage("�������");
			return validateInfo;
		}
	}
	/**
	 * ���ݹؼ��ֲ��ҵ�λ�б�
	 * @param keyword
	 * @return
	 */
	public List<PhyAcceptUnit> getUnitListByKeyword(String keyword){
		return unitCheckDao.getUnitInfo(keyword);
	}
	/**
	 * ���ݵ�λ��Ų��ҵ�λ��������Ϣ
	 * @param code
	 * @return
	 */
	public PhyAcceptUnit getUnitInfoByCode(String code){
		return unitCheckDao.getUnitInfoByCode(code);
	}
}
