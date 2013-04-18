<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" view="text/html; charset=UTF-8">
<title>Lista de Usuários</title>
</head>
<body>

	<div class="page-header">
		<h1>Lista de Usuários</h1>
	</div>
	
	<c:if test="${not empty users}">
		<table class="table table-bordered table-striped">
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
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.description}</td>
						<td>${user.price}</td>
						<td>${user.lastUpdate}</td>
						<td><a href="edit.do?id=${user.id}">Editar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<c:if test="${empty users}">
		<h4>Nenhum usuário encontrado!</h4>
	</c:if>

</body>
</html>