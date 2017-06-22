package cn.zjzt.action.system;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.system.Depart;
import cn.zjzt.service.system.DepartService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DepartAction extends ActionSupport {
	private String departID;
	private String departName;
	private String intro;
	private ValidateInfo checkData;
	private Depart result;
	public Depart getResult() {
		return result;
	}
	public void setResult(Depart result) {
		this.result = result;
	}
	public ValidateInfo getCheckData() {
		return checkData;
	}
	public void setCheckData(ValidateInfo checkData) {
		this.checkData = checkData;
	}
	public String getDepartID() {
		return departID;
	}
	public void setDepartID(String departID) {
		this.departID = departID;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	private DepartService departService;

	public DepartAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		departService = (DepartService) context.getBean("departService");
	}
	/**
	 * ��ȡ���еĿ�����Ϣ
	 * @return
	 */
	public String getAllDepart(){
		ActionContext context=ActionContext.getContext();
		context.put("departList", departService.getAllDepart());
		return SUCCESS;
	}
	
	/**
	 * �������޸Ŀ�����Ϣ
	 * @return
	 */
	public String addOrUpdateDepart() {
		Depart depart=new Depart();
		if ((departID != null) && (departID.trim().length() > 0)) {
			depart.setId(Integer.parseInt(departID));
		}
		depart.setName(departName);
		depart.setIntro(intro);
		this.setCheckData(departService.addOrUpdateDepart(depart));
		return SUCCESS;
	}
	/**
	 * ��ȡ�������ҵ���Ϣ(�ڿ�����ϸҳ����ʾ)
	 * @return
	 */
	public String getDepartInfo(){
		ActionContext context=ActionContext.getContext();
		context.put("depart", departService.getDepartById(departID));
		return SUCCESS;
	}
	
	/**
	 * ��ȡ�������ҵ���Ϣ(Json��ʽ)
	 * @return
	 */
	public String getDepartInfoJson(){
		this.setResult(departService.getDepartById(departID));
		return SUCCESS;
	}
	/**
	 * ɾ��������Ϣ
	 * @return
	 */
	public String deleteDepart(){
		return departService.deleteDepart(departID)?SUCCESS:ERROR;
	}
}
