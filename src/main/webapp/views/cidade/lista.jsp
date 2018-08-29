<%@ page import="br.com.sonner.estagio.vos.CidadeFiltroVO"%>
<%@page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>

<%
	CidadeFiltroVO vo = (CidadeFiltroVO) session.getAttribute("filtroCidade");

	if (vo == null) {
		vo = new CidadeFiltroVO();
		vo.setNome("");
		vo.setCod("");
		vo.setCep("");
		vo.setEstado(null);
	}
%>
<jsp:useBean id="estados"
	class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>
<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="/assets/js/MascaraValidacao.js"></script>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Cidades" page="cidade"
			actionFiltrar="true" actionNovo="/views/cidade/insere.jsp"
			formId="filter-form" actionFechar="true">
		</sge:header>
		<div class="div-form">
			<form name="form1" action=" /pesquisa-cidade" method="get"
				id="filter-form" style="width: 60%;">
				<div class="form-row">
					<div>Cidade:</div>
					<input type="text" name="cidade" class="form-control"
						maxlength="50" id="pesquisa-cidade-nome" value="<%=vo.getNome()%>"
						onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
				</div>
				<div class="form-row">
					<div>Código:</div>
					<input type="text" name="codigo" class="form-control"
						id="pesquisa-cidade-sigla" value="<%=vo.getCod()%>"
						style="width: 20.2%" maxlength="2" onKeyPress="mascaraInteiro()">
					<div>CEP:</div>
					<input type="text" name="cep" class="form-control"
						id="pesquisa-cidade-cep" value="<%=vo.getCep()%>"
						onKeyPress="MascaraCep(form1.cep);" maxlength="10"
						style="width: 20.2%;" placeholder="Ex.: 00.000-000">
				</div>


				<div class="form-row">
					<div>Estado:</div>
					<select name="estado" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<option value="">Selecione uma opção...</option>
						<c:forEach items="${estados.all}" var="estado">
							<c:choose>
								<c:when test="${estado.id == filtroCidade.estado}">
									<option value="${estado.id}" selected>${estado.nome}</option>
								</c:when>
								<c:otherwise>
									<option value="${estado.id}">${estado.nome}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>


					</select>
				</div>
			</form>
			<div class="msg danger"></div>
		</div>
		<div class="content">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Cidade</th>
						<th>Código</th>
						<th>Cep</th>
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listaCidade}" var="cidade">
						<tr>
							<td id="botoes" width="150px" style="text-align: center">
								<button class="main-btn btn-editar"
									onclick="location.href='/cidade/preenche-vo?id=${cidade.id}'">
									<i class="fas fa-pen-square"></i>
								</button>
								<button class="main-btn btn-red" value="${cidade.id}"
									data-toggle="modal" data-target="#confirm-modal" type="button"
									onclick="$('#deletar').val(this.value)">
									<i class="fas fa-times-circle"></i>
								</button>
							</td>
							<td>${cidade.nome}</td>
							<td>${cidade.cod}</td>
							<td>${cidade.cep}</td>
							<td>${cidade.estado.nome}</td>
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
								onclick="location.href = '/cidade-deleta?id='+this.value">Sim
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
