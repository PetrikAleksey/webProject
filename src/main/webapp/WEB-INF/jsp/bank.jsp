<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CRUD Bank</title>
<%--	<jsp:include page="headConfig.jsp"/>--%>
    <link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%--<div class="alert alert-success alert-dismissible" id="myAlert">--%>
<%--	<a href="#" class="close">&times;</a>--%>
<%--	<strong>Success!</strong> This alert box could indicate a successful or positive action.--%>
<%--</div>--%>
	<div id="topButton">
		<div id="logo">Банк</div>
		<input type="button" class="btn btn-success topButton buttonAdd" data-toggle='modal' data-target="#exampleModal" value=<spring:message code="message.button.add"/>>
		<input type="button" disabled class="btn btn-success topButton buttonDelete" value=<spring:message code="message.button.delete"/> onclick="doAllSelectedDelete()">
		<input type="button" disabled class="btn btn-success topButton buttonEdit" data-toggle='modal' data-target='#exampleModal' value=<spring:message code="message.button.edit"/>>
		<span style="float: right">
    		<a href="<%=request.getContextPath()%>/bank?lang=ru">RU</a>
    		<a href="<%=request.getContextPath()%>/bank?lang=en">EN</a>
    	</span>
	</div>
	<div id="leftButton">
		<div class="vertical-menu">
				<a id="bank" class="active"><spring:message code="message.menu.bank"/></a>
				<a id="worker" ><spring:message code="message.menu.worker"/></a>
				<a id="client" ><spring:message code="message.menu.client"/></a>
				<a id="account" ><spring:message code="message.menu.account"/></a>
				<a href="<%=request.getContextPath()%>/index.jsp">Go index</a>
		</div>
	</div>
 	<div id="content">
 		<table id = "result_table_id" class = "result_table">
<%--			<tbody></tbody>--%>
 		</table>
 	</div>
	<!-- Модальное окно -->
	<div id="testModal" class="addContent">
	</div>
</body>
<%--<script>--%>
<%--	$(document).ready(function(){--%>
<%--		$(".close").click(function(){--%>
<%--			$("#myAlert").remove();--%>
<%--		});--%>
<%--	});--%>
<%--</script>--%>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="<c:url value='/resources/js/bank.js' />" type="text/javascript"></script>

<script>
	function sendTestAjax(url,type,data,callback) {
		console.log("url = " + url + " type = " + type + " data = " + data + " callback = " + callback);
		$.ajax({
			url : url,
			type: type,
			data : data,
			success: function (data) {
				callback(data);
			}
		});
	}

	function testModalAdd() {
		//debugger;
		sendTestAjax('workerModalAdd', 'GET', "", function (html) {
			if(($('#testModal').children().length) > 0){
				$('#testModal').empty();
			}
			$('#testModal').append(html);
			$("#exampleModal").modal('show');

			// $.when($('#testModal').append(html)).then(function () {
			// 	console.log("2");
			// 	$("#exampleModal").modal('show');
			// 	console.log("3");
			// });

			//m.html(html).ready(function () {
			//    $("#exampleModal").modal();
			//});
		});
		// $(function () {
		// 	$("#btn2").click(function () {
		// 		$("#myModal2").modal('show');
		// 	});
		// });
	}
</script>
</html>