<%@ page import="br.com.sonner.estagio.vos.CidadeFiltroVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="estados" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<%
    CidadeFiltroVO vo = (CidadeFiltroVO) session.getAttribute("cidadeParaEditar");

    if (vo == null) {
        vo = new CidadeFiltroVO();
        vo.setNome("");
        vo.setSigla("");
        vo.setCep("");
        vo.setEstado(null);
    }
%>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Editar estado"
            actionSalvar="true"
            formId="edit-form"
            actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/atualiza-cidade?id=<%= vo.getId() %>" method="post" id="edit-form" style="width: 100%">
            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="cidade" class="form-control" value="<%= vo.getNome() %>">
            </div>
            <div class="form-row">
                <div>Sigla:</div>
                <input type="text" name="sigla" class="form-control" value="<%= vo.getSigla() %>" style="width: 20.2%;">
                <div>Cep:</div>
                <input type="text" name="cep" class="form-control" value="<%= vo.getCep() %>" style="width: 20.2%;">
            </div>
            <div class="form-row">
                <div>Estado:</div>
                <select name="estado" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${estados.all}" var="estado">
                        <c:choose>
                            <c:when test="${estado.id == cidadeParaEditar.estado}">
                                <option value="${estado.id}" selected>${estado.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${estado.id}">${estado.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>
</div>
</body>
</html>