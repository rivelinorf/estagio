<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/cadastro.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="container borda-login">
    <div class="box-img">
        <img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
    </div>
    <h1>Cadastro de Usuario</h1>
    <form action="/usuario-insere" method="post" id="form-cadastro">
        <div class="div-form">
            <div class="form-row">
                <input class="form-control" type="text" name="email" placeholder="insira seu email">
            </div>
            <div class="form-row">
                <input class="form-control" type="text" name="usuario" placeholder="insira seu usuario">
            </div>
            <div class="form-row">
                <input class="form-control" type="password" name="senha" placeholder="insira sua senha">
            </div>
            <br>
            <button class="main-btn btn-green">Cadastrar</button>
            <br>
        </div>
    </form>

    <jsp:include page="/includes/msg.jsp"></jsp:include>

</div>
</body>
</html>