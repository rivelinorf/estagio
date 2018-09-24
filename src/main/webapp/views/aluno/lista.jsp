<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="alunoController"
             class="br.com.sonner.estagio.controller.AlunoControllerImpl"></jsp:useBean>


<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Pesquisa de Alunos" page="aluno"
                actionFiltrar="true" actionNovo="/views/aluno/insere.jsp"
                formId="filter-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/pesquisa-aluno" method="get" id="filter-form"
              style="width: 1000px;">

            <div class="form-row">
                <div>Matricula:</div>
                <input type="text" name="matricula" class="form-control"
                       style="background-color: rgb(46, 46, 46)" value=""
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))" style="width: 460px;">
            </div>


            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="aluno" class="form-control"
                       style="background-color: rgb(46, 46, 46)" value=""
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))" style="width: 460px;">
            </div>
            <div class="form-row">
                <div>Sexo:</div>
                <select name="sexo" class="form-control">
                    <option value="m">Masculino</option>
                    <option value="f">Feminino</option>
                </select>
            </div>


        </form>
    </div>
    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Matricula</th>
                <th>Nome</th>
                <th>Sexo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaMatricula}" var="aluno">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"
                                onclick="location.href='/aluno/preenche-vo?id=${aluno.id}'">
                            <i class="fas fa-pen-square"></i>
                        </button>
                        <button class="main-btn btn-red" value="${aluno.id}"
                                data-toggle="modal" data-target="#confirm-modal" type="button"
                                onclick="$('#deletar').val(this.value)">
                            <i class="fas fa-times-circle"></i>
                        </button>
                    </td>
                    <td>${aluno.matricula}</td>
                    <td>${aluno.nome}</td>
                    <td>${aluno.sexo}</td>
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
                                onclick="location.href = '/aluno-deleta?id='+this.value">Sim
                        </button>
                        <button type="button" class="main-btn btn-red"
                                data-dismiss="modal">N�o
                        </button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>