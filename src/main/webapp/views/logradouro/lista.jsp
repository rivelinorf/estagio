<%@ page import="br.com.sonner.estagio.vos.LogradouroFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="logradouroCtl" class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

<%
    LogradouroFiltroVO vo = (LogradouroFiltroVO) session.getAttribute("filtroLogradouro");

    if (vo == null) {
        vo = new LogradouroFiltroVO();
        vo.setNome("");
        vo.setCidade(null);
        vo.setTipologradouro(null);
    }
%>
<jsp:useBean id="tipologradouroCtl" class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
<jsp:useBean id="cidadesCtl" class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <style type="text/css">
        .form-control {
            background-color: rgb(46, 46, 46);
        }
    </style>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Pesquisa de Logradouro"
                page="logradouro"
                actionFiltrar="true"
                actionNovo="/views/logradouro/insere.jsp"
                formId="filter-form"
                actionFechar="true"
    >
    </sge:header>

    <div class="div-form" style="width: 60%;">
        <form action="/pesquisa-logradouro" method="get" id="filter-form">

            <div class="form-row">
                <div>Tipo de Logradouro:</div>
                <select name="tipologradouro" class="form-control">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${tipologradouroCtl.all}" var="tipologradouro">
                        <c:choose>
                            <c:when test="${tipologradouro.id == filtroLogradouro.tipologradouro}">
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
                <input type="text" name="logradouro" class="form-control" style="background-color: rgb(46,46,46)"
                       id="pesquisa-logradouro-nome" value="<%= vo.getNome() %>">
            </div>

            <div class="form-row">
                <div>Cidade:</div>
                <select name="cidade" class="form-control "
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${cidadesCtl.all}" var="cidade">
                        <c:choose>
                            <c:when test="${cidade.id == filtroLogradouro.cidade}">
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

    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Tipo de Logradouro</th>
                <th>Logradouro</th>
                <th>Cidade</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaLogradouro}" var="logradouro">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"
                                onclick="location.href='/logradouro/preenche-vo?id=${logradouro.id}'"><i
                                class="fas fa-pen-square"></i></button>
                        <button class="main-btn btn-red" value="${logradouro.id}" data-toggle="modal"
                                data-target="#confirm-modal" type="button" onclick="$('#deletar').val(this.value)"><i
                                class="fas fa-times-circle"></i></button>
                    </td>
                    <td>${logradouro.tipologradouro.nome}</td>
                    <td>${logradouro.nome}</td>
                    <td>${logradouro.cidade.nome}</td>
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
                    <p>Desja realmente remover o registro do banco?</p>
                    <div style="text-align: right">
                        <button type="button" class="main-btn btn-black" id="deletar" data-dismiss="modal"
                                onclick="location.href = '/deleta-logradouro?id='+this.value">Sim
                        </button>
                        <button type="button" class="main-btn btn-red" data-dismiss="modal">Não</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>