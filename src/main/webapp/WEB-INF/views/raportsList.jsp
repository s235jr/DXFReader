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

<a href="/"><h1 class="no-print" align="center">Dxf Reader</h1></a>
<hr class="no-print">
<h3 class="no-print" align="center">Zlecenia</h3>
<hr class="no-print">
<div width="100%" align="center">
    <c:if test="${not empty raports}">
        <div align="center">

            <c:forEach items="${raports}" var="raport" varStatus="loop">
                <div>
                    <div class="blockraports">
                        <form style="width: max-content" method="get" action="/showRaport">
                            <input type="hidden" name="id" value="${raport.id}"/>
                            <button type="submit">
                                <table>
                                    <tr>
                                        <td><c:out value="${raport.status.name}"/></td>
                                        <td>Id:</td>
                                        <td><c:out value="${raport.id}"/></td>
                                        <td>Data utworzenia:</td>
                                        <td><c:out value="${raport.createdDate}"/></td>
                                        <td>Ilość elementów:</td>
                                        <td><c:out value="${raport.numberOfDxfFile}"/></td>
                                        <td>Opis:</td>
                                        <td><c:out value="${raport.description}"/></td>
                                    </tr>
                                </table>
                            </button>
                        </form>
                    </div>
                    <div class="blockraports">
                        <form style="width: 200px" method="get" action="/editRaport">
                            <input type="hidden" name="idEditRaport" value="${raport.id}"/>
                            <button type="submit">
                                <table>
                                    <tr>
                                        <td>Edytuj zlecenie</td>
                                    </tr>
                                </table>
                            </button>
                        </form>
                    </div>
                    <div class="blockraports">
                        <form style="width: 200px" method="post" action="/delRaport">
                            <input type="hidden" name="idDelRaport" value="${raport.id}"/>
                            <button type="submit">
                                <table>
                                    <tr>
                                        <td>Usuń zlecenie</td>
                                    </tr>
                                </table>
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

</body>
</html>



