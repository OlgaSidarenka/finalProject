<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js"></script>
    <link href="css/footer.css" rel="stylesheet">
</head>
<body>
<%@ include file="menuAdmin.jsp" %>
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
        <th><fmt:message key="label.alienNewDescription"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="aliens" items="${aliens}">
        <td><c:out value=" ${aliens.alienId}"/></td>
        <td><c:out value=" ${aliens.alienName}"/></td>
        <td style="width: 30%"><c:out value=" ${aliens.description}"/></td>
        <td><c:out value=" ${aliens.homeland.homelandName}"/></td>
        <td><c:out value=" ${aliens.averageMark}"/></td>
        <td>
            <form method="GET" action="controller">
                <div class="input-group mb-3">
          <textarea class="form-control mr-sm-2" type="text" name="newDescription" cols="10" rows="1"
                    required></textarea>
                    <div class="input-group-append">
                        <input type="hidden" name="command" value="update-alien"/>
                        <input type="hidden" value="${aliens.alienName}" name="alienName"/>
                        <input class="btn btn-outline-success btn-sm" value="update" type="submit" id="submit">
                    </div>
                </div>
            </form>
    </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>