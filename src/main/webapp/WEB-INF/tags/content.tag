<%@ attribute name="titulo" required="true" %>
<%@ attribute name="url" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    $("meta[name='titulo']").attr("content", "${titulo}");
    $("meta[name='url']").attr("content", "${url}");

</script>

<header>
    <jsp:include page="/includes/header.jsp">
        <jsp:param name="titulo" value="${titulo}"></jsp:param>
    </jsp:include>
</header>
<div id="table-content">
    <c:if test="${not empty url}">
        <jsp:include page="${url}"></jsp:include>
    </c:if>
</div>
