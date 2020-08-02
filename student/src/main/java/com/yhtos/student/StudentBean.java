package com.yhtos.student;
/*
 * 函数名：StudentBean
 * 功能：javabean
 * */
public class StudentBean {
    private  String name;
    private String native_;
    private String stu_id;
    private String major;

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

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    @Override
    public String toString() {
        return "StudentBean{" +
                "name='" + name + '\'' +
                ", native_='" + native_ + '\'' +
                ", stu_id='" + stu_id + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
