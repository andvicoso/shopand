<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${product.name} - Detalhes</title>
</head>
<body>

	<div class="page-header">
		<h1>${product.name} - Detalhes</h1>
	</div>

	<c:if test="${not empty product}">
		<table class="table table-bordered table-striped" id="cartTable">
			<tbody>
				<tr>
					<td>Nome</td>
					<td>${product.name}</td>
				</tr>
				<tr>
					<td>Descrição</td>
					<td>${product.description}</td>
				</tr>
				<tr>
					<td>Preço</td>
					<td>${product.price}</td>
				</tr>
			</tbody>
		</table>

		<form action="${viewHref}/cart/add.do" method="post">
			<input type="hidden" name="id" value="${product.id}" />
			<input type="submit" value="Adicionar ao carrinho"
				class="btn btn-primary" />
		</form>

	</c:if>
</body>
</html>