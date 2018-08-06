<%@page import="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.TipoLogradouro"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Deletar</title>
</head>
<body>
	Deleta tipologradouro - ${tipologradouro.id}
	<form action="/tipologradouro-deleta" method="post">
		tipologradouro: <select name="id">
			<%
				for (TipoLogradouro tipologradouro : new TipoLogradouroControllerImpl().getAll()) {
			%>
			<option value="<%=tipologradouro.getId()%>">
				<%=tipologradouro.getNome()%>
			</option>
			<%
				}
			%>
		</select>
		<button>Enviar</button>
	</form>

</body>
</html>
