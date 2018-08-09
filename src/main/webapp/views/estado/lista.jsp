<%@ page import="java.util.List" %>
<%@ page import="br.com.sonner.estagio.model.Estado" %>
<%@ page import="br.com.sonner.estagio.vos.EstadoFiltroVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="estadoController" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<%
    List<Estado> lista  =  (List) session.getAttribute("lista");
    EstadoFiltroVO vo = (EstadoFiltroVO) session.getAttribute("filtro");

    if (vo == null) {
        vo = new EstadoFiltroVO();
        vo.setEstado("");
        vo.setAbv("");
    }
%>

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
                       id="pesquisa-estado-nome" value="<%=vo.getEstado() %>"></div>
            <div class="form-row">
                <div>Abreviação:</div>
                <input type="text" name="abv" class="form-control" style="background-color: rgb(46,46,46)"
                       id="pesquisa-estado-abv" value="<%=vo.getAbv() %>"></div>
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
            <c:forEach items="${lista}" var="estado">
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