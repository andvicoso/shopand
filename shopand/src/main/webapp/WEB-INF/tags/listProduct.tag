<%@ tag pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/imports.jsp"%>

<%@ attribute name="products" required="true" rtexprvalue="true"
	type="java.util.Collection"%>

<c:if test="${not empty products}">
	<div class="row">
		<ul class="thumbnails">
			<c:forEach var="product" items="${products}">
				<li class="span3">
					<div class="thumbnail text-center">
						<c:set value="${viewHref}/product/details.do?id=${product.id}" var="descUrl"/> 
						<h3><a href="${descUrl}">${product.name}</a></h3>
						<a href="${descUrl}">
						<img alt="${product.description}"
							style="width: 100px; height: 100px;"
							src="${baseHref}/image.do?id=${product.photo.id}" />
						</a>
						<div class="caption">
							<p>${product.description}</p>
							<h2 class="label">R$ ${product.price}</h2><br />
							<form action="${viewHref}/cart/add.do" method="post" style="margin: 0px">
								<input type="hidden" name="id" value="${product.id}" />
								<input type="submit" value="Adicionar ao carrinho"
									class="btn btn-primary" />
							</form>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<c:if test="${empty products}">
	<h4>Nenhum produto encontrado!</h4>
</c:if>