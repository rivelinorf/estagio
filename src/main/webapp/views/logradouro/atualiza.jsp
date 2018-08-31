<%@ page import="br.com.sonner.estagio.vos.LogradouroFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="tipologradouroCtl" class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
<jsp:useBean id="cidadesCtl" class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>


<%
    LogradouroFiltroVO vo = (LogradouroFiltroVO) session.getAttribute("logradouroParaEditar");

    if (vo == null) {
        vo = new LogradouroFiltroVO();
        vo.setNome("");
        vo.setCidade(null);
        vo.setTipologradouro(null);
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
            titulo="Editar logradouro"
            actionSalvar="true"
            actionLimpar="/logradouro/preenche-vo"
            formId="edit-form"
            actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/atualiza-logradouro?id=<%= vo.getId() %>" method="post" id="edit-form"
              style="width: 60%; margin: auto">
            <input type="hidden" value="<%=vo.getId()%>" id="id">

            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="logradouro" class="form-control" value="<%= vo.getNome() %>">
            </div>


            <div class="form-row">
                <div>Tipo Logradouro:</div>
                <select name="tipologradouro" class="form-control" style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${tipologradouroCtl.all}" var="tipologradouro">
                        <c:choose>
                            <c:when test="${tipologradouro.id == logradouroParaEditar.tipologradouro}">
                                <option value="${tipologradouro.id}" selected>${tipologradouro.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${tipologradouro.id}">${tipologradouro.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row">
                <div>Cidade:</div>
                <select name="cidade" class="form-control" style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${cidadesCtl.all}" var="cidade">
                        <c:choose>
                            <c:when test="${cidade.id == logradouroParaEditar.cidade}">
                                <option value="${cidade.id}" selected>${cidade.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${cidade.id}">${cidade.nome}</option>
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
