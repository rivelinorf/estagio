<%@page import="br.com.sonner.estagio.vos.TipologradouroFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="tipologradouroController"
             class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>


<%
    TipologradouroFiltroVO vo = (TipologradouroFiltroVO) session.getAttribute("filtroTipologradouro");
    if (vo == null) {
        vo = new TipologradouroFiltroVO();
        vo.setNome("");
    }
%>
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Pesquisa de Tipo de Logradouros"
                page="tipologradouro"
                actionFiltrar="true"
                actionNovo="/views/tipologradouro/insere.jsp"
                formId="filter-form"
                actionFechar="true">
    </sge:header>
    <div class="div-form" style="width: 60%;">
        <form action="/pesquisa-tipologradouro" method="get" id="filter-form">
            <div class="form-row">
                <div>Tipo de Logradouro:</div>
                <input type="text" name="tipologradouro" class="form-control"
                       style="background-color: rgb(46, 46, 46)"
                       id="pesquisa-tipologradouro-nome" value="<%=vo.getNome()%>"onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return false; else return true;">
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
            <c:forEach items="${listaTipologradouro}" var="tipologradouro">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar" onclick="location.href='/tipologradouro/preenche-vo?id=${tipologradouro.id}'">
                            <i class="fas fa-pen-square"></i>
                        </button>
                        <button class="main-btn btn-red" value="${tipologradouro.id}"
                                data-toggle="modal" data-target="#confirm-modal" type="button"
                                onclick="$('#deletar').val(this.value)">
                            <i class="fas fa-times-circle"></i>
                        </button>
                    </td>
                    <td>${tipologradouro.nome}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="modal fade" id="confirm-modal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Desja realmente remover o registro do banco?</p>
                    <div style="text-align: right">
                        <button type="button" class="main-btn btn-black" id="deletar"
                                data-dismiss="modal"
                                onclick="location.href ='/deleta-tipologradouro?id='+this.value">Sim
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