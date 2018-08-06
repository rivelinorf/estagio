
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Atualizar</title>
</head>
<body>
	<jsp:useBean id="tipologradouro"
		class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
	Altera tipologradouro - ${tipologradouro.nome}

	<form action="/tipologradouro-atualiza" method="post">

		<input type="hidden" name="id" value="${tipologradouro.id}" /> <br> Novo
		nome: <input type="text" name="nome" value="${tipologradouro.nome}"> <br>

		<br> Novo tipologradouro: <select name="tipologradouroID">
			<c:forEach items="${tipologradouro.all}" var="tipologradouro">
				<option value="${tipologradouro.id}">
				${tipologradouro.nome}
				</option>

			</c:forEach>

		</select><br> <br>

		<button>Enviar</button>
	</form>
</body>
</html>
