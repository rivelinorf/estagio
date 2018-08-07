<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="estados" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>
<sge:header titulo="Pesquisa de Estados"></sge:header>
<table class="table" id="table-content">
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
                <button class="main-btn btn-excluir" id="deleta-estado" value="${estado.id}"><i class="fas fa-times-circle"></i></button>
            </td>
            <td>${estado.nome}</td>
            <td>${estado.abv}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
