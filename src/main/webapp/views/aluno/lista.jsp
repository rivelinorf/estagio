<%@ page import="br.com.sonner.estagio.vos.EstadoFiltroVO" %>
<%@ page import="br.com.sonner.estagio.vos.AlunoFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="alunoController"
             class="br.com.sonner.estagio.controller.AlunoControllerImpl"></jsp:useBean>
<%
    AlunoFiltroVO vo = (AlunoFiltroVO) session.getAttribute("filtroAluno");
    if (vo == null) {
        vo = new AlunoFiltroVO();
        vo.setPessoa(null);

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
    <sge:header titulo="Pesquisa de Alunos" page="aluno"
                actionFiltrar="true" actionNovo="/views/aluno/insere.jsp"
                formId="filter-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/pesquisa-aluno" method="get" id="filter-form"
              style="width: 1000px;">
            <jsp:include page="/includes/pessoaComponete.jsp"></jsp:include>
        </form>
    </div>
    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Nome</th>
                <th>Matricula</th>
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
                    <td>${aluno.nome}</td>
                    <td>${aluno.matricula}</td>
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
                                onclick="location.href = '/estado-deleta?id='+this.value">Sim
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