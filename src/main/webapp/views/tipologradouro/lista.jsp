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
	<div id="content">
		<sge:header titulo="Pesquisa de Tipos de Logradouros"></sge:header>
		<thead>
			<tr>
				<th></th>
				<th>Nome</th>
			</tr>
		</thead>
		</tbody>
		<c:forEach items="${tipologradouros.all}" var="tipologradouro">
			<tr>
				<td width="90px" id="botoes"><i class="fas fa-pen-square"></i>
					<i class="fas fa-times-circle"></i></td>
				<td>${tipologradouro.nome}</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
</body>
</html>

