<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/footerTag.tld" prefix="footer"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="jsp">
    <html lang="${language}">
    <head>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link href="css/index.css" rel="stylesheet">
        <title><fmt:message key="label.title"/></title>
    </head>
    <body>
    <%--<%@ include file="/jsp/header.jsp" %>--%>
    <%--<form action="controller" method="POST">--%>
        <%--<input type="hidden" name="command" value="rate-alien"/>--%>
        <%--<input type="hidden" value="${user.login}" name="login"/>--%>
        <%--<input type="hidden" value="${aliens.alienId}" name="alienId"/>--%>
        <%--<div class="star-rating">--%>
                <%--<fieldset>--%>
            <%--<input onchange="form.submit()"  type="radio" id="star5" name="rating" value="5" /><label for="star5" title="Outstanding">5 stars</label>--%>
            <%--<input onchange="form.submit()"  type="radio" id="star4" name="rating" value="4" /><label for="star4" title="Very Good">4 stars</label>--%>
            <%--<input onchange="form.submit()"  type="radio" id="star3" name="rating" value="3" /><label for="star3" title="Good">3 stars</label>--%>
            <%--<input onchange="form.submit()"  type="radio" id="star2" name="rating" value="2" /><label for="star2" title="Poor">2 stars</label>--%>
            <%--<input onchange="form.submit()"  type="radio" id="star1" name="rating" value="1" /><label for="star1" title="Very Poor">1 star</label>--%>
                <%--</fieldset>--%>
        <%--</div>--%>
    <%--</form>--%>
    <%--&lt;%&ndash;<form action="controller" method="POST">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<div class="star-rating">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<fieldset>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="Outstanding">5 stars</label>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="Very Good">4 stars</label>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="Good">3 stars</label>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="Poor">2 stars</label>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="Very Poor">1 star</label>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</fieldset>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</form>&ndash;%&gt;--%>

    <%--<div class="stars">--%>
        <%--<form action="">--%>
            <%--<input onchange="form.submit()" class="star star-5" id="star-5" type="radio" name="star"/>--%>
            <%--<label class="star star-5" for="star-5"></label>--%>
            <%--<input class="star star-4" id="star-4" type="radio" name="star"/>--%>
            <%--<label class="star star-4" for="star-4"></label>--%>
            <%--<input class="star star-3" id="star-3" type="radio" name="star"/>--%>
            <%--<label class="star star-3" for="star-3"></label>--%>
            <%--<input class="star star-2" id="star-2" type="radio" name="star"/>--%>
            <%--<label class="star star-2" for="star-2"></label>--%>
            <%--<input class="star star-1" id="star-1" type="radio" name="star"/>--%>
            <%--<label class="star star-1" for="star-1"></label>--%>
        <%--</form>--%>
    <%--</div>--%>



    <form  method="POST" action="controller">
        <input type="hidden" name="command" value="change-language"/>
    <button type="submit" name="language"
            value="ru" class="btn btn-default navbar-btn">RU</button>
    <button type="submit" name="language"
            value="en" class="btn btn-default navbar-btn">EN</button>
    </form>
    <div class="login-page">
        <div class="form">
            <form class="login-form" name="loginForm" method="POST" action="controller">
                <input type="hidden" name="command" value="login"/>
                <input type="text" placeholder=<fmt:message key="label.login"/> name="login" value=""/>
                <input type="password" placeholder=<fmt:message key="label.password"/> name="password" value=""/>
                <fmt:message key="label.submit.login" var="buttonValue"/>
                <input type="submit" id="submit" value="${buttonValue}">
                <p class="message"><fmt:message key="label.notregistrated"/> <a
                        href="controller?command=goToRegistrationPage"><fmt:message key="label.registration"/></a></p>
                <p align="center" class="message"> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} </p>
            </form>
            <c:if test="${not empty wrongInfoData}">
                <div class="alert-danger" align="centre">
                        ${wrongInfoData}
                </div>
            </c:if>
        </div>
    </div>
    </form>
</fmt:bundle>




</body>

</html>
