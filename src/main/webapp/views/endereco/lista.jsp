<%@ page import="br.com.sonner.estagio.model.Endereco"%>
<%@ page import="br.com.sonner.estagio.vos.EnderecoFiltroVO"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="enderecos"
	class="br.com.sonner.estagio.controller.EnderecoControllerImpl"></jsp:useBean>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

<%
	List<Endereco> lista = (List) session.getAttribute("lista");
	EnderecoFiltroVO vo = (EnderecoFiltroVO) session.getAttribute("filtro");

	if (vo == null) {
		vo = new EnderecoFiltroVO();
		vo.setCep("");
	}
%>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">

		<sge:header titulo="Pesquisa de Endereços" page="estado"
			actionFiltrar="true" actionNovo="/views/endereco/insere.jsp"
			formId="filter-form" actionFechar="true">
		</sge:header>

		<div class="div-form" style="width: 60%;">
			<form action="/pesquisa-endereco" method="get" id="filter-form">
				<div class="form-row">
					<div>CEP:</div>
					<input type="text" name="cep" class="form-control"
						style="background-color: rgb(46, 46, 46)">
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
					<c:forEach items="${lista}" var="endereco">
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

