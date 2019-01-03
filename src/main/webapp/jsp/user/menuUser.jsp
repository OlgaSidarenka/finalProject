<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link href="css/menu.css" rel="stylesheet">
<nav id="primary_nav_wrap" role='navigation'>
    <ul>Login: ${user.login}, <br>${user.userRole}</ul>
    <ul>
        <li><a href="#"><fmt:message key="label.menu.aliens"/></a></li>
        <li><a href="controller?command=logout"><fmt:message key="label.menu.logout"/></a></li>
    </ul>
</nav>
