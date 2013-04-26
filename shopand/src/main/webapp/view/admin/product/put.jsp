<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produto</title>
</head>
<body>

	<c:set value="Cadastrar" var="title" />
	<c:if test="${not empty product}">
		<c:set value="Editar" var="title" />
	</c:if>

	<div class="page-header">
		<h1>${title} Produto</h1>
	</div>

	<jsp:useBean id="helper" class="org.andvicoso.shopand.web.ViewHelper" />
	<c:set var="categories" value="${helper.categories}" />

	<form method="post" action="save.do" enctype="multipart/form-data">
		<c:if test="${not empty product}">
			<input type="hidden" name="id" value="${product.id}" />
		</c:if>

		<fieldset>
			<label for="name">Nome</label>
			<input type="text" id="name" name="name" value="${product.name}" class="span3" required />
			<tags:msg inputName="name" />

			<label for="description">Descrição</label>
			<textarea rows="3" name="description" name="id" cols="10" class="span3" required>${product.description}</textarea>
			<tags:msg inputName="description" />

			<label for="price">Preço</label>
			<div class="input-prepend">
				<span class="add-on"><i>R$</i></span>
				<input type="number" name="price" id="price" value="${product.price}" min="0.1"
					step="any" class="span3" required>
			</div>

			<tags:msg inputName="price" />

			<label for="categoryId">Categoria</label>
			<select name="categoryId" class="span3" required id="categoryId">
				<option value="">Selecione a categoria</option>
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}"
						${category.id eq product.category.id ? "selected='selected'" : ""}>${category.name}</option>
				</c:forEach>
			</select>
			<tags:msg inputName="category" />

			<label for="photo">Foto</label>
			<div class="fileupload fileupload-new" data-provides="fileupload">
				<div class="fileupload-preview thumbnail" style="width: 200px; height: 150px;">
					<img alt="${product.description}" style="width: 200px; height: 150px;"
						src="${baseHref}/image.do?id=${product.photo.id}" />
				</div>
				<div>
					<span class="btn btn-file">
						<c:if test="${empty product.photo}">
							<span class="fileupload-new">Selecionar</span>
						</c:if>
						<c:if test="${not empty product.photo}">
							<span class="fileupload-exists">Alterar</span>
						</c:if>
						<input type="file" name="photo" accept="image/*" class="span3" required id="photo" />
					</span>
				</div>
			</div>
			<tags:msg inputName="photo" />

		</fieldset>
		<br />

		<div class="form-actions">
			<input type="submit" class="btn btn-primary" value="Gravar" />
			<c:if test="${not empty product}">
				<input type="button" value="Remover" class="btn btn-danger" 
				onclick="confirm('Tem certeza que deseja remover?') ? $('#removeForm').submit() : false;"/>
			</c:if>
		</div>
	</form>
	<c:if test="${not empty product}">
		<form action="remove.do" method="post" id="removeForm">
			<input type="hidden" name="id" value="${product.id}" />
		</form>
	</c:if>
</body>
</html>