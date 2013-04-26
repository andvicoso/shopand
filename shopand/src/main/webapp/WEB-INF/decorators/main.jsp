<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="cssHref" value="${baseHref}/static/styles" />
<c:set var="jsHref" value="${baseHref}/static/scripts" />
<c:set var="bootstrapHref" value="${baseHref}/static/bootstrap" />

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="andvicoso">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="pt-BR" />

<link rel="shortcut icon" type="image/jpg" href="${imgsHref}/logo_16.jpg" />

<link href="${bootstrapHref}/css/bootstrap.css" rel="stylesheet">
<link href="${bootstrapHref}/css/bootstrap-responsive.css" rel="stylesheet">

<link href="${cssHref}/default.css" rel="stylesheet">
<link href="${cssHref}/normalize.css" rel="stylesheet">
<link href="${cssHref}/sticky_footer.css" rel="stylesheet">

<script src="${jsHref}/jquery/jquery.js" type="text/javascript"></script>
<script src="${jsHref}/jquery/jquery.tablesorter.js" type="text/javascript"></script>
<script src="${jsHref}/jquery/jquery.tablesorter.widgets.js" type="text/javascript"></script>
<script src="${jsHref}/jquery/jquery.tablesorter.bootstrap.js" type="text/javascript"></script>

<script src="${bootstrapHref}/js/bootstrap.js" type="text/javascript"></script>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

<title>Shopand - <sitemesh:write property="title" /></title>

<sitemesh:write property="head" />
</head>
<body>

	<c:set value="${empty login ? 'guest' : login}" var="menu" />

	<jsp:include page="template/navbar/${menu}.jsp" />

	<jsp:include page="template/header.jsp" />
	<div id="wrap" class="container">
		<div class="row">
			<div class="span3">
				<jsp:include page="template/context_menu/${menu}.jsp" />
			</div>
			<div class="span9">
				<c:if test="${not empty global.error[inputName]}">
					<span class="alert alert-error">${global.error[inputName]}</span>
				</c:if>
				<c:if test="${not empty global.warn[inputName]}">
					<span class="alert alert-warning">${global.warn[inputName]}</span>
				</c:if>
				<c:if test="${not empty global.info[inputName]}">
					<span class="alert alert-info">${global.info[inputName]}</span>
				</c:if>
				<sitemesh:write property="body" />
			</div>
		</div>
	</div>

	<div id="push"></div>

	<jsp:include page="template/footer.jsp" />
	<!-- javascript -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript">
		var url = window.location;
		// Will only work if string in href matches with location
		$('ul.nav a[href="' + url + '"]').parent().addClass('active');

		// Will also work for relative and absolute hrefs
		$('ul.nav a').filter(function() {
			return this.href == url;
		}).parent().addClass('active');
	</script>
</body>
</html>