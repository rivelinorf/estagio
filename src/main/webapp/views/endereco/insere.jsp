<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@page import="br.com.sonner.estagio.model.Logradouro"%>
<%@page
	import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserir</title>
</head>
<body>
<body>
	<jsp:useBean id="bairros"
		class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
	<jsp:useBean id="logradouros"
		class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>
	<form action="/endereco-insere" method="post">
		<h3>Endereço</h3>
		Número: <input type="text" name="numero" placeholder="Ex.: 111"><br>
		<br> cep: <input type="text" name="cep"
			placeholder="Ex.: 00000-000"> <br> Complemento: <input
			type="text" name="complemento"> <br> Bairro: <select
			name="bairro">
			<c:forEach items="${bairros.all}" var="bairro">
				<option value="${bairro.id}">${bairro.nome}
					${bairro.cidade.nome}</option>
			</c:forEach>

		</select> <br>
		<br>Logradouro: <select name="logradouro">
			<c:forEach items="${logradouros.all}" var="logradouro">
				<option value="${logradouro.id}">
					${logradouro.tipologradouro.nome} ${logradouro.nome}</option>
			</c:forEach>

		</select>

		<button>Enviar</button>
	</form>
</body>
</html>
