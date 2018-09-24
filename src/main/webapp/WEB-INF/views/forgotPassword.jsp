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
        <form method="post" action="/forgotpassword">
            <tr>
                <td><input type="text" name="email" placeholder="Email" value="user@user.pl" autofocus="true"/></td>
            </tr>
            <tr>
                <td colspan="2"><input name="submit" type="submit" value="Resetuj hasło"></td>
            </tr>
        </form>
    </table>

</div>
</body>
</html>

