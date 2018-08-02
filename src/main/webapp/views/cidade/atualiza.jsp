<%@ page import="br.com.sonner.estagio.controller.CidadeControllerImpl" %>
<%@ page import="br.com.sonner.estagio.model.Cidade" %><%--
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
    <form action="/cidade-atualiza" method="post">
        Cidade: <select name="id">
        <% for(Cidade cidade: new CidadeControllerImpl().getAll()) { %>
        <option value="<%=cidade.getId()%>"><%=cidade.getNome()%></option>
        <%}%>
    </select><br>
        nome: <input type="text" name="nome"> <br>
        codigo: <input type="text" name="codigo"> <br>
        cep: <input type="text" name="cep"> <br>
        estado: <input type="text" name="estado"> <br>
        <button>Enviar</button>
    </form>
</body>
</html>
