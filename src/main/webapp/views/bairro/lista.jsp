<%@ page import="br.com.sonner.estagio.vos.BairroFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="cidades"
             class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>

<%
    BairroFiltroVO vo = (BairroFiltroVO) session.getAttribute("filtroBairro");

    if (vo == null) {
        vo = new BairroFiltroVO();
        vo.setNome("");
        vo.setCidade(null);
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
    <sge:header titulo="Pesquisa de Bairros" page="bairro"
                actionFiltrar="true" actionNovo="/insere-bairro" formId="filter-form"
                actionFechar="true">
    </sge:header>

    <div class="div-form">
        <form action="/pesquisa-bairro" method="get" id="filter-form" style="width: 1000px;">
            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="nome" class="form-control" maxlength="50"
                       value="<%=vo.getNome()%>"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))" style="width: 460px;">
            </div>

            <div class="form-row">
                <div>Cidade:</div>
                <select name="cidadeID" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${cidades.all}" var="cidade">
                        <c:choose>
                            <c:when test="${cidade.id == filtroBairro.cidade}">
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
                <th>Nome</th>
                <th>Cidade</th>
                <th>Estado</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaBairro}" var="bairro">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">

                        <button class="main-btn btn-editar"
                                onclick="location.href='/bairro/preenche-vo?id=${bairro.id}'">
                            <i class="fas fa-pen-square"></i>
                        </button>
                        <button class="main-btn btn-red" value="${bairro.id}"
                                data-toggle="modal" data-target="#confirm-modal" type="button"
                                onclick="$('#deletar').val(this.value)">
                            <i class="fas fa-times-circle"></i>
                        </button>
                    </td>
                    <td>${bairro.nome}</td>
                    <td>${bairro.cidade.nome}</td>
                    <td>${bairro.cidade.estado.nome}</td>
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
                                onclick="location.href = '/bairro-deleta?id='+this.value">Sim
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
