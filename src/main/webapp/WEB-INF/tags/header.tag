<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="titulo" required="true" type="java.lang.String" %>
<%@ attribute name="page" required="false" type="java.lang.String" %>
<%@ attribute name="actionFiltrar" required="false"
              type="java.lang.Boolean" %>
<%@ attribute name="actionNovo" required="false" type="java.lang.String" %>
<%@ attribute name="actionLimpar" required="false"
              type="java.lang.String" %>
<%@ attribute name="actionSalvar" required="false"
              type="java.lang.Boolean" %>
<%@ attribute name="actionLancar" required="false"
              type="java.lang.Boolean" %>
<%@ attribute name="actionFechar" required="false"
              type="java.lang.Boolean" %>
<%@ attribute name="formId" required="false" type="java.lang.String" %>
<%@ attribute name="formIdlancar" required="false" type="java.lang.String" %>
<%@ attribute name="id" required="false" type="java.lang.String" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<header>
    <div class="titulo">
        <h3>${titulo}</h3>
    </div>
    <div id="tollbar">
        <c:if test="${actionFiltrar && not empty formId}">
            <button class="main-btn" id="filtrar">
                <i class="fa fa-search"></i> Filtrar
            </button>
        </c:if>
        <c:if test="${not empty actionNovo}">
            <button class="main-btn" onclick="location.href='${actionNovo}'">
                <i class="fas fa-plus-circle"></i> Novo
            </button>
        </c:if>
        <c:if test="${actionSalvar && not empty formId}">
            <button class="main-btn" id="salvar">
                <i class="fas fa-save"></i> Salvar
            </button>
        </c:if>
        <c:if test="${actionLancar && not empty formIdlancar}">
            <button class="main-btn" id="lancar">
                <i class="fas fa-save"></i> Lançar
            </button>
        </c:if>
        <c:if test="${not empty formId}">
            <c:choose>
                <c:when test="${not empty actionLimpar}">
                    <button class="main-btn" id="limpar">
                        <i class="fas fa-eraser"></i> Limpar
                    </button>
                    <script>
                        $("#limpar").on("click", function () {
                            location.href = "${actionLimpar}?id=" + $('#id').val();
                        })
                    </script>
                </c:when>
                <c:otherwise>
                    <button class="main-btn" id="limpar">
                        <i class="fas fa-eraser"></i> Limpar
                    </button>
                    <script>
                        $("#limpar").on("click", function () {
                            $("#${formId} input").val(null)
                            $("#${formId} select").val(null)
                        })
                    </script>
                </c:otherwise>
            </c:choose>
        </c:if>

        <c:if test="${actionFechar}">
            <button class="main-btn" onclick="location.href='/views/home.jsp'">
                <i class="fas fa-window-close"></i> Fechar
            </button>
        </c:if>
    </div>
</header>

<jsp:include page="/includes/msg.jsp"></jsp:include>

<script>
    $("#filtrar").on("click", function () {
        $("#${formId}").submit();
    });

    $("#salvar").on("click", function () {
        console.log("#${formId}");
        $("#${formId}").submit();

    })

    $("#lancar").on("click", function () {
        console.log("#${formIdlancar}");
        $("#${formIdlancar}").submit();

    })

    $("#olho").on("click", function () {
        $("#senha").attr("type", "text");
    });

    $("#olho").mouseup(function () {
        $("#senha").attr("type", "password");
    });
</script>


































