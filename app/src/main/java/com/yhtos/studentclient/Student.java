package com.yhtos.studentclient;

/*
 * 函数名：Student
 * 功能：javabean
 * 创建者：巴莹、高雨鑫
 * 创建时间：2020.07.13
 * */

public class Student {
    private String stu_id;
    private String stu_pwd;

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

    @Override
    public String toString() {
        return "Student{" +
                "stu_id='" + stu_id + '\'' +
                ", stu_pwd='" + stu_pwd + '\'' +
                '}';
    }
}
