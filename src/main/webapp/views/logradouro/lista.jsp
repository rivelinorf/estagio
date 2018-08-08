<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Logradouro"
			actionListar="/views/logradouro/lista.jsp"
			actionNovo="/views/logradouro/insere.jsp" actionLimpar="ff">
		</sge:header>
		<div class="content">
			<input type="text" placeholder="Buscar..." class="form-control"
				style="width: 300px; margin-bottom: 10px">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Nome</th>
						<th>Cidade</th>
						<th>TipoLogradouro</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${logradouros.all}" var="logradouro">
						<tr>
							<td width="90px" id="botoes">
								<button class="main-btn btn-editar">
									<i class="fas fa-pen-square"></i>
								</button>
								<button class="main-btn btn-excluir" id="deleta-logradouro"value="${logradouro.id}">
									<i class="fas fa-times-circle"></i>
								</button>
							</td>
							<td>${logradouro.nome}</td>
							<td>${logradouro.cidade.nome}</td>
							<td>${logradouro.cidade.estado.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>