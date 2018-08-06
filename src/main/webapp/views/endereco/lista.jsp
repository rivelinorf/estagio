<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@page import="br.com.sonner.estagio.controller.api.EnderecoController"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Endereços</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<jsp:useBean id="enderecos"
		class="br.com.sonner.estagio.controller.EnderecoControllerImpl"></jsp:useBean>
	<table>
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
		<c:forEach items="${enderecos.all}" var="endereco">
			<tr>
				<td>${endereco.id}</td>
				<td>${endereco.logradouro.tipologradouro.nome}</td>
				<td>${endereco.logradouro.nome}</td>
				<td>${endereco.numero}</td>
				<td>${endereco.cep}</td>
				<td>${endereco.complemento}</td>
				<td>${endereco.bairro.nome}</td>
				<td>${endereco.bairro.cidade.nome}</td>
				<td>${endereco.bairro.cidade.estado.nome}</td>
				<td><a href="/endereco-atualiza?id=${endereco.id}">Editar</a></td>
				<td><a href="/endereco-deleta?id=${endereco.id}">Remover</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="/views/endereco/insere.jsp">Adicionar novo</a>
</body>
</html>
