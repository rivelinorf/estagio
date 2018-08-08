<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="titulo" required="true" type="java.lang.String" %>
<%@ attribute name="page" required="false" type="java.lang.String" %>
<%@ attribute name="actionFiltrar" required="false" type="java.lang.String" %>
<%@ attribute name="actionNovo" required="false" type="java.lang.String" %>
<%@ attribute name="actionSalvar" required="false" type="java.lang.String" %>
<%@ attribute name="actionLimpar" required="false" type="java.lang.String" %>
<%@ attribute name="actionFechar" required="false" type="java.lang.String" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<header>
    <div class="titulo">
        <h3>${titulo}</h3>
    </div>
    <div id="tollbar">
        <c:if test="${not empty actionFiltrar}">
            <button class="main-btn" id="filtrar">
                <i class="fa fa-search"></i> Filtrar
            </button>
        </c:if>
        <c:if test="${not empty actionNovo}">
            <button class="main-btn" onclick="location.href='${actionNovo}'"><i class="fas fa-plus-circle"></i> Novo</button>
        </c:if>
        <c:if test="${not empty actionSalvar}">
            <button class="main-btn" id="salvar"><i class="fas fa-save"></i> Salvar</button>
        </c:if>
        <c:if test="${not empty actionLimpar}">
            <button class="main-btn"><i class="fas fa-eraser"></i> Limpar</button>
        </c:if>
        <c:if test="${not empty actionFechar}">
            <button class="main-btn" onclick="location.href='/views/home.jsp'"><i class="fas fa-window-close"></i> Fechar</button>
        </c:if>
    </div>
</header>

<script>
    $("#filtrar").on("click", function () {
       $.ajax({
           url: "/pesquisa-${page}",
           type: "GET",
           data: {
               estado: $("#pesquisa-estado-nome").val(),
               abv: $("#pesquisa-estado-abv").val()
           },
           success: function (data) {
               location.reload();
           }
       })
    });
</script>
