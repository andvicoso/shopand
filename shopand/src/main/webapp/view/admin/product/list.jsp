<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" view="text/html; charset=UTF-8">
<title>Lista de Produtos</title>
</head>
<body>

	<div class="page-header">
		<h1>Lista de Produtos</h1>
	</div>
	
	<c:if test="${not empty products}">
		<table class="table table-bordered table-striped" id="productsTable">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Descrição</th>
					<th>Preço</th>
					<th>Útima Atualiz.</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product">
					<tr>
						<td>${product.name}</td>
						<td>${product.description}</td>
						<td>${product.price}</td>
						<td>${product.lastUpdate}</td>
						<td><a href="edit.do?id=${product.id}">Editar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<script type="text/javascript">
		$(document).ready(function() { 
			$("#productsTable").tablesorter(); 
		});
		</script>
	</c:if>

	<c:if test="${empty products}">
		<h4>Nenhum produto encontrado!</h4>
	</c:if>

</body>
</html>