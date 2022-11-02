<%--
  Created by IntelliJ IDEA.
  User: said
  Date: 10/29/2022
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/login.css" type="text/css">
</head>
<body>
<div>welcome to login page
    <%=request.getAttribute("name") %>

    <% if (request.getAttribute("auth") != null) { %>
    <form action="logout" method="post">
        <input type="submit" value="logout">
    </form>
    <% } else { %>
    <form action="login" method="post">
        <input type="text" name="username">
        <input type="password" name="password">
        <input type="submit" value="login">
    </form>
    <% } %>


</div>
</body>
</html>
