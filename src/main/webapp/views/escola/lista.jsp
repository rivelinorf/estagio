<%@ page import="br.com.sonner.estagio.vos.EscolaFiltroVO" %>
<%@ page import="br.com.sonner.estagio.model.Estado" %>
<%@ page import="br.com.sonner.estagio.model.Cidade" %>
<%@ page import="br.com.sonner.estagio.model.Bairro" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<%
    EscolaFiltroVO vo = (EscolaFiltroVO) session.getAttribute("filtroEscola");

    if (vo == null) {
        vo = new EscolaFiltroVO();
        vo.setNome("");
        vo.setEndereco(null);
    }


%>
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
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <script type="text/javascript" src="/assets/js/MascaraValidacao.js"></script>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Pesquisa de Escolas" page="escola"
                actionFiltrar="true" actionNovo="/escola/preenche-vo"
                formId="filter-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form name="form1" action=" /pesquisa-escola" method="get"
              id="filter-form" style="width: 1000px;">
            <div class="form-row">
                <div>Escola:</div>
                <input type="text" name="nome" class="form-control"
                       maxlength="50" id="pesquisa-escola-nome" value="<%=vo.getNome()%>"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
            </div>


            <div class="msg danger"></div>

            <div class="form-row">
                <div>Estado:</div>
                <select name="estado" class="form-control"
                        style="background-color: rgb(46, 46, 46)" style="width: 460px;">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${estados.all}" var="estado">
                        <c:choose>
                            <c:when test="${estado.id == estadoFiltro.id}">
                                <option value="${estado.id}" selected>${estado.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${estado.id}">${estado.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row">
                <div>Cidade:</div>
                <select name="cidade" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${cidades.all}" var="cidade">
                        <c:choose>
                            <c:when test="${cidade.id == cidadeFiltro.id}">
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
                <div>Bairro:</div>
                <select name="bairro" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${bairros.all}" var="bairro">
                        <c:choose>
                            <c:when test="${bairro.id == bairroFiltro.id}">
                                <option value="${bairro.id}" selected>${bairro.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${bairro.id}">${bairro.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

        </form>
    </div>
    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Escola</th>
                <th>Logradouro</th>
                <th>Número</th>
                <th>CEP</th>
                <th>Complemento</th>
                <th>Bairro</th>
                <th>Cidade</th>
                <th>Estado</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaEscola}" var="escola">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"
                                onclick="location.href='/escola/preenche-vo?id=${escola.id}'">
                            <i class="fas fa-pen-square"></i>
                        </button>
                        <button class="main-btn btn-red" value="${escola.id}"
                                data-toggle="modal" data-target="#confirm-modal" type="button"
                                onclick="$('#deletar').val(this.value)">
                            <i class="fas fa-times-circle"></i>
                        </button>
                    </td>
                    <td>${escola.nome}</td>
                    <td>${escola.endereco.logradouro.tipologradouro.nome}
                            ${escola.endereco.logradouro.nome}</td>
                    <td>${escola.endereco.numero}</td>
                    <td>${escola.endereco.cep}</td>
                    <td>${escola.endereco.complemento}</td>
                    <td>${escola.endereco.bairro.nome}</td>
                    <td>${escola.endereco.bairro.cidade.nome}</td>
                    <td>${escola.endereco.bairro.cidade.estado.nome}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="confirm-modal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-body">
                    <p>Deseja realmente remover o registro do banco?</p>
                    <div style="text-align: right">
                        <button type="button" class="main-btn btn-black" id="deletar"
                                data-dismiss="modal"
                                onclick="location.href = '/escola-deleta?id='+this.value">Sim
                        </button>
                        <button type="button" class="main-btn btn-red"
                                data-dismiss="modal">Não
                        </button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
