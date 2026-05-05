<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transfer Funds - Banking Application</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h2>Transfer Funds</h2>
        <html:errors/>
        <html:form action="/transferFunds" method="post">
            <table>
                <tr><td>From Account ID:</td><td><html:text property="fromAccountId" size="30"/></td></tr>
                <tr><td>To Account ID:</td><td><html:text property="toAccountId" size="30"/></td></tr>
                <tr><td>Amount:</td><td><html:text property="amount" size="30"/></td></tr>
                <tr><td colspan="2"><html:submit value="Transfer"/></td></tr>
            </table>
        </html:form>
        <p><html:link page="/jsp/dashboard.jsp">Back to Dashboard</html:link></p>
    </div>
</body>
</html>
