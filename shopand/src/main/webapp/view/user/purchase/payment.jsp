<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" view="text/html; charset=UTF-8">
<title>Finalizar Compra</title>
</head>
<body>
	<div class="page-header">
		<h1>Escolha o serviço de pagamento</h1>
	</div>

	<h2>Pagar o total de R$ ${cart.total}</h2>
	
	<c:if test="${not empty cart.products}">
		<div class="row-fluid">
            <ul class="thumbnails">
              <li class="span3">
              	<form action="pay.do" method="post">
              		<input type="hidden" name="paymentServiceId" value="0"/> 
                	<div class="thumbnail text-center">
                  		<input type="image" src="${imgsHref}/payment/pagseguro.gif" alt="Pagar com pagseguro">
                	</div>
                </form>
              </li>
              <li class="span3">
              	<form action="pay.do" method="post">
              		<input type="hidden" name="paymentServiceId" value="1"/> 
                	<div class="thumbnail text-center">
                  		<input type="image" src="${imgsHref}/payment/paypal.gif" alt="Pagar com paypal">
                	</div>
                </form>
              </li>
            </ul>
          </div>
	</c:if>

	<c:if test="${empty cart.products}">
		<h4>Carrinho vazio!</h4>
	</c:if>
</body>
</html>