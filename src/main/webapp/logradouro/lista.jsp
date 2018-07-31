<%@page import="br.com.sonner.estagio.model.Logradouro"%>
<%@page import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
<%@page import="br.com.sonner.estagio.controller.api.LogradouroController"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <% LogradouroController logradouroController = new LogradouroControllerImpl(); %>
        <% for (Logradouro logradouro: logradouroController.getAll()) { %>
            <tr>
                <td><%= logradouro.getId() %></td>
                <td><%= logradouro.getTipologradouro().getNome() %></td>
                <td><%= logradouro.getNome() %></td>
                <td><%= logradouro.getCidade().getNome() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>
