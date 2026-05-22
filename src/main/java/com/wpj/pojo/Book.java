package com.wpj.pojo;

import java.util.Date;


/**
 * 功能描述：图书信息
 * 作者：zz
 * 时间：2021/10/8 8:54
 */
public class Book {
    private Integer bid;
    private String bname;
    private Double price;
    private String author;
    private Date publishdate;
    private String imgurl;
    private Type type;

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", publishdate=" + publishdate +
                ", imgurl='" + imgurl + '\'' +
                ", type=" + type +
                '}';
    }

    public Book() {
    }

    public Book(Integer bid, String bname, Double price, String author, Date publishdate, String imgurl, Type type) {
        this.bid = bid;
        this.bname = bname;
        this.price = price;
        this.author = author;
        this.publishdate = publishdate;
        this.imgurl = imgurl;
        this.type = type;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
