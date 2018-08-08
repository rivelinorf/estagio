<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="tipologradouro"
	class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
<style>
.form-div {
	display: flex;
	width: 75%;
	margin: auto auto 6px;
}

.form-div input {
	width: 75%;
}

.form-div div {
	margin: 5px;
	width: 20%;
	text-align: right;
}
</style>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Inserir novo Logradouro" actionSalvar="fdas"
			actionLimpar="fads" actionFechar="/views/tipologradouro/lista.jsp">
		</sge:header>

		<div class="content">
			<form action="/insere-tipologradouro" method="post">
				<div class="form-div">
					<div>Nome:</div>
					<input type="text" name="nome" class="form-control">
				</div>

			</form>
		</div>
	
</body>
</html>

