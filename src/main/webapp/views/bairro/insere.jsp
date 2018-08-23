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
	CidadeFiltroVO vo = (CidadeFiltroVO) session.getAttribute("filtroCidade_insereBairro");

	if (vo == null) {
		vo = new CidadeFiltroVO();
		vo.setNome("");
		vo.setCod("");
		vo.setCep("");
		vo.setEstado(null);
	}
%>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="/assets/js/MascaraValidacao.js"></script>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>

	<div class="main">
		<sge:header titulo="Inserir novo Bairro" actionSalvar="true"
			actionLimpar="/insere-bairro" formId="insere-form"
			actionFechar="true">
		</sge:header>


		<form name="form1" action="/insere-bairro" method="get"
			id="filter-form" style="width: 100%;">
			<div class="form-row">
				<div>Estado:</div>
				<select name="estado" class="form-control"
					style="background-color: rgb(46, 46, 46)" id="estadoid"
					onclick="location.href = '/insere-bairro?estado='+this.value">
					<option value="">Selecione uma opção...</option>
					<c:forEach items="${estados.all}" var="estado">
						<c:choose>
							<c:when test="${estado.id == filtroCidade_insereBairro.estado}">
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
			<form action="/insere-bairro" method="post" id="insere-form"
				style="width: 100%;">

				<div class="form-row">
					<div>Cidade:</div>
					<select name="cidadeID" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<option value="">Selecione uma opção...</option>
						<c:forEach items="${listaCidade_insereBairro}" var="cidade">
							<option value="${cidade.id}">${cidade.nome}</option>

						</c:forEach>
					</select>
				</div>

				<div class="form-row">
					<div>Nome:</div>
					<input type="text" name="nome" class="form-control" maxlength="50"
						onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return false; else return true;">
				</div>
			</form>
		</div>

	</div>
</body>
</html>
