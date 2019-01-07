<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">--%>

    <link href="css/footer.css" rel="stylesheet">
</head>
<body>
<%@ include file="menuUser.jsp" %>
<input type="hidden" name="userId" value=${user.userId}/>

<table class="table table-hover table-sm" id="resultTable">
    <caption><h3><fmt:message key="label.tableAlienName"/></h3></caption>
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.id"/></th>
        <th><fmt:message key="label.alienName"/></th>
        <th><fmt:message key="label.alienDescription"/></th>
        <th><fmt:message key="label.alienHomeland"/></th>
        <th><fmt:message key="label.alienAverageMark"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="aliens" items="${aliens}">
        <td><c:out value=" ${aliens.alienId}"></c:out></td>
        <td><c:out value=" ${aliens.alienName}"></c:out></td>
        <td style="width: 30%"><c:out value=" ${aliens.description}"></c:out></td>
        <td><c:out value=" ${aliens.homeland.homelandName}"></c:out></td>
        <td><c:out value=" ${aliens.averageMark}"></c:out></td>
        <td>
            <%--<form action="controller" method="POST">--%>
                <%--<input type="hidden" name="command" value="rate-alien"/>--%>
                <%--<input type="hidden" value="${user.login}" name="login"/>--%>
                <%--<input type="hidden" value="${aliens.alienId}" name="alienId"/>--%>
                <%--<div class="star-rating">--%>
                    <%--<fieldset>--%>
                        <%--<input onchange="form.submit()"  type="radio" id="star5" name="rating"--%>
                               <%--value="5"/><label for="star5" title="Outstanding">5</label>--%>
                        <%--<input onchange="form.submit()" type="radio" id="star4" name="rating"--%>
                               <%--value="4"/><label for="star4" title="Very Good">4</label>--%>
                        <%--<input onchange="form.submit()" type="radio" id="star3" name="rating" value="3"/><label--%>
                            <%--for="star3" title="Good">3</label>--%>
                        <%--<input onchange="form.submit()" type="radio" id="star2" name="rating" value="2"/><label--%>
                            <%--for="star2" title="Poor">2</label>--%>
                        <%--<input onchange="form.submit()" type="radio" id="star1" name="rating" value="1"/><label--%>
                            <%--for="star1" title="Very Poor">1</label>--%>
                    <%--</fieldset>--%>
                <%--</div>--%>
            <%--</form>--%>
        </td>
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
<script type="text/javascript" src="${pageContext.request.contextPath}\js\pagination.js"></script>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>