<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>

</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Inserir endereço"
			actionListar="/views/endereco/lista.jsp"
			actionSalvar="/views/endereco/insere.jsp">
		</sge:header>

		<div class="content">
			<form action="/endereco-insere" method="post">

				Número: <input type="text" name="numero" placeholder="Ex.: 111"
					class="form-control">
					
				CEP: <input type="text" name="cep"
					placeholder="Ex.: 00000-000" class="form-control">


				Complemento: <input type="text" name="complemento" placeholder="(opcional)"
					class="form-control">
					
				Bairro: <select name="bairro">
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


				<button class="main-btn">Enviar</button>

			</form>
		</div>
	</div>
</body>
</html>
