<%@ page import="br.com.sonner.estagio.vos.BairroFiltroVO" %>
<%@ page import="br.com.sonner.estagio.vos.CidadeFiltroVO" %>
<%@ page import="br.com.sonner.estagio.model.Estado" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="cidades"
             class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
<jsp:useBean id="estados"
             class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<%
    CidadeFiltroVO vo = (CidadeFiltroVO) session.getAttribute("filtroCidade_insereBairro");

    if (vo == null) {
        vo = new CidadeFiltroVO();
        vo.setNome("");
        vo.setCod("");
        vo.setCep("");
        vo.setEstado(null);
    }
%>

<%
    BairroFiltroVO bairrovo = (BairroFiltroVO) session.getAttribute("bairroParaInserir");

    if (bairrovo == null) {
        bairrovo = new BairroFiltroVO();
        bairrovo.setNome("");
        bairrovo.setCidade(null);
    }
%>

<%
    Estado estado = (Estado) session.getAttribute("estado");
    if (estado == null){
        estado = new Estado();
        estado.setAbv("");
        estado.setNome("");

    }
%>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <script type="text/javascript" src="/assets/js/MascaraValidacao.js"></script>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>

<div class="main">
    <sge:header titulo="Inserir novo Bairro" actionSalvar="true"
                actionLimpar="/insere-bairro" formId="insere-form"
                actionFechar="true">
    </sge:header>


    <form name="form1" action="/insere-bairro" method="get"
          id="filter-form" style="width: 60%; margin: auto">
        <div class="form-row">
            <div>Estado:</div>
            <select name="estado" class="form-control"
                    style="background-color: rgb(46, 46, 46)" id="estadoid"
                    onchange="location.href = '/insere-bairro?estado='+this.value">
                <option value="">Selecione uma opção...</option>
                <c:forEach items="${estados.all}" var="estado">
                    <c:choose>
                        <c:when test="${estado.id == filtroCidade_insereBairro.estado}">
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

    <div class="div-form">
        <form action="/insere-bairro" method="post" id="insere-form"
              style="width: 60%; margin: auto">
            <input type="hidden" value="<%=estado.getId()%>" name="estadoSession">

            <div class="form-row">
                <div>Cidade:</div>
                <select name="cidadeID" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${listaCidade_insereBairro}" var="cidade">
                        <c:choose>
                            <c:when test="${cidade.id == filtroCidade_insereBairro.id}">
                                <option value="${cidade.id}" selected>${cidade.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${cidade.id}">${cidade.nome}</option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </div>

            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="nome" class="form-control" maxlength="50"
                       value="<%=bairrovo.getNome()%>"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
            </div>
        </form>
    </div>

</div>
</body>
</html>
