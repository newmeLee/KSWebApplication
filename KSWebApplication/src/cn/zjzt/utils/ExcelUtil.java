package cn.zjzt.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * �������ݵ�Excel�����
 * @author liyumin
 *
 */
public class ExcelUtil {
    /**
     * ������д�뵽Excel�ļ�
     * @param filePath �ļ�·��
     * @param sheetName ����������
     * @param title �����������
     * @param data ����������
     * @throws FileNotFoundException �ļ��������쳣
     * @throws IOException IO�쳣
     */
    public void writeToFile(String filePath,String[] sheetName,List<? extends Object[]> title,
    		List<? extends List<? extends Object[]>> data) 
    				throws FileNotFoundException, IOException{
        //��������ȡ����������
        Workbook wb=getWorkBook( sheetName, title, data);
        //д�뵽�ļ�
        FileOutputStream out=new FileOutputStream(filePath);
        wb.write(out);
        out.close();
    }
    /**
     * ��������������<br>
     * <font color="red">���������ƣ���������⣬��������������ܹ���Ӧ����</font><br>
     * ����������ͬ����ͬ�Ĺ��������ƣ����鲻ͬ����ͬ�Ĺ�������⣬���鲻ͬ����ͬ�Ĺ���������<br>
     * <b>
     * ע�⣺<br>
     * ��ҪΪÿ��������ָ��<font color="red">���������ƣ���������⣬����������</font><br>
     * ������������Ŀ���ڹ��������ݵļ��ϣ���ô���Ȼ����˳��һһ������Ӧ�Ĺ��������ƺ����ݼ��ϣ�Ȼ�󴴽��Ĺ�����������û�����ݵ�<br>
     * ������������ĿС�ڹ��������ݵļ��ϣ���ô��������ݽ�����д�빤������
     * </b>
     * @param sheetName ���������Ƶ�����
     * @param title ÿ�����������Ƶ����鼯��
     * @param data ÿ�����������ݵļ��ϵļ���
     * @return Workbook������
     * @throws FileNotFoundException �ļ��������쳣
     * @throws IOException IO�쳣
     */
    @SuppressWarnings("deprecation")
	public Workbook getWorkBook(String[] sheetName,List<? extends Object[]> title,
			List<? extends List<? extends Object[]>> data) 
					throws FileNotFoundException, IOException{
         
        //������������֧��2007���Ժ���ĵ���ʽ
        Workbook wb = new XSSFWorkbook();
        //����һ��������sheet
        Sheet sheet = null;
        //������
        Row row = null;
        //������Ԫ��
        Cell cell = null;
        //��Ԫ����ʽ
        CellStyle titleStyle=wb.createCellStyle();
        CellStyle cellStyle=wb.createCellStyle();
        //������ʽ
        Font font=wb.createFont();
        //����
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        titleStyle.setFont(font);
        //ˮƽ����  
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //��ֱ����  
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
         
        //ˮƽ����  
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //��ֱ����  
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
         
        //��������
        Object[] title_temp=null;
         
        //������
        Object[] rowData=null;
         
        //����������
        List<? extends Object[]> sheetData=null;
         
        //����sheet
        for(int sheetNumber=0;sheetNumber<sheetName.length;sheetNumber++){
            //����������
            sheet=wb.createSheet();
            //���ù���������
            wb.setSheetName(sheetNumber, sheetName[sheetNumber]);
            //���ñ���
            title_temp=title.get(sheetNumber);
            row=sheet.createRow(0);
 
            //д�����
            for(int i=0;i<title_temp.length;i++){
                cell=row.createCell(i);
                cell.setCellStyle(titleStyle);
                cell.setCellValue(title_temp[i].toString());
            }
             
            try {
                sheetData=data.get(sheetNumber);
            } catch (Exception e) {
                continue;
            }
            //д��������
            for(int rowNumber=0;rowNumber<sheetData.size();rowNumber++){
                //���û�б���������ʼ�о���0������б��������кž�Ӧ��Ϊ1
                row=sheet.createRow(title_temp==null?rowNumber:(rowNumber+1));
                rowData=sheetData.get(rowNumber);
                for(int columnNumber=0;columnNumber<rowData.length;columnNumber++){
                    cell=row.createCell(columnNumber);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(rowData[columnNumber].toString());
                }
            }
        }
        return wb;
}
}
