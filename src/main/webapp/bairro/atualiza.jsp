<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
	<form action="/bairro-atualiza" method="post">
		Bairro: <select name="id">
			<%
				for (Bairro bairro : new BairroControllerImpl().getAll()) {
			%>
			<option value="<%=bairro.getId()%>">
			<%=bairro.getNome()%>
			"<%=bairro.getCidade()%>"
			</option>
			<%
				}
			%>
		</select><br> 
		nome: <input type="text" name="nome"> <br>
		cidade: <input type="text" name="cidade">

		<button>Enviar</button>
	</form>
</body>
</html>
