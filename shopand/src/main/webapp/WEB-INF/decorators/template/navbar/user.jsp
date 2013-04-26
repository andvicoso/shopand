<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/imports.jsp"%>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<div class="navbar-view">
				<ul class="nav menu">
					<li><a href="${baseHref}/index.jsp">Início</a></li>
					<li><a href="${viewHref}/location.jsp">Localização</a></li>
					<li><a href="${viewHref}/contact.html">Contato</a></li>
					<li><a href="${viewHref}/about.html">Sobre</a></li>
				</ul>
			</div>
			<form class="navbar-search pull-right"
				action="${userHref}/product/search.do">
				<input type="text" class="search-query" placeholder="Buscar" name="q"/>
			</form>
			<ul class="nav nav-pills pull-right">
				<li><a href="${viewHref}/cart/list.jsp">Carrinho</a></li>
         		<li ><a href="${userHref}/logout.do">Sair</a></li>
        	</ul>
		</div>
	</div>

</div>

