<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Editar Informações</title>
</head>
<body>

	<div class="page-header">
		<h1>Editar Informações</h1>
	</div>

	<form action="${userHref}/save.do" method="post">

		<label for="name">Nome completo*</label>
		<input type="text" name="name" value="${user.name}" class="span3" required />
		<tags:msg inputName="name" />

		<label for="email">E-Mail*:</label>
		<div class="input-prepend">
			<span class="add-on"><i class="icon-envelope"></i></span>
			<input type="email" name="email" value="${user.email}" class="span3" required />
		</div>
		<tags:msg inputName="email" />

		<label for="password">Senha*:</label>
		<input type="password" id="password" value="${user.password}" class="span3" required />
		<tags:msg inputName="password" />

		<label for="confPassword">Confirmação da senha*:</label>
		<input type="password" name="confPassword" value="${user.confPassword}" class="span3"
			required />
		<tags:msg inputName="confPassword" />

		<input type="submit" value="Salvar" class="btn-primary btn" />

	</form>
</body>
</html>