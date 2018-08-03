<%@ page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@ page import="br.com.sonner.estagio.controller.api.BairroController"%>
<%@ page import="br.com.sonner.estagio.model.Bairro"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Bairros</title>
</head>
<body>
	<jsp:useBean id="bairros"
		class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>Editar</th>
				<th>Remover</th>
			</tr>
		</thead>
		<c:forEach items="${bairros.all}" var="bairro">
			<tr>
				<td>${bairro.id}</td>
				<td>${bairro.nome}</td>
				<td>${bairro.cidade.nome}</td>
				<td>${bairro.cidade.estado.nome}</td>
				<td><a href="/bairro-atualiza?id=${bairro.id}">Editar</a></td>
				<td><a href="/bairro-deleta?id=${bairro.id}">Remover</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="/views/bairro/insere.jsp">Adicionar novo</a>
</body>
</html>
