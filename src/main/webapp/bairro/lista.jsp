<%@ page import="br.com.sonner.estagio.controller.BairroControllerImpl" %>
<%@ page import="br.com.sonner.estagio.controller.api.BairroController" %>
<%@ page import="br.com.sonner.estagio.model.Bairro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <% BairroController bairroController = new BairroControllerImpl(); %>
        <% for (Bairro bairro: bairroController.getAll()) { %>
            <tr>
                <td><%= bairro.getId() %></td>
                <td><%= bairro.getNome() %></td>
                <td><%= bairro.getCidade().getNome() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>
