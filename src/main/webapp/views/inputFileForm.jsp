<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 27.07.18
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Input File</title>
</head>
<body>

<form method="post" enctype="multipart/form-data">
    <input type="file" name="file" \/>
    <input type="submit" value="Załaduj plik">
</form>


</body>
</html>
