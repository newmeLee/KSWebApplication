package cn.zjzt.entity;

import java.util.Date;

/**
 * 报告结果视图，真正的体检报告的结果
 * 
 * @author Administrator
 * 
 */
public class ViewPhyCheckResult {
	
	private String RegID;
	private String PhyID;
	private String PtSex;
	private String PtAge;
	private String DeptID;
	private String deptName;
	private String TemplateName;
	private String GroupID;
	private String groupName;
	private String TariffID;
	private String TariffName;
	private String Unit;
	private String NaturalResult;
	private String ChkResult;
	private String lastChkResult;//上一次体检结果
	private String ChkDescribe;
	private String ChkRtCode;
	private String LISResult;
	private String referLow;
	private String referHigh;
	private String referArea;
	private String LowPrompt;
	private String HighPrompt;
	private String MinValue;
	private String MaxValue;
	private String AbnormityResult;
	private String ChkRtPrompt;
	private String ChkRtColor;
	private String ChkDoctor;
	private Date ChkDate;
	private String ChkGrpResult;
	private String ChkGrpAdvice;
	private String DefaultNaturalResult;
	private String ChkSummary;
	private String ChkDAdvice;
	private String Category;
	private String TradeType;
	private String ChkOPName;
	private String ChkOprator;
	private Date ChkResultDate;

	public String getRegID() {
		return RegID;
	}

	public void setRegID(String regID) {
		RegID = regID;
	}

	public String getPhyID() {
		return PhyID;
	}

	public void setPhyID(String phyID) {
		PhyID = phyID;
	}

	public String getPtSex() {
		return PtSex;
	}

	public void setPtSex(String ptSex) {
		PtSex = ptSex;
	}

	public String getPtAge() {
		return PtAge;
	}

	public void setPtAge(String ptAge) {
		PtAge = ptAge;
	}

	public String getDeptID() {
		return DeptID;
	}

	public void setDeptID(String deptID) {
		DeptID = deptID;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTemplateName() {
		return TemplateName;
	}

	public void setTemplateName(String templateName) {
		TemplateName = templateName;
	}

	public String getGroupID() {
		return GroupID;
	}

	public void setGroupID(String groupID) {
		GroupID = groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}



	public String getTariffID() {
		return TariffID;
	}

	public void setTariffID(String tariffID) {
		TariffID = tariffID;
	}

	public String getTariffName() {
		return TariffName;
	}

	public void setTariffName(String tariffName) {
		this.TariffName = tariffName;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getNaturalResult() {
		return NaturalResult;
	}

	public void setNaturalResult(String naturalResult) {
		NaturalResult = naturalResult;
	}

	public String getChkResult() {
		return ChkResult;
	}

	public void setChkResult(String chkResult) {
		ChkResult = chkResult;
	}
	
	public String getLastChkResult() {
		return lastChkResult;
	}

	public void setLastChkResult(String lastChkResult) {
		this.lastChkResult = lastChkResult;
	}

	public String getChkDescribe() {
		return ChkDescribe;
	}

	public void setChkDescribe(String chkDescribe) {
		ChkDescribe = chkDescribe;
	}

	public String getChkRtCode() {
		return ChkRtCode;
	}

	public void setChkRtCode(String chkRtCode) {
		ChkRtCode = chkRtCode;
	}

	public String getLISResult() {
		return LISResult;
	}

	public void setLISResult(String lISResult) {
		LISResult = lISResult;
	}


	public String getReferLow() {
		return referLow;
	}

	public void setReferLow(String referLow) {
		this.referLow = referLow;
	}

	public String getReferHigh() {
		return referHigh;
	}

	public void setReferHigh(String referHigh) {
		this.referHigh = referHigh;
	}

	public String getReferArea() {
		return referArea;
	}

	public void setReferArea(String referArea) {
		this.referArea = referArea;
	}

	public String getLowPrompt() {
		return LowPrompt;
	}

	public void setLowPrompt(String lowPrompt) {
		LowPrompt = lowPrompt;
	}

	public String getHighPrompt() {
		return HighPrompt;
	}

	public void setHighPrompt(String highPrompt) {
		HighPrompt = highPrompt;
	}

	public String getMinValue() {
		return MinValue;
	}

	public void setMinValue(String minValue) {
		MinValue = minValue;
	}

	public String getMaxValue() {
		return MaxValue;
	}

	public void setMaxValue(String maxValue) {
		MaxValue = maxValue;
	}

	public String getAbnormityResult() {
		return AbnormityResult;
	}

	public void setAbnormityResult(String abnormityResult) {
		AbnormityResult = abnormityResult;
	}

	public String getChkRtPrompt() {
		return ChkRtPrompt;
	}

	public void setChkRtPrompt(String chkRtPrompt) {
		ChkRtPrompt = chkRtPrompt;
	}

	public String getChkRtColor() {
		return ChkRtColor;
	}

	public void setChkRtColor(String chkRtColor) {
		ChkRtColor = chkRtColor;
	}

	public String getChkDoctor() {
		return ChkDoctor;
	}

	public void setChkDoctor(String chkDoctor) {
		ChkDoctor = chkDoctor;
	}

	public Date getChkDate() {
		return ChkDate;
	}

	public void setChkDate(Date chkDate) {
		ChkDate = chkDate;
	}

	public String getChkGrpResult() {
		return ChkGrpResult;
	}

	public void setChkGrpResult(String chkGrpResult) {
		ChkGrpResult = chkGrpResult;
	}

	public String getChkGrpAdvice() {
		return ChkGrpAdvice;
	}

	public void setChkGrpAdvice(String chkGrpAdvice) {
		ChkGrpAdvice = chkGrpAdvice;
	}

	public String getDefaultNaturalResult() {
		return DefaultNaturalResult;
	}

	public void setDefaultNaturalResult(String defaultNaturalResult) {
		DefaultNaturalResult = defaultNaturalResult;
	}

	public String getChkSummary() {
		return ChkSummary;
	}

	public void setChkSummary(String chkSummary) {
		ChkSummary = chkSummary;
	}

	public String getChkDAdvice() {
		return ChkDAdvice;
	}

	public void setChkDAdvice(String chkDAdvice) {
		ChkDAdvice = chkDAdvice;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getTradeType() {
		return TradeType;
	}

	public void setTradeType(String tradeType) {
		TradeType = tradeType;
	}

	public String getChkOPName() {
		return ChkOPName;
	}

	public void setChkOPName(String chkOPName) {
		ChkOPName = chkOPName;
	}


	public String getChkOprator() {
		return ChkOprator;
	}

	public void setChkOprator(String chkOprator) {
		ChkOprator = chkOprator;
	}

	public Date getChkResultDate() {
		return ChkResultDate;
	}

	public void setChkResultDate(Date chkResultDate) {
		ChkResultDate = chkResultDate;
	}

}
