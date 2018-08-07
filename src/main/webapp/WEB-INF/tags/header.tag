<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="titulo" required="true" type="java.lang.String" %>
<%@ attribute name="botoes" required="false" type="java.lang.String[]" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<header>
    <div class="titulo">
        <h3>${titulo}</h3>
    </div>
    <div id="tollbar">
        <c:forEach items="${botoes}" var="classe">
            <i class="${classe}"></i>
        </c:forEach>
    </div>
</header>
