package cn.zjzt.action.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadAction extends ActionSupport {
	// 封装上传文件域的属性
	private File file;
	private String fileFileName;
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	// 接受依赖注入的属性
	private String savePath;
	//图片上传后用来展示的前缀
	private String imagePrefix;
	private String message="0";
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setImagePrefix(String imagePrefix) {
		this.imagePrefix = imagePrefix;
	}
	public String getSavePath() throws Exception {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	/**
	 * 设备图片上传
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String uploadPic(){
		FileInputStream inputStream=null;
		FileOutputStream outputStream=null;		
		//上传图片的保存路径，savePath在action配置文件中有定义
        String path = ServletActionContext.getRequest().getRealPath(savePath);
        File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        String[] fileSuffix = new String[] { ".exe" };// 禁止文件
        try {
            File f = this.getFile();
            //imageFileName=f.getName();
            //MyPrintUtil.printError(fileFileName);            
            // 判断文件格式
            for (int i = 0; i < fileSuffix.length; i++) {
                if (fileFileName.endsWith(fileSuffix[i])) {
                    message = "0";
                    return ERROR;
                }
            }
            inputStream = new FileInputStream(f);
            outputStream = new FileOutputStream(path + "\\"+ fileFileName);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
            message = imagePrefix + this.getFileFileName();
        } catch (Exception e) {
            e.printStackTrace();
            message = "2";
        }finally{
        	close(outputStream, inputStream);
        }
        return SUCCESS;
    }

	/**
	 * 关闭文件输入输出流
	 * 
	 * @param fos
	 * @param fis
	 */
	private void close(FileOutputStream fos, FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				System.out.println("FileInputStream关闭失败");
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				System.out.println("FileOutputStream关闭失败");
				e.printStackTrace();
			}
		}
	}
}
