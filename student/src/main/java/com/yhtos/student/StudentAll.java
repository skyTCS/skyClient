package com.yhtos.student;

public class StudentAll {

	private String stu_id;
	private String stu_pwd;
	private  String name;
    private String native_;
    private String major;
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_pwd() {
		return stu_pwd;
	}
	public void setStu_pwd(String stu_pwd) {
		this.stu_pwd = stu_pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNative_() {
		return native_;
	}
	public void setNative_(String native_) {
		this.native_ = native_;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "StudentAll{" +
				"stu_id='" + stu_id + '\'' +
				", stu_pwd='" + stu_pwd + '\'' +
				", name='" + name + '\'' +
				", native_='" + native_ + '\'' +
				", major='" + major + '\'' +
				'}';
	}
}
