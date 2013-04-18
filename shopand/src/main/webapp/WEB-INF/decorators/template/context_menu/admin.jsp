<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/imports.jsp"%>

<div class="dropdown clearfix">
	<!-- <ul class="nav nav-list"> -->
	<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="display:inline;margin-top: 20px;">
		<li class="nav-header">Produtos</li>
		<li><a href="${adminHref}/product/put.jsp">Cadastrar</a></li>
		<li><a href="${adminHref}/product/list.do">Listar</a></li>
		<li class="nav-header">Categorias</li>
		<li><a href="${adminHref}/category/put.jsp">Cadastrar</a></li>
		<li><a href="${adminHref}/category/list.do">Listar</a></li>
	</ul>
</div>
