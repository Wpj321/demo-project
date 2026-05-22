package com.wpj.pojo;

/**
 * 功能描述：用户实体类
 * 作者：zz
 * 时间：2021/10/8 8:50
 */
public class User {
    private Integer uid;
    private String uname;
    private String password;
    private Role role;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(Integer uid, String uname, String password, Role role) {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
