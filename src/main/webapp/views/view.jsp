<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 25.07.18
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Path</title>
</head>
<body>

<table border="5px">
    <tr><td colspan="6"><img src="<c:url value="${dxfFile.namePng}"/>" alt="image" height="420" width="420"></td></tr>
    <tr>
        <td>Thickness:</td>
        <td><c:out value="${dxfFile.thickness}"/></td>
        <td>Amount: </td>
        <td><c:out value="${dxfFile.amount}"/></td>
        <td>Material: </td>
        <td><c:out value="${dxfFile.materialTyp}"/></td>
    </tr>
    <tr>
        <td>Width:</td>
        <td colspan="2"><fmt:formatNumber type="number" maxFractionDigits="10"><c:out value="${dxfFile.width}"/></fmt:formatNumber></td>
        <td>Height: </td>
        <td colspan="2"><fmt:formatNumber type="number" maxFractionDigits="10"><c:out value="${dxfFile.height}"/></fmt:formatNumber></td>
    </tr>
    <tr>
        <td>Name: </td>
        <td colspan="2"><c:out value="${dxfFile.name}"/></td>
        <td>Project: </td>
        <td colspan="2"></td>
    </tr>
</table>
</body>
</html>



