<%@ tag pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/imports.jsp"%>

<%@ attribute name="inputName" required="true" rtexprvalue="true"
	type="java.lang.String"%>

<c:if test="${not empty error[inputName]}">
	<span class="alert alert-error">${error[inputName]}</span>
</c:if>
<c:if test="${not empty warn[inputName]}">
	<span class="alert alert-warning">${warn[inputName]}</span>
</c:if>