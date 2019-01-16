<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/pagination.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}\js\pagination.js"></script>--%>
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link href="css/footer.css" rel="stylesheet">
</head>
<body>
<%@ include file="menuUser.jsp" %>
<div><h3><fmt:message key="label.tableAlienName"/></h3></div>
<table class="table table-hover table-sm" id="myTable">
    <caption><h3><fmt:message key="label.tableAlienName"/></h3></caption>
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.id"/></th>
        <th><fmt:message key="label.alienName"/></th>
        <th><fmt:message key="label.alienDescription"/></th>
        <th><fmt:message key="label.alienHomeland"/></th>
        <th><fmt:message key="label.alienAverageMark"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="aliens" items="${aliens}">
        <td><c:out value=" ${aliens.alienId}"></c:out></td>
        <td><c:out value=" ${aliens.alienName}"></c:out></td>
        <td style="width: 30%"><c:out value=" ${aliens.description}"></c:out></td>
        <td><c:out value=" ${aliens.homeland.homelandName}"></c:out></td>
        <td><c:out value=" ${aliens.averageMark}"></c:out></td>
        <td>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="see-reviews"/>
                <input type="hidden" value="${aliens.alienId}" name="alienId"/>
                <input type="hidden" value="${aliens.alienName}" name="alienName"/>
                <fmt:message key="label.submit.seeReviews" var="buttonValue"/>
                <input class="btn btn-outline-success btn-sm" type="submit" id="submit" value="${buttonValue}">
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</form>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>