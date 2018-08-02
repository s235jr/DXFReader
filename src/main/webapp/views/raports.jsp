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
    <style>
        figure {
            display: inline-block;
            width: 300px;
            height: auto;
            padding: 0;
            border: 2px solid black;
            margin: 0;
        }
    </style>
</head>
<body>
<div class="no-print" align="right">
    Witaj&nbsp;<c:out value="${user.firstName}"/>&nbsp;<c:out value="${user.lastName}"/>!
    <form style="display: inline-block" method="get" action=/logout>
        <input type="submit" value="Wyloguj się"/>
    </form>
    <form style="display: inline-block" method="get" action=/showMyRaports>
        <input type="submit" value="Moje zlecenia"/>
    </form>
</div>
<a href="/"><h1 class="no-print" align="center">Dxf Reader</h1></a>
<hr class="no-print">
<div>
    <table align="center" border="2px">
        <tr>
            <td>Id</td>
            <td>Data utworzenia</td>
            <td>Ostatnia modyfikacja</td>
            <td>Liczba elementów</td>
            <td>Opis</td>
            <td>Status</td>
        </tr>
        <tr>
            <td><c:out value="${raport.id}"/></td>
            <td><c:out value="${raport.createdDate}"/></td>
            <td><c:out value="${raport.updatedDate}"/></td>
            <td><c:out value="${raport.numberOfDxfFile}"/></td>
            <td><c:out value="${raport.description}"/></td>
            <td><c:out value="${raport.status.name}"/></td>
        </tr>
    </table>
</div>
<hr>
<div width="100%" align="center">
    <c:if test="${not empty user}">
        <c:forEach items="${dxfFileRaport}" var="dxfFile">
            <figure class="figure">
                <img width="300px" height="auto" src="<c:url value="${dxfFile.namePng}"/>" alt="image"/>
                <figcaption>
                    <hr>
                    <div class="block_container">
                        <div><c:out value="${dxfFile.thickness}"/>mm&nbsp;||&nbsp;</div>
                        <div><c:out value="${dxfFile.amount}"/>&nbsp;||&nbsp;</div>
                        <div><c:out value="${dxfFile.materialTyp}"/></div>
                    </div>
                    <hr>
                    <div class="block_container">
                        <div>szer:&nbsp;</div>
                        <div><fmt:formatNumber type="number" maxFractionDigits="0"><c:out
                                value="${dxfFile.width}"/></fmt:formatNumber>mm&nbsp;||&nbsp;
                        </div>
                        <div>wys:&nbsp;</div>
                        <div><fmt:formatNumber type="number" maxFractionDigits="0"><c:out
                                value="${dxfFile.height}"/></fmt:formatNumber>mm
                        </div>
                    </div>
                    <hr>
                    <div class="block_container">
                        <div>Nazwa:&nbsp;</div>
                        <div><c:out value="${dxfFile.name}"/>.dxf&nbsp;</div>
                    </div>
                    <hr>
                </figcaption>
            </figure>
        </c:forEach>
    </c:if>
</div>
<div class="divFooter">
    <hr>
    <div class="block_container" style="border: 3px">
        <div>Zamawiający:&nbsp;</div>
        <div><c:out value="${user.firstName}"/>&nbsp;<c:out value="${user.lastName}"/></div>
    </div>
    <hr>
    <div class="block_container">
        <div>Data utworzenia:&nbsp;</div>
        <div><c:out value="${raport.createdDate}"/></div>
    </div>
    <hr>
    <div class="block_container">
        <div>Opis:&nbsp;</div>
        <div><c:out value="${raport.description}"/></div>
    </div>
    <hr>
</div>
</body>
</html>



