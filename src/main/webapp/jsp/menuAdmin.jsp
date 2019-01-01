<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link href="css/menu.css" rel="stylesheet">
<%--Login: ${user.login}, <br>${user.userRole}--%>

<nav id="primary_nav_wrap" role='navigation'>
    <ul>Login: ${user.login}, <br>${user.userRole}</ul>
    <ul>
        <li><a href="#"><fmt:message key="label.menu.aliens"/></a>
            <ul>
                <li><a href="controller?command=show-aliens"><fmt:message key="label.menu.showAliens"/></a></li>
                <li><a href="controller?command=fill-new-alien-data"><fmt:message key="label.menu.addAlien"/></a></li>
            </ul>
        </li>
        <li><a href="controller?command=show-users"><fmt:message key="label.users"/></a></li>
        <li><a href="controller?command=logout"><fmt:message key="label.menu.logout"/></a></li>
    </ul>
</nav>
