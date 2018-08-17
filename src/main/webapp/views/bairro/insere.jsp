<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="cidades"
	class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
<jsp:useBean id="estados"
	class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>
<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Inserir novo Bairro" actionSalvar="true"
			formId="insere-form" actionFechar="true">
		</sge:header>


		<div class="div-form">
			<form action="/insere-bairro" method="post" id="insere-form"
				style="width: 100%;">

				<div class="form-row">
					<div>Estado:</div>
					<select name="estado" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<option value="">Selecione uma opção...</option>
						<c:forEach items="${estados.all}" var="estado">
							<option value="${estado.id}">${estado.nome}</option>

						</c:forEach>
					</select>
				</div>

				<div class="form-row">
					<div>Cidade:</div>
					<select name="cidadeID" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<option value="">Selecione uma opção...</option>
						<c:forEach items="${cidades.all}" var="cidade">
							<option value="${cidade.id}">${cidade.nome}</option>

						</c:forEach>
					</select>
				</div>

				<div class="form-row">
					<div>Nome:</div>
					<input type="text" name="nome" class="form-control">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
