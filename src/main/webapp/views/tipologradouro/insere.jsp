<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Inserir</title>
</head>
<body>
	<jsp:useBean id="tipologradouro"
		class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
	<form action="/tipologradouro-insere" method="post">
		<h3>Tipo logradouro</h3>
		Nome: <input type="text" name="nome"> <br> <br>tipologradouro:
		<select name="tipologradouroID">
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
