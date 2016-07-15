package com.resource.modules.entiy;

/**
 * Created by Sandwich on 2016/7/6.
 */
public class UserInfo {
    private String userName;
    private String pwd;
    private String openId;
    private String sex;
    private String addr;
    private String company;
    private String job;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = ("0".equals(sex))?"男":"女";
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "{" +
                "用户名->'" + userName + '\'' +
                ", 密码->'" + pwd + '\'' +
                ", 用户ID->'" + openId + '\'' +
                ", 性别->'" + sex + '\'' +
                ", 地址->'" + addr + '\'' +
                ", 公司->'" + company + '\'' +
                ", 岗位->'" + job + '\'' +
                '}';
    }
}