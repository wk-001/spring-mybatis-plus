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
<jsp:forward page="/list"></jsp:forward>
</body>
</html>
