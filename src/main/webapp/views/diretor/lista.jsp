<%@ page import="br.com.sonner.estagio.vos.DiretorFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="diretorController"
             class="br.com.sonner.estagio.controller.DiretorControllerImpl"></jsp:useBean>

<%
    DiretorFiltroVO vo = (DiretorFiltroVO) session.getAttribute("filtroDiretor");
    if (vo == null) {
        vo = new DiretorFiltroVO();
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
    <sge:header titulo="Pesquisa de Diretor" page="diretor"
                actionFiltrar="true" actionNovo="/views/diretor/insere.jsp"
                formId="filter-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/pesquisa-diretor" method="get" id="filter-form"
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
            <c:forEach items="${listaDiretor}" var="diretor">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"
                                onclick="location.href='/diretor/preenche-vo?id=${diretor.id}'">
                            <i class="fas fa-pen-square"></i>
                        </button>
                        <button class="main-btn btn-red" value="${diretor.id}" data-toggle="modal"
                                data-target="#confirm-modal" type="button" onclick="$('#deletar').val(this.value)">
                            <i class="fas fa-times-circle"></i>
                        </button>
                    </td>
                    <td>${diretor.pessoa.nome}</td>
                    <td>${diretor.pessoa.cpf}</td>
                    <td>${diretor.escola.nome}</td>
                    <td>${diretor.pessoa.dataNascimento}</td>
                    <td>${diretor.admissao}</td>
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
                                onclick="location.href = '/diretor-deleta?id='+this.value">Sim
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