<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:choose>
	<c:when test="${static_server == null}">
		<c:set var="static_path" value="${pageContext.request.contextPath}"/>
	</c:when>
	<c:otherwise>
		<c:set var="static_path" value="${static_server}"/>
	</c:otherwise>
</c:choose>