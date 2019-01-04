<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <%--<link href="css/index.css" rel="stylesheet">--%>
    <%--<link href="css/table.css" rel="stylesheet">--%>

</head>
<body>


<%@ include file="menuAdmin.jsp" %>
<div><h3><fmt:message key="label.tableUserName"/></h3></div>
<table class="table table-hover table-sm">
    <caption><h3><fmt:message key="label.tableUserName"/></h3></caption>
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.userLogin"/></th>
        <th><fmt:message key="label.userPassword"/></th>
        <th><fmt:message key="label.userEmail"/></th>
        <th><fmt:message key="label.userStatus"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="users" items="${users}">
        <td><c:out value=" ${users.login}"/></td>
        <td><c:out value=" ${users.password}"/></td>
        <td><c:out value=" ${users.email}"/></td>
        <td><c:out value=" ${users.userStatus}"/></td>
        <td>
            <form action="controller" method="POST">
                <input type="hidden" name="command" value="change-user-status"/>
                <input type="hidden" name="selectedUser" value="${users.login}"/>
                <select class="form-control form-control-sm" name="selectedStatus" onchange="this.form.submit()">
                    <option value="">change status</option>
                    <option value="1">Newcomer</option>
                    <option value="2">Experienced</option>
                    <option value="3">Blocked</option>
                </select>
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>