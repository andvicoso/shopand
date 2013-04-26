<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
						<td><form action="remove.do" method="post" style="margin: 0px" id="removeForm_${product.id}">
								<input type="hidden" name="id" value="${product.id}" />
								<input type="submit" value="Remover"
									class="btn btn-danger" 
									onclick="confirm('Tem certeza que deseja remover?') ? $('#removeForm_${product.id}').submit() : false;"/>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<h2>Total: ${cart.total}</h2>

		<tags:ifUser>
			<form method="post" action="${userHref}/purchase/choose_service.jsp">
				<button type="submit" class="btn btn-primary">Finalizar Compra</button>
			</form>
		</tags:ifUser>
		
		<tags:ifGuest>
			<form method="post" action="${guestHref}/login.jsp">
				<button type="submit" class="btn btn-primary">Entrar e Pagar</button>
			</form>
		</tags:ifGuest>
		
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