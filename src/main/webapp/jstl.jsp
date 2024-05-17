<%--
  Created by IntelliJ IDEA.
  User: cjk11
  Date: 2024/5/16
  Time: 下午1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入JSTL标签库--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>测试JSTL标签</title>
</head>
<body>
<%--    与范围对象相关的标签--%>
<%--    将数据以指定的名字存入指定的范围对象中--%>
<%--scope属性可以省略，如果省略则默认最小的范围对象：page--%>
<%--    <% session.setAttribute("userName","张三"); %>--%>
    <c:set value="<div style='color:#FF0000'>张三</div>" var="userName" scope="session"></c:set>


    <ul>
        <li>${sessionScope.userName}</li>
    <%--        当需要使用默认值的时候，当不需要执行值中的代码，推荐使用c:out--%>
    <%--        escapeXml属性：设置c:out标签“不执行”值中的代码，默认为true--%>
        <li><c:out value="${sessionScope.userName}" default="默认值" escapeXml="true"></c:out> </li>
    </ul>

    <c:remove var="userName" scope="session"></c:remove>
    <c:out value="${sessionScope.userName}" default="默认值"></c:out>

    <hr>
    <c:set value="20" var="userAge" scope="request"></c:set>

    <%--c:if标签: 多个c:if标签之间没有关系，如果分支很多时，不推荐使用c:if标签--%>
    <c:if test="${userAge ge 20}">
        年龄大于等于20<br>
    </c:if>
    <c:if test="${userAge eq 20}">
        年龄等于20<br>
    </c:if>
    <c:if test="${userAge le 20}">
        年龄小于等于20<br>
    </c:if>

    <c:choose>
        <c:when test="${userAge ge 20}">
            年龄大于等于20<br>
        </c:when>
        <c:when test="${userAge eq 20}">
            年龄等于20<br>
        </c:when>
        <c:when test="${userAge le 20}">
            年龄小于等于20<br>
        </c:when>
    </c:choose>

    <hr>
    <%
        //创建长度为10的int类型的数组
        int[] array = new int[10];
        //向数组中添加10个1-100之间的随机数
        for(int i=0;i<array.length;i++){
            array[i]=(int)(Math.random()*100+1);
        }
        //将数组numbers名字存入到session中
        session.setAttribute("numbers",array);
    %>

<%--    c:forEach标签，遍历集合--%>
<%--    items属性：设置将要进行遍历的集合--%>
<%--    var属性：声明变量，用于保存集合中的数据 数据默认保存在最小的范围对象中--%>
<%--varStatus属性，声明变量，用于保存保存循环的状态--%>

    <c:forEach items="${sessionScope.numbers}" var="num" varStatus="loop">
<%--        index:表示循环的下标 从0开始--%>
<%--        count:表示循环的次数，次数从1开始--%>
        ${loop.index}->${loop.count}->${num}<br>

    </c:forEach>
</body>
</html>
