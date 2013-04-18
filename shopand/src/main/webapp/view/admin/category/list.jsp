<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" view="text/html; charset=UTF-8">
<title>Lista de Categorias</title>
</head>
<body>
	<div class="page-header">
		<h1>Lista de Categorias</h1>
	</div>

	<c:if test="${not empty categories}">
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${categories}" var="category">
					<tr>
						<td>${category.name}</td>
						<td><a href="edit.do?id=${category.id}">Editar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<c:if test="${empty categories}">
		<h4>Nenhuma categoria encontrada!</h4>
	</c:if>

</body>
</html>