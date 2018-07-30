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

<form method="post" action="/" enctype="multipart/form-data">
    <input type="file" name="files" multiple/>
    <input type="submit" value="Załaduj plik">
</form>

<table border="5px">
    <c:if test="${not empty dxfFileList}">
        <c:forEach items="${dxfFileList}" var="dxfFile">
            <tr>
            <c:forEach items="${dxfFile}" var="dxfFile">
                <td>
                <table border="3px">
                <tr>
                    <td colspan="6"><img src="<c:url value="${dxfFile.namePng}"/>" alt="image" height="300px" width="300px">
                    </td>
                </tr>
                <tr>
                    <td>Thickness:</td>
                    <td><c:out value="${dxfFile.thickness}"/>mm</td>
                    <td>Amount:</td>
                    <td><c:out value="${dxfFile.amount}"/></td>
                    <td>Material:</td>
                    <td><c:out value="${dxfFile.materialTyp}"/></td>
                </tr>
                <tr>
                    <td>Width:</td>
                    <td colspan="2"><fmt:formatNumber type="number" maxFractionDigits="0"><c:out
                            value="${dxfFile.width}"/></fmt:formatNumber>mm
                    </td>
                    <td>Height:</td>
                    <td colspan="2"><fmt:formatNumber type="number" maxFractionDigits="0"><c:out
                            value="${dxfFile.height}"/></fmt:formatNumber>mm
                    </td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td colspan="5"><c:out value="${dxfFile.name}"/>.dxf</td>
                </tr>
                </table>
                </td>
            </c:forEach>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>



