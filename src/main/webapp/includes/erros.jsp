<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="msg">
    <c:forEach var="erro" items="${errors}">
        ${erro}<br>
    </c:forEach>
</div>

<script>
    <c:if test="${not empty errors}">
        $(".msg").css({"background-color": "rgba(255,0,0,0.3)"}).fadeIn(400);
        <% session.setAttribute("errors", "");%>
    </c:if>
</script>