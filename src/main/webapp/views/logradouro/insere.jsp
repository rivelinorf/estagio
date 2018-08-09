<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="estados"
	class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Inserir novo Logradouro" actionSalvar="true"
			actionLimpar="true" actionFechar="true">
		</sge:header>

		<div class="content">
			<div class="div-form">
				<form action="/insere-logradouro" method="post">

					<div class="form-row">
						<div>Nome:</div>
						<input type="text" name="nome" class="form-control">
					</div>


					<div class="form-row">
						<div>Tipo de Logradouro:</div>
						<select name="tipologradouroID" class="form-control">
							<c:forEach items="${tipologradouro.all}" var="tipologradouro">
								<option value="${tipologradouro.id}">${tipologradouro.nome}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-row">
						<div>Cidade:</div>
						<select name="cidadeID" class="form-control">
							<c:forEach items="${cidade.all}" var="tipologradouro">
								<option value="${cidade.id}">${cidade.nome}</option>
							</c:forEach>
						</select>
					</div>
				</form>

			</div>
		</div>
</body>
</html>

