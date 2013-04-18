<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" view="text/html; charset=UTF-8">
<title>Buscando por "${query}"</title>
</head>
<body>

	<div class="page-header">
		<h1>Buscando por "${query}"</h1>
	</div>
	
	<tags:listProduct items="${products}" />

</body>
</html>