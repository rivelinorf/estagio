<%@ page import="br.com.sonner.estagio.model.Estado" %>
<%@ page import="br.com.sonner.estagio.controller.EstadoControllerImpl" %><%--
  Created by IntelliJ IDEA.
  User: matheus
  Date: 7/27/18
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/estado-atualiza" method="post">
        Estado: <select name="id">
                    <% for(Estado estado: new EstadoControllerImpl().getAll()) { %>
                        <option value="<%=estado.getId()%>"><%=estado.getNome()%></option>
                    <%}%>
                </select><br>
        nome: <input type="text" name="nome"> <br>
        abreviacao: <input type="text" name="abv">
        <button>Enviar</button>
    </form>
</body>
</html>
