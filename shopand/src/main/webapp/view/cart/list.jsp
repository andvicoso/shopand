<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" view="text/html; charset=UTF-8">
<title>Carrinho de Compras</title>
</head>
<body>

	<div class="page-header">
		<h1>Carrinho de Compras</h1>
	</div>

	<c:if test="${not empty cart.products}">
		<table class="table table-bordered table-striped" id="cartTable">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Preço</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cart.products}" var="product">
					<tr>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td><a href="remove.do?id=${product.id}">Remover</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h2>Total: ${cart.total}</h2>

		<form method="post" action="../purchase/payment.jsp">
			<button type="submit" class="btn">Finalizar Compra</button>
		</form>
		
		<script type="text/javascript">
		$(document).ready(function() { 
			$("#cartTable").tablesorter(); 
		});
		</script>
		
	</c:if>
	<c:if test="${empty cart.products}">
		<h4>Carrinho vazio!</h4>
	</c:if>

</body>
</html>