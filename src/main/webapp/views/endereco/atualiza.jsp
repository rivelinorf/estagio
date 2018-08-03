<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@page import="br.com.sonner.estagio.model.Logradouro"%>
<%@page
	import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Atualizar</title>
</head>
<body>
	<jsp:useBean id="bairros"
		class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>

	<jsp:useBean id="logradouros"
		class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

	 Altera endereco - ${endereco.numero}
	<form action="/endereco-atualiza" method="post">
	<input type="hidden" name="id" value="${endereco.id}" />
		Endereco: <br> Número: <input type="text" name="numero"
			placeholder="Ex.: 111"> <br> CEP: <input type="text"
			name="cep" placeholder="Ex.: 00000-000"><br>
		Complemento: <input type="text" name="complemento"> Bairro: <select
			name="bairro">
			<c:forEach items="${bairros.all}" var="bairro">
				<option value="${bairro.id}">${bairro.nome}
					${bairro.cidade.nome}</option>
			</c:forEach>
		</select> Logradouro: <select name="logradouro">
			<c:forEach items="${logradouros.all}" var="logradouro">
				<option value="${logradouro.id}">
					${logradouro.tipologradouro.nome} ${logradouro.nome}</option>
			</c:forEach>
		</select>

		<button>Enviar</button>
	</form>

</body>
</html>