<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.wpj.service.BookService" %>
<%@ page import="com.wpj.service.Impl.BookServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wpj.pojo.Type" %>
<%@ page import="com.wpj.pojo.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<%--%>
<%--    request.setCharacterEncoding("UTF-8");--%>
<%--    BookService bks= new BookServiceImpl();--%>
<%--    String bid=request.getParameter("bid");--%>
<%--    Book book= bks.getBookByid(bid);--%>
<%--    List<Type> blist = bks.addbook();--%>
<%--%>--%>

<form action="${pageContext.request.contextPath}/BooksServlet?methodName=updateBook" method="post">

<table align="center" cellpadding="10px" cellspacing="5px">
<tr>
    <td colspan="2"><h2 align="center">修改图书信息</h2></td>
</tr>
<tr>
    <td>名程</td>
    <td>
        <input type="text" name="bname" value="${book.bname}">
    </td>
    <td>
        <input type="hidden" name="bid" value="${book.bid}">
    </td>
</tr>
    <tr>
        <td>价格</td>
        <td><input type="text" name="price" value="${book.price}">
        </td>
    </tr>
    <tr>
        <td>作者</td>
        <td><input type="text" name="author" value="${requestScope.book.author}">
        </td>
    </tr>
    <tr>
        <td>发布日期</td>
        <td><input type="date" name="publishdate"  value="${book.publishdate}">
    </tr>
    <tr>
        <td>书本照片</td>
        <td>
<%--          <input type="image" name="imgurl" src="images/bg.jpg">--%>
        <img src="./images/bg.jpg" width="50px" height="50px" name="imgurl"  value="${book.imgurl}">
        </td>
    </tr>
    <tr>
        <td>图书类型</td>
        <td><select name ="tid" style="height: 23px" >
            <option value="-1">请选择</option>
<%--            <%for (Type t:blist){%>--%>
<%--            <% if (t.getTid()==book.getType().getTid()){%>--%>
<%--                 <option value="<%=t.getTid()%>" selected><%=t.getTname()%></option>--%>
<%--            <% }else {%>--%>
<%--            <option value="<%=t.getTid()%>"><%=t.getTname()%></option>--%>
<%--            <% }%>--%>
<%--            <%}%>--%>
            <c:forEach items="${tlist}" var="t">
                <option value="${t.tid}" ${t.tid==book.type.tid?"selected":""}>${t.tname}</option>
            </c:forEach>
        </select>
        </td>
    </tr>
    <tr>
        <input align="center" cellpadding="10px" cellspacing="5px" class="tets" type="submit" value="提交"><br>
    </tr>
</table>
</form>
</body>
</html>