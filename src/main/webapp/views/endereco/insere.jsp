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
    CidadeFiltroVO cidadevo = (CidadeFiltroVO) session.getAttribute("filtroCidade_insereEndereco");

    if (cidadevo == null) {
        cidadevo = new CidadeFiltroVO();
        cidadevo.setNome("");
        cidadevo.setCod("");
        cidadevo.setCep("");
        cidadevo.setEstado(null);
    }
%>

<%
    BairroFiltroVO bairrovo = (BairroFiltroVO) session.getAttribute("filtroBairro_insereEndereco");

    if (bairrovo == null) {
        bairrovo = new BairroFiltroVO();
        bairrovo.setNome("");
        bairrovo.setCidade(null);
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

<%
    EnderecoFiltroVO vo = (EnderecoFiltroVO) session.getAttribute("enderecoParaInserir");
    if (vo == null) {
        vo = new EnderecoFiltroVO();
        vo.setNumero(null);
        vo.setCep("");
        vo.setComplemento("");
        vo.setLogradouro(null);
        vo.setBairro(null);
    }

    LogradouroFiltroVO logradourovo = (LogradouroFiltroVO) session
            .getAttribute("filtroLogradouro_insereEndereco");


    if (logradourovo == null) {
        logradourovo = new LogradouroFiltroVO();
        logradourovo.setNome("");
        logradourovo.setCidade(null);
        logradourovo.setTipologradouro(null);
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
    <sge:header titulo="Inserir novo Endereco" actionSalvar="true"
                actionLimpar="/insere-endereco" formId="insere-form"
                actionFechar="true">
    </sge:header>

    <form action="/insere-endereco" method="get" id="filter-form"
          style="width: 100%;">

        <div class="form-row">
            <div>Estado:</div>
            <select name="estado" class="form-control"

                    onchange="location.href = '/insere-endereco?estado='+this.value" style="width: 20.35%;">
                <option value="">Selecione uma opção...</option>
                <c:forEach items="${estados.all}" var="estado">
                    <c:choose>
                        <c:when test="${estado.id == filtroCidade_insereEndereco.estado}">
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
                    onchange="location.href = '/insere-endereco?cidade='+this.value">
                <option value="">Selecione uma opção...</option>
                <c:forEach items="${listaCidade_insereEndereco}" var="cidade">
                    <c:choose>
                        <c:when test="${cidade.id == filtroBairro_insereEndereco.cidade}">
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
        <form name="form1" action="/insere-endereco" method="post"
              id="insere-form" style="width: 100%;">

            <input type="hidden" value="<%=estado.getId()%>" name="estadoSession">
            <input type="hidden" value="<%=cidade.getId() == null ? ("") : (cidade.getId()) %>" name="cidadeSession">


            <div class="form-row">
                <div>Bairro:</div>
                <select name="bairro" class="form-control"
                        style="width: 20.35%;">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${listaBairro_insereEndereco}" var="bairro">
                        <option value="${bairro.id}">${bairro.nome}</option>
                    </c:forEach>
                </select>

                <div>Tipo de Logradouro:</div>
                <select name="tipologradouro" class="form-control" style="width: 20.35%;">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${tipologradouros.all}" var="tipologradouro">
                        <option value="${tipologradouro.id}">${tipologradouro.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row">
                <div>Logradouro:</div>
                <input type="text" name="logradouro" class="form-control"
                       value="<%=logradourovo.getNome()%>">
            </div>

            <div class="form-row">
                <div>Número:</div>
                <%
                    if (vo.getNumero() == null) {
                %>
                <input type="text" name="numero" placeholder="Ex.: 111"
                       class="form-control" maxlength="4" style="width: 20.35%;">
                <%
                } else {
                %>
                <input type="text" name="numero" placeholder="Ex.: 111"
                       class="form-control" maxlength="4" value="<%=vo.getNumero()%>" style="width: 20.35%;">
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