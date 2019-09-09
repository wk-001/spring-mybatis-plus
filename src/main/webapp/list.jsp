<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
    <link href="<%=basePath%>static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/html" src="<%=basePath%>static/bootstrap-3.3.7/js/jquery-3.4.1.js"></script>
    <script type="text/html" src="<%=basePath%>static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-MyBatis-Plus</h1>
        </div>
    </div>
    <%--操作按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8"> <%--div占4列，偏移8列--%>
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--表格--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>age</th>
                    <th>email</th>
                    <%--<th>gender</th>
                    <th>deptName</th>
                    <th>operate</th>--%>
                </tr>
                <c:forEach items="${user}" var="list">
                    <tr>
                        <td>${list.id}</td>
                        <td>${list.name}</td>
                        <td>${list.age}</td>
                        <td>${list.email}</td>
                       <%-- <td>${list.gender==1?"男":"女"}</td>
                        <td>${list.department.deptName}</td>
                        <td>
                            <button class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true" style="padding-right: 5px;"></span>编辑
                            </button>
                            <button class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true" style="padding-right: 5px;"></span>删除
                            </button>
                        </td>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
