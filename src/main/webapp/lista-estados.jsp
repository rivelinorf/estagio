<%@ page import="br.com.sonner.estagio.dao.api.EstadoDAO" %>
<%@ page import="br.com.sonner.estagio.dao.EstadoDAOImpl" %>
<%@ page import="br.com.sonner.estagio.model.Estado" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <% EstadoDAO estadoDAO = new EstadoDAOImpl(); %>
        <% for (Estado estado: estadoDAO.getAll()) { %>
            <tr>
                <td><%= estado.getId() %></td>
                <td><%= estado.getNome() %></td>
                <td><%= estado.getAbv() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>
