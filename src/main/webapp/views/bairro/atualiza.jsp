<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="cidades"
	class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Atualizar bairro" actionSalvar="true"
			actionLimpar="true" actionFechar="true">
		</sge:header>

		<div class="content">
			<div class="div-form">
				<form action="/bairro-atualiza" method="post">
					<input type="hidden" name="id" value="${bairro.id}" />
					<div class="form-row">
						<div>Nome:</div>
						<input type="text" name="nome" class="form-control"
							value="${bairro.nome}">
					</div>
					<div class="form-row">
						<div>Cidade:</div>
						<select name="cidadeID" class="form-control">
							<option value="${bairro.cidade.id}">${bairro.cidade.nome}</option>
							<c:forEach items="${cidades.all}" var="cidade">
								<option value="${cidade.id}">${cidade.nome}</option>
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
