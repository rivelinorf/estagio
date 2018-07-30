
<%@page import="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.TipoLogradouro"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<form action="/tipologradouro-atualiza" method="post">
		TipoLogradouro: <select name="id">
			<%
				for (TipoLogradouro tipoLogradouro : new TipoLogradouroControllerImpl().getAll()) {
			%>
			<option value="<%=tipoLogradouro.getId()%>"><%=tipoLogradouro.getNome()%></option>
			<%
				}
			%>
		</select><br> Tipo De Logradouro: <input type="text" name="nome" placeholder="Ex:Rua,Avenida....">	<br>
		<button>Enviar</button>
	</form>

</body>
</html>
