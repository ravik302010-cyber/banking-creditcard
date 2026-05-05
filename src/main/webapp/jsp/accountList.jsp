<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Accounts - Banking Application</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h2>My Accounts</h2>
        <table border="1">
            <tr>
                <th>Account Number</th>
                <th>Type</th>
                <th>Balance</th>
                <th>Status</th>
            </tr>
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td>${account.accountNumber}</td>
                    <td>${account.accountType}</td>
                    <td>${account.balance}</td>
                    <td>${account.status}</td>
                </tr>
            </c:forEach>
        </table>
        <p><html:link page="/jsp/dashboard.jsp">Back to Dashboard</html:link></p>
    </div>
</body>
</html>
