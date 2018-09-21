<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<sec:authentication var="currentUser" property="principal"/>

<sec:authorize access="isAuthenticated()">
    <div align="right">
        Witaj ${currentUser.user.fullName}
        <form style="display: inline-block" method="post" action=/logout>
            <input type="submit" value="Wyloguj się"/>
        </form>
    </div>
</sec:authorize>

<div align="right">
    <form style="display: inline-block" method="get" action=/showMyRaports>
        <input type="submit" value="Moje zlecenia"/>
    </form>
</div>

<a href="/"><h1 class="no-print" align="center">Dxf Reader</h1></a>
<hr class="no-print">

<table align="center">
    <tr>
        <td>Numer zamówienia</td>
        <td>Opis</td>
        <td>Status</td>
    </tr>
    <form:form method="post" action="/acceptEdit" modelAttribute="raport">
        <tr>
            <td><form:input readonly="true" path="id" value="${raport.id}"/></td>
            <td><form:input label="Opis" value="${raport.description}" path="description"/></td>
            <td><form:select itemLabel="name" itemValue="id" items="${status}" path="status"/></td>
            <td><input type="submit" value="Zapisz zmiany"></td>
        </tr>
    </form:form>
</table>
<div width="100%" align="center">
        <c:forEach items="${dxfFileRaportEdit}" var="dxfFile">
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
                    <div>
                        <form class="no-print" method="post" action="/delDxfFileFromRaport">
                            <input type="hidden" name="del" value="${dxfFile.id}">
                            <button type="submit">~Usuń~</button>
                        </form>
                    </div>
                </figcaption>
            </figure>
        </c:forEach>
</div>
<div class="divFooter">
    <hr>
    <div class="block_container" style="border: 3px">
        <div>Zamawiający:&nbsp;</div>
        <div>${currentUser.user.fullName}</div>
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



