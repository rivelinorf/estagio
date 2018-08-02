


<%@page import="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.TipoLogradouro"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
<title>Title</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/lib/bootstrap/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/assets/lib/jquery/jquery.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/main.css">
<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
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
				<a href=""><button class="nav-btn drop-btn">Inserir</button></a> <a
					href=""><button class="nav-btn drop-btn">Atualizar</button></a> <a
					href=""><button class="nav-btn drop-btn">Deletar</button></a>
			</div>
			<button type="button" class="nav-btn btn-title cidade">Cidade</button>
			<div id="dropdownCidade" class="dropdown-content cidade">
				<a href=""><button class="nav-btn drop-btn">Inserir</button></a> <a
					href=""><button class="nav-btn drop-btn">Atualizar</button></a> <a
					href=""><button class="nav-btn drop-btn">Deletar</button></a>
			</div>
			<button type="button" class="nav-btn btn-title tipologradouro">Tipo
				de Logradouro</button>
			<div id="dropdownTipoLogradouro"
				class="dropdown-content tipologradouro">
				<a href=""><button class="nav-btn drop-btn">Inserir</button></a> <a
					href=""><button class="nav-btn drop-btn">Atualizar</button></a> <a
					href=""><button class="nav-btn drop-btn">Deletar</button></a>
			</div>
			<button type="button" class="nav-btn btn-title logradouro">Logradouro</button>
			<div id="dropdownLogradouro" class="dropdown-content logradouro">
				<a href=""><button class="nav-btn drop-btn">Inserir</button></a> <a
					href=""><button class="nav-btn drop-btn">Atualizar</button></a> <a
					href=""><button class="nav-btn drop-btn">Deletar</button></a>
			</div>
			<button type="button" class="nav-btn btn-title bairro">Bairro</button>
			<div id="dropdownBairro" class="dropdown-content bairro">
				<a href=""><button class="nav-btn drop-btn">Inserir</button></a> <a
					href=""><button class="nav-btn drop-btn">Atualizar</button></a> <a
					href=""><button class="nav-btn drop-btn">Deletar</button></a>
			</div>
			<button type="button" class="nav-btn btn-title endereco">Endereço</button>
			<div id="dropdownEndereço" class="dropdown-content endereco">
				<a href=""><button class="nav-btn drop-btn">Inserir</button></a> <a
					href=""><button class="nav-btn drop-btn">Atualizar</button></a> <a
					href=""><button class="nav-btn drop-btn">Deletar</button></a>
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
	<form action="/tipologradouro-atualiza" method="post">
		TipoLogradouro: <select name="id">
			<%
				for (TipoLogradouro tipoLogradouro : new TipoLogradouroControllerImpl().getAll()) {
			%>
			<option value="<%=tipoLogradouro.getId()%>"><%=tipoLogradouro.getNome()%></option>
			<%
				}
			%>
		</select><br> Tipo De Logradouro: <input type="text" name="nome"
			placeholder="Ex:Rua,Avenida...."> <br>
		<button>Enviar</button>
	</form>

</body>
</html>
