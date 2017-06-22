package cn.zjzt.action.system;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zjzt.entity.ValidateInfo;
import cn.zjzt.entity.system.Equipment;
import cn.zjzt.service.system.EquipmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EquipmentAction extends ActionSupport{
	private String equipId;
	private String pic_url;
	private String name;
	private String intro;
	private ValidateInfo checkData;
	private Equipment result;
	public Equipment getResult() {
		return result;
	}
	public void setResult(Equipment result) {
		this.result = result;
	}
	public ValidateInfo getCheckData() {
		return checkData;
	}
	public void setCheckData(ValidateInfo checkData) {
		this.checkData = checkData;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	private EquipmentService equipmentService;
	public EquipmentAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		equipmentService = (EquipmentService) context.getBean("equipmentService");
	}
	
	/**
	 * 获取所有设备
	 * 
	 * @return
	 */
	public String equipment() {
		ActionContext context = ActionContext.getContext();
		context.put("equipList", equipmentService.getAllEquip());
		return SUCCESS;
	}
	/**
	 * 新增或修改设备信息
	 * @return
	 */
	public String addOrUpdateEquip() {
		Equipment equipment=new Equipment();
		if ((equipId != null) && (equipId.trim().length() > 0)) {
			equipment.setId(Integer.parseInt(equipId));
		}
		equipment.setPic_url(pic_url);
		equipment.setName(name);
		equipment.setIntro(intro);
		this.setCheckData(equipmentService.addOrUpdateEquip(equipment));
		return SUCCESS;
	}
	/**
	 * 获取单个设备的信息(在设备详细页面显示)
	 * @return
	 */
	public String getEquipInfo(){
		ActionContext context=ActionContext.getContext();
		context.put("equipment", equipmentService.getEquipById(equipId));
		return SUCCESS;
	}
	/**
	 * 获取单个科室的信息(Json格式)
	 * @return
	 */
	public String getEquipInfoJson(){
		this.setResult(equipmentService.getEquipById(equipId));
		return SUCCESS;
	}
	/**
	 * 删除科室信息
	 * @return
	 */
	public String deleteEquip(){
		return equipmentService.deleteEquip(equipId)?SUCCESS:ERROR;
	}
}
