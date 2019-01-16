<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<html>
<head>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
        <%--<link rel="stylesheet" href="/css/font-awesome.min.css">--%>
    <script src="/js/bootstrap.min.js" ></script>
    <title><fmt:message key="label.title.review"/></title>
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/star.css" rel="stylesheet">
</head>
<body>
<%@ include file="menuUser.jsp" %>

<table width="40%">
        <tr>
        <td width="500px">
            <h3>
                ${alien.homeland.homelandName}<br>
                ${alien.alienName}
                ${alien.averageMark}
            </h3>
        </td>
            <td width="500px">
            <div class="form-group">
                <form action="controller" method="POST">
                    <input type="hidden" name="command" value="rate-alien"/>
                    <input type="hidden" value="${user.login}" name="login"/>
                    <input type="hidden" value="${alien.alienId}" name="alienId"/>
                    <input type="hidden" value="${alien.alienName}" name="alienName"/>
                    <div class="star-rating">
                        <fieldset>
                            <input onchange="form.submit()" type="radio" id="star5" name="rating"
                                   value="5"/><label for="star5" title="Outstanding">5</label>
                            <input onchange="form.submit()" type="radio" id="star4" name="rating"
                                   value="4"/><label for="star4" title="Very Good">4</label>
                            <input onchange="form.submit()" type="radio" id="star3" name="rating" value="3"/><label
                                for="star3" title="Good">3</label>
                            <input onchange="form.submit()" type="radio" id="star2" name="rating" value="2"/><label
                                for="star2" title="Poor">2</label>
                            <input onchange="form.submit()" type="radio" id="star1" name="rating" value="1"/><label
                                for="star1" title="Very Poor">1</label>
                        </fieldset>
                    </div>
                    <c:if test="${not empty infoData}">
                        <div class="alert-danger" align="centre">
                                ${infoData}
                        </div>
                    </c:if>
                </form>
            </div>

        </td>
    </tr>
</table>
<table class="table table-hover table-sm" id="myTable">
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
                <input type="hidden" value="${alien.alienName}" name="alienName"/>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="textReview" cols="70"
                          placeholder="Enter review"></textarea>
            </td>
            <td>
                <input type="hidden" value="${alien.alienId}" name="alienId"/>
                <input class="btn btn-outline-success" type="submit" id="submit" value="add review">
            </td>
        </form>
    </tr>
    </tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}\js\pagination.js"></script>
<%@ include file="/jsp/footer.jsp" %>
</br>
</body>
</html>