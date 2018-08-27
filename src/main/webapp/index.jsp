<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/index.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="container borda-login">
    <div class="box-img">
        <img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
    </div>
    <form action="/usuario-logar" method="post" id="form-cadastro">
        <div class="div-form">
            <div class="form-row">
                <input class="form-control" type="text" name="usuario" placeholder="Usuario">
            </div>
            <div class="form-row">
                <input class="form-control" type="password" name="senha" placeholder="Senha">
            </div>
            <br>
            <button class="main-btn btn-green" style="width: 291px;">login</button>
            <br>
        </div>
    </form>
    <a class="link" href="<%=request.getContextPath()%>/senha.jsp">Esqueceu sua senha ?</a><br><br>
    <a class="link" href="<%=request.getContextPath()%>/cadastro.jsp">Cadastrar-se</a>
    <jsp:include page="/includes/msg.jsp"></jsp:include>
</div>
</body>
</html>