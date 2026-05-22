package com.wpj.util;

import java.util.List;

public class PageInfo<T> {
    private int currentPage;//当前页
    private int PageSize;//每页显示的记录数
    private int totalRows;//总记录数
    private int totalPages;//总页数
    private int startIndex;//索引的开始页
    private int prevPage;//上一数
    private int nextPage;//下一页
    private List<?> list;//设置查询的结果集
    private T t;//设置

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public PageInfo() {

    }
//    prevPage
    public PageInfo(String currentPage, int pageSize, int totalRows) {
//        this.currentPage = currentPage;
        //当前页
        getCp(currentPage);
        this.PageSize = pageSize;
        this.totalRows = totalRows;
        //设置总页数
        getTp();
        //处理索引的开始页
        getSI();
        //处理上一页
        getPp();
        //处理下一页
        getNp();
    }
    //处理下一页
    private void getNp() {
        if (this.currentPage==this.totalPages){
            this.nextPage=this.totalPages;
        }else {
            this.nextPage=this.currentPage+1;
        }
    }
    //处理上一页
    private void getPp() {
        if (this.currentPage==1){
            this.prevPage=1;
        }else {
            this.prevPage=this.currentPage-1;
        }
    }

    private void getSI() {

        this.startIndex=(this.currentPage - 1) * this.PageSize;
    }


    private void getTp() {
        if (this.totalRows % this.PageSize == 0){
            this.totalPages=this.totalRows / this.PageSize;
        }else {
            this.totalPages=this.totalRows / this.PageSize+1;
        }
    }

    //处理当前页
    private void getCp(String currentPage) {
        if (currentPage==null||"".equals(currentPage)){
            this.currentPage=1;
        }else {
            this.currentPage=Integer.valueOf(currentPage);
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
