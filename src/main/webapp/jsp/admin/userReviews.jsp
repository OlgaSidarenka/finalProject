<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js"></script>
    <link href="css/footer.css" rel="stylesheet">
</head>
<body>
<%@ include file="menuAdmin.jsp" %>
<div><h2><fmt:message key="label.tableUserReviews"/></h2></div>
<a href="controller?command=show-users" class="nav-link"><fmt:message key="label.link.backToUserList"/></a>
<table class="table table-hover table-sm" id="myTable">
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.usersLogin"/></th>
        <th><fmt:message key="label.alienName"/></th>
        <th><fmt:message key="label.review"/></th>
        <th><fmt:message key="label.data"/></th>
        <th><fmt:message key="label.reviewAvailable"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="reviews" items="${reviews}">
        <td><c:out value=" ${reviews.login}"></c:out></td>
        <td><c:out value=" ${reviews.alienName}"></c:out></td>
        <td><c:out value=" ${reviews.textReview}"></c:out></td>
        <td><c:out value=" ${reviews.dateReview}"></c:out></td>
        <td><c:out value=" ${reviews.unblocked}"></c:out></td>

        <td>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="block-review"/>
                <input type="hidden" value="${reviews.userId}" name="userId"/>
                <input type="hidden" value="${reviews.reviewId}" name="reviewId"/>
                <fmt:message key="label.submit.blockReview" var="buttonValue"/>
                <input class="btn btn-outline-danger btn-sm" type="submit" id="submit" value="${buttonValue}">
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}\js\pagination.js"></script>
<%@ include file="/jsp/footer.jsp" %>
</body>
