<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="msg error">
    <div class="fechar"><i class="fas fa-window-close"></i></div>
    <div>
        <c:forEach var="erro" items="${errors}">
            ${erro}<br>
        </c:forEach>
    </div>
</div>
<div class="msg success">
    <div class="fechar"><i class="fas fa-window-close"></i></div>
    <div>
        ${success}
    </div>
</div>

<script>
    <c:if test="${not empty errors}">
    $(".error").css({"background-color": "rgba(255,0,0,0.3)"}).fadeIn(400);
    <% session.setAttribute("errors", "");%>
    </c:if>
    <c:if test="${not empty success}">
    $(".success").css({"background-color": "rgba(0,255,0,0.2)"}).fadeIn(400);
    <% session.setAttribute("success", "");%>
    </c:if>

    $(".fechar").on("click", function () {
        $(this).parent().fadeOut(200);
    })
</script>