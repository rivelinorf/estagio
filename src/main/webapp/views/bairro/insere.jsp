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
		<sge:header titulo="Inserir bairro"
			actionListar="/views/estado/lista.jsp"
			actionSalvar="/views/estado/insere.jsp">
		</sge:header>

		<div class="content">
			<form action="/bairro-insere" method="post">

				Nome: <input type="text" name="nome" class="form-control" width="20px">

				Cidade: <select name="cidadeID">
					<c:forEach items="${cidades.all}" var="cidade">
						<option value="${cidade.id}">${cidade.nome}</option>

					</c:forEach>
				</select>
				

				<button class="main-btn">Enviar</button>

			</form>
		</div>
	</div>
</body>
</html>
