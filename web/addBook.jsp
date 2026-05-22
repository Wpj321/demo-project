<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/data.js"></script>
    <script>
        function checkFormDate() {
            if (checkBname() && checkPrice() && checks() && checkss()&&checksss()) {
                $(".my").submit();
            }else{
                return checkFormDate;
            }
            // if (checkBname() && checkPrice() && checks()) {
            //     $(".my").submit();
            // }else{
            //     return checkFormDate;
            // }
        }
        //把每个输入框当成一个函数
        function checkBname() {
            var bname=$("[name=bname]").val();
            if (bname==""){
                $("#bnamemsg").html("图书输入错误");
                return false;
            }
            if (bname.length<2){
                $("#bnamemsg").html("图书要在大于2");
                return false;

            }else {
                $("#bnamemsg").html("");
                return true;
            }
        }

        //验证图书价格
        function checkPrice() {
            var price=$("[name=price]").val();
            if (price<1||price>200){
                $("#pricemsg").html("价格输入错误");
                return false;
            }else {
                $("#pricemsg").html("");
                return true;
            }
        }
        //验证作者
        function checks() {
            var authora=$("[name=author]").val();
            if (authora==""){
                $("#ddaass").html("请输入作者");
                return false;
            }else {
                $("#ddaass").html("");
                return true;
            }
        }
        //验证发布时间
        function checkss() {
            var publishdate=$("[name=publishdate]").val();
            if (publishdate==""){
                $("#publishdatemsg").html("时间输入错误");
                return false;
            }else {
                $("#publishdatemsg").html("");
                return true;
            }

    }
        //验证图书类型
        function checksss() {
            var tsid=$("[name=tsid]").val();
            if (tsid==""){
                $("#tidmsg").html("书本类型错误");
                return false;
            } else {
                $("#tidmsg").html("");
                return true;
            }
        }
    </script>
</head>
<%--<body>--%>
<%--<%--%>
<%--    request.setCharacterEncoding("UTF-8");--%>
<%--    BookService bks= new BookServiceImpl();--%>
<%--    List<Type> blist = bks.addbook();--%>
<%--%>--%>
<form action="${pageContext.request.contextPath}/BooksServlet?methodName=addBook" method="post" class="my" >
<table align="center" cellpadding="10px" cellspacing="5px">
<tr>
    <td colspan="2"><h2 align="center">添加信息</h2></td>
</tr>
<tr>
    <td>名程</td>
    <td><input type="text" name="bname" id="bname" onblur="checkName(this.value)">
        <span id="bnamemsg" style="color:#00ff73"></span>
    </td>

</tr>
    <tr>
        <td>价格</td>
        <td><input type="text" name="price">
            <span id="pricemsg" style="color:#00ff73"></span>
        </td>
    </tr>
    <tr>
        <td>作者</td>
        <td><input type="text" name="author">
            <span id="ddaass" style="color:red"></span>
        </td>
    </tr>
    <tr>
        <td>发布日期</td>
        <span id="publishdatemsg" style="color:#00ff73">
            <td>
                <input type="date" name="publishdate"> <%-- name="publishdate" id="publishdatemsg"--%>
            </td>
        </span>
    </tr>
    <tr>
        <td>书本照片</td>
        <td>
<%--          <input type="image" name="imgurl" src="images/bg.jpg">--%>
        <img src="./images/bg.jpg" width="50px" height="50px" name="imgurl"/>
        </td>
    </tr>
    <tr>
        <td>图书类型</td>
        <td>

        <select name ="tid" style="height: 23px" id="tidmsg">

            <option value="-1">请选择</option>

        <c:forEach items="${tlist}" var="t">
            <sapn name ="tsid" >
                <option value="${t.tid}">
<%--                    <sapn name ="tsid" >--%>
                            ${t.tname}
<%--                    </sapn>--%>
                </option>
            </sapn>
        </c:forEach>
        </select>
        </td>
    </tr>
    <tr>
        <input align="center" cellpadding="10px" cellspacing="5px" class="tets" type="button" onclick="checkFormDate()" value="提交" ><br>
<%--        onsubmit="return checkFormDate()"--%>
    </tr>
</table>
</form>
</body>
</html>
