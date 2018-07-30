<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/endereco-atualiza" method="post">
		Endereco: <select name="id">
			<%
				for (Endereco endereco : new EnderecoControllerImpl().getAll()) {
			%>
			<option value="<%=endereco.getId()%>">
			<%=endereco.getNumero()%>
			<%=endereco.getCep()%>
			</option>
			<%
				}
			%>
		</select><br> 
		número: <input type="text" name="numero"> <br>
		cep: <input type="text" name="cep">
		bairro: <input type="text" name="bairro">
		logradouro: <input type="text" name="logradouro">

		<button>Enviar</button>
	</form>

</body>
</html>
