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
		<sge:header titulo="Atualizar bairro"
			actionListar="/views/estado/lista.jsp"
			actionSalvar="/views/estado/insere.jsp">
		</sge:header>

		<form action="/bairro-atualiza" method="post">

			<input type="hidden" name="id" value="${bairro.id}" /> <br>
			Novo nome: <input type="text" name="nome" value="${bairro.nome}">
			<br> <br> Nova cidade: <select name="cidade">
				<c:forEach items="${cidades.all}" var="cidade">
					<option value="${cidade.id}">${cidade.nome}</option>

				</c:forEach>

			</select><br> <br>

			<button>Enviar</button>
		</form>
	</div>
</body>
</html>
