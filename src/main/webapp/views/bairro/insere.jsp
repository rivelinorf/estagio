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
		<sge:header titulo="Inserir novo Bairro" actionSalvar="true"
			formId="insere-form" actionFechar="true">
		</sge:header>

		<div class="content">
			<form action="/bairro-insere" method="post">
				<div class="div-form">


					<div class="form-row">
						<div>Nome:</div>
						<input type="text" name="nome" class="form-control" width="20px">
					</div>
					<div class="form-row">
						<div>Cidade:</div>
						<select name="cidadeID" class="form-control">
							<c:forEach items="${cidades.all}" var="cidade">
								<option value="${cidade.id}">${cidade.nome}</option>

							</c:forEach>
						</select>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
