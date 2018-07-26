<%@ page import="br.com.sonner.estagio.dao.api.EstadoDAO" %>
<%@ page import="br.com.sonner.estagio.dao.EstadoDAOImpl" %>
<%@ page import="br.com.sonner.estagio.model.Estado" %>
<%@ page import="br.com.sonner.estagio.controller.api.EstadoController" %>
<%@ page import="br.com.sonner.estagio.controller.EstadoControllerImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <% EstadoController estadoController = new EstadoControllerImpl(); %>
        <% for (Estado estado: estadoController.getAll()) { %>
            <tr>
                <td><%= estado.getId() %></td>
                <td><%= estado.getNome() %></td>
                <td><%= estado.getAbv() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>
