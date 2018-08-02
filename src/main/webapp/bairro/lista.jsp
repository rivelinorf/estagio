<%@ page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@ page import="br.com.sonner.estagio.controller.api.BairroController"%>
<%@ page import="br.com.sonner.estagio.model.Bairro"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<style type="text/css" media="all">
@import url("./css/maven-base.css");

@import url("./css/maven-theme.css");

@import url("./css/estilo.css");
</style>
<html>
<head>
<title>Bairros</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/lib/bootstrap/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/assets/lib/jquery/jquery.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/estilo.css">
<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<table border="1">
		<thead>
			<tr>
				<th>#</th>
				<th>Nome</th>
				<th>Cidade</th>
				<th>Estado</th>
			</tr>
		</thead>
		<%
			BairroController bairroController = new BairroControllerImpl();
		%>
		<%
			for (Bairro bairro : bairroController.getAll()) {
		%>
		<tr>
			<td><%=bairro.getId()%></td>
			<td><%=bairro.getNome()%></td>
			<td><%=bairro.getCidade().getNome()%></td>
			<td><%=bairro.getCidade().getEstado().getNome()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
