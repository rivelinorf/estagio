<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

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
			actionLimpar="fads" actionFechar="/views/logradouro/lista.jsp">
		</sge:header>

		<div class="content">
			<form action="/insere-logradouro" method="post">
				<div class="form-div">
					<div>Nome:</div>
					<input type="text" name="nome" class="form-control">
				</div>

				<div class="form-div">
					<div>Tipo de Logradouro:</div>
					<select name="tipologradouroID" class="form-control">
				<c:forEach items="${tipologradouro.all}" var="tipologradouro">
					<option value="${tipologradouro.id}">${tipologradouro.nome}</option>
				</c:forEach>
				</select>		
				</div>


				<div class="form-div">
					<div>Cidade:</div>
					<select name="cidadeID" class="form-control">
				</div>
				<c:forEach items="${cidade.all}" var="tipologradouro">
					<option value="${cidade.id}">${cidade.nome}</option>
				</c:forEach>
				</select>
				</div>


			</form>
		</div>
	
</body>
</html>

