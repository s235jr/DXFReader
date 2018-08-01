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
    <title>Dxf Reader</title>
    <link rel="stylesheet" href="/style/style.css">
</head>
<body>
<h1 class="no-print" align="center">Dxf Reader</h1>

<div class="no-print, block_container" align="center">
    <div id="loadFiles" align="left">
        <form method="post" action="/" enctype="multipart/form-data">
            <input type="file" name="files" multiple/>
            <select name="columns">
                <option value="2" <c:if test="${columns == 2}">selected</c:if>>2 Columns</option>
                <option value="3" <c:if test="${columns == 3}">selected</c:if>>3 Columns</option>
                <option value="4" <c:if test="${columns == 4}">selected</c:if>>4 Columns</option>
            </select>
            <input type="submit" value="Załaduj plik">
        </form>
    </div>
    <div id="newSession" align="right">
        <form method="POST" action="/clearSession">
            <input type="submit" value="Stwórz nowe zlecenie"/>
        </form>
    </div>
</div>
<div class="no-print" align="center">
    <form method="post" action="/addFooter">
        <label>Imię:</label>
        <input type="text" name="firstName" value="${firstName}">
        <label>Nazwisko:</label>
        <input type="text" name="lastName" value="${lastName}">
        <label>Projekt:</label>
        <input type="text" name="projectName" value="${projectName}">
        <input type="submit" value="Zapisz">
    </form>

</div>

<table align="center" width="100%">
    <c:if test="${not empty dxfFileList}">
        <c:forEach items="${dxfFileList}" var="dxfFile" varStatus="loop">

            <c:if test="${loop.count % columns == 1}"><tr></c:if>
            <td>
                <table border="3px" align="center" width="100%/${columns}">
                    <tr>
                        <td colspan="6" align="center"><img src="<c:url value="${dxfFile.namePng}"/>" alt="image"
                                                            width="100%/${columns}" height="400px">
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
                        <td colspan="3"><c:out value="${dxfFile.name}"/>.dxf</td>
                        <td colspan="2">
                            <form class="no-print" method="post" action="/delDxfFileFromList">
                                <input type="hidden" name="del" value="${loop.count-1}">
                                <input type="submit" value="Del">
                            </form>
                        </td>
                    </tr>
                </table>
            </td>
            <c:if test="${loop.count % columns == 0}"></tr></c:if>
        </c:forEach>
    </c:if>
    <div class="divFooter" align="center">
        <table class="tableFooter" width="100%" border="5px">
            <tr>
                <td>Imię</td>
                <td colspan="3"><c:out value="${firstName}"/></td>
                <td>Nazwisko</td>
                <td colspan="3"><c:out value="${lastName}"/></td>
            </tr>
            <tr>
                <td>Data utworzenia</td>
                <td colspan="3"><c:out value="${projectName}"/></td>
                <td>Projekt</td>
                <td colspan="3"><c:out value="${createDate}"/></td>
            </tr>
        </table>
    </div>
</table>
</body>
</html>



