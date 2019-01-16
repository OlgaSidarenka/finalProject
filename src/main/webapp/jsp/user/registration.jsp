<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<html>
<head><title><fmt:message key="label.registration"/></title>
    <link href="../../css/index.css" rel="stylesheet">
    <link href="css/menu.css" rel="stylesheet">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
</head>
<body>
<div class="login-page">
    <div class="form">
        <h4><fmt:message key="label.registration"/></h4>
        <form style="whidth:400px" name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="registration"/>
            <input style="margin: 0px" type="text" name="login" pattern="[a-zA-Z][a-zA-Z0-9-_]{4,20}" placeholder=
            <fmt:message key="label.login"/> required/>
            <p style="margin: 0px" class="message"><fmt:message key="label.loginPattern"/></p>
            <br/> <input style="margin: 0px" type="password" name="password"
                         pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}"
                         placeholder=<fmt:message key="label.password"/> required/>
            <p style="margin: 0px" class="message" ><fmt:message key="label.PasswordPattern"/></p>
            <input style="margin: 15px" type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" name="email"
                   placeholder=
                   <fmt:message key="label.email"/> required value=""/>
            ${errorLoginPassMessage} ${wrongAction} <br/> ${nullPage} <br/>
            <fmt:message key="label.submit.send" var="buttonValue"/>
            <input type="submit" id="submit" value="${buttonValue}">
            <p class="message"><a href="controller?command=logout"><fmt:message key="label.submit.backToLogin"/></a></p>
            <%--<p align="center" class="message"> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} </p>--%>
        </form>
        <c:if test="${not empty wrongInfoData}">
            <div class="alert-danger" align="centre">
                    ${wrongInfoData}
            </div>
        </c:if>
    </div>
</div>
</div>
</body>
</html>