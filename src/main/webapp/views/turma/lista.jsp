<%@page import="br.com.sonner.estagio.vos.TurmaFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="salaController"
             class="br.com.sonner.estagio.controller.SalaControllerImpl"></jsp:useBean>
<jsp:useBean id="escolaController"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>

<%
    TurmaFiltroVO vo = (TurmaFiltroVO) session.getAttribute("filtroTurma");
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
    <style type="text/css">
        .form-control {
            background-color: rgb(46, 46, 46);
        }
    </style>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Pesquisa de Turma"
                page="sala"
                actionFiltrar="true"
                actionNovo="/views/turma/insere.jsp"
                formId="filter-form"
                actionFechar="true"
    >
    </sge:header>

    <div class="div-form" style="width: 60%;">
        <form action="/pesquisa-turma" method="get" id="filter-form" style="width: 1000px;">

            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <c:choose>
                            <c:when test="${escola.id == filtroTurma.escola}">
                                <option value="${escola.id}" selected>${escola.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${escola.id}">${escola.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row">
                <div>Turma:</div>
                <input type="text" name="turma" class="form-control" style="background-color: rgb(46,46,46)"
                       id="pesquisa-turma-nome" value="<%= vo.getNome() %>" style="width: 460px;">
            </div>

        </form>
    </div>

    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Escola</th>
                <th>Turma</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaTurma}" var="turma">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"
                                onclick="location.href='/turma/preenche-vo?id=${turma.id}'"><i
                                class="fas fa-pen-square"></i></button>
                        <button class="main-btn btn-red" value="${turma.id}" data-toggle="modal"
                                data-target="#confirm-modal" type="button" onclick="$('#deletar').val(this.value)"><i
                                class="fas fa-times-circle"></i></button>
                    </td>
                    <td>${turma.escola.nome}</td>
                    <td>${turma.nome}</td>
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
                        <button type="button" class="main-btn btn-black" id="deletar" data-dismiss="modal"
                                onclick="location.href = '/deleta-turma?id='+this.value">Sim
                        </button>
                        <button type="button" class="main-btn btn-red" data-dismiss="modal">Não</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>