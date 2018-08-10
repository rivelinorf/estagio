<%@ page import="br.com.sonner.estagio.model.Bairro"%>
<%@ page import="br.com.sonner.estagio.vos.BairroFiltroVO"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="cidades"
	class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>

<%
	List<Bairro> lista = (List) session.getAttribute("lista");
	BairroFiltroVO vo = (BairroFiltroVO) session.getAttribute("filtro");

	if (vo == null) {
		vo = new BairroFiltroVO();
		vo.setNome("");
		vo.setCidade(null);
	}
%>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Bairros" page="bairro"
			actionFiltrar="true" actionNovo="/views/bairro/insere.jsp"
			formId="filter-form" actionFechar="true">
		</sge:header>

		<div class="div-form" style="width: 60%;">
			<form action="/pesquisa-bairro" method="get" id="filter-form">
				<div class="form-row">
					<div>Nome:</div>
					<input type="text" name="nome" class="form-control"
						style="background-color: rgb(46, 46, 46)">
				</div>


				<div class="form-row">
					<div>Cidade:</div>
					<select name="cidadeID" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<option disabled selected>Selecione uma opção...</option>
						<c:forEach items="${cidades.all}" var="cidade">
							<option value="${cidade.id}">${cidade.nome}</option>

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
						<th>Nome</th>
						<th>Cidade</th>
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista}" var="bairro">
						<tr>
							<td id="botoes" width="150px" style="text-align: center"><a
								href="/bairro-atualiza?id=${bairro.id}"><button
										class="main-btn btn-editar">
										<i class="fas fa-pen-square"></i>
									</button></a> <a href="/bairro-deleta?id=${bairro.id}"><button
										class="main-btn btn-red">
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
