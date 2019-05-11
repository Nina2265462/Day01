package com.bawei.day_mvp.bean;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class User {
    private String phone;
    private String pwd;

    public User() {
    }

    public User(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

