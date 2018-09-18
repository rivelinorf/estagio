<%@page import="br.com.sonner.estagio.vos.DisciplinaFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="disciplinaController"
             class="br.com.sonner.estagio.controller.DisciplinaControllerImpl"></jsp:useBean>

<%
    DisciplinaFiltroVO vo = (DisciplinaFiltroVO) session.getAttribute("filtroDisciplina");
    if (vo == null) {
        vo = new DisciplinaFiltroVO();
        vo.setNome("");
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
    <sge:header titulo="Pesquisa de Disciplinas"
                page="disciplina"
                actionFiltrar="true"
                actionNovo="/views/disciplina/insere.jsp"
                formId="filter-form"
                actionFechar="true">
    </sge:header>
    <div class="div-form" style="width: 60%;">
        <form action="/pesquisa-disciplina" method="get" id="filter-form" style="width: 1000px;">
            <div class="form-row">
                <div>Disciplina:</div>
                <input type="text" name="disciplina" class="form-control"
                       style="background-color: rgb(46, 46, 46)"
                       id="pesquisa-disciplina-nome" value="<%=vo.getNome()%>"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))" style="width: 460px;">
            </div>
        </form>
    </div>
    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Nome</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaDisciplina}" var="disciplina">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"
                                onclick="location.href='/disciplina/preenche-vo?id=${disciplina.id}'">
                            <i class="fas fa-pen-square"></i>
                        </button>
                        <button class="main-btn btn-red" value="${disciplina.id}"
                                data-toggle="modal" data-target="#confirm-modal" type="button"
                                onclick="$('#deletar').val(this.value)">
                            <i class="fas fa-times-circle"></i>
                        </button>
                    </td>
                    <td>${disciplina.nome}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="modal fade" id="confirm-modal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Deseja realmente remover o registro do banco?</p>
                    <div style="text-align: right">
                        <button type="button" class="main-btn btn-black" id="deletar"
                                data-dismiss="modal"
                                onclick="location.href ='/deleta-disciplina?id='+this.value">Sim
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