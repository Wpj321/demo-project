<%@ page import="com.wpj.pojo.Book" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        *{
            text-align: center;
        }
        a {
            Font-size:30px;
            text-align:center;
            color: #ff0000;
            text-decoration: none
        }
        .ddssaa{
            text-align: center;
        }
        .ddssaa td{
            Font-size:30px;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/data.js"></script>
    <script>
        function deleteById(bid){
           if(confirm("不删除吗？")){
               alert("原来你真的不删除")
           }else {
               window.location.href="${pageContext.request.contextPath}/BooksServlet?methodName=deleteBook&bid="+bid;
           }
        }
        function getListByParam() {
            //获取文本框中输入的问题
            var bname = $("[name=bname]").val();
            window.location.href="${pageContext.request.contextPath}/BooksServlet?methodName=getBookList&bname="+bname;
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/BooksServlet?methodName=getBookList" method="post">
<h4 align="center">
    图书名称<input type="text" name="bname" placeholder="请输入图书名称" value="${page.t.bname}">
    图书类型<select name="tid">
    <option value="-1">请选择</option>
    <c:forEach items="${requestScope.tlist}" var="t">
        <option value="${t.tid}">${t.tname}</option>
    </c:forEach>
</select>
<%--    <button type="button" onclick="getListByParam()">查询</button>--%>
    <input type="submit" value="查询">
</h4>
</form>

<table border="1" color="red">
    <tr>
    <td>图书编号</td>
       <td>图书名</td>
       <td>单价</td>
       <td>作者</td>
       <td>发布日期</td>
       <td>图书图片</td>
        <td>图书类型</td>
        <td align="center" cellpadding="10px" cellspacing="5px">
 操作
        </td>
    </tr>
<c:forEach items="${requestScope.page.list}" var="b">
    <tr>
    <td>${b.bid}</td>
    <td>${b.bname}</td>
    <td>${b.price}</td>
    <td>${b.author}</td>
    <td>${b.publishdate}</td>
    <td>${b.imgurl}</td>
    <td>${b.type.tname}</td>
        <td>
            <a href="${pageContext.request.contextPath}/BooksServlet?methodName=toAdd">添加新图书</a>
            <a href="${pageContext.request.contextPath}/BooksServlet?methodName=toUpdate&bid=${b.bid}">修改图书</a>
            <a href="#" onclick="deleteById(${b.bid})">删除图书</a>
        </td>
    </tr>
</c:forEach>
    <tr class="ddssaa">
        <td colspan="8">
            总记录数${page.totalRows}
            当前页数${page.currentPage}/总页数${page.totalPages}
            <a href="${pageContext.request.contextPath}/BooksServlet?methodName=getBookList&page=1&bname=${page.t.bname}">首页</a>
            <a href="${pageContext.request.contextPath}/BooksServlet?methodName=getBookList&page=${page.prevPage}&bname=${page.t.bname}">上一页</a>
            <c:forEach begin="1" end="${page.totalPages}" var="p">
                <a href="${pageContext.request.contextPath}/BooksServlet?methodName=getBookList&page=${p}">${p}</a>
            </c:forEach>
            <c:if test="${page.currentPage < page.totalPages}">
                <a href="${pageContext.request.contextPath}/BooksServlet?methodName=getBookList&page=${page.nextPage}&bname=${page.t.bname}">下一页</a>
            </c:if>
            <a href="${pageContext.request.contextPath}/BooksServlet?methodName=getBookList&page=${page.totalPages}&bname=${page.t.bname}">尾页</a>
        </td>
    </tr>
</table>
</body>
</html>
