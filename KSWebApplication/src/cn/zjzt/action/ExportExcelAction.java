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

	private InputStream excelStream; // ���������
	private String excelFileName; // �����ļ���

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
	 * ���˱��浼��excel
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String exportExcel() {
		try {
			// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
			HSSFWorkbook wb = new HSSFWorkbook();
			// �ڶ�������webbook�����һ��sheet����ӦExcel�ļ��е� sheet
			HSSFSheet sheet = wb.createSheet("�����");
			// ����������sheet����ӱ�ͷ��0�У�ע���ϰ汾poi��Excel����������������
			HSSFRow row = sheet.createRow(2);
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
			queryCheckResult();
			//
			row = sheet.createRow(0);
			row.createCell(0).setCellValue("������"+userInfo.getPtName());
			row.createCell(1).setCellValue("�Ա�"+userInfo.getPtSex());
			row.createCell(2).setCellValue("���䣺"+userInfo.getAge());
			row.createCell(3).setCellValue("����ײ�:"+deptList.get(0).getTemplateName());
			int rowIndex = 2;
			if (deptList.size() > 0) {// ����
				for (int i = 0; i < deptList.size(); i++) {
					row = sheet.createRow(rowIndex);
					rowIndex++;
					row.createCell(0).setCellStyle(departStyle);
					row.createCell(0).setCellValue("���ң�" + deptList.get(i).getDeptName());
					sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex-1, 0, 6));//�ϲ���Ԫ��
					for (int j = 0; j < groupList.size(); j++) {
						if (groupList.get(j).getDeptID().equals(deptList.get(i).getDeptID())) {
							row = sheet.createRow(rowIndex);
							rowIndex++;
							row.createCell(0).setCellValue("��Ŀ��ϣ�"+ groupList.get(j).getGroupName());
							sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex-1, 0, 6));//�ϲ���Ԫ��
							row=sheet.createRow(rowIndex);
							rowIndex++;
							// ���岽��������ͷ��Ԫ�񣬲�������ʽ
							HSSFCell headCell;
							headCell = row.createCell(0);
							headCell.setCellValue("��Ŀ����");
							headCell.setCellStyle(style);
							headCell = row.createCell(1);
							headCell.setCellValue("�ο���Χ");
							headCell.setCellStyle(style);

							headCell = row.createCell(2);
							headCell.setCellValue("��λ");
							headCell.setCellStyle(style);

							headCell = row.createCell(3);
							headCell.setCellValue("�쳣��ʾ");
							headCell.setCellStyle(style);

							headCell = row.createCell(4);
							headCell.setCellValue("���");
							headCell.setCellStyle(style);

							headCell = row.createCell(5);
							headCell.setCellValue("�ϴ������");
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
							//��ĿС�Ჿ�֣�
							row=sheet.createRow(rowIndex);
							row.createCell(0).setCellValue("��ĿС�᣺"+groupList.get(j).getChkGrpResult());
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 10));
							rowIndex++;
							row=sheet.createRow(rowIndex);
							row.createCell(0).setCellValue("С��ҽ����"+groupList.get(j).getChkDoctor());
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 10));
							rowIndex++;
							row=sheet.createRow(rowIndex);
							row.createCell(0).setCellValue("С�����ڣ�");
							row.createCell(1).setCellValue(format.format(groupList.get(j).getChkDate()));
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 10));
							rowIndex++;
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 10));
							rowIndex++;
						}
					}
				}
			}
			//С��
			rowIndex++;
			row=sheet.createRow(rowIndex);
			row.createCell(0).setCellValue("���С�᣺");
			//��ȡС��
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
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 10));//�ϲ���Ԫ��
			rowIndex++;
			//����
			row=sheet.createRow(rowIndex);
			row.createCell(0).setCellValue("���飺");
			//��ȡ����
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
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 10));//�ϲ���Ԫ��
			rowIndex++;
			
			// ���߲������ļ��浽����
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] fileContent = os.toByteArray();
			ByteArrayInputStream is = new ByteArrayInputStream(fileContent);

			excelStream = is; // �ļ���
			excelFileName = new String(userInfo.getPtName().getBytes("gb2312"),
					"iso8859-1") + regID + ".xls"; // �������ص��ļ���(�ǼǺ�)
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * �����ֶ������ֶ�ֵ��ȡ�û������Ϣ
	 * 
	 * @param columnName
	 * @param value
	 */
	private void queryCheckResult() {
		// ��ȡ���е������
		checkList = phyCheckService.getAllViewResult(regID);
		// ��ȡ���еļ�����
		deptList = phyCheckService
				.getDeptOrGroupListResult("deptID", checkList);
		// ��ȡ���е���Ŀ���
		groupList = phyCheckService.getDeptOrGroupListResult("groupID",
				checkList);
		// ��ȡ�û���Ϣ
		userInfo = patientService.qureyPatient(regID);
		// ������洢����������Թ�����
		ActionContext context = ActionContext.getContext();
		context.put("checkList", checkList);// �����
		context.put("deptList", deptList);
		context.put("groupList", groupList);
		context.put("userInfo", userInfo);
		//
	}
	
	
}
