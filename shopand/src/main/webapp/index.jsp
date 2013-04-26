<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!doctype html>
<html>
<head>
<title>Sua loja virtual</title>
</head>
<body>

	<jsp:useBean id="helper" class="org.andvicoso.shopand.web.ViewHelper" />
	<c:set var="products" value="${helper.highlights}" />

	<div class="container">
		<c:if test="${not empty products}">
			<div class="page-header">
				<h1>Ofertas</h1>
			</div>

			<tags:listProduct products="${products}" />
		</c:if>
	</div>
</body>
</html>