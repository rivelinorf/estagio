<%@page import="br.com.sonner.estagio.model.TipoLogradouro"%>
<%@page
	import="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"%>
<%@page
	import="br.com.sonner.estagio.controller.api.TipoLogradouroController"%>
<%@ page import="br.com.sonner.estagio.controller.EstadoControllerImpl"%>
<%@ page import="br.com.sonner.estagio.controller.api.EstadoController"%>
<%@ page import="br.com.sonner.estagio.model.Estado"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<table border="1">
		<%
			TipoLogradouroController tipoLougradorController = new TipoLogradouroControllerImpl();
		%>
		<%
			for (TipoLogradouro tipoLogradouro : tipoLougradorController.getAll()) {
		%>
		<tr>
			<td><%=tipoLogradouro.getId()%></td>
			<td><%=tipoLogradouro.getNome()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
