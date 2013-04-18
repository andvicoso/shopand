<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" view="text/html; charset=UTF-8">
<title>Usuário</title>
</head>
<body>

<c:set value="Cadastrar" var="title"/>
<c:if test="${not empty user}">
	<c:set value="Editar" var="title"/>
</c:if>

	<div class="page-header">
		<h1>Lista de Usuário</h1>
	</div>

<form method="post" action="save.do">
	<c:if test="${not empty user}">
		<input type="hidden" name="id" value="${user.id}"/>
	</c:if>
	
  <fieldset>
    <label>Nome</label>
    <input type="text"  name="name" value="${user.name}">
    <tags:msg inputName="name"/>
    
    <label>Descrição</label>
    <textarea rows="3" name="description" cols="10">${user.description}</textarea>
    <tags:msg inputName="description"/>
    
	<label>Preço</label>
	<input type="text" name="price" value="${user.price}">
	<tags:msg inputName="price"/>
    
  </fieldset>
  <br/>
  <input type="submit" class="btn" value="Gravar"/>
</form>
<c:if test="${not empty user}">
	<form action="remove.do" method="post" onsubmit="return javascript:confirm('Tem certeza que deseja remover?');">
		<input type="hidden" name="id" value="${user.id}"/>
		<input type="submit" value="Remover" class="btn" />
	</form>
</c:if>
</body>
</html>