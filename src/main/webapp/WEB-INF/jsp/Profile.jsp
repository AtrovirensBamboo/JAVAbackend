<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 89288
  Date: 2018/4/19
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<!-- 注册成功后个人信息页面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Your Profile</title>
</head>
<body>
    <h2>Your Profile</h2>
    Username:${spitter.username}<br />
    Name:${spitter.firstName}  ${spitter.lastName}<br />
    <c:if test="${spitter.picturePath ne 'http://localhost:8080/tmp' and spitter.picturePath ne ''}">
        Picture:<img src="${spitter.picturePath}" alt="spitter picture"/>
    </c:if>
    <br />
    <a href="/download">Download</a>
</body>
</html>
