<%@page import="br.com.sonner.estagio.controller.EnderecoControllerImpl"%>
<%@page import="br.com.sonner.estagio.model.Endereco"%>
<%@page import="br.com.sonner.estagio.controller.api.EnderecoController"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
<title>Endereços</title>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/lib/bootstrap/css/bootstrap.css">
    <script src="<%=request.getContextPath()%>/assets/lib/jquery/jquery.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css">
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
                <a href=""><button class="nav-btn drop-btn">Home</button></a>
                <a href=""><button class="nav-btn drop-btn">About</button></a>
                <a href=""><button class="nav-btn drop-btn">Contact</button></a>
            </div>
            <button type="button" class="nav-btn btn-title cidade">Cidade</button>
            <div id="dropdownCidade" class="dropdown-content cidade">
                <a href=""><button class="nav-btn drop-btn">Home</button></a>
                <a href=""><button class="nav-btn drop-btn">About</button></a>
                <a href=""><button class="nav-btn drop-btn">Contact</button></a>
            </div>
            <button type="button" class="nav-btn btn-title tipologradouro">Tipo de Logradouro</button>
            <div id="dropdownTipoLogradouro" class="dropdown-content tipologradouro">
                <a href=""><button class="nav-btn drop-btn">Home</button></a>
                <a href=""><button class="nav-btn drop-btn">About</button></a>
                <a href=""><button class="nav-btn drop-btn">Contact</button></a>
            </div>
            <button type="button" class="nav-btn btn-title logradouro">Logradouro</button>
            <div id="dropdownLogradouro" class="dropdown-content logradouro">
                <a href=""><button class="nav-btn drop-btn">Home</button></a>
                <a href=""><button class="nav-btn drop-btn">About</button></a>
                <a href=""><button class="nav-btn drop-btn">Contact</button></a>
            </div>
            <button type="button" class="nav-btn btn-title bairro">Bairro</button>
            <div id="dropdownBairro" class="dropdown-content bairro">
                <a href=""><button class="nav-btn drop-btn">Home</button></a>
                <a href=""><button class="nav-btn drop-btn">About</button></a>
                <a href=""><button class="nav-btn drop-btn">Contact</button></a>
            </div>
            <button type="button" class="nav-btn btn-title endereco">Endereço</button>
            <div id="dropdownEndereço" class="dropdown-content endereco">
                <a href=""><button class="nav-btn drop-btn">Home</button></a>
                <a href=""><button class="nav-btn drop-btn">About</button></a>
                <a href=""><button class="nav-btn drop-btn">Contact</button></a>
            </div>
        </div>
        <div id="login">
            <a href="">
                <div class="box-img">
                    <img src="<%=request.getContextPath()%>/assets/imgs/usuario.png">
                    <span>Bem vindo: Fulano</span>
                </div>
            </a>
            <a href=""><button class="btn-sair">Sair</button></a>
        </div>
    </nav>
	<table border=1 cellpading="5">
		<thead>
			<tr>
				<th></th>
				<th></th>
				<th>Logradouro</th>
				<th>Número</th>
				<th>CEP</th>
				<th>Complemento</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>	

			</tr>
		</thead>
		<%
			EnderecoController enderecoController = new EnderecoControllerImpl();
		%>
		<%
			for (Endereco endereco : enderecoController.getAll()) {
		%>

		<tr>
			<td><%=endereco.getId()%></td>
			<td><%=endereco.getLogradouro().getTipologradouro().getNome()%></td>
			<td><%=endereco.getLogradouro().getNome()%></td>
			<td><%=endereco.getNumero()%></td>
			<td><%=endereco.getCep()%></td>
			<td><%=endereco.getComplemento()%></td>
			<td><%=endereco.getBairro().getNome()%></td>
			<td><%=endereco.getBairro().getCidade().getNome()%></td>
			<td><%=endereco.getBairro().getCidade().getEstado().getNome()%></td>


		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
