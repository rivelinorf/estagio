<%@page import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
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
	<jsp:useBean id="logradouro"
		class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>
	Altera logradouro - ${logradouro.nome}

	<form action="/logradouro-atualiza" method="post">

		<input type="hidden" name="id" value="${logradouro.id}" /> <br> Novo
		nome: <input type="text" name="nome" value="${logradouro.nome}"> <br>

		<br> Novo logradouro: <select name="logradouroID">
			<c:forEach items="${logradouro.all}" var="cidade">
				<option value="${logradouro.id}">
				${logradouro.nome}
				</option>

			</c:forEach>

		</select><br> <br>

		<button>Enviar</button>
	</form>
</body>
</html>
