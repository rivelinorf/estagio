<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>
<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Atualizar endereço" actionSalvar="true"
			actionLimpar="true" actionFechar="true">
		</sge:header>

		<div class="content">
			<div class="div-form">
				<form action="/endereco-atualiza" method="post">
					<input type="hidden" name="id" value="${endereco.id}" />
					<div class="form-row">
						<div>Número:</div>
						<input type="text" name="numero" placeholder="Ex.: 111"
							class="form-control" value="${endereco.numero}">
					</div>
					<div class="form-row">
						<div>Logradouro:</div>
						<select name="logradouro" class="form-control">
							<option disabled selected>Selecione uma opção...</option>
							<c:forEach items="${logradouros.all}" var="logradouro">
								<option value="${logradouro.id}">
									${logradouro.tipologradouro.nome} ${logradouro.nome}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-row">
						<div>CEP:</div>
						<input type="text" name="cep" placeholder="Ex.: 00000-000"
							class="form-control" value="${endereco.cep}">
					</div>
					<div class="form-row">
						<div>Complemento:</div>
						<input type="text" name="complemento" class="form-control"
							value="${endereco.complemento}">
					</div>
					<div class="form-row">
						<div>Bairro:</div>
						<select name="bairro" class="form-control">
							<option disabled selected>Selecione uma opção...</option>
							<c:forEach items="${bairros.all}" var="bairro">
								<option value="${bairro.id}">${bairro.nome}
									${bairro.cidade.nome}</option>
							</c:forEach>
						</select>
					</div>

					<button class="main-btn">Enviar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>