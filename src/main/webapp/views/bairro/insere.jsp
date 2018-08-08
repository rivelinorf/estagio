<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="cidades"
	class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Inserir novo Bairro" list="true"
			actionListar="/views/estado/lista.jsp"
			actionSalvar="/views/estado/insere.jsp">
		</sge:header>

		<div class="content">
			<form action="/bairro-insere" method="post">
				<table class="novo">
					<tr>
						<td  align="right" width="200px">Nome:</td>
						<td><input type="text" name="nome" class="form-control"></td>
					</tr>
					<tr>
						<td>Cidade:</td>
						<td><select name="cidadeID" align="right">
								<c:forEach items="${cidades.all}" var="cidade">
									<option value="${cidade.id}">${cidade.nome}</option>

								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><button class="main-btn">Enviar</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
