<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js"></script>
    <link href="css/footer.css" rel="stylesheet">
</head>
<html>
<%@ include file="menuAdmin.jsp" %>
<div style="width: 50%" class="form-group">
    <form class="login-form" name="loginForm" method="POST" action="controller">
        <h3 class="text-success"><fmt:message key="label.alien.fillForm"/></h3>
        <input type="hidden" name="command" value="add-alien"/>
        <label class="text-success" for="formAlienInput"> <fmt:message key="label.alienName"/> </label>
        <input type="text" class="form-control" id="formAlienInput" required
               name="alienName" value=""/>
        <label class="text-success" for="formAlienInput"> <fmt:message key="label.alienHomelandInForm"/> </label>
        <select class="form-control mdb-select md-form" searchable="Search here.." name="alienHomeland">
            <c:forEach var="homelands" items="${homelands}">
                <option value="${homelands.homelandId}">${homelands.homelandName}</option>
            </c:forEach>
        </select>
        <div class="form-group">
            <label class="text-success" for="descriptionAlienInput"><fmt:message key="label.alienDescription"/></label>
            <textarea class="form-control" id="descriptionAlienInput" rows="3" name="alienDescription"
                      pattern></textarea>
        </div>
        <fmt:message key="label.submit.send" var="buttonValue"/>
        <input class="btn btn-success btn-sm" type="submit" id="submit" value="${buttonValue}">
        <c:if test="${not empty infoData}">
        <div class="alert-success" align="centre">
                ${infoData}
        </div>
        </c:if>
</div>
</form>
<hr>
<div>
    <form style="width: 50%" method="GET" action="controller">
        <h3 class="text-info"><fmt:message key="label.homeland.fillForm"/></h3>
        <input type="text" class="form-control" name="newHomeland" required value=""/>
        <div class="input-group-append">
            <input type="hidden" name="command" value="add-homeland"/>
            <fmt:message key="label.submit.addHomeland" var="buttonValue"/>
            <input class="btn btn-info btn-sm" value="${buttonValue}" type="submit">
            <c:if test="${not empty infoDataHomeland}">
                <div class="alert-success" align="centre">
                        ${infoDataHomeland}
                </div>
            </c:if>
        </div>
    </form>
</div>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>