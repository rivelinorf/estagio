<%@ page import="br.com.sonner.estagio.controller.api.CidadeController" %>
<%@ page import="br.com.sonner.estagio.controller.CidadeControllerImpl" %>
<%@ page import="br.com.sonner.estagio.model.Cidade" %><%--
  Created by IntelliJ IDEA.
  User: matheus
  Date: 7/27/18
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <% CidadeController cidadeController = new CidadeControllerImpl(); %>
    <% for (Cidade cidade: cidadeController.getAll()) { %>
    <tr>
        <td><%= cidade.getId() %></td>
        <td><%= cidade.getNome() %></td>
        <td><%= cidade.getCod() %></td>
        <td><%= cidade.getCep() %></td>
        <td><%= cidade.getEstado().getNome() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
