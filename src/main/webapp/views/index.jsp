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
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"--%>
          <%--integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">--%>
    <style>
        figure {
            display: inline-block;
            width: 300px;
            height: auto;
            padding: 0;
            border: 3px solid black;
            margin: 0;
        }
    </style>
</head>
<body>
<h1 class="no-print" align="center">Dxf Reader</h1>

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

<div class="no-print" align="center">
    <form method="post" action="/addFooter">
        <label>Imię:</label>
        <input type="text" name="firstName" value="${firstName}">
        <label>Nazwisko:</label>
        <input type="text" name="lastName" value="${lastName}">
        <label>Opis:</label>
        <input type="text" name="description" value="${description}">
        <input type="submit" value="Zapisz">
    </form>
</div>

<div width="100%" align="center">
    <c:if test="${not empty dxfFileList}">
        <c:forEach items="${dxfFileList}" var="dxfFile" varStatus="loop">
            <figure>
                <img width="300px" height="auto" src="<c:url value="${dxfFile.namePng}"/>" alt="image">
                <figcaption>
                    <hr>
                    <div class="block_container">
                        <div width="15%"><c:out value="${dxfFile.thickness}"/>mm&nbsp;||&nbsp;</div>
                        <div width="15%"><c:out value="${dxfFile.amount}"/>&nbsp;||&nbsp;</div>
                        <div width="10%"><c:out value="${dxfFile.materialTyp}"/></div>
                    </div>
                    <hr>
                    <div class="block_container">
                        <div width="20%">szer:&nbsp;</div>
                        <div width="20%"><fmt:formatNumber type="number" maxFractionDigits="0"><c:out
                                value="${dxfFile.width}"/></fmt:formatNumber>mm&nbsp;||&nbsp;
                        </div>
                        <div width="20%">wys:&nbsp;</div>
                        <div width="20%"><fmt:formatNumber type="number" maxFractionDigits="0"><c:out
                                value="${dxfFile.height}"/></fmt:formatNumber>mm
                        </div>
                    </div>
                    <hr>
                    <div class="block_container">
                        <div width="25%">Nazwa:&nbsp;</div>
                        <div width="25%"><c:out value="${dxfFile.name}"/>.dxf&nbsp;</div>
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
</div>

</body>
</html>



