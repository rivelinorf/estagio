<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav>
    <div class="container-nav">
        <div>
            <a href="">
                <div class="box-img">
                    <img class="logo"
                         src="<%=request.getContextPath()%>/assets/imgs/logo.png"> <span>Sistema
					de Gestão Educacional</span>
                </div>
            </a>
        </div>
        <hr>
        <div>
            <a href="/views/estado/lista.jsp"><button type="button" class="main-btn btn-menu" id="estado">Estado</button></a>
            <a href="/views/cidade/lista.jsp"> <button type="button" class="main-btn btn-menu cidade">Cidade</button></a>
            <a href="/views/tipologradouro/lista.jsp"><button type="button" class="main-btn btn-menu tipologradouro" id=tipologradouro>Tipo de Logradouro</button></a>
            <a href="/views/logradouro/lista.jsp"><button type="button" class="main-btn btn-menu logradouro" id=logradouro>Logradouro</button></a>
            <a href="/views/bairro/lista.jsp"><button type="button" class="main-btn btn-menu" id="bairro">Bairro</button></a>
            <a href="/views/endereco/lista.jsp"><button type="button" class="main-btn btn-menu" id="endereco">Endereço</button></a>
        </div>
    </div>
    <footer class="container-nav">
        <a href="">
            <div class="box-img">
                <img class="user-img"
                     src="<%=request.getContextPath()%>/assets/imgs/user.jpg"> <span>Bem
				vindo: Matheus</span>
            </div>
        </a>
        <a href="/usuario-logout">
            <button class="btn-sair">Sair</button>
        </a>
    </footer>
</nav>