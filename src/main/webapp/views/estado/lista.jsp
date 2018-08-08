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
            actionListar="/views/estado/lista.jsp"
            actionSalvar="/views/estado/insere.jsp">
    </sge:header>
    <div class="content">
        <input type="text" placeholder="Buscar..." class="form-control" style="width: 300px; margin-bottom: 10px">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Nome</th>
                <th>Abreviação</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${estados.all}" var="estado">
                <tr>
                    <td style="text-align: center" id="botoes">
                        <button class="main-btn btn-editar"><i class="fas fa-pen-square"></i></button>
                        <button class="main-btn btn-excluir" id="deleta-estado" value="${estado.id}"><i
                                class="fas fa-times-circle"></i></button>
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