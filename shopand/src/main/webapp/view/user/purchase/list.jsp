<%@ page import="org.andvicoso.shopand.model.entity.CustomerOrder"%>
<%@ page import="org.andvicoso.shopand.model.service.PaymentProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" view="text/html; charset=UTF-8">
<title>Compras Realizadas</title>
</head>
<body>

	<div class="page-header">
		<h1>Compras Realizadas</h1>
	</div>

	<c:if test="${not empty orders}">
	
		<c:set var="paymentServices" value="<%=PaymentProvider.values()%>"/>
	
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Data</th>
					<th>Valor</th>
					<th>Serviço</th>
					<th>Status</th>
					<th>Confirmação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order">
					<tr class="${order.status.image}">
						<td>${order.creationDate}</td>
						<td>${order.amount}</td>
						<td>${paymentServices[order.paymentServiceId]}</td>
						<td>${order.status.description}</td>
						<td>${order.confirmationNumber}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</c:if>
	<c:if test="${empty orders}">
		<h4>Nenhuma compra realizada.</h4>
	</c:if>

</body>
</html>