<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="headConfig.jsp" />
<title>CRUD Bank</title>
	<link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet" />
<%--	<script src="<c:url value='/resources/js/bank.js' />" type="text/javascript"></script>--%>
<%--	<script src="${pageContext.request.contextPath}/resources/js/bank.js" type="text/javascript"></script>--%>
</head>
<body>
<%--<div class="alert alert-success alert-dismissible" id="myAlert">--%>
<%--	<a href="#" class="close">&times;</a>--%>
<%--	<strong>Success!</strong> This alert box could indicate a successful or positive action.--%>
<%--</div>--%>
	<div id="topButton">
		<div id="logo">Банк</div>
 		<input type="button" class="btn btn-success topButton buttonAdd" data-toggle='modal' data-target="#exampleModal" value="Добавить">
		<input type="button" disabled class="btn btn-success topButton buttonDelete" value="Удалить" onclick="doAllSelectedDelete()">
		<input type="button" disabled class="btn btn-success topButton buttonEdit" data-toggle='modal' data-target='#exampleModal' value="Изменить">
        <input type="button" class="btn btn-success topButton buttonSearch" data-toggle='modal' data-target='#exampleModal' value="Поиск">
		<input type="search" class = "search" name="search" placeholder="Поиск">
	</div>
	<div id="leftButton">
		<div class="vertical-menu">
			<a id="bank" class="active">Банки</a>
			<a id="worker" >Рабочие</a>
			<a id="client" >Клиенты</a>
			<a id="account" >Аккаунты</a>
		</div>
	</div>
 	<div id="content">
 		<table id = "result_table_id" class = "result_table">
 		</table>
 	</div>
	<!-- Модальное окно -->
	<div class="addContent"></div>
    <div class="dialogSearch"></div>
</body>
<%--<script>--%>
<%--	$(document).ready(function(){--%>
<%--		$(".close").click(function(){--%>
<%--			$("#myAlert").remove();--%>
<%--		});--%>
<%--	});--%>
<%--</script>--%>
<script src="<c:url value='/resources/js/bank.js' />" type="text/javascript"></script>
</html>