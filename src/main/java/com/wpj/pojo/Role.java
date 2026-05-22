package com.wpj.pojo;

/**
 * 功能描述：角色实体类
 * 作者：zz
 * 时间：2021/10/8 8:51
 */
public class Role {
    private Integer rid;
    private String rolename;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Role(Integer rid, String rolename) {
        this.rid = rid;
        this.rolename = rolename;
    }

    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
