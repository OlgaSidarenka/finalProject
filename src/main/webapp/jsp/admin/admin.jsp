<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link href="css/index.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
</head>
<html>
<%@ include file="../header.jsp" %>
<%@ include file="menuAdmin.jsp" %>
<table>
    <caption><h1><fmt:message key="label.tableAlienName"/></h1>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="show-alien-by-name"/>
            <input type="text" placeholder=
            <fmt:message key="label.alienName"/> name="alienName" value=""/>
            <fmt:message key="label.submit.searchAlien" var="buttonValue"/>
            <input type="submit" value="${buttonValue}">
        </form>
    </caption>
    <tr>
        <th><fmt:message key="label.id"/></th>
        <th><fmt:message key="label.alienName"/></th>
        <th><fmt:message key="label.alienDescription"/></th>
        <th><fmt:message key="label.alienHomeland"/></th>
        <th><fmt:message key="label.alienAverageMark"/></th>
        <th><fmt:message key="label.alienNewDescription"/></th>
    </tr>
    <c:forEach var="aliens" items="${aliens}">
        <td><c:out value=" ${aliens.alienId}"/></td>
        <td><c:out value=" ${aliens.alienName}"/></td>
        <td><c:out value=" ${aliens.description}"/></td>
        <td><c:out value=" ${aliens.homeland.homelandName}"/></td>
        <td><c:out value=" ${aliens.averageMark}"/></td>
        <form method="GET" action="controller">
            <td><textarea name="newDescription" cols="20" rows="2"></textarea></td>
            <td>
                <input type="hidden" name="command" value="update-alien"/>
                <input type="hidden" value="${aliens.alienName}" name="alienName"/>
                <input value="update" type="submit">
        </form>
        </td>
        <br>
        </tr>
    </c:forEach>
</table>

</body>
</html>