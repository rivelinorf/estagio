<%@page import="br.com.sonner.estagio.vos.SalaFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="salaController"
             class="br.com.sonner.estagio.controller.SalaControllerImpl"></jsp:useBean>
<jsp:useBean id="escolaController"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>

<%
    SalaFiltroVO vo = (SalaFiltroVO) session.getAttribute("filtroSala");
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
    <style type="text/css">
        .form-control {
            background-color: rgb(46, 46, 46);
        }
    </style>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Pesquisa de Sala"
                page="sala"
                actionFiltrar="true"
                actionNovo="/views/sala/insere.jsp"
                formId="filter-form"
                actionFechar="true"
    >
    </sge:header>

    <div class="div-form" style="width: 60%;">
        <form action="/pesquisa-sala" method="get" id="filter-form" style="width: 1000px;">

            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <c:choose>
                            <c:when test="${escola.id == filtroSala.escola}">
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
                <div>Sala:</div>
                <input type="text" name="sala" class="form-control" style="background-color: rgb(46,46,46)"
                       id="pesquisa-sala-nome" value="<%= vo.getNome() %>" style="width: 460px;">
            </div>

        </form>
    </div>

    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Escola</th>
                <th>Sala</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaSala}" var="sala">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"
                                onclick="location.href='/sala/preenche-vo?id=${sala.id}'"><i
                                class="fas fa-pen-square"></i></button>
                        <button class="main-btn btn-red" value="${sala.id}" data-toggle="modal"
                                data-target="#confirm-modal" type="button" onclick="$('#deletar').val(this.value)"><i
                                class="fas fa-times-circle"></i></button>
                    </td>
                    <td>${sala.escola.nome}</td>
                    <td>${sala.nome}</td>
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
                                onclick="location.href = '/deleta-sala?id='+this.value">Sim
                        </button>
                        <button type="button" class="main-btn btn-red" data-dismiss="modal">Não</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>