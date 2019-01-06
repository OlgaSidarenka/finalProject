<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link href="css/footer.css" rel="stylesheet">

    <%--<link href="css/table.css" rel="stylesheet">--%>

</head>
<body>
<%--<%@ include file="../header.jsp" %>--%>
<%@ include file="menuAdmin.jsp" %>
<div><h3><fmt:message key="label.tableAlienName"/></h3></div>
<table class="table table-hover table-sm">
    <caption><h3><fmt:message key="label.tableAlienName"/></h3></caption>
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.id"/></th>
        <th><fmt:message key="label.alienName"/></th>
        <th><fmt:message key="label.alienDescription"/></th>
        <th><fmt:message key="label.alienHomeland"/></th>
        <th><fmt:message key="label.alienAverageMark"/></th>
        <th><fmt:message key="label.alienNewDescription"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="aliens" items="${aliens}">
        <td><c:out value=" ${aliens.alienId}"/></td>
        <td><c:out value=" ${aliens.alienName}"/></td>
        <td><c:out value=" ${aliens.description}"/></td>
        <td><c:out value=" ${aliens.homeland.homelandName}"/></td>
        <td><c:out value=" ${aliens.averageMark}"/></td>
        <form method="GET" action="controller">
            <td><textarea class="form-control mr-sm-2" type="text" name="newDescription" cols="10" rows="1" required></textarea></td>
            <td>
                <input type="hidden" name="command" value="update-alien"/>
                <input type="hidden" value="${aliens.alienName}" name="alienName"/>
                <input class="btn btn-outline-success btn-sm" value="update" type="submit">
        </form>
        </td>
          </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}\js\pagination.js"></script>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>