<%@page import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Inserir</title>
</head>
<body>
	<jsp:useBean id="logadouro"
		class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>
	<form action="/logadouro-insere" method="post">
		<h3>Logradouro</h3>
		Nome: <input type="text" name="nome"> <br> <br>Logradouro:
		<select name="logadouroID">
			<c:forEach items="${logadouro.all}" var="logadouro">
				<option value="${logadouro.id}">
				${logadouro.nome}
				</option>

			</c:forEach>

		</select><br> <br>
		<button>Enviar</button>
	</form>
</body>
</html>
