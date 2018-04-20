<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Spittle: 89288
  Date: 2018/4/18
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <h2>Hello World!</h2>
    <a href="<c:url value='/spittle-list'/>">Spittles</a> |
    <a href="<c:url value='/register'/>">Register</a>
</body>
</html>

