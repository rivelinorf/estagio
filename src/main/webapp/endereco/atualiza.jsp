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
	<nav>
		<a href="<%=request.getContextPath()%>/index.jsp">
			<div class="box-img">
				<img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
				<span>Sistema de Gestão Educacional</span>
			</div>
		</a>
		<hr>
		<div id="menu-content">
			<button type="button" class="nav-btn btn-title estado">Estado</button>
			<div id="dropdownEstado" class="dropdown-content estado">
				<a href=""><button class="nav-btn drop-btn">Home</button></a> <a
					href=""><button class="nav-btn drop-btn">About</button></a> <a
					href=""><button class="nav-btn drop-btn">Contact</button></a>
			</div>
			<button type="button" class="nav-btn btn-title cidade">Cidade</button>
			<div id="dropdownCidade" class="dropdown-content cidade">
				<a href=""><button class="nav-btn drop-btn">Home</button></a> <a
					href=""><button class="nav-btn drop-btn">About</button></a> <a
					href=""><button class="nav-btn drop-btn">Contact</button></a>
			</div>
			<button type="button" class="nav-btn btn-title tipologradouro">Tipo
				de Logradouro</button>
			<div id="dropdownTipoLogradouro"
				class="dropdown-content tipologradouro">
				<a href=""><button class="nav-btn drop-btn">Home</button></a> <a
					href=""><button class="nav-btn drop-btn">About</button></a> <a
					href=""><button class="nav-btn drop-btn">Contact</button></a>
			</div>
			<button type="button" class="nav-btn btn-title logradouro">Logradouro</button>
			<div id="dropdownLogradouro" class="dropdown-content logradouro">
				<a href=""><button class="nav-btn drop-btn">Home</button></a> <a
					href=""><button class="nav-btn drop-btn">About</button></a> <a
					href=""><button class="nav-btn drop-btn">Contact</button></a>
			</div>
			<button type="button" class="nav-btn btn-title bairro">Bairro</button>
			<div id="dropdownBairro" class="dropdown-content bairro">
				<a href=""><button class="nav-btn drop-btn">Home</button></a> <a
					href=""><button class="nav-btn drop-btn">About</button></a> <a
					href=""><button class="nav-btn drop-btn">Contact</button></a>
			</div>
			<button type="button" class="nav-btn btn-title endereco">Endereço</button>
			<div id="dropdownEndereço" class="dropdown-content endereco">
				<a href=""><button class="nav-btn drop-btn">Home</button></a> <a
					href=""><button class="nav-btn drop-btn">About</button></a> <a
					href=""><button class="nav-btn drop-btn">Contact</button></a>
			</div>
		</div>
		<div id="login">
			<a href="">
				<div class="box-img">
					<img src="<%=request.getContextPath()%>/assets/imgs/usuario.png">
					<span>Bem vindo: Fulano</span>
				</div>
			</a> <a href=""><button class="btn-sair">Sair</button></a>
		</div>
	</nav>
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
