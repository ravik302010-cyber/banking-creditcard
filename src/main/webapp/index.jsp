<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
<head>
    <title>Banking Application - Welcome</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Welcome to Legacy Banking System</h1>
        <p>Struts 1.x + EJB 2.x Application</p>
        
        <div class="menu">
            <ul>
                <li><html:link forward="login">Login</html:link></li>
                <li><html:link page="/jsp/register.jsp">Register</html:link></li>
            </ul>
        </div>
    </div>
</body>
</html>