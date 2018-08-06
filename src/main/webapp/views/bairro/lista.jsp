<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
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
				<td style="text-align: center">
					<button class="main-btn btn-editar">
						<i class="fas fa-pen-square"></i>
					</button>
					<button class="main-btn btn-excluir">
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
<a href="/views/bairro/insere.jsp">Adicionar novo</a>

