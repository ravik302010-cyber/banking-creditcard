<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Banking Application</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h2>Customer Registration</h2>
        <html:errors/>
        <html:form action="/registerCustomer" method="post">
            <table>
                <tr><td>First Name:</td><td><html:text property="firstName" size="30"/></td></tr>
                <tr><td>Last Name:</td><td><html:text property="lastName" size="30"/></td></tr>
                <tr><td>Email:</td><td><html:text property="email" size="30"/></td></tr>
                <tr><td>Phone:</td><td><html:text property="phone" size="30"/></td></tr>
                <tr><td>Address:</td><td><html:textarea property="address" rows="3" cols="30"/></td></tr>
                <tr><td>Username:</td><td><html:text property="username" size="30"/></td></tr>
                <tr><td>Password:</td><td><html:password property="password" size="30"/></td></tr>
                <tr><td>Confirm Password:</td><td><html:password property="confirmPassword" size="30"/></td></tr>
                <tr><td colspan="2"><html:submit value="Register"/></td></tr>
            </table>
        </html:form>
    </div>
</body>
</html>
