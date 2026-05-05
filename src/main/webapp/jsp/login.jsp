<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Banking Application</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <html:errors/>
        <html:form action="/login" method="post">
            <table>
                <tr>
                    <td><label>Username:</label></td>
                    <td><html:text property="username" size="30"/></td>
                </tr>
                <tr>
                    <td><label>Password:</label></td>
                    <td><html:password property="password" size="30"/></td>
                </tr>
                <tr>
                    <td colspan="2"><html:submit value="Login"/></td>
                </tr>
            </table>
        </html:form>
        <p><a href="register.jsp">New User? Register here</a></p>
    </div>
</body>
</html>
