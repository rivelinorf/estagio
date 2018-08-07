<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="logradouro"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>
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
		<c:forEach items="${logradouro.all}" var="logradouro">
			<tr>
				<td width="90px" id="botoes">
					<i class="fas fa-pen-square"></i>
					<i class="fas fa-times-circle"></i></td>
				<td>${logradouro.nome}</td>
				<td>${logradouro.cidade.nome}</td>
				<td>${logradouro.cidade.estado.nome}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a href="/views/logradouro/insere.jsp">Adicionar novo</a>

