<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="tipologradouros"
	class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Pesquisa de Tipos de Logradouros" botoes='<%=new String[]{"fas fa-filter","fas fa-plus-circle","fas fa-eraser","fas fa-times-circle"}%>'></sge:header>
		<table class="table content">
		<thead>
			<tr>
				<th></th>
				<th>Nome</th>
			</tr>
		</thead>
		</tbody>
		<c:forEach items="${tipologradouros.all}" var="tipologradouro">
			<tr>
				<td width="90px" id="botoes">
					 <a href="/views/tipologradouro/atualiza.jsp"><i class="fas fa-pen-square"></i></a>
					<i class="fas fa-times-circle"></i>
					 </td>
				<td>${tipologradouro.nome}</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
</body>
</html>

