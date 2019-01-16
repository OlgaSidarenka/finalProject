<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="jsp"/>
<html>
<head>
</head>
<body>
<h3>WRRONG COMMAND</h3>
<img src="${pageContext.request.contextPath}/img/alien.jpg" width="300" height="450">
<form name="goToMainPage" method="post" action="controller">
    <input type="hidden" name="command" value="go-to-start-page">
    <button type="submit" class="btn btn-danger">Go to start page</button>
</form>
</body>
</html>