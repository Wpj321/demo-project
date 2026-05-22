<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script>
        function addInfo() {
            window.location.href="${pageContext.request.contextPath}/bookServlet?method=toAdd";
        }
        //删除方法
        function deleteById(bid) {
            if (confirm("确认删除?")){
                window.location.href="${pageContext.request.contextPath}/bookServlet?method=deleteBook&bid="+bid;
            }
        }
        function getPage(page) {
            $("#page").val(page);
            $("#mvfrom").submit();
        }
    </script>
</head>
<body>
    <h4 align="center">
        欢迎你&nbsp;&nbsp;${sessionScope.user.userame}&nbsp;&nbsp;登录
    </h4>
    <form action="${pageContext.request.contextPath}/bookServlet?method=getBookList" method="post" id="mvfrom">
        <h3 align="center">
            图书名称<input type="text" name="bname" placeholder="请输入图书名称" value="${pageInfo.t.bname}">
            价格<input type="number" name="minprice">-<input type="number" name="maxprice">
            图书类型<select name="tid">
            <option value="-1">请选择</option>
            <c:forEach items="${requestScope.tlist}" var="t">
                <option value="${t.tid}" ${t.tid==pageInfo.t.tp.tid?"selected":""}>${t.tname}</option>
            </c:forEach>
        </select>
            <input type="hidden" name="page" id="page" value="1">
            <input type="submit" value="查询">
        </h3>
</form>
<table align="center" cellpadding="8px" cellspacing="5px" style="border-color: red">
            <tr>
                <td colspan="8">
                    <button type="button" onclick="addInfo()">添加</button>
                    <button type="button" onclick="delAll()">批量删除</button>
                </td>
            </tr>
            <tr>
                <td>图书名称</td>
                <td>作者</td>
                <td>价格</td>
                <td>出版日期</td>
                <td>图书类型</td>
                <td>操作</td>
            </tr>
                <c:forEach items="${requestScope.pageInfo.list}" var="b">
                    <tr>
                        <td>${b.bid}</td>
                        <td>${b.bname}</td>
                        <td>${b.author}</td>
                        <td>${b.price}</td>
                        <td><fmt:formatDate value="${b.publishdate}"></fmt:formatDate></td>
                        <td>${b.tp.tname}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/bookServlet?method=toUpdate&bid="+bid></a>
                            <a href="#" onclick="deleteById(${b.bid})">删除</a>
                        </td>
                    </tr>
            </c:forEach>
            <tr>
                <td colspan="8">
                总记录数:${pageInfo.totalRows}
                当前页/总页数:${pageInfo.currentPage}/${pageInfo.totalPages}
                <a href="#" onclick="getPage(1)">首页</a>
                <a href="#" onclick="getPage(${pageInfo.prevPage})">上一页</a>
            <c:forEach begin="1" end="${pageInfo.totalPages}" var="p">
                <a href="#" onclick="getPage(${p})">${p}</a>
            </c:forEach>
            <c:if test="${pageInfo.currentPage < pageInfo.totalPages}">
                <a href="#" onclick="getPage(${pageInfo.nextPage})">下一页</a>
            </c:if>
            <a href="#" onclick="getPage(${pageInfo.totalPages})">尾页</a>
                </td>
        </td>
    </tr>
</table>
</body>
</html>