<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<link href="css/index.css" rel="stylesheet">
<link href="css/table.css" rel="stylesheet">
<html>
<head><title>Welcome</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="menuUser.jsp" %>
<p>Reviews page</p>
${alien}, hello!
<table>
    <tr>
        <th>user login</th>
        <th>review</th>
        <th>date</th>

    </tr>
    <tr>
        <c:forEach var="reviews" items="${reviews}">
        <td><c:out value=" ${reviews.login}"></c:out></td>
        <td><c:out value=" ${reviews.textReview}"></c:out></td>
        <td><c:out value=" ${reviews.dateReview}"></c:out></td>
    </tr>
    </c:forEach>
</table>
<table>
    <tr>
        <form action="controller">
            <td>
                <input type="hidden" name="command" value="add-review"/>
                <input type="hidden" value="${user.login}" name="login"/>
                <textarea name="textReview" cols="70" rows="5" placeholder="Enter review"/><br>
            </td>
            <td>
                <input type="hidden" value="${alien}" name="alienId"/>
                <input type="submit" id="submit" value="add review">
            </td>
        </form>
    </tr>
</table>
<a href="controller?command=Show-Aliens">Logout</a>
</body>
</html>