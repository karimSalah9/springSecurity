<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- spring MVC Tag form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- JSTL Tag lib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/AthunticatedUser"
		method="post">

		<C:if test="${param.error != null }">
			<i style="color: red; font-size: 30px "> Sorry! You Entered Invalid UserName/Password</i>
		</C:if>
		<p>
			UserName : <input type="text" name="username">
		</p>
		<p>
			Password : <input type="password" name="password">
		</p>
		<p>
			<input type="submit" value="login">
		</p>
	</form:form>
</body>
</html>