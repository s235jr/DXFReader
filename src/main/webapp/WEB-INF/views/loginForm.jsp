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
<div align="center">

    <table align="center">
        <form:form method="post" action="/login" modelAttribute="user">
            <tr>
                <td><form:input path="email" placeholder="Email" value="tomasz.ciecwierz@gmail.com"/><form:errors path="email"/></td>
            </tr>
            <tr>
                <td><form:password path="password" placeholder="Hasło" value="admin"/><form:errors path="password"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Zaloguj się"></td>
            </tr>
        </form:form>
    </table>

</div>
</body>
</html>

