<%@ page import="java.util.List"%>
<%@ page import="com.test.entity.UserMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户查询</title>
    <!-- 引入Bootstrap CSS and JS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <%-- 使用bootstrap的card组件 查询条件的开始 --%>
    <div class="card">
        <div class="card-header">
            查询条件
        </div>
        <div class="card-body">
            <c:url value="userSelect" var="userSelectUrl"></c:url>
            <form action="${userSelectUrl}" method="post" class="form-inline">
                <div class="form-group mr-2">
                    <label for="userName" class="mr-2">用户姓名:</label>
                    <input type="search" name="userName" id="userName" class="form-control">
                </div>
                <div class="form-group mr-2">
                    <label for="userSex" class="mr-2">用户性别:</label>
                    <select name="userSex" id="userSex" class="form-control">
                        <option value="n">不限</option>
                        <option value="x">男</option>
                        <option value="y">女</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">查询</button>
            </form>
        </div>
    </div>
    <%-- 查询条件的结束 --%>
    <div class="table-responsive mt-4">
        <table class="table table-striped table-bordered">
<%--            使用thead-dard加深头类--%>
            <thead class="thead-dark">
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
            <%-- 判断request中是否不存在名字为userList的集合，或名为userList的集合长度是否为0 --%>
            <c:if test="${empty requestScope.userList || fn:length(requestScope.userList) == 0}">
                <tr>
                    <td colspan="7" class="text-center">没有查询到满足条件的用户</td>
                </tr>
            </c:if>
            <%-- 判断request中userList名字的集合存在，并且长度大于0 --%>
            <c:if test="${!empty requestScope.userList && fn:length(requestScope.userList) gt 0}">
                <%-- 使用循环遍历userlist集合 --%>
                <c:forEach items="${requestScope.userList}" var="user" varStatus="Loop">
                    <tr>
                        <td>${Loop.count }</td>
                        <td>${user.userId}</td>
                        <td>${user.userName}</td>
                        <td>${user.userPhone}</td>
                        <td>${user.userEmail}</td>
                        <td>${user.userSex == "x" ? "男" : "女"}</td>
                        <td>
                            <button class="btn btn-sm btn-warning" data-toggle="modal" data-target="#editUserModal"
                                    data-id="${user.userId}" data-name="${user.userName}"
                                    data-phone="${user.userPhone}" data-email="${user.userEmail}"
                                    data-sex="${user.userSex}">编辑</button>
                            <a href="userDelete?userId=${user.userId}" class="btn btn-sm btn-danger" onclick="return confirm('确定要删除吗？')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
    <!-- 查询结果的结束 -->
    <!-- 如果查询失败，显示session中名为error的错误信息 -->
    <div role="alert">
        ${sessionScope.error}
    </div>
    <!-- 移除session中名为error的错误信息 -->
    <c:remove var="error" scope="session"></c:remove>
</div>

<%--添加模态框，用于编辑用于信息--%>
<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <c:url value="userUpdate" var="userupdate"></c:url>
            <form action="${userupdate}" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="editUserModalLabel">编辑用户</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="userId" id="editUserId">
                    <div class="form-group">
                        <label for="editUserName">用户姓名</label>
                        <input type="text" class="form-control" id="editUserName" name="newuserName" required>
                    </div>
                    <div class="form-group">
                        <label for="editUserPassword">用户密码</label>
                        <input type="text" class="form-control" id="editUserPassword" name="newuserPassword" required>
                    </div>
                    <div class="form-group">
                        <label for="editUserPhone">用户手机</label>
                        <input type="text" class="form-control" id="editUserPhone" name="newuserPhone" required>
                    </div>
                    <div class="form-group">
                        <label for="editUserEmail">用户邮箱</label>
                        <input type="email" class="form-control" id="editUserEmail" name="newuserEmail" required>
                    </div>
                    <div class="form-group">
                        <label for="editUserSex">用户性别</label>
                        <select class="form-control" id="editUserSex" name="newuserSex" required>
                            <option value="x">男</option>
                            <option value="y">女</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    // 填充模态框中的数据
    $('#editUserModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // 触发按钮
        var userId = button.data('id');
        var userName = button.data('name');
        var userPhone = button.data('phone');
        var userEmail = button.data('email');
        var userSex = button.data('sex');

        var modal = $(this);
        modal.find('#editUserId').val(userId);
        modal.find('#editUserName').val(userName);
        modal.find('#editUserPhone').val(userPhone);
        modal.find('#editUserEmail').val(userEmail);
        modal.find('#editUserSex').val(userSex);
    });
</script>
</body>
</html>
