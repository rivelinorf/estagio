<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Logradouro"
			actionFiltrar="/views/logradouro/lista.jsp"
			actionNovo="/views/logradouro/insere.jsp" actionLimpar="true"
			actionFechar="true">
		</sge:header>
		<div class="div-form" style="width: 60%;">
			<form action="/pesquisa-logradouro" method="get" id="filter-form">
			
			<div class="form-row">
				<div>Nome:</div>
				<input type="text" name="nome" class="form-control"
					style="background-color: rgb(46, 46, 46)" id="pesquisa-logradouro-nome">
			</div>
			
			<div class="form-row">
				<div>Tipo de logradouro:</div>
				<select name="tipologradouroID" class="form-control"
					style="background-color: rgb(46, 46, 46)" id="pesquisa-estado-abv">
					<c:forEach items="${tipologradouro.all}" var="tipologradouro">
						<option value="${tipologradouro.id}">${tipologradouro.nome}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-row">
				<div>Cidade:</div>
				<select name="cidadeID" class="form-control"
					style="background-color: rgb(46, 46, 46)"
					id="pesquisa-logradouro-abv">
				</select>
			</div>
			<c:forEach items="${cidade.all}" var="cidade">
				<option value="${cidade.id}">${cidade.nome}</option>
			</c:forEach>
		</div>

		<div class="content">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Nome</th>
						<th>Tipo de Logradouro</th>
						<th>Cidade</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${logradouros.logradourosPesquisados}" var="logradouro">
						<tr>
							<td id="botoes" width="150px" style="text-align: center">
								<button class="main-btn btn-editar">
									<i class="fas fa-pen-square"></i>
								</button>
								<button class="main-btn btn-excluir" id="deleta-logradouro"
									value="${logradouro.id}">
									<i class="fas fa-times-circle"></i>
								</button>
							</td>
							<td>${logradouro.nome}</td>
							<td>${logradouro.cidade.nome}</td>
							<td>${logradouro.cidade.estado.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>