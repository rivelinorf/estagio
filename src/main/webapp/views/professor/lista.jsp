<%@ page import="br.com.sonner.estagio.vos.FuncionarioFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="funcionarioController"
             class="br.com.sonner.estagio.controller.FuncionarioControllerImpl"></jsp:useBean>

<%
    FuncionarioFiltroVO vo = (FuncionarioFiltroVO) session.getAttribute("filtroFuncionario");
    if (vo == null) {
        vo = new FuncionarioFiltroVO();
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
    <sge:header titulo="Pesquisa de Funcionários" page="funcionario"
                actionFiltrar="true" actionNovo="/views/funcionario/insere.jsp"
                formId="filter-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/pesquisa-funcionario" method="get" id="filter-form"
              style="width: 1000px;">
            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="nome" class="form-control"
                       style="background-color: rgb(46, 46, 46)" value=""
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))" style="width: 460px;">
            </div>
            <div class="form-row">
                <div>CPF:</div>
                <input type="text" name="cpf" class="form-control" placeholder="000.000.000-00"
                       style="background-color: rgb(46, 46, 46)" value="" style="width: 460px;">
            </div>
        </form>
    </div>
    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Escola</th>
                <th>Data de nascimento</th>
                <th>Data de admissão</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaFuncionario}" var="funcionario">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"
                                onclick="location.href='/funcionario/preenche-vo?id=${funcionario.id}'">
                            <i class="fas fa-pen-square"></i>
                        </button>
                        <button class="main-btn btn-red" value="${funcionario.id}" data-toggle="modal"
                                data-target="#confirm-modal" type="button" onclick="$('#deletar').val(this.value)">
                            <i class="fas fa-times-circle"></i>
                        </button>
                    </td>
                    <td>${funcionario.pessoa.nome}</td>
                    <td>${funcionario.pessoa.cpf}</td>
                    <td>${funcionario.escola.nome}</td>
                    <td>${funcionario.pessoa.dataNascimento}</td>
                    <td>${funcionario.admissao}</td>
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
                                onclick="location.href = '/funcionario-deleta?id='+this.value">Sim
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