<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 89288
  Date: 2018/4/18
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<!-- 展示最近的spittle消息页面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Information</title>
</head>
<body>
    <h3>Spittle Information:</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Message</th>
            <th>Time</th>
            <th>Latitude</th>
            <th>Longitude</th>
        </tr>
        <c:forEach var="spittle" items="${spittles}">
            <tr>
                <td>${spittle.id}</td>
                <td>${spittle.message}</td>
                <td>${strings.get(spittle)}</td>
                <td>${spittle.latitude}</td>
                <td>${spittle.longitude}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
