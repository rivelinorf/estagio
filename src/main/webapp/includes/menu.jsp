<%@ page import="br.com.sonner.estagio.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% Usuario usuario = (Usuario) request.getSession().getAttribute("USER"); %>
<script>
    function showOpt(id) {
        for (var i = 0; i < $(".dropdown-content").length; i++) {
            $('.dropdown-content:eq(' + i + ')').hide(220)
        }
        $(id).next(".dropdown-content").toggle(220)
    }

    $(document).ready(function () {
        $(".dropdown-content button").on("click", function () {
            $(".lds-ring").css({"display": "flex"})
        })
    })
</script>

<div class="lds-ring">
    <div></div>
    <div></div>
    <div></div>
    <div></div>
</div>
<nav>
    <div class="container-nav">
        <div>
            <a href="/views/home.jsp">
                <div class="box-img">
                    <img class="logo"
                         src="<%=request.getContextPath()%>/assets/imgs/logo.png"> <span>Sistema
					de Gestão Educacional</span>
                </div>
            </a>
        </div>
        <hr>
        <div>
            <button type="button" class="main-btn btn-menu" onclick="showOpt(this)">Configurações</button>
            <div class="dropdown-content">
                <a href="/pesquisa-endereco">
                    <button type="button" class="main-btn btn-menu drop-btn" id="endereco">Endereco</button>
                </a>
                <a href="/pesquisa-estado">
                    <button type="button" class="main-btn btn-menu drop-btn" id="estado">Estado</button>
                </a>
                <a href="/pesquisa-cidade">
                    <button type="button" class="main-btn btn-menu drop-btn cidade">Cidade</button>
                </a>
                <a href="/pesquisa-tipologradouro">
                    <button type="button" class="main-btn btn-menu drop-btn tipologradouro" id=tipologradouro>Tipo de
                        Logradouro
                    </button>
                </a>
                <a href="/pesquisa-logradouro">
                    <button type="button" class="main-btn btn-menu drop-btn logradouro" id=logradouro>Logradouro
                    </button>
                </a>
                <a href="/pesquisa-bairro">
                    <button type="button" class="main-btn btn-menu drop-btn" id="bairro">Bairro</button>
                </a>
            </div>
        </div>
    </div>
    <footer class="container-nav">
        <a href="/views/home.jsp">
            <div class="box-img">
                <img class="user-img" src="<%=usuario.getFoto()%>"> <span>Bem
				vindo: <%=usuario.getUsuario()%></span>
            </div>
        </a>
        <a href="/usuario-logout">
            <button class="btn-sair">Sair</button>
        </a>
    </footer>
</nav>