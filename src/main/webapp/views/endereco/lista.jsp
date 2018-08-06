<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="enderecos"
	class="br.com.sonner.estagio.controller.EnderecoControllerImpl"></jsp:useBean>
<table class="table">
	<thead>
		<tr>
			<th></th>
			<th>Logradouro</th>
			<th>Número</th>
			<th>CEP</th>
			<th>Complemento</th>
			<th>Bairro</th>
			<th>Cidade</th>
			<th>Estado</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${enderecos.all}" var="endereco">
			<tr>
				<td style="text-align: center">
					<button class="main-btn btn-editar">
						<i class="fas fa-pen-square"></i>
					</button>
					<button class="main-btn btn-excluir">
						<i class="fas fa-times-circle"></i>
					</button>
				</td>
				<td>${endereco.logradouro.tipologradouro.nome}</td>
				<td>${endereco.logradouro.nome}</td>
				<td>${endereco.numero}</td>
				<td>${endereco.cep}</td>
				<td>${endereco.complemento}</td>
				<td>${endereco.bairro.nome}</td>
				<td>${endereco.bairro.cidade.nome}</td>
				<td>${endereco.bairro.cidade.estado.nome}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a href="/views/endereco/insere.jsp">Adicionar novo</a>

