package com.wpj.pojo;

/**
 * 功能描述：图书类型
 * 作者：zz
 * 时间：2021/10/8 8:54
 */
public class Type {
    private Integer tid;
    private String tname;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "Type{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                '}';
    }

    public Type() {
    }

    public Type(Integer tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }
}
