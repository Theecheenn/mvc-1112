<%@page import="java.util.List"%>
<%@page import="com.test.entity.UserMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户查询</title>
    <link href="./css/main.css" rel="stylesheet">
</head>
<body>
<%--    查询条件的开始--%>
    <hr>
    <form action="userSelect" method="post">
        用户姓名:<input type="search" name="userName"/>
        用户性别:
        <select name="userSex">
            <option value="n">不限</option>
            <option value="x">男</option>
            <option value="y">女</option>
        </select>
        <button type="submit">查询</button>
    </form>
    <hr>
<%--   查询条件的结束--%>
    <table>
        <thead>
        <tr>
            <th>序号</th>
            <th>用户编号</th>
            <th>用户姓名</th>
            <th>用户手机</th>
            <th>用户邮箱</th>
            <th>用户性别</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            //获得请求中名为userList的用户信息
            List<UserMessage> list = (List<UserMessage>) request.getAttribute("userList");
            //判断list是否为null或list中元素的个数是否为0
            if (list == null || list.size() == 0) {
        %>
        <tr>
            <td colspan="7">没有查询到满足条件的用户信息！！！</td>
        </tr>
        <%
        } else {
            //遍历list集合，将list中的用户信息显示到表格中
            for (UserMessage user : list) {
        %>
                <tr>
                    <td></td>
                    <td><%=user.getUserId()%></td>
                    <td><%=user.getUserName()%></td>
                    <td><%=user.getUserPhone()%></td>
                    <td><%=user.getUserEmail()%></td>
                    <td><%=user.getUserSex().equalsIgnoreCase("x") ? "男" : "女"%></td>
                    <td></td>
                </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <!-- 查询结果的结束 -->
    <!-- 如果查询失败，显示session中名为error的错误信息 -->
    ${sessionScope.error}
    <!--移除session中名为error的错误信息 -->
    <c:remove var="error" scope="session"></c:remove>
</body>
</html>