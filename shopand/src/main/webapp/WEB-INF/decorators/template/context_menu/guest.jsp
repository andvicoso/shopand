<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/imports.jsp"%>

<jsp:useBean id="helper" class="org.andvicoso.shopand.web.ViewHelper" />
<c:set var="categories" value="${helper.categories}" />

<div class="dropdown clearfix">
	<!-- <ul class="nav nav-list" role="menu"> -->
	<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="display:inline;">
		<li class="nav-header">Bem Vindo!</li>
		<li><a href="${guestHref}/login.jsp">Entrar</a></li>
		<li><a href="${guestHref}/put.jsp">Cadastre-se</a></li>
		
		<c:if test="${not empty categories}">
			<li class="nav-header">Categorias</li>
			<c:forEach items="${categories}" var="category">
				<li>
					<a href="${viewHref}/product/list.do?id=${category.id}">${category.name}</a>
				</li>
			</c:forEach>
			<li><a href="${viewHref}/product/list.do"><b>Todas</b></a></li>
		</c:if>
	</ul>
</div>
