package com.kh.khculture.notice.model.vo;

public class UploadFile {
	private int image_no;
	private String route;
	private String image_name;
	private String image_r_name;
	private int image_level;
	private String image_status;
	
	public UploadFile() {}

	public UploadFile(int image_no, String route, String image_name, String image_r_name, int image_level,
			String image_status) {
		super();
		this.image_no = image_no;
		this.route = route;
		this.image_name = image_name;
		this.image_r_name = image_r_name;
		this.image_level = image_level;
		this.image_status = image_status;
	}

	public int getImage_no() {
		return image_no;
	}

	public void setImage_no(int image_no) {
		this.image_no = image_no;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getImage_r_name() {
		return image_r_name;
	}

	public void setImage_r_name(String image_r_name) {
		this.image_r_name = image_r_name;
	}

	public int getImage_level() {
		return image_level;
	}

	public void setImage_level(int image_level) {
		this.image_level = image_level;
	}

	public String getImage_status() {
		return image_status;
	}

	public void setImage_status(String image_status) {
		this.image_status = image_status;
	}

	@Override
	public String toString() {
		return "UploadFile [image_no=" + image_no + ", route=" + route + ", image_name=" + image_name
				+ ", image_r_name=" + image_r_name + ", image_level=" + image_level + ", image_status=" + image_status
				+ "]";
	}
	
	
}
