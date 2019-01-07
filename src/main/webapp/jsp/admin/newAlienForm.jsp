<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
     <link href="css/footer.css" rel="stylesheet">
</head>
<html>
<%@ include file="menuAdmin.jsp" %>
<div style="width: 50%" class="form-group">
    <form class="login-form" name="loginForm" method="POST" action="controller">
        <h3><fmt:message key="label.alien.fillForm"/></h3>
        <input type="hidden" name="command" value="add-alien"/>

        <h5>Upload File:</h5>
        <br/>
        <INPUT type="file" name="content">
        <br/><br/>


        <label for="formAlienInput"> <fmt:message key="label.alienName"/> </label>
        <input type="text" class="form-control" id="formAlienInput" required
               name="alienName" value=""/>
        <label for="formAlienInput"> <fmt:message key="label.alienHomeland"/> </label>
        <input type="text" class="form-control" id="formAlienInput"
               name="alienHomeland" required value=""/>
        <div class="form-group">
            <label for="descriptionAlienInput"><fmt:message key="label.alienDescription"/></label>
            <textarea class="form-control" id="descriptionAlienInput" rows="3" name="alienDescription" pattern></textarea>
        </div>
        <fmt:message key="label.submit.send" var="buttonValue"/>
        <input class="btn btn-outline-success btn-sm" type="submit" id="submit" value="${buttonValue}">
        <c:if test="${not empty infoData}">
        <div class="alert-success" align="centre">
                ${infoData}
        </div>
        </c:if>
</div>
</form>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>