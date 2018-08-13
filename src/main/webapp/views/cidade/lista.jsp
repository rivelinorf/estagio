<%@ page import="br.com.sonner.estagio.model.Cidade" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<% List<Cidade> lista = (List) session.getAttribute("lista-cidade"); %>
<jsp:useBean id="estados" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Pesquisa de Cidades"
            page="cidade"
            actionFiltrar="true"
            actionNovo="/views/cidade/insere.jsp"
            formId="filter-form"
            actionFechar="true"
    >
    </sge:header>
    <div class="div-form" style="width: 60%;">
        <form action="/pesquisa-cidade" method="get" id="filter-form">
            <div class="form-row">
                <div>Cidade:</div>
                <input type="text" name="cidade" class="form-control" style="background-color: rgb(46,46,46)"
                       id="pesquisa-cidade-nome">
            </div>
            <div class="form-row">
                <div>Cep:</div>
                <input type="text" name="cep" class="form-control" style="background-color: rgb(46,46,46)"
                       id="pesquisa-cidade-cep">
            </div>
            <div class="form-row">
                <div>Sigla:</div>
                <input type="text" name="sigla" class="form-control" style="background-color: rgb(46,46,46)"
                       id="pesquisa-cidade-sigla">
            </div>
            <div class="form-row">
                <div>Estado:</div>
                <select name="estado" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option disabled selected>Selecione uma opção...</option>
                    <c:forEach items="${estados.all}" var="estado">
                        <option value="${estado.id}">${estado.nome}</option>

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
                <th>Cidade</th>
                <th>Sigla</th>
                <th>Cep</th>
                <th>Estado</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaCidade}" var="cidade">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar" onclick="location.href='/preenche-vo?id=${cidade.id}'"><i
                                class="fas fa-pen-square"></i></button>
                        <button class="main-btn btn-red" value="${cidade.id}" data-toggle="modal"
                                data-target="#confirm-modal" type="button" onclick="$('#deletar').val(this.value)"><i
                                class="fas fa-times-circle"></i></button>
                    </td>
                    <td>${cidade.nome}</td>
                    <td>${cidade.cod}</td>
                    <td>${cidade.cep}</td>
                    <td>${cidade.estado.nome}</td>
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
                                onclick="location.href = '/estado-deleta?id='+this.value">Sim
                        </button>
                        <button type="button" class="main-btn btn-red" data-dismiss="modal">Não</button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
