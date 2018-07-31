<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@page import="br.com.sonner.estagio.model.Logradouro"%>
<%@page	import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<form action="/endereco-deleta" method="post">
		<h3>Endereco</h3>
		<select name="id">
			<%
				for (Endereco endereco : new EnderecoControllerImpl().getAll()) {
			%>
			<option value="<%=endereco.getId()%>">
				<%=endereco.getLogradouro().getTipologradouro().getNome()%>
				<%=endereco.getLogradouro().getNome()%>
				<%=endereco.getNumero()%>
				<%=endereco.getCep()%>
				<%=endereco.getBairro().getNome()%>
				<%=endereco.getBairro().getCidade().getNome()%>
				<%=endereco.getBairro().getCidade().getEstado().getNome()%>
			</option>
			<%
				}
			%>
		</select>
		<button>Enviar</button>
	</form>

</body>
</html>
