<%@page import="br.com.sonner.estagio.vos.SalaFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="salaController"
             class="br.com.sonner.estagio.controller.SalaControllerImpl"></jsp:useBean>
<jsp:useBean id="escolaController"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>
<%
    SalaFiltroVO vo = (SalaFiltroVO) session.getAttribute("salaParaEditar");
    if (vo == null) {
        vo = new SalaFiltroVO();
        vo.setNome("");
        vo.setEscola(null);
    }
%>
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Editar Sala"
            actionSalvar="true"
            actionLimpar="/sala/preenche-vo"
            formId="edit-form"
            actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form name="form1" action="/atualiza-sala?id=<%=vo.getId()%>"
              method="post" id="edit-form" style="width: 100%">
            <input type="hidden" value="<%=vo.getId()%>" id="id">
            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="sala" class="form-control"
                       maxlength="50" value="<%=vo.getNome()%>">
            </div>

            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma op��o...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <c:choose>
                            <c:when test="${escola.id == salaParaEditar.escola}">
                                <option value="${escola.id}" selected>${escola.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${escola.id}">${escola.nome}</option>
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




