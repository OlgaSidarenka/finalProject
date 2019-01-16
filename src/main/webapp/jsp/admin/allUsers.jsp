<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="jsp"/>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js" ></script>
    <link href="css/footer.css" rel="stylesheet">
     <script>
        function selectFunction() {
            // Declare variables
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");
            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
 </head>
<body>
<%@ include file="menuAdmin.jsp" %>
<div><h3><fmt:message key="label.tableUserName"/></h3></div>

<input type="text" id="searchInput" onkeyup="selectFunction()" placeholder="Search by login..">
<table class="table table-hover table-sm" id="myTable">
    <caption><h3><fmt:message key="label.tableUserName"/></h3></caption>
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="label.userLogin"/></th>
        <th><fmt:message key="label.userPassword"/></th>
        <th><fmt:message key="label.userEmail"/></th>
        <th><fmt:message key="label.userCountReview"/></th>
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
            <td><c:out value=" ${users.countReview}"/></td>
            <td><p class="text-lowercase"><c:out  value=" ${users.userStatus}" /></p></td>
            <td>
                <form action="controller" method="GET">
                    <input type="hidden" name="command" value="change-user-status"/>
                    <input type="hidden" name="selectedUser" value="${users.login}"/>
                    <select class="form-control form-control-sm" name="selectedStatus" onchange="this.form.submit()">
                        <option value=""><fmt:message key="label.userChangeStatus"/></option>
                        <option value="1"><fmt:message key="label.userNewcomer"/></option>
                        <option value="2"><fmt:message key="label.userExperienced"/></option>
                        <option value="3"><fmt:message key="label.userBlocked"/></option>
                    </select>
                </form>
            </td>
            <td>
                <form method="POST" action="controller">
                    <input type="hidden" name="command" value="see-user-reviews"/>
                    <input type="hidden" value="${users.userId}" name="userId"/>
                    <fmt:message key="label.submit.seeReviews" var="buttonValue"/>
                    <input class="btn btn-outline-success btn-sm" type="submit" id="submit" value="${buttonValue}">
                </form>
            </td>
      </tr>
    </c:forEach>
    </tbody>
</table>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}\js\pagination.js"></script>--%>
<%@ include file="/jsp/footer.jsp" %>
</body>
</html>
