<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@ page import="br.com.sonner.estagio.controller.CidadeControllerImpl"%>
<%@ page import="br.com.sonner.estagio.model.Cidade"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Atualizar</title>
</head>
<body>
	<form action="/bairro-atualiza" method="post">
		Bairro: <select name="id">
			<%
				for (Bairro bairro : new BairroControllerImpl().getAll()) {
			%>
			<option value="<%=bairro.getId()%>">
				<%=bairro.getNome()%>
			</option>
			<%
				}
			%>
		</select><br>
		<br> Novo nome: <input type="text" name="nome"> <br>
		<br> Nova cidade: <select name="cidade">
			<%
				for (Cidade cidade : new CidadeControllerImpl().getAll()) {
			%>
			<option value="<%=cidade.getId()%>"><%=cidade.getNome()%></option>
			<%
				}
			%>
		</select><br>
		<br>

		<button>Enviar</button>
	</form>
</body>
</html>
