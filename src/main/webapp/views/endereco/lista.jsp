<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="enderecos"
	class="br.com.sonner.estagio.controller.EnderecoControllerImpl"></jsp:useBean>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Enderecos" page="bairro"
			actionFiltrar="/views/endereco/lista.jsp"
			actionNovo="/views/endereco/insere.jsp" actionLimpar="true"
			actionFechar="true">
		</sge:header>

		<div class="div-form" style="width: 60%;">
			<div class="form-row">
				<div>Nome:</div>
				<input type="text" name="nome" class="form-control"
					style="background-color: rgb(46, 46, 46)">
			</div>

			<div class="form-row">
				<div>Bairro:</div>
				<select name="bairro" class="form-control"
					style="background-color: rgb(46, 46, 46)">
					<option	disabled selected>Selecione	uma	opção...</option>
					<c:forEach items="${bairros.all}" var="bairro">
						<option value="${bairro.id}">${bairro.nome}
							${bairro.cidade.nome}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-row">
				<div>Cidade:</div>
				<select name="logradouro" class="form-control"
					style="background-color: rgb(46, 46, 46)">
					<option	disabled selected>Selecione	uma	opção...</option>
					<c:forEach items="${logradouros.all}" var="logradouro">
						<option value="${logradouro.cidade.id}">
							${logradouro.cidade.nome} </option>
					</c:forEach>
				</select>
			</div>

		</div>

		<div class="content">
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
							<td id="botoes" width="150px" style="text-align: center"><a
								href="/endereco-atualiza?id=${endereco.id}"><button
										class="main-btn btn-editar">
										<i class="fas fa-pen-square"></i>
									</button></a> <a href="/endereco-deleta?id=${endereco.id}"><button
										class="main-btn btn-excluir">
										<i class="fas fa-times-circle"></i>
									</button></a></td>
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
		</div>
	</div>
</body>
</html>

