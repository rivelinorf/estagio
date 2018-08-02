<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="estados" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>
    <tr>
        <th>Nome</th>
        <th>Abreviação</th>
        <th>Editar</th>
        <th>Excluir</th>
    </tr>
    <c:forEach items="${estados.all}" var="estado">
       <tr>
           <td>${estado.nome}</td>
           <td>${estado.abv}</td>
           <td><button class="table-content-btn editar">Editar</button></td>
           <td><button class="table-content-btn excluir">Excluir</button></td>
       </tr>
    </c:forEach>