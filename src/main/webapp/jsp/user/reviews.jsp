<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<html>
<head>

    <title><fmt:message key="label.title.review"/></title>
</head>
<body>
<%@ include file="menuUser.jsp" %>
<h3>${alienName}</h3>
<table class="table table-hover table-sm">
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.usersLogin"/></th>
        <th><fmt:message key="label.review"/></th>
        <th><fmt:message key="label.data"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="reviews" items="${reviews}">
        <td><c:out value=" ${reviews.login}"></c:out></td>
        <td><c:out value=" ${reviews.textReview}"></c:out></td>
        <td><c:out value=" ${reviews.dateReview}"></c:out></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<table class="table table-hover table-sm">
    <tbody>
    <tr>
        <form action="controller">
            <td>
                <input type="hidden" name="command" value="add-review"/>
                <input type="hidden" value="${user.login}" name="login"/>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="textReview" cols="70"placeholder="Enter review"></textarea>
            </td>
            <td>
                <input type="hidden" value="${alien}" name="alienId"/>
                <input class="btn btn-outline-success"type="submit" id="submit" value="add review">
            </td>
        </form>
    </tr>
    </tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}\js\pagination.js"></script>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>