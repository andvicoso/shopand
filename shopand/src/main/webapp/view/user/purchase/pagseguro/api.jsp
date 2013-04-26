<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Finalizar Compra</title>
</head>
<body>
	<div class="page-header">
		<h1>Pagamento através da API Java</h1>
	</div>

	<c:if test="${not empty cart.products}">
		<h2>Pagar o total de R$ ${cart.total}*</h2>

		<form action="${baseHref}/payment/pagseguro/placeOrder.do"
			method="post">
			<input type="submit" value="Pagar" class="btn-primary btn" />
		</form>

		<small>*Pagamento real de R$0,01</small>
	</c:if>



	<c:if test="${empty cart.products}">
		<jsp:forward page="${baseHref}/index.jsp" />
	</c:if>

</body>
</html>