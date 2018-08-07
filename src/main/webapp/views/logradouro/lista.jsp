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
	<div id="content">
		<sge:header titulo="Pesquisa de Logradouros" botoes='<%=new String[]{"fas fa-filter", "fas fa-plus-circle", "fas fa-eraser", "fas fa-times-circle"} %>'></sge:header>
		 <table class="table" id="table-content">
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
						<i class="fas fa-pen-square"></i>
						<i class="fas fa-times-circle"></i>
						</td>
					<td>${logradouro.nome}</td>
					<td>${logradouro.cidade.nome}</td>
					<td>${logradouro.cidade.estado.nome}</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
</body>
</html>
