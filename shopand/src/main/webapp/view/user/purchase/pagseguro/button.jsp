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
		<h1>Pagamento Fixo - Botão de Pagamento</h1>
	</div>

	<c:if test="${not empty cart.products}">

		<h2>Pagar o total de R$ ${cart.total}*</h2>

		<!-- INICIO FORMULARIO BOTAO PAGSEGURO -->
		<form target="pagseguro"
			action="https://pagseguro.uol.com.br/checkout/v2/cart.html?action=add"
			method="post">
			<!-- NÃO EDITE OS COMANDOS DAS LINHAS ABAIXO -->
			<input type="hidden" name="itemCode"
				value="6769BE5B79795B6CC49F2FAB2D6447D0" />
			<input type="image"
				src="https://p.simg.uol.com.br/out/pagseguro/i/botoes/pagamentos/120x53-pagar-cinza.gif"
				name="submit" alt="Pague com PagSeguro - é rápido, grátis e seguro!" />
		</form>
		<!-- FINAL FORMULARIO BOTAO PAGSEGURO -->

		<small>*Pagamento real de R$0,01</small>
	</c:if>
	
	<c:if test="${empty cart.products}">
		<jsp:forward page="${baseHref}/index.jsp"/>
	</c:if>
	
</body>
</html>