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
		<h1>Escolha o serviço de pagamento</h1>
	</div>

	<h2>Pagar o total de R$ ${cart.total}</h2>

	<c:if test="${not empty cart.products}">
		<h3>Pagar com pagseguro</h3>
		<ul>
			<li><a href="pagseguro/button.jsp">Pagamento Fixo - Botão de
					pagamento</a></li>
			<li><a href="pagseguro/api.jsp">Pagamento através da API
					Java</a></li>
		</ul>
	</c:if>

	<c:if test="${empty cart.products}">
		<h4>Carrinho vazio!</h4>
	</c:if>
</body>
</html>