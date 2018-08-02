<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@page import="br.com.sonner.estagio.controller.api.EnderecoController"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Endereços</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="resources/css/endereco.css" rel="stylesheet">
</head>
<body>
	<table border=1>
		<thead>
			<tr>
				<th>ID</th>
				<th></th>
				<th>Logradouro</th>
				<th>Número</th>
				<th>CEP</th>
				<th>Complemento</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>
			</tr>
		</thead>
		<%
			EnderecoController enderecoController = new EnderecoControllerImpl();
		%>
		<%
			for (Endereco endereco : enderecoController.getAll()) {
		%>

		<tr>
			<td><%=endereco.getId()%></td>
			<td><%=endereco.getLogradouro().getTipologradouro().getNome()%></td>
			<td><%=endereco.getLogradouro().getNome()%></td>
			<td><%=endereco.getNumero()%></td>
			<td><%=endereco.getCep()%></td>
			<td><%=endereco.getComplemento()%></td>
			<td><%=endereco.getBairro().getNome()%></td>
			<td><%=endereco.getBairro().getCidade().getNome()%></td>
			<td><%=endereco.getBairro().getCidade().getEstado().getNome()%></td>

		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
