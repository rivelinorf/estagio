<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="cidades"
	class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Bairros" page="bairro"
			actionFiltrar="/views/bairro/lista.jsp"
			actionNovo="/views/bairro/insere.jsp" actionLimpar="true"
			actionFechar="true">
		</sge:header>

		<div class="div-form" style="width: 60%;">
			<div class="form-row">
				<div>Nome:</div>
				<input type="text" name="nome" class="form-control"
					style="background-color: rgb(46, 46, 46)">
			</div>


			<div class="form-row">
				<div>Cidade:</div>
				<select name="cidadeID" class="form-control"
					style="background-color: rgb(46, 46, 46)">
					<option	disabled selected>Selecione	uma	opção...</option>
					<c:forEach items="${cidades.all}" var="cidade">
						<option value="${cidade.id}">${cidade.nome}</option>

					</c:forEach>
				</select>
			</div>

		</div>


		<div class="content">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Nome</th>
						<th>Cidade</th>
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bairros.all}" var="bairro">
						<tr>
							<td id="botoes" width="150px" style="text-align: center"><a
								href="/bairro-atualiza?id=${bairro.id}"><button
										class="main-btn btn-editar">
										<i class="fas fa-pen-square"></i>
									</button></a> <a href="/bairro-deleta?id=${bairro.id}"><button
										class="main-btn btn-excluir">
										<i class="fas fa-times-circle"></i>
									</button></a></td>
							<td>${bairro.nome}</td>
							<td>${bairro.cidade.nome}</td>
							<td>${bairro.cidade.estado.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
