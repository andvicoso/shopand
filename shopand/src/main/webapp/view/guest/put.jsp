<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastre-se</title>
</head>
<body>
	<div class="page-header">
		<h1>Cadastre-se</h1>
	</div>

	<form action="${viewHref}/guest/addUser.do" method="post">

		<label for="name">Nome completo</label>
		<input type="text" name="name" id="name" class="span3" required />
		<tags:msg inputName="name" />

		<label for="email">E-Mail:</label>
		<div class="input-prepend">
			<span class="add-on"><i class="icon-envelope"></i></span>
			<input type="email" name="email" id="email" class="span3" required />
		</div>
		<tags:msg inputName="email" />

		<label for="password">Senha:</label>
		<input type="password" name="password" id="password" class="span3" required />
		<tags:msg inputName="password" />

		<label for="confPassword">Confirmação da senha:</label>
		<input type="password" name="confPassword" id="confPassword" class="span3" required />
		<tags:msg inputName="confPassword" />

		<label for="acceptedTerms" class="checkbox">
			Tenho pelo menos 18 anos de idade, li e aceito os <a href="../terms.html"
				target="_blank">Termos e Condições.</a>
			<input type="checkbox" name="acceptedTerms" id="acceptedTerms" />
		</label>
		<input class="btn btn-primary" type="submit" value="Cadastrar" />

	</form>
</body>
</html>