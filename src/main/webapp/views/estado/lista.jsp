<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="estados" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Pesquisa de Estados"
            page="estado"
            actionFiltrar="true"
            actionNovo="/views/estado/insere.jsp"
            formId="filter-form"
            actionFechar="true"
    >
    </sge:header>
    <div class="div-form" style="width: 60%;">
        <form action="/pesquisa-estado" method="get" id="filter-form">
            <div class="form-row">
                <div>Estado:</div>
                <input type="text" name="estado" class="form-control" style="background-color: rgb(46,46,46)"
                       id="pesquisa-estado-nome"></div>
            <div class="form-row">
                <div>Abreviação:</div>
                <input type="text" name="abv" class="form-control" style="background-color: rgb(46,46,46)"
                       id="pesquisa-estado-abv"></div>
        </form>
    </div>
    <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Nome</th>
                <th>Abreviação</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${estados.estadosPesquisados}" var="estado">
                <tr>
                    <td id="botoes" width="150px" style="text-align: center">
                        <button class="main-btn btn-editar"><i class="fas fa-pen-square"></i></button>
                        <button class="main-btn btn-excluir" id="deleta-estado" value="${estado.id}"><i class="fas fa-times-circle"></i></button>
                    </td>
                    <td>${estado.nome}</td>
                    <td>${estado.abv}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>