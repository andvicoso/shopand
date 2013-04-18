<%@ tag pageEncoding="UTF-8" body-content="scriptless" trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/imports.jsp"%>

<%@ attribute name="items" required="true" rtexprvalue="true" type="java.util.Collection"%>

<c:if test="${not empty items}">
	<div class="row">
		<ul class="thumbnails">
			<c:forEach var="item" items="${items}">
				<li class="span3">
					<div class="thumbnail">
						<img alt="${item.description}" style="width: 100px; height: 100px;"
							src="${baseHref}/image.do?id=${item.photo.id}" />
						<div class="caption">
							<h3>${item.name}</h3>
							<p>${item.description}</p>
							<span class="label">${item.price}</span><br />
							<form action="${baseHref}/cart/add.do" method="post">
								<input type="hidden" name="id" value="${item.id}" />
								<input type="submit" value="Adicionar ao carrinho" class="btn btn-primary" />
							</form>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<c:if test="${empty items}">
	<h4>Nenhum produto encontrado!</h4>
</c:if>