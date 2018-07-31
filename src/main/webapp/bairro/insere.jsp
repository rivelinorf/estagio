<%@ page import="br.com.sonner.estagio.controller.CidadeControllerImpl"%>
<%@ page import="br.com.sonner.estagio.model.Cidade"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<form action="/bairro-insere" method="post">
		<h3>Bairro</h3>
		nome: <input type="text" name="nome"> <br> <br>Cidade:
		<select name="cidade">
			<%
				for (Cidade cidade : new CidadeControllerImpl().getAll()) {
			%>
			<option value="<%=cidade.getId()%>"><%=cidade.getNome()%></option>
			<%
				}
			%>
		</select>
		<br><br>
		<button>Enviar</button>
	</form>
</body>
</html>
