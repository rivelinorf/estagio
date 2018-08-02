<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Deletar</title>
</head>
<body>
	<form action="/bairro-deleta" method="post">
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
		</select>
		<button>Enviar</button>
	</form>

</body>
</html>
