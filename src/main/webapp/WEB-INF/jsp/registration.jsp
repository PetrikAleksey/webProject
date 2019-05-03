<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
<title>Insert title here</title>
	<script><%@include file="/WEB-INF/js/script.js"%></script>
</head>
<body>
 <form action="<c:url value="/registration"/>" method="post">
	<label for = "firstname">First name:</label>
	<input type = "text" id = "firstname" name = "firstname"><br>
		<label for = "lastname">Last name:</label>
	<input type = "text" id = "lastname" name = "lastname"><br>
	<button type = "submit" value = "submit">Save</button>
</form>
<p>${firstname}</p>
<p>${lastname}</p>
</body>
</html>