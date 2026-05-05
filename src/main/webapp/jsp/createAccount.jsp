<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Account - Banking Application</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h2>Create New Account</h2>
        <html:errors/>
        <html:form action="/createAccount" method="post">
            <table>
                <tr><td>Account Number:</td><td><html:text property="accountNumber" size="30"/></td></tr>
                <tr>
                    <td>Account Type:</td>
                    <td>
                        <html:select property="accountType">
                            <html:option value="SAVINGS">Savings</html:option>
                            <html:option value="CHECKING">Checking</html:option>
                            <html:option value="FIXED_DEPOSIT">Fixed Deposit</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr><td>Initial Balance:</td><td><html:text property="balance" size="30"/></td></tr>
                <tr><td colspan="2"><html:submit value="Create Account"/></td></tr>
            </table>
        </html:form>
        <p><html:link page="/jsp/dashboard.jsp">Back to Dashboard</html:link></p>
    </div>
</body>
</html>
