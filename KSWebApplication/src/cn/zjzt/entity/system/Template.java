package cn.zjzt.entity.system;

/**
 * Ì×²ÍÐÅÏ¢
 * 
 * @author Administrator
 * 
 */
public class Template {
	private int id;
	private String name;
	private String apply_sex;
	private String intro;
	private double price;
	private String gmt_create;
	private String gmt_modified;
	private String pic_url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApply_sex() {
		return apply_sex;
	}

	public void setApply_sex(String apply_sex) {
		this.apply_sex = apply_sex;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}

	public String getGmt_modified() {
		return gmt_modified;
	}

	public void setGmt_modified(String gmt_modified) {
		this.gmt_modified = gmt_modified;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
}
