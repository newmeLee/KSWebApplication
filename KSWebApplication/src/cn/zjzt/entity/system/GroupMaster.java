package cn.zjzt.entity.system;

public class GroupMaster {
	private String Code;
	private String Name;
	private double Price;
	private double mbPrice;
	private String ApplySex;
	private String Category;
	private String GrpSort;
	private String DeptID;
	private String DefaultNaturalResult;
	private String ClinicDefine;
	private String UseMsg;
	private String RowNum;
	private String Status;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public double getMbPrice() {
		return mbPrice;
	}
	public void setMbPrice(double mbPrice) {
		this.mbPrice = mbPrice;
	}
	public String getApplySex() {
		return ApplySex;
	}
	public void setApplySex(String applySex) {
		ApplySex = applySex;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getGrpSort() {
		return GrpSort;
	}
	public void setGrpSort(String grpSort) {
		GrpSort = grpSort;
	}
	public String getDeptID() {
		return DeptID;
	}
	public void setDeptID(String deptID) {
		DeptID = deptID;
	}
	public String getDefaultNaturalResult() {
		return DefaultNaturalResult;
	}
	public void setDefaultNaturalResult(String defaultNaturalResult) {
		DefaultNaturalResult = defaultNaturalResult;
	}
	public String getClinicDefine() {
		return ClinicDefine;
	}
	public void setClinicDefine(String clinicDefine) {
		ClinicDefine = clinicDefine;
	}
	public String getUseMsg() {
		return UseMsg;
	}
	public void setUseMsg(String useMsg) {
		UseMsg = useMsg;
	}
	public String getRowNum() {
		return RowNum;
	}
	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

}
