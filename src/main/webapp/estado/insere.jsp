<%--
  Created by IntelliJ IDEA.
  User: matheus
  Date: 7/27/18
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css">
</head>
<body>
    <nav>
        <a href="<%=request.getContextPath()%>/index.jsp">
            <div class="logo">
                <img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
                <span>Sistema de Gestão Educacional</span>
            </div>
        </a>
        <hr>
        <a href=""><button class="nav-btn">Estado</button></a>
        <a href=""><button class="nav-btn">Cidade</button></a>
        <a href=""><button class="nav-btn">Tipo de Logradouro</button></a>
        <a href=""><button class="nav-btn">Logradouro</button></a>
        <a href=""><button class="nav-btn">Bairro</button></a>
        <a href=""><button class="nav-btn">Endereço</button></a>

        <div id="login">
            <a href="">
                <div class="logo">
                    <img class="user" src="<%=request.getContextPath()%>/assets/imgs/usuario.png">
                    <span>Bem vindo: Fulano</span>
                </div>
            </a>
            <a href=""><button class="btn-sair">Sair</button></a>
        </div>
    </nav>
    <form action="/estado-insere" method="post">
        <h3>Estado</h3>
        nome: <input type="text" name="nome"> <br>
        abreviacao: <input type="text" name="abv">
        <button>Enviar</button>
    </form>
</body>
</html>
