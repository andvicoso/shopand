<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Entrar</title>
</head>
<body>
	<div class="page-header">
		<h1>Entrar</h1>
	</div>
	<form method="post" class="validate form-signin" action="login.do">
		<div class="clearfix">
			<label for="email">E-Mail</label>
			<div class="input-prepend">
				<span class="add-on"><i class="icon-envelope"></i></span>
				<input id="email" type="email" autofocus="autofocus" name="email"
					placeholder="seu@email.com" class="span3" required>
			</div>
			<tags:msg inputName="email" />
		</div>
		<div class="clearfix">
			<label for="password">Senha</label>
			<div class="input">
				<input id="password" type="password" name="password" placeholder="********"
					class="span3" required>
				<tags:msg inputName="password" />
			</div>
		</div>
		<div class="actions">
			<button id="login" type="submit" class="btn-primary btn btn-large">Entrar</button>
			<a href="${guestHref}/forgotPassword.do">Esqueceu a senha?</a>
		</div>
	</form>

</body>
</html>