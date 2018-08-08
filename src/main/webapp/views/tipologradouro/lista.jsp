
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="tipologradouros" class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Pesquisa de Tipo de Logradouro"
            page="tipologradouro"
            actionFiltrar="/views/tipologradouro/lista.jsp"
            actionNovo="/views/tipologradouro/insere.jsp"
            actionLimpar="true"
            actionFechar="true"
    >
    </sge:header>
    <div class="div-form" style="width: 60%;">
        <div class="form-div">
            <div>Tipo de Logradouro:</div>
            <input type="text" name="nome" class="form-control" style="background-color: rgb(46,46,46)" id="pesquisa-tipologradouro-nome"></div>
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
            <c:forEach items="${tipologradouros.all}" var="tipologradouro">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"><i class="fas fa-pen-square"></i></button>
                        <button class="main-btn btn-excluir" id="deleta-tipologradouro" value="${tipologradouro.id}"><i
                                class="fas fa-times-circle"></i></button>
                    </td>
                    <td>${tipologradouro.nome}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>












