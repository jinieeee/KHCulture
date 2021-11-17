package com.kh.khculture.notice.model.vo;

import java.util.Date;

public class Membertest {

	
	private int m_no;
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
	private int post_code;
	private String address;
	private int hint_no;
	private String hint_a;
	private Date m_enroll_date;
	private Date m_modify_date;
	private Date pwd_changed_date;
	private Date pwd_exp_date;
	private int login_failed_count;
	private String acc_lock_yn;
	private String acc_secession_yn;
	private Date acc_secession_date;
	
	
	public void Membertest() {}


	public int getM_no() {
		return m_no;
	}


	public void setM_no(int m_no) {
		this.m_no = m_no;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getPost_code() {
		return post_code;
	}


	public void setPost_code(int post_code) {
		this.post_code = post_code;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getHint_no() {
		return hint_no;
	}


	public void setHint_no(int hint_no) {
		this.hint_no = hint_no;
	}


	public String getHint_a() {
		return hint_a;
	}


	public void setHint_a(String hint_a) {
		this.hint_a = hint_a;
	}


	public Date getM_enroll_date() {
		return m_enroll_date;
	}


	public void setM_enroll_date(Date m_enroll_date) {
		this.m_enroll_date = m_enroll_date;
	}


	public Date getM_modify_date() {
		return m_modify_date;
	}


	public void setM_modify_date(Date m_modify_date) {
		this.m_modify_date = m_modify_date;
	}


	public Date getPwd_changed_date() {
		return pwd_changed_date;
	}


	public void setPwd_changed_date(Date pwd_changed_date) {
		this.pwd_changed_date = pwd_changed_date;
	}


	public Date getPwd_exp_date() {
		return pwd_exp_date;
	}


	public void setPwd_exp_date(Date pwd_exp_date) {
		this.pwd_exp_date = pwd_exp_date;
	}


	public int getLogin_failed_count() {
		return login_failed_count;
	}


	public void setLogin_failed_count(int login_failed_count) {
		this.login_failed_count = login_failed_count;
	}


	public String getAcc_lock_yn() {
		return acc_lock_yn;
	}


	public void setAcc_lock_yn(String acc_lock_yn) {
		this.acc_lock_yn = acc_lock_yn;
	}


	public String getAcc_secession_yn() {
		return acc_secession_yn;
	}


	public void setAcc_secession_yn(String acc_secession_yn) {
		this.acc_secession_yn = acc_secession_yn;
	}


	public Date getAcc_secession_date() {
		return acc_secession_date;
	}


	public void setAcc_secession_date(Date acc_secession_date) {
		this.acc_secession_date = acc_secession_date;
	}


	@Override
	public String toString() {
		return "Membertest [m_no=" + m_no + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + ", post_code=" + post_code + ", address=" + address + ", hint_no=" + hint_no
				+ ", hint_a=" + hint_a + ", m_enroll_date=" + m_enroll_date + ", m_modify_date=" + m_modify_date
				+ ", pwd_changed_date=" + pwd_changed_date + ", pwd_exp_date=" + pwd_exp_date + ", login_failed_count="
				+ login_failed_count + ", acc_lock_yn=" + acc_lock_yn + ", acc_secession_yn=" + acc_secession_yn
				+ ", acc_secession_date=" + acc_secession_date + "]";
	}

	


}
