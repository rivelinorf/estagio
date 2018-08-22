<%@ page import="br.com.sonner.estagio.vos.BairroFiltroVO"%>
<%@ page import="br.com.sonner.estagio.vos.CidadeFiltroVO"%>
<%@page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="cidades"
	class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
<jsp:useBean id="estados"
	class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<%
	BairroFiltroVO bairrovo = (BairroFiltroVO) session.getAttribute("bairroParaEditar");

	if (bairrovo == null) {
		bairrovo = new BairroFiltroVO();
		bairrovo.setNome("");
		bairrovo.setCidade(null);
	}
%>

<%
	CidadeFiltroVO cidadevo = (CidadeFiltroVO) session.getAttribute("filtroCidade_atualiza");

	if (cidadevo == null) {
		cidadevo = new CidadeFiltroVO();
		cidadevo.setNome("");
		cidadevo.setSigla("");
		cidadevo.setCep("");
		cidadevo.setEstado(null);
	}
%>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Editar bairro" actionSalvar="true" actionLimpar="/atualiza-bairro"
			formId="edit-form" actionFechar="true">
		</sge:header>

		<form action="/atualiza-bairro" method="get" id="filter-form"
			style="width: 100%;">
			<div class="form-row">
				<div>Estado:</div>
				<select name="estado" class="form-control"
					style="background-color: rgb(46, 46, 46)"
					onclick="location.href = '/atualiza-bairro?estado='+this.value">
					<option value="">Selecione uma opção...</option>
					<c:forEach items="${estados.all}" var="estado">
						<c:choose>
							<c:when test="${estado.id == filtroCidade_atualiza.estado}">
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

		<div class="div-form">
			<form action="/atualiza-bairro?id=<%=bairrovo.getId()%>"
				method="post" id="edit-form" style="width: 100%">

				<div class="form-row">
					<div>Cidade:</div>
					<select name="cidade" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<option value="">Selecione uma opção...</option>
						<c:forEach items="${listaCidade_atualiza}" var="cidade">
							<c:choose>
								<c:when test="${cidade.id == filtroCidade_atualiza.id}">
									<option value="${cidade.id}" selected>${cidade.nome}</option>
								</c:when>
								<c:otherwise>
									<option value="${cidade.id}">${cidade.nome}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="form-row">
					<div>Nome:</div>
					<input type="text" name="nome" class="form-control"
						value="<%=bairrovo.getNome()%>">
				</div>

			</form>
		</div>
	</div>
</body>
</html>
