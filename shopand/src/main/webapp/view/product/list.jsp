<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" view="text/html; charset=UTF-8">
<title>Lista de Produtos</title>
</head>
<body>

	<div class="page-header">
		<c:if test="${not empty category}">
			<h1>${category.name}</h1>
		</c:if>
		<c:if test="${empty category}">
			<h1>Lista de Produtos</h1>
		</c:if>
	</div>

	<tags:listProduct items="${products}" />

</body>
</html>