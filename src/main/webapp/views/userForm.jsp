<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 25.07.18
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dxf Reader</title>
</head>
<body>
<a href="/"><h1 class="no-print" align="center">Dxf Reader</h1></a>

<table align="center">
    <form:form method="post" modelAttribute="user">
        <tr>
            <td><form:input path="firstName" placeholder="Imię"/><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td><form:input path="lastName" placeholder="Nazwisko"/><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td><form:input path="email" placeholder="Email"/><form:errors path="email"/></td>
        </tr>
        <tr>
            <td><form:password path="password" placeholder="Hasło"/><form:errors path="password"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Zarejestruj się"></td>
        </tr>
    </form:form>
</table>
</body>
</html>

