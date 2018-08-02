<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@ page import="br.com.sonner.estagio.controller.CidadeControllerImpl"%>
<%@ page import="br.com.sonner.estagio.model.Cidade"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Atualizar</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/lib/bootstrap/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/assets/lib/jquery/jquery.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/estilo.css">
<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<form action="/bairro-atualiza" method="post">
		Bairro: <select name="id">
			<%
				for (Bairro bairro : new BairroControllerImpl().getAll()) {
			%>
			<option value="<%=bairro.getId()%>">
				<%=bairro.getNome()%>
			</option>
			<%
				}
			%>
		</select><br> <br> Novo nome: <input type="text" name="nome">
		<br> <br> Nova cidade: <select name="cidade">
			<%
				for (Cidade cidade : new CidadeControllerImpl().getAll()) {
			%>
			<option value="<%=cidade.getId()%>"><%=cidade.getNome()%></option>
			<%
				}
			%>
		</select><br> <br>

		<button>Enviar</button>
	</form>
</body>
</html>
