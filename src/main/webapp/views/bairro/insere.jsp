<%@ page import="br.com.sonner.estagio.controller.CidadeControllerImpl"%>
<%@ page import="br.com.sonner.estagio.model.Cidade"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Inserir</title>
</head>
<body>
	<jsp:useBean id="cidades"
		class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
	<form action="/bairro-insere" method="post">
		<h3>Bairro</h3>
		Nome: <input type="text" name="nome"> <br> <br>Cidade:
		<select name="cidadeID">
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
