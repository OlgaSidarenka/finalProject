<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<nav class="navbar navbar-expand-lg navbar-light sticky-top" style="background-color:#8DC26F;">
    <a class="navbar-brand" href="#">
        <img src="${pageContext.request.contextPath}/img/alien.jpg" width="50" height="80" alt="">
    </a>
    <a class="navbar-brand" href="#">Login: ${user.login}, <br>${user.userRole}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a href="controller?command=show-aliens" class="nav-link">
                <fmt:message key="label.menu.showAliens"/></a>
            </li>
            <li class=nav-item">
            <form class="form-inline my-2 my-lg-0" method="POST" action="controller">
                <input type="hidden" name="command" value="show-rated-aliens"/>
                <input type="hidden" value="${user.userId}" name="userId"/>
                <fmt:message key="label.submit.usersAlien" var="buttonValue"/>
                <input type="submit" class="btn btn-link-default"value="${buttonValue}">
            </form>
            </li>
            <li class="nav-item"><a href="controller?command=logout" class="nav-link">
                <fmt:message key="label.menu.logout"/></a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="POST" action="controller">
            <input type="hidden" name="command" value="show-alien-by-name"/>
            <input class="form-control mr-sm-2" type="text" placeholder=
            <fmt:message key="label.alienName"/> name="alienName" value=""/>
            <fmt:message key="label.submit.searchAlien" var="buttonValue"/>
            <input class="btn btn-outline-dark btn-sm" type="submit" value="${buttonValue}">
        </form>
    </div>
</nav>
