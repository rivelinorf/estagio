
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<jsp:useBean id="tipologradouro"
		class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
	<table class="table">
		<thead>
			<tr>
				<th></th>
				<th>Nome</th>
			</tr>
		</thead>
		<c:forEach items="${tipologradouro.all}" var="tipologradouro">
			<tr>
			   <td style="text-align: center" id="botoes">
                <button class="main-btn btn-editar"><i class="fas fa-pen-square"></i></button>
                <button class="main-btn btn-excluir"><i class="fas fa-times-circle"></i></button>
            </td>
				<td>${tipologradouro.nome}</td>
			
			</tr>
		</c:forEach>
	</table>
	<a href="/views/tipologradouro/insere.jsp">Adicionar novo</a>


