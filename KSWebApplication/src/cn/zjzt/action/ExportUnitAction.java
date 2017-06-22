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

import com.opensymphony.xwork2.ActionSupport;
import cn.zjzt.entity.PhyAcceptUnit;
import cn.zjzt.entity.ViewPhyCheckMaster;
import cn.zjzt.service.PhyUnitCheckService;

@SuppressWarnings("serial")
public class ExportUnitAction extends ActionSupport {
	private String unitID;
	private InputStream excelStream; // 输出流变量
	private String excelFileName; // 下载文件名\
	private String startDate;
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getUnitID() {
		return unitID;
	}

	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}

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

	private PhyUnitCheckService unitCheckService;
	private PhyAcceptUnit unitInfo = null;
	private List<ViewPhyCheckMaster> empList = null;
	private SimpleDateFormat format=null;
	public ExportUnitAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		unitCheckService = (PhyUnitCheckService) context
				.getBean("phyUnitCheckService");
		format=new SimpleDateFormat("yyyy/MM/dd");
	}

	/**
	 * 
	 * @return
	 */
	public String ExportUnitExcel() {
		try {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet，对应Excel文件中的 sheet
			HSSFSheet sheet = wb.createSheet("单位体检汇总");
			// 第三步，在sheet中添加表头第0行，注意老版本poi对Excel的行数列数有限制
			HSSFRow row = sheet.createRow(0);
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
			getUnitInfo();
			//
			row.createCell(0).setCellValue("单位编号：");
			row.createCell(1).setCellValue(unitInfo.getCode());
			row.createCell(2).setCellValue("单位名称：");
			row.createCell(3).setCellValue(unitInfo.getName());
			row.createCell(4).setCellValue("单位类型：");
			row.createCell(5).setCellValue(unitInfo.getProperty());
			row.createCell(6).setCellValue("联系电话：");
			row.createCell(7).setCellValue(unitInfo.getPhone());
			row.createCell(8).setCellValue("查询人数"+empList.size());
			// 第五步，创建表头单元格，并设置样式
			row = sheet.createRow(2);
			HSSFCell headCell;
			headCell = row.createCell(0);
			headCell.setCellValue("人员姓名");
			headCell.setCellStyle(style);
			headCell = row.createCell(1);
			headCell.setCellValue("体检日期");
			headCell.setCellStyle(style);

			headCell = row.createCell(2);
			headCell.setCellValue("体检小结");
			headCell.setCellStyle(style);
			sheet.addMergedRegion(new CellRangeAddress(2,2, 2, 4));
			headCell = row.createCell(5);
			headCell.setCellValue("体检建议");
			headCell.setCellStyle(style);
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 5, 7));
			
			int rowIndex = 3;
			if (empList != null) {
				if (empList.size() > 0) {
					for (int index = 0; index < empList.size(); index++) {
						row = sheet.createRow(rowIndex);
						row.createCell(0).setCellValue(
								empList.get(index).getPtName());
						
						row.createCell(1).setCellValue(format.format(
								empList.get(index).getPhyDate()));
						row.createCell(2).setCellValue(
								empList.get(index).getChkSummary());
						sheet.addMergedRegion(new CellRangeAddress(rowIndex,
								rowIndex, 2, 4));
						row.createCell(5).setCellValue(
								empList.get(index).getChkAdvice());
						sheet.addMergedRegion(new CellRangeAddress(rowIndex,
								rowIndex, 5, 7));
						rowIndex++;
					}
				}
			}
			// 第七步，将文件存到流中
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] fileContent = os.toByteArray();
			ByteArrayInputStream is = new ByteArrayInputStream(fileContent);

			excelStream = is; // 文件流
			excelFileName = new String(unitInfo.getName().getBytes("gb2312"),
					"iso8859-1") + unitInfo.getCode() + ".xls"; // 设置下载的文件名(登记号)
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private void getUnitInfo() {
		if (null != unitID) {
			unitInfo = unitCheckService.getUnitInfo(unitID);
		}
		if (startDate != null && endDate != null) {
			empList = unitCheckService.getUnitEmplInfo(unitID, startDate,
					endDate);
		}
	}
}
