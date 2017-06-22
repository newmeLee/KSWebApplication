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
	private InputStream excelStream; // ���������
	private String excelFileName; // �����ļ���\
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
			// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
			HSSFWorkbook wb = new HSSFWorkbook();
			// �ڶ�������webbook�����һ��sheet����ӦExcel�ļ��е� sheet
			HSSFSheet sheet = wb.createSheet("��λ������");
			// ����������sheet����ӱ�ͷ��0�У�ע���ϰ汾poi��Excel����������������
			HSSFRow row = sheet.createRow(0);
			// ���Ĳ���������Ԫ����ʽ������
			HSSFCellStyle style = wb.createCellStyle();
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			HSSFCellStyle departStyle = wb.createCellStyle();
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			departStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			HSSFFont font = wb.createFont();
			font.setFontHeightInPoints((short) 12);
			// ������Ӧ�õ���ǰ����ʽ
			departStyle.setFont(font);
			// ��ȡ�����
			getUnitInfo();
			//
			row.createCell(0).setCellValue("��λ��ţ�");
			row.createCell(1).setCellValue(unitInfo.getCode());
			row.createCell(2).setCellValue("��λ���ƣ�");
			row.createCell(3).setCellValue(unitInfo.getName());
			row.createCell(4).setCellValue("��λ���ͣ�");
			row.createCell(5).setCellValue(unitInfo.getProperty());
			row.createCell(6).setCellValue("��ϵ�绰��");
			row.createCell(7).setCellValue(unitInfo.getPhone());
			row.createCell(8).setCellValue("��ѯ����"+empList.size());
			// ���岽��������ͷ��Ԫ�񣬲�������ʽ
			row = sheet.createRow(2);
			HSSFCell headCell;
			headCell = row.createCell(0);
			headCell.setCellValue("��Ա����");
			headCell.setCellStyle(style);
			headCell = row.createCell(1);
			headCell.setCellValue("�������");
			headCell.setCellStyle(style);

			headCell = row.createCell(2);
			headCell.setCellValue("���С��");
			headCell.setCellStyle(style);
			sheet.addMergedRegion(new CellRangeAddress(2,2, 2, 4));
			headCell = row.createCell(5);
			headCell.setCellValue("��콨��");
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
			// ���߲������ļ��浽����
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] fileContent = os.toByteArray();
			ByteArrayInputStream is = new ByteArrayInputStream(fileContent);

			excelStream = is; // �ļ���
			excelFileName = new String(unitInfo.getName().getBytes("gb2312"),
					"iso8859-1") + unitInfo.getCode() + ".xls"; // �������ص��ļ���(�ǼǺ�)
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
