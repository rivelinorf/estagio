<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@page import="br.com.sonner.estagio.model.Logradouro"%>
<%@page
	import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserir</title>
</head>
<body>
	<form action="/endereco-insere" method="post">
		<h3>Endereço</h3>
		número: <input type="text" name="numero" placeholder="Ex.: 111"><br>
		<br> cep: <input type="text" name="cep"
			placeholder="Ex.: 00000-000"> <br> Complemento: <input
			type="text" name="complemento"> <br> Bairro: <select
			name="bairro">
			<%
				for (Bairro bairro : new BairroControllerImpl().getAll()) {
			%>
			<option value="<%=bairro.getId()%>">
				<%=bairro.getNome()%>
				<%=bairro.getCidade().getNome()%>
			</option>
			<%
				}
			%>
		</select><br> <br>Logradouro: <select name="logradouro">
			<%
				for (Logradouro logradouro : new LogradouroControllerImpl().getAll()) {
			%>
			<option value="<%=logradouro.getId()%>">
				<%=logradouro.getTipologradouro().getNome()%>
				<%=logradouro.getNome()%>

			</option>
			<%
				}
			%>
		</select>

		<button>Enviar</button>
	</form>
</body>
</html>
