<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Banking Application</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= session.getAttribute("customerName") %></h2>
        <div class="menu">
            <ul>
                <li><html:link action="/listAccounts">View Accounts</html:link></li>
                <li><html:link page="/jsp/createAccount.jsp">Create Account</html:link></li>
                <li><html:link page="/jsp/transfer.jsp">Transfer Funds</html:link></li>
                <li><html:link action="/logout">Logout</html:link></li>
            </ul>
        </div>
    </div>
</body>
</html>
