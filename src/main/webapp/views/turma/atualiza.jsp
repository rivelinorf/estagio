<%@page import="br.com.sonner.estagio.vos.SalaFiltroVO" %>
<%@ page import="javax.persistence.Table" %>
<%@ page import="br.com.sonner.estagio.model.Turma" %>
<%@ page import="br.com.sonner.estagio.vos.TurmaFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="turmaController"
             class="br.com.sonner.estagio.controller.TurmaControllerImpl"></jsp:useBean>
<jsp:useBean id="escolaController"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>
<%
    TurmaFiltroVO vo = (TurmaFiltroVO) session.getAttribute("turmaParaEditar");
    if (vo == null) {
        vo = new TurmaFiltroVO();
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
            titulo="Editar Turma"
            actionSalvar="true"
            actionLimpar="/turma/preenche-vo"
            formId="edit-form"
            actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/atualiza-turma?id=<%= vo.getId() %>" method="post" id="edit-form"
              style="width: 60%; margin: auto">
            <input type="hidden" value="<%=vo.getId()%>" id="id">

            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="turma" class="form-control" value="<%= vo.getNome() %>"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
            </div>


            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control" style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma op��o...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <c:choose>
                            <c:when test="${escola.id == turmaParaEditar.escola}">
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




