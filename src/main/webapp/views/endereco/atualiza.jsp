<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@page import="br.com.sonner.estagio.controller.BairroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Bairro"%>
<%@page import="br.com.sonner.estagio.model.Logradouro"%>
<%@page
	import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Atualizar</title>
</head>
<body>
	<form action="/endereco-atualiza" method="post">
		Endereco: <select name="id">
			<%
				for (Endereco endereco : new EnderecoControllerImpl().getAll()) {
			%>
			<option value="<%=endereco.getId()%>">
				<%=endereco.getLogradouro().getTipologradouro().getNome()%>
				<%=endereco.getLogradouro().getNome()%>
				<%=endereco.getNumero()%>
				<%=endereco.getCep()%>
				<%=endereco.getComplemento()%>
				<%=endereco.getBairro().getNome()%>
				<%=endereco.getBairro().getCidade().getNome()%>
				<%=endereco.getBairro().getCidade().getEstado().getNome()%>
			</option>
			<%
				}
			%>
		</select><br> Número: <input type="text" name="numero"
			placeholder="Ex.: 111"> <br> CEP: <input type="text"
			name="cep" placeholder="Ex.: 00000-000"><br>
		Complemento: <input type="text" name="complemento"> Bairro: <select
			name="bairro">
			<%
				for (Bairro bairro : new BairroControllerImpl().getAll()) {
			%>
			<option value="<%=bairro.getId()%>">
				<%=bairro.getNome()%>
			</option>
			<%
				}
			%>
		</select> Logradouro: <select name="logradouro">
			<%
				for (Logradouro logradouro : new LogradouroControllerImpl().getAll()) {
			%>
			<option value="<%=logradouro.getId()%>">
				<%=logradouro.getTipologradouro().getNome()%>
				<%=logradouro.getNome()%>
				<%=logradouro.getCidade().getNome()%>
			</option>
			<%
				}
			%>
		</select>

		<button>Enviar</button>
	</form>

</body>
</html>
