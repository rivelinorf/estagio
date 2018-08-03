<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@ page import="br.com.sonner.estagio.controller.CidadeControllerImpl"%>
<%@ page import="br.com.sonner.estagio.model.Cidade"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Atualizar</title>
</head>
<body>
	<jsp:useBean id="cidades"
		class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
	Altera bairro - ${bairro.nome}

	<form action="/bairro-atualiza" method="post">

		<input type="hidden" name="id" value="${bairro.id}" /> <br> Novo
		nome: <input type="text" name="nome" value="${bairro.nome}"> <br>

		<br> Nova cidade: <select name="cidadeID">
			<c:forEach items="${cidades.all}" var="cidade">
				<option value="${cidade.id}">
				${cidade.nome}
				</option>

			</c:forEach>

		</select><br> <br>

		<button>Enviar</button>
	</form>
</body>
</html>
