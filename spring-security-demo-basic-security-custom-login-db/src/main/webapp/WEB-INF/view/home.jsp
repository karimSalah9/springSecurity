<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- spring MVC Tag form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- JSP Tag Libraries Spring Security -->
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<h2>Welcome To HomePage!</h2>

	User
	<security:authentication property="principal.username" />
	<br> Role(s)
	<security:authentication property="principal.authorities" />


	<br>
	<!-- add link for leaders -->
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders"> LeaderShip
				Managment </a> (only for Managers)
		</p>
	</security:authorize>
	<br>
	<!-- add link for Admins -->
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/admins"> Admin
				Managment </a> (only for Admins)
		</p>
	</security:authorize>
	<br>
	<form:form action="${pageContext.request.contextPath}/logout"
		method="post">
		<input type="submit" value="logout">
	</form:form>

</body>
</html>