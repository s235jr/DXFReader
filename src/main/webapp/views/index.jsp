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
<c:if test="${not empty user}">
    <div class="no-print" align="right">
        Witaj&nbsp;<c:out value="${user.firstName}"/>&nbsp;<c:out value="${user.lastName}"/>!
        <form method="get" action=/logout>
            <input type="submit" value="Wyloguj się"/>
        </form>
    </div>
</c:if>
<c:if test="${empty user}">
    <div class="no-print" align="right">
        Witaj Nieznajomy!
        <form style="display: inline-block" method="get" action=/login>
            <input type="submit" value="Zaloguj się"/>
        </form>
        <form style="display: inline-block" method="get" action="/addUser">
            <input type="submit" value="Zarejestruj się"/>
        </form>

    </div>
</c:if>
<h1 class="no-print" align="center">Dxf Reader</h1>
<hr>
<div class="no-print" align="center">
    <div class="block_container">
        <div id="loadFiles" align="left">
            <form method="post" action="/" enctype="multipart/form-data">
                <input type="file" name="files" multiple/>
                <input type="submit" value="Załaduj plik">
            </form>
        </div>
        <div id="newSession" align="right">
            <form method="POST" action="/clearSession">
                <input type="submit" value="Stwórz nowe zlecenie"/>
            </form>
        </div>
    </div>
</div>
<hr>
<div class="no-print" align="center">
    <c:if test="${not empty user}">
        <div class="block_container">
            <form method="post" action="/addFooter">
                <input READONLY type="text" name="firstName" value="${user.firstName}">
                <input READONLY type="text" name="lastName" value="${user.lastName}">
                <input type="text" name="description" value="${description}" placeholder="Opis">
                <input type="submit" value="Dodaj opis do stopki">
            </form>
            <div>
                <input type="button" onClick="window.print()" value="Drukuj"/>
            </div>
            <div>
                <form method="post" action="/saveRaport">
                    <input type="hidden" name="description" value="${description}">
                    <input type="submit" value="Zapisz zamówienie">
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${empty user}">
        <div class="block_container">
            <form method="post" action="/addFooter">
                <input type="text" name="firstName" value="${firstName}" placeholder="Imię">
                <input type="text" name="lastName" value="${lastName}" placeholder="Nazwisko">
                <input type="text" name="description" value="${description}" placeholder="Opis">
                <input type="submit" value="Dodaj do stopki">
            </form>
            <div>
                <input type="button" onClick="window.print()" value="Drukuj"/>
            </div>
        </div>
    </c:if>
</div>
<hr>
<div width="100%" align="center">
    <c:if test="${not empty dxfFileList}">
        <c:forEach items="${dxfFileList}" var="dxfFile" varStatus="loop">
            <figure>
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
                        <form class="no-print" method="post" action="/delDxfFileFromList">
                            <input type="hidden" name="del" value="${loop.count-1}">
                            <button type="submit">~Usuń~</button>
                        </form>
                    </div>
                </figcaption>
            </figure>
        </c:forEach>
    </c:if>
</div>
<div class="divFooter">
    <c:if test="${empty user}">
        <hr>
        <div class="block_container" style="border: 3px">
            <div>Zamawiający:&nbsp;</div>
            <div><c:out value="${firstName}"/>&nbsp;<c:out value="${lastName}"/></div>
        </div>
        <hr>
        <div class="block_container">
            <div>Data utworzenia:&nbsp;</div>
            <div><c:out value="${createDate}"/></div>
        </div>
        <hr>
        <div class="block_container">
            <div>Opis:&nbsp;</div>
            <div><c:out value="${description}"/></div>
        </div>
        <hr>
    </c:if>
    <c:if test="${not empty user}">
        <hr>
        <div class="block_container" style="border: 3px">
            <div>Zamawiający:&nbsp;</div>
            <div><c:out value="${user.firstName}"/>&nbsp;<c:out value="${user.lastName}"/></div>
        </div>
        <hr>
        <div class="block_container">
            <div>Data utworzenia:&nbsp;</div>
            <div><c:out value="${createDate}"/></div>
        </div>
        <hr>
        <div class="block_container">
            <div>Opis:&nbsp;</div>
            <div><c:out value="${description}"/></div>
        </div>
        <hr>
    </c:if>
</div>
</body>
</html>



