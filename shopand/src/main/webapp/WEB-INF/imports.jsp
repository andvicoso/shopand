<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="baseHref" value="${pageContext.request.contextPath}" />
<c:set var="imgsHref" value="${baseHref}/static/imgs" />
<c:set var="viewHref" value="${baseHref}/view" />
<c:set var="guestHref" value="${viewHref}/guest" />
<c:set var="userHref" value="${viewHref}/user" />
<c:set var="adminHref" value="${viewHref}/admin" />
