<%@page
	import="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.TipoLogradouro"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div id="content">
		<sge:header titulo="Deleta Tipos de Logradouros"></sge:header>
		</tbody>
			- ${tipologradouro.id}
			<form action="/tipologradouro-deleta" method="post">
				Tipo de logradouro: <select name="id">
					<%
						for (TipoLogradouro tipologradouro : new TipoLogradouroControllerImpl().getAll()) {
					%>
					<option value="<%=tipologradouro.getId()%>"><%=tipologradouro.getNome()%></option>
					<%
						}
					%>
				</select>
				<button>Enviar</button>
			</form>
		</div>
	</tbody>
</body>
</html>
