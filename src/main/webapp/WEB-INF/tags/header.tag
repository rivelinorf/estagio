<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="titulo" required="true" type="java.lang.String" %>
<%@ attribute name="botoes" required="false" type="java.lang.String" %>
<%@ attribute name="list" required="false" type="java.lang.Boolean" %>
<%@ attribute name="actionListar" required="false" type="java.lang.String" %>
<%@ attribute name="actionSalvar" required="false" type="java.lang.String" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<header>
    <div class="titulo">
        <h3>${titulo}</h3>
    </div>
    <div id="tollbar">
        <c:if test="${list}">
            <button class="main-btn" onclick="location.href='${actionListar}'" type="button"><i class="fa fa-search"></i> Filtrar</button>
            <button class="main-btn" onclick="location.href='${actionSalvar}'" type="button"><i class="fas fa-plus-circle"></i> Novo</button>
            <button class="main-btn"><i class="fas fa-eraser"></i> Limpar</button>
            <button class="main-btn"><i class="fas fa-window-close"></i> Fechar</button>
        </c:if>
    </div>
</header>
