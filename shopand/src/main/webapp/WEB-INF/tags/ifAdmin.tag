<%@ tag pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/imports.jsp"%>

<c:if test="${ not empty sessionScope.login and sessionScope.login eq 'admin'}">
	<jsp:doBody />
</c:if>