<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/footerTag.tld" prefix="footer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="jsp">
    <html lang="${language}">
    <head>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link href="css/index.css" rel="stylesheet">
        <title><fmt:message key="label.title"/></title>
    </head>
    <body>
    <form method="POST" action="controller">
        <input type="hidden" name="command" value="change-language"/>
        <button type="submit" name="language"
                value="ru"><fmt:message key="label.submit.languageRussian"/>
        </button>
        <button type="submit" name="language"
                value="en"><fmt:message key="label.submit.languageEnglish"/>
        </button>
    </form>
    <div class="login-page">
        <div class="form">
            <form id="login-form" name="loginForm" method="POST" action="controller" role="form"
                  style="display: block;"/>
                <input type="hidden" name="command" value="login"/>
                <input type="text" placeholder=
                    <fmt:message key="label.login"/> name="login" required value=""/>
                <input type="password" placeholder=
                    <fmt:message key="label.password"/> name="password" required value=""/>

                <fmt:message key="label.submit.login" var="buttonValue"/>
                <input type="submit" id="submit" value="${buttonValue}">
                <p class="message"><fmt:message key="label.notregistrated"/> <a
                        href="controller?command=go-to-registration-page"><fmt:message key="label.registration"/></a></p>
                <%--<p align="center" class="message"> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} </p>--%>
            </form>
            <c:if test="${not empty wrongInfoDataLogin}">
                <div class="alert-danger" align="centre">
                        ${wrongInfoDataLogin}
                </div>
            </c:if>
        </div>
    </div>
    </form>
</fmt:bundle>
</body>
</html>
