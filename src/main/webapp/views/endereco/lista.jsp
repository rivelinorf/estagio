<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="enderecos"
	class="br.com.sonner.estagio.controller.EnderecoControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Estados" list="true"
			actionListar="/views/endereco/lista.jsp"
			actionSalvar="/views/endereco/insere.jsp">
		</sge:header>
		<div class="content">
			<input type="text" placeholder="Buscar..." class="form-control"
				style="width: 300px; margin-bottom: 10px">
			<table class="table">
				<thead>
					<tr>
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
				<tbody>
					<c:forEach items="${enderecos.all}" var="endereco">
						<tr>
							<td style="text-align: center" id="botoes">
								<button type="button" class="main-btn btn-editar"
									id="editar-endereco" value="${endereco.id}">
									<i class="fas fa-pen-square"></i>
								</button>
								<button class="main-btn btn-excluir">
									<i class="fas fa-times-circle"></i>
								</button>
							</td>
							<td>${endereco.logradouro.tipologradouro.nome}
								${endereco.logradouro.nome}</td>
							<td>${endereco.numero}</td>
							<td>${endereco.cep}</td>
							<td>${endereco.complemento}</td>
							<td>${endereco.bairro.nome}</td>
							<td>${endereco.bairro.cidade.nome}</td>
							<td>${endereco.bairro.cidade.estado.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/views/endereco/insere.jsp">Adicionar novo</a>
		</div>
	</div>
</body>
</html>

