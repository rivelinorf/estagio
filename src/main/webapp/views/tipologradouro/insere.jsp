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
		<sge:header titulo="Inserir novo Tipo de Logradouro"
			actionSalvar="true" formId="insere-form" actionFechar="true">
		</sge:header>
		<div class="content">
			<form action="/insere-tipologradouro" method="post" id="insere-form">
				<div class="div-form">
					<div class="form-row">
						<div>Nome:</div>
						<input type="text" name="nome" class="form-control">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>