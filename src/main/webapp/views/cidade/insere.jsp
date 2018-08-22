<%@page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="estados"
	class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
<script type="text/javascript" src="/assets/js/MascaraValidacao.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Inserir nova Cidade" actionSalvar="true"
			formId="insere-form" actionFechar="true">
		</sge:header>


		<div class="div-form">
			<form name="form1" action="/insere-cidade" method="post"
				id="insere-form" style="width: 100%;">
				<div class="form-row">
					<div>Nome:</div>
					<input type="text" name="nome" class="form-control">
				</div>
				<div class="form-row">
					<div>Sigla:</div>
					<input type="text" name="sigla" class="form-control"
						style="width: 20.2%;" maxlength="2">
					<div>CEP:</div>
					<input type="text" name="cep" class="form-control"
						style="width: 20.2%" onKeyPress="MascaraCep(form1.cep);"
						maxlength="10"placeholder="Ex.: 00.000-000">
				</div>
				<div class="form-row">
					<div>Estado:</div>
					<select name="estado" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<c:forEach items="${estados.all}" var="estado">
							<option value="${estado.id}">${estado.nome}</option>
						</c:forEach>
					</select>
				</div>
			</form>
		</div>

	</div>
</body>
</html>