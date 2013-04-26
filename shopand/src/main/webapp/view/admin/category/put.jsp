<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>
</head>
<body>

	<c:set value="Cadastrar" var="title" />
	<c:if test="${not empty category}">
		<c:set value="Editar" var="title" />
	</c:if>

	<div class="page-header">
		<h1>${title} Categoria</h1>
	</div>

	<form method="post" action="save.do">
		<c:if test="${not empty category}">
			<input type="hidden" name="id" value="${category.id}" />
		</c:if>
		<fieldset>
			<label>Nome</label>
			<input type="text" name="name" value="${category.name}" class="span3" required />
			<tags:msg inputName="name" />
		</fieldset>
		<br/>
		<div class="form-actions">
			<input type="submit" class="btn btn-primary" value="Gravar" />
			<c:if test="${not empty category}">
			<input type="button" value="Remover" class="btn btn-danger" 
			onclick="confirm('Tem certeza que deseja remover?') ? $('#removeForm').submit() : false ;"/>
			</c:if>
		</div>
	</form>
	<c:if test="${not empty category}">
		<form id="removeForm" action="remove.do" method="post" >
			<input type="hidden" name="id" value="${category.id}" />
		</form>
	</c:if>
</body>
</html>