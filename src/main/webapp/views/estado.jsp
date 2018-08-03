<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="estados" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>
<table class="table">

    <tr>
        <th>Nome</th>
        <th>Abreviação</th>
        <th><span style="margin-left: 8%">Editar</span></th>
        <th><span style="margin-left: 8%">Excluir</span></th>
    </tr>
    <c:forEach items="${estados.all}" var="estado">
        <tr>
            <td>${estado.nome}</td>
            <td>${estado.abv}</td>
            <td>
                <button class="main-btn btn-editar"><i class="fas fa-pen-square"></i></button>
            </td>
            <td>
                <button class="main-btn btn-excluir"><i class="fas fa-times-circle"></i></button>
            </td>
        </tr>
    </c:forEach>
</table>