package cn.zjzt.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.ViewPhyCheckMaster;
import cn.zjzt.entity.ViewPhyCheckResult;
import cn.zjzt.service.PatientService;
import cn.zjzt.service.PhyCheckService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ExportExcelAction extends ActionSupport {

	private String regID;

	public String getRegID() {
		return regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	private InputStream excelStream; // 输出流变量
	private String excelFileName; // 下载文件名

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	private PhyCheckService phyCheckService;
	private PatientService patientService;
	private List<ViewPhyCheckResult> checkList = null;
	private List<ViewPhyCheckResult> deptList = null;
	private List<ViewPhyCheckResult> groupList = null;
	private ViewPhyCheckMaster userInfo = null;
	private SimpleDateFormat format=null;
	public ExportExcelAction() {
		// TODO Auto-generated constructor stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		phyCheckService = (PhyCheckService) context.getBean("phyCheckService");
		patientService = (PatientService) context.getBean("patientService");
		format=new SimpleDateFormat("yyyy/MM/dd");
	}
	/**
	 * 个人报告导出excel
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String exportExcel() {
		try {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet，对应Excel文件中的 sheet
			HSSFSheet sheet = wb.createSheet("体检结果");
			// 第三步，在sheet中添加表头第0行，注意老版本poi对Excel的行数列数有限制
			HSSFRow row = sheet.createRow(2);
			// 第四步，创建单元格样式：居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			HSSFCellStyle departStyle = wb.createCellStyle();
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			departStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			HSSFFont font = wb.createFont();
			font.setFontHeightInPoints((short) 12);
			// 把字体应用到当前的样式
			departStyle.setFont(font);
			// 获取体检结果
			queryCheckResult();
			//
			row = sheet.createRow(0);
			row.createCell(0).setCellValue("姓名："+userInfo.getPtName());
			row.createCell(1).setCellValue("性别："+userInfo.getPtSex());
			row.createCell(2).setCellValue("年龄："+userInfo.getAge());
			row.createCell(3).setCellValue("体检套餐:"+deptList.get(0).getTemplateName());
			int rowIndex = 2;
			if (deptList.size() > 0) {// 科室
				for (int i = 0; i < deptList.size(); i++) {
					row = sheet.createRow(rowIndex);
					rowIndex++;
					row.createCell(0).setCellStyle(departStyle);
					row.createCell(0).setCellValue("科室：" + deptList.get(i).getDeptName());
					sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex-1, 0, 6));//合并单元格
					for (int j = 0; j < groupList.size(); j++) {
						if (groupList.get(j).getDeptID().equals(deptList.get(i).getDeptID())) {
							row = sheet.createRow(rowIndex);
							rowIndex++;
							row.createCell(0).setCellValue("项目组合："+ groupList.get(j).getGroupName());
							sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex-1, 0, 6));//合并单元格
							row=sheet.createRow(rowIndex);
							rowIndex++;
							// 第五步，创建表头单元格，并设置样式
							HSSFCell headCell;
							headCell = row.createCell(0);
							headCell.setCellValue("项目名称");
							headCell.setCellStyle(style);
							headCell = row.createCell(1);
							headCell.setCellValue("参考范围");
							headCell.setCellStyle(style);

							headCell = row.createCell(2);
							headCell.setCellValue("单位");
							headCell.setCellStyle(style);

							headCell = row.createCell(3);
							headCell.setCellValue("异常提示");
							headCell.setCellStyle(style);

							headCell = row.createCell(4);
							headCell.setCellValue("结果");
							headCell.setCellStyle(style);

							headCell = row.createCell(5);
							headCell.setCellValue("上次体检结果");
							headCell.setCellStyle(style);
							for (int index = 0; index < checkList.size(); index++) {
								if (checkList.get(index).getGroupID().equals(groupList.get(j).getGroupID())) {
									
									row = sheet.createRow(rowIndex);
									rowIndex++;
									row.createCell(0)
											.setCellValue(
													checkList.get(index)
															.getTariffName() != null ? checkList
															.get(index)
															.getTariffName()
															: "");
									row.createCell(1)
											.setCellValue(
													checkList.get(index)
															.getReferArea() != null ? checkList
															.get(index)
															.getReferArea()
															: "");
									row.createCell(2)
											.setCellValue(
													checkList.get(index)
															.getUnit() != null ? checkList
															.get(index)
															.getUnit() : "");
									row.createCell(3)
											.setCellValue(
													checkList.get(index)
															.getChkRtPrompt() != null ? checkList
															.get(index)
															.getChkRtPrompt()
															: "");
									row.createCell(4)
											.setCellValue(
													checkList.get(index)
															.getChkResult() != null ? checkList
															.get(index)
															.getChkResult()
															: "");
									row.createCell(5).setCellValue(checkList.get(index).getLastChkResult() != null ? checkList
															.get(index)
															.getLastChkResult()
															: "");
								}
							}
							//项目小结部分：
							row=sheet.createRow(rowIndex);
							row.createCell(0).setCellValue("项目小结："+groupList.get(j).getChkGrpResult());
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 10));
							rowIndex++;
							row=sheet.createRow(rowIndex);
							row.createCell(0).setCellValue("小结医生："+groupList.get(j).getChkDoctor());
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 10));
							rowIndex++;
							row=sheet.createRow(rowIndex);
							row.createCell(0).setCellValue("小结日期：");
							row.createCell(1).setCellValue(format.format(groupList.get(j).getChkDate()));
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 10));
							rowIndex++;
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 10));
							rowIndex++;
						}
					}
				}
			}
			//小结
			rowIndex++;
			row=sheet.createRow(rowIndex);
			row.createCell(0).setCellValue("体检小结：");
			//获取小结
			String summary="";
			if (deptList.size()>0) {
				for (int p = 0; p < deptList.size(); p++) {
				if ((deptList.get(p).getChkSummary() == null)||
					(deptList.get(p).getChkSummary().trim().isEmpty())) {
						continue;
					}
					summary+=deptList.get(p).getChkSummary().trim()+";";
				}
			}
			row.createCell(1).setCellValue(summary);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 10));//合并单元格
			rowIndex++;
			//建议
			row=sheet.createRow(rowIndex);
			row.createCell(0).setCellValue("建议：");
			//获取建议
			String advice="";
			if (deptList.size()>0) {
				for (int p = 0; p < deptList.size(); p++) {
				if ((deptList.get(p).getChkDAdvice() == null)||
					(deptList.get(p).getChkDAdvice().trim().isEmpty())) {
						continue;
					}
					advice+=deptList.get(p).getChkDAdvice().trim();
				}
			}
			row.createCell(1).setCellValue(advice);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 10));//合并单元格
			rowIndex++;
			
			// 第七步，将文件存到流中
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] fileContent = os.toByteArray();
			ByteArrayInputStream is = new ByteArrayInputStream(fileContent);

			excelStream = is; // 文件流
			excelFileName = new String(userInfo.getPtName().getBytes("gb2312"),
					"iso8859-1") + regID + ".xls"; // 设置下载的文件名(登记号)
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 根据字段名和字段值获取用户体检信息
	 * 
	 * @param columnName
	 * @param value
	 */
	private void queryCheckResult() {
		// 获取所有的体检结果
		checkList = phyCheckService.getAllViewResult(regID);
		// 获取所有的检查科室
		deptList = phyCheckService
				.getDeptOrGroupListResult("deptID", checkList);
		// 获取所有的项目组合
		groupList = phyCheckService.getDeptOrGroupListResult("groupID",
				checkList);
		// 获取用户信息
		userInfo = patientService.qureyPatient(regID);
		// 将结果存储到域对象中以供调用
		ActionContext context = ActionContext.getContext();
		context.put("checkList", checkList);// 检查结果
		context.put("deptList", deptList);
		context.put("groupList", groupList);
		context.put("userInfo", userInfo);
		//
	}
	
	
}
