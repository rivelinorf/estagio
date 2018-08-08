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
		<sge:header titulo="Inserir novo Endereco" list="true"
			actionListar="/views/endereco/lista.jsp"
			actionSalvar="/views/endereco/insere.jsp">
		</sge:header>

		<div class="content">
			<form action="/endereco-insere" method="post">
				<table class="novo">
					<tr>
						<td width="200px">Número:</td>
						<td><input type="text" name="numero" placeholder="Ex.: 111" class="form-control"></td>
					</tr>
					<tr>
						<td>cep:</td>
						<td><input type="text" name="cep"
							placeholder="Ex.: 00000-000" class="form-control"></td>

					</tr>
					<tr>
						<td>Complemento:</td>
						<td><input type="text" name="complemento" class="form-control"></td>
					</tr>
					<tr>
						<td>Bairro:</td>
						<td><select name="bairro">
								<c:forEach items="${bairros.all}" var="bairro">
									<option value="${bairro.id}">${bairro.nome}
										${bairro.cidade.nome}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>Logradouro:</td>
						<td><select name="logradouro">
								<c:forEach items="${logradouros.all}" var="logradouro">
									<option value="${logradouro.id}">
										${logradouro.tipologradouro.nome} ${logradouro.nome}</option>
								</c:forEach>
						</select></td>
					</tr>

					<td><button class="main-btn">Enviar</button></td>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
