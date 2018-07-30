
<%@page import="br.com.sonner.estagio.model.Logradouro"%>
<%@page
	import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<form action="/logradouro-atualiza" method="post">
		Logradouro: <select name="id">
			<%
				for (Logradouro logradouro : new LogradouroControllerImpl().getAll()) {
			%>
			<option value="<%=logradouro.getId()%>"><%=logradouro.getNome()%>"<%=logradouro.getCidade()%>""<%=logradouro.getTipologradouro()%>"
			</option>
			<%
				}
			%>
		</select><br> 
		nome: <input type="text" name="nome" placeholder="nome do logradouro"> <br>
		cidade: <input type="text" name="cidade" placeholder="id da cidade a ser atualizado" >
		 tipo Logradouro: <input type="text" name="tipo logradouro" placeholder=" id do tipo do logradouro">

		<button>Enviar</button>
	</form>

</body>
</html>
