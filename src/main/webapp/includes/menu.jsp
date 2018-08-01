<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav>
    <div class="container-nav">
        <div>
            <a href="<%=request.getContextPath()%>/index.jsp">
                <div class="box-img">
                    <img class="logo" src="<%=request.getContextPath()%>/assets/imgs/logo.png">
                    <span>Sistema de Gestão Educacional</span>
                </div>
            </a>
        </div>
        <hr>
        <div>
            <button type="button" class="nav-btn btn-title estado">Estado</button>
            <div id="dropdownEstado" class="dropdown-content estado">
                <a href=""><button class="nav-btn drop-btn">Listar</button></a>
                <a href=""><button class="nav-btn drop-btn">Inserir</button></a>
                <a href=""><button class="nav-btn drop-btn">Atualizar</button></a>
                <a href=""><button class="nav-btn drop-btn">Deletar</button></a>
            </div>
            <button type="button" class="nav-btn btn-title cidade">Cidade</button>
            <div id="dropdownCidade" class="dropdown-content cidade">
                <a href=""><button class="nav-btn drop-btn">Listar</button></a>
                <a href=""><button class="nav-btn drop-btn">Inserir</button></a>
                <a href=""><button class="nav-btn drop-btn">Atualizar</button></a>
                <a href=""><button class="nav-btn drop-btn">Deletar</button></a>
            </div>
            <button type="button" class="nav-btn btn-title tipologradouro">Tipo de Logradouro</button>
            <div id="dropdownTipoLogradouro" class="dropdown-content tipologradouro">
                <a href=""><button class="nav-btn drop-btn">Listar</button></a>
                <a href=""><button class="nav-btn drop-btn">Inserir</button></a>
                <a href=""><button class="nav-btn drop-btn">Atualizar</button></a>
                <a href=""><button class="nav-btn drop-btn">Deletar</button></a>
            </div>
            <button type="button" class="nav-btn btn-title logradouro">Logradouro</button>
            <div id="dropdownLogradouro" class="dropdown-content logradouro">
                <a href=""><button class="nav-btn drop-btn">Listar</button></a>
                <a href=""><button class="nav-btn drop-btn">Inserir</button></a>
                <a href=""><button class="nav-btn drop-btn">Atualizar</button></a>
                <a href=""><button class="nav-btn drop-btn">Deletar</button></a>
            </div>
            <button type="button" class="nav-btn btn-title bairro">Bairro</button>
            <div id="dropdownBairro" class="dropdown-content bairro">
                <a href=""><button class="nav-btn drop-btn">Listar</button></a>
                <a href=""><button class="nav-btn drop-btn">Inserir</button></a>
                <a href=""><button class="nav-btn drop-btn">Atualizar</button></a>
                <a href=""><button class="nav-btn drop-btn">Deletar</button></a>
            </div>
            <button type="button" class="nav-btn btn-title endereco">Endereço</button>
            <div id="dropdownEndereço" class="dropdown-content endereco">
                <a href=""><button class="nav-btn drop-btn">Listar</button></a>
                <a href=""><button class="nav-btn drop-btn">Inserir</button></a>
                <a href=""><button class="nav-btn drop-btn">Atualizar</button></a>
                <a href=""><button class="nav-btn drop-btn">Deletar</button></a>
            </div>
        </div>
    </div>
    <footer class="container-nav">
        <a href="">
            <div class="box-img">
                <img class="user-img" src="<%=request.getContextPath()%>/assets/imgs/user.jpg">
                <span>Bem vindo: Matheus</span>
            </div>
        </a>
        <a href=""><button class="btn-sair">Sair</button></a>
    </footer>
</nav>