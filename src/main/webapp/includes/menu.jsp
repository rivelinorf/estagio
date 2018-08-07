<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="application/javascript">
    $(document).ready(function () {
        $("#estado").on("click", function () {
            $.ajax({
                url: "/views/estado/lista.jsp",
                type: "GET",
                success: function (data) {
                    $("#content").html(data).fadeIn(500)
                }
            })
        });

        $("#tipologradouro").on("click", function () {
            $.ajax({
                url: "/views/tipologradouro/lista.jsp",
                type: "GET",
                success: function (data) {
                    $("#table-content").html(data).fadeIn(500)
                }
            })
        });

        $("#logradouro").on("click", function () {
            $.ajax({
                url: "/views/logradouro/lista.jsp",
                type: "GET",
                success: function (data) {
                    $("#table-content").html(data).fadeIn(500)
                }
            })
        });

        $("#bairro").on("click", function () {
            $.ajax({
                url: "/views/bairro/lista.jsp",
                type: "GET",
                success: function (data) {
                    $("#table-content").html(data).fadeIn(500)
                }
            })
        });

        $("#endereco").on("click", function () {
            $.ajax({
                url: "/views/endereco/lista.jsp",
                type: "GET",
                success: function (data) {
                    $("#table-content").html(data).fadeIn(500)
                }
            })
        });

    });
</script>

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
        <button type="button" class="main-btn btn-title" id="estado">Estado</button>
        <button type="button" class="main-btn btn-title cidade">Cidade</button>
        <button type="button" class="main-btn btn-title tipologradouro" id=tipologradouro>Tipo de Logradouro</button>
        <button type="button" class="main-btn btn-title logradouro" id=logradouro>Logradouro</button>
        <button type="button" class="main-btn btn-title" id="bairro">Bairro</button>
        <button type="button" class="main-btn btn-title" id="endereco">Endereço</button>

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
    <a href="<%=request.getContextPath()%>/index.jsp"><button class="btn-sair">Sair</button></a>
</footer>
