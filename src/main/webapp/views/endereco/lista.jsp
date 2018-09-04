<%@ page import="br.com.sonner.estagio.vos.EnderecoFiltroVO"%>
<%@page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

<%
	EnderecoFiltroVO vo = (EnderecoFiltroVO) session.getAttribute("filtroEndereco");
	if (vo == null) {
		vo = new EnderecoFiltroVO();
		vo.setNumero(null);
		vo.setCep("");
		vo.setComplemento("");
		vo.setLogradouro(null);
		vo.setBairro(null);
	}
%>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
<script type="text/javascript" src="/assets/js/MascaraValidacao.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Enderecos" page="endereco"
			actionFiltrar="true" actionNovo="/insere-endereco"
			formId="filter-form" actionFechar="true">
		</sge:header>

		<div class="div-form">
			<form name="form1" action="/pesquisa-endereco" method="get"
				id="filter-form" style="width: 1000px;">

				<div class="form-row">
					<div>Número:</div>
					<%
						if (vo.getNumero() == null) {
					%>
					<input type="text" name="numero" placeholder="Ex.: 111"
						class="form-control" maxlength="4" style="width: 150px;">
					<%
						} else {
					%>
					<input type="text" name="numero" placeholder="Ex.: 111"
						class="form-control" maxlength="4" value="<%=vo.getNumero()%>" style="width: 150px;">
					<%
						}
					%>

					<div>CEP:</div>
					<input type="text" name="cep" class="form-control"
						value="<%=vo.getCep()%>" onKeyPress="MascaraCep(form1.cep);"
						maxlength="10" onKeyPress="mascaraInteiro()" placeholder="Ex.: 00.000-000" style="width: 150px;">
				</div>
				<div class="form-row">
					<div>Complemento:</div>
					<input type="text" name="complemento" class="form-control" maxlength="50"
						value="<%=vo.getComplemento()%>" style="width: 460px;">
				</div>
				<div class="form-row">
					<div>Logradouro:</div>
					<select name="logradouro" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<option value="">Selecione uma opção...</option>
						<c:forEach items="${logradouros.all}" var="logradouro">
							<c:choose>
								<c:when test="${logradouro.id == filtroEndereco.logradouro}">
									<option value="${logradouro.id}" selected>${logradouro.tipologradouro.nome}
										${logradouro.nome}, ${logradouro.cidade.nome}</option>
								</c:when>
								<c:otherwise>
									<option value="${logradouro.id}">${logradouro.tipologradouro.nome}
										${logradouro.nome}, ${logradouro.cidade.nome}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="form-row">
					<div>Bairro:</div>
					<select name="bairro" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<option value="">Selecione uma opção...</option>
						<c:forEach items="${bairros.all}" var="bairro">
							<c:choose>
								<c:when test="${bairro.id == filtroEndereco.bairro}">
									<option value="${bairro.id}" selected>${bairro.nome}</option>
								</c:when>
								<c:otherwise>
									<option value="${bairro.id}">${bairro.nome}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</form>
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
					<c:forEach items="${listaEndereco}" var="endereco">
						<tr>
							<td id="botoes" width="150px" style="text-align: center">
								<button class="main-btn btn-editar"
									onclick="location.href='/endereco/preenche-vo?id=${endereco.id}'">
									<i class="fas fa-pen-square"></i>
								</button>
								<button class="main-btn btn-red" value="${endereco.id}"
									data-toggle="modal" data-target="#confirm-modal" type="button"
									onclick="$('#deletar').val(this.value)">
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
		</div>
		<!-- Modal -->
		<div class="modal fade" id="confirm-modal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body">
						<p>Deseja realmente remover o registro do banco?</p>
						<div style="text-align: right">
							<button type="button" class="main-btn btn-black" id="deletar"
								data-dismiss="modal"
								onclick="location.href = '/endereco-deleta?id='+this.value">Sim
							</button>
							<button type="button" class="main-btn btn-red"
								data-dismiss="modal">Não</button>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>

