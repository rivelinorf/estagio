<%@page import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Logradouro"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Deletar</title>
</head>
<body>
	Deleta Logradouro - ${logradouro.id}
	<form action="/deleta-logradouro" method="post">
		Logradouro: <select name="id">
			<%
				for (Logradouro logradouro : new LogradouroControllerImpl().getAll()) {
			%>
			<option value="<%=logradouro.getId()%>">
				<%=logradouro.getNome()%>
			</option>
			<%
				}
			%>
		</select>
		<button>Enviar</button>
	</form>

</body>
</html>
