<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@page import="br.com.sonner.estagio.controller.api.EnderecoController"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <% EnderecoController enderecoController = new EnderecoControllerImpl(); %>
        <% for (Endereco endereco: enderecoController.getAll()) { %>
            <tr>
                <td><%= endereco.getId() %></td>
                <td><%= endereco.getNumero() %></td>
                <td><%= endereco.getCep() %></td>
                <td><%= endereco.getBairro().getNome() %> </td>
                <td><%= endereco.getLogradouro().getNome() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>
