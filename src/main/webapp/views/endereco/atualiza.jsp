<%@ page import="br.com.sonner.estagio.vos.EnderecoFiltroVO" %>
<%@ page import="br.com.sonner.estagio.vos.LogradouroFiltroVO" %>
<%@ page import="br.com.sonner.estagio.vos.CidadeFiltroVO" %>
<%@ page import="br.com.sonner.estagio.vos.BairroFiltroVO" %>
<%@ page import="br.com.sonner.estagio.model.Estado" %>
<%@ page import="br.com.sonner.estagio.model.Cidade" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="bairros"
             class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="logradouros"
             class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>
<jsp:useBean id="cidades"
             class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
<jsp:useBean id="estados"
             class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>
<jsp:useBean id="tipologradouros"
             class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
<%
    EnderecoFiltroVO vo = (EnderecoFiltroVO) session.getAttribute("enderecoParaEditar");
    LogradouroFiltroVO logradourovo = (LogradouroFiltroVO) session
            .getAttribute("filtroLogradouro_atualizaEndereco");
    BairroFiltroVO bairrovo = (BairroFiltroVO) session.getAttribute("filtroBairro_atualizaEndereco");
    CidadeFiltroVO cidadevo = (CidadeFiltroVO) session.getAttribute("filtroCidade_atualizaEndereco");

    if (vo == null) {
        vo = new EnderecoFiltroVO();
        vo.setNumero(null);
        vo.setCep("");
        vo.setComplemento("");
        vo.setLogradouro(null);
        vo.setBairro(null);
    }

    if (logradourovo == null) {
        logradourovo = new LogradouroFiltroVO();
        logradourovo.setNome("");
        logradourovo.setCidade(null);
        logradourovo.setTipologradouro(null);
    }

    if (cidadevo == null) {
        cidadevo = new CidadeFiltroVO();
        cidadevo.setNome("");
        cidadevo.setCod("");
        cidadevo.setCep("");
        cidadevo.setEstado(null);
    }
%>

<%
    Estado estado = (Estado) session.getAttribute("estado");
    if (estado == null) {
        estado = new Estado();
        estado.setAbv("");
        estado.setNome("");

    }
%>

<%
    Cidade cidade = (Cidade) session.getAttribute("cidade");
    if (cidade == null) {
        cidade = new Cidade();
        cidade.setCep("");
        cidade.setCod("");
        cidade.setEstado(null);
        cidade.setNome("");
    }
%>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <script type="text/javascript" src="/assets/js/MascaraValidacao.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Editar endere�o" actionSalvar="true" actionLimpar="/endereco/preenche-vo"
                formId="edit-form" actionFechar="true">
    </sge:header>

    <form action="/atualiza-endereco" method="get" id="filter-form"
          style="width: 100%;">

        <div class="form-row">
            <div>Estado:</div>
            <select name="estado" class="form-control"
                    style="width: 20.35%;"
                    onchange="location.href = '/atualiza-endereco?estado='+this.value">
                <option value="">Selecione uma op��o...</option>
                <c:forEach items="${estados.all}" var="estado">
                    <c:choose>
                        <c:when
                                test="${estado.id == filtroCidade_atualizaEndereco.estado}">
                            <option value="${estado.id}" selected>${estado.nome}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${estado.id}">${estado.nome}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>

            <div>Cidade:</div>
            <select name="cidade" class="form-control"
                    style="width: 20.35%;"
                    onchange="location.href = '/atualiza-endereco?cidade='+this.value">
                <option value="">Selecione uma op��o...</option>
                <c:forEach items="${listaCidade_atualizaEndereco}" var="cidade">
                    <c:choose>
                        <c:when
                                test="${cidade.id == filtroBairro_atualizaEndereco.cidade}">
                            <option value="${cidade.id}" selected>${cidade.nome}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${cidade.id}">${cidade.nome}</option>
                        </c:otherwise>
                    </c:choose>>

                </c:forEach>
            </select>
        </div>

    </form>

    <div class="div-form">
        <form name="form1" action="/atualiza-endereco?id=<%=vo.getId()%>"
              method="post" id="edit-form" style="width: 100%">
            <input type="hidden" value="<%=vo.getId()%>" id="id">
            <input type="hidden" value="<%=estado.getId()%>" name="estadoSession">
            <input type="hidden" value="<%=cidade.getId() == null ? ("") : (cidade.getId()) %>" name="cidadeSession">
            <div class="form-row">
                <div>Bairro:</div>
                <select name="bairro" class="form-control"
                        style="width: 20.35%;">
                    <option value="">Selecione uma op��o...</option>
                    <c:forEach items="${listaBairro_atualizaEndereco}" var="bairro">
                        <c:choose>
                            <c:when test="${bairro.id == filtroBairro_atualizaEndereco.id}">
                                <option value="${bairro.id}" selected>${bairro.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${bairro.id}">${bairro.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>

                <div>Tipo de Logradouro:</div>
                <select name="tipologradouro" class="form-control"
                        style="width: 20.35%;">
                    <option value="">Selecione uma op��o...</option>
                    <c:forEach items="${tipologradouros.all}" var="tipologradouro">
                        <c:choose>
                            <c:when
                                    test="${tipologradouro.id == filtroLogradouro_atualizaEndereco.tipologradouro}">
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
                <div>Logradouro:</div>
                <input type="text" name="logradouro" class="form-control"
                       value="<%=logradourovo.getNome()%>">
            </div>

            <div class="form-row">
                <div>N�mero:</div>
                <%
                    if (vo.getNumero() == null) {
                %>
                <input type="text" name="numero" placeholder="Ex.: 111"
                       class="form-control" onKeyPress="mascaraInteiro()" maxlength="4" style="width: 150px;">
                <%
                } else {
                %>
                <input type="text" name="numero" placeholder="Ex.: 111"
                       class="form-control" onKeyPress="mascaraInteiro()" maxlength="4" value="<%=vo.getNumero()%>"
                       style="width: 20.35%;">
                <%
                    }
                %>

                <div>CEP:</div>
                <input type="text" name="cep" placeholder="Ex.: 00.000-000"
                       class="form-control" value="<%=vo.getCep()%>"
                       onKeyPress="MascaraCep(form1.cep);" maxlength="10" style="width: 20.35%;">
            </div>

            <div class="form-row">
                <div>Complemento:</div>
                <input type="text" name="complemento" class="form-control" maxlength="50"
                       class="form-control" value="<%=vo.getComplemento()%>" placeholder="(opcional)">
            </div>


        </form>
    </div>
</div>
</body>
</html>