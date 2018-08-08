<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Bairros" list="true"
			actionListar="/views/bairro/lista.jsp"
			actionSalvar="/views/bairro/insere.jsp">
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
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bairros.all}" var="bairro">
						<tr>
							<td style="text-align: center" id="botoes">
								<button class="main-btn btn-editar" onclick="editarbairro()"
									value="${bairro.id}"=>
									<i class="fas fa-pen-square"></i>
								</button>
								<button class="main-btn btn-excluir" onclick="excluirbairro()"
									value="${bairro.id}">
									<i class="fas fa-times-circle"></i>
								</button>
							</td>
							<td>${bairro.nome}</td>
							<td>${bairro.cidade.nome}</td>
							<td>${bairro.cidade.estado.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

