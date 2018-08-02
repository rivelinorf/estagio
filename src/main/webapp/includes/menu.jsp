<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <button type="button" class="nav-btn btn-title" id="estado">Estado</button>
        <a href="<%=request.getContextPath()%>/views/cidade/lista.jsp">
            <button type="button" class="nav-btn btn-title cidade">Cidade</button>
        </a>
        <a href="<%=request.getContextPath()%>/views/tipologradouro/lista.jsp">
            <button type="button" class="nav-btn btn-title tipologradouro">Tipo de Logradouro</button>
        </a>
        <a href="<%=request.getContextPath()%>/views/logradouro/lista.jsp">
            <button type="button" class="nav-btn btn-title logradouro">Logradouro</button>
        </a>
        <a href="<%=request.getContextPath()%>/views/bairro/lista.jsp">
            <button type="button" class="nav-btn btn-title bairro">Bairro</button>
        </a>
        <a href="<%=request.getContextPath()%>/views/endereco/lista.jsp">
            <button type="button" class="nav-btn btn-title endereco">Endereço</button>
        </a>
    </div>
</div>
<footer class="container-nav">
    <a href="">
        <div class="box-img">
            <img class="user-img" src="<%=request.getContextPath()%>/assets/imgs/user.jpg">
            <span>Bem vindo: Matheus</span>
        </div>
    </a>
    <a href="">
        <button class="btn-sair">Sair</button>
    </a>
</footer>