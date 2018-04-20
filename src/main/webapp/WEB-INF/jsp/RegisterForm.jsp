<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 89288
  Date: 2018/4/19
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<!-- 注册界面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<form action="processRegistraction" method="post">
    <!-- 登陆验证后如果表单内容不符合要求则说明错误 -->
    <c:if test="${errors.firstName ne null}">
        <c:forEach var="value" items="${errors.firstName}">
            <h4>${value}</h4>
        </c:forEach>
    </c:if>
    First Name:<input type="text" name="firstName"/>
    <br />

    <!-- 登陆验证后如果表单内容不符合要求则说明错误 -->
    <c:if test="${errors.lastName ne null}">
        <c:forEach var="value" items="${errors.lastName}">
            <h4>${value}</h4>
        </c:forEach>
    </c:if>
    Last Name:<input type="text" name="lastName"/>
    <br />

    <!-- 登陆验证后如果表单内容不符合要求则说明错误 -->
    <c:if test="${errors.username ne null}">
        <c:forEach var="value" items="${errors.username}">
            <h4>${value}</h4>
        </c:forEach>
    </c:if>
    Username:<input type="text" name="username"/>
    <br />

    <!-- 登陆验证后如果表单内容不符合要求则说明错误 -->
    <c:if test="${errors.password ne null}">
        <c:forEach var="value" items="${errors.password}">
            <h4>${value}</h4>
        </c:forEach>
    </c:if>
    Password:<input type="password" name="password"/>
    <br />
    <input type="submit" value="Register">
</form>

</body>
</html>
