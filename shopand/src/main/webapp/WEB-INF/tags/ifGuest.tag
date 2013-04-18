<%@ tag pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/imports.jsp"%>

<c:if test="${empty sessionScope.login}">
	<jsp:doBody />
</c:if>