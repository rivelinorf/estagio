<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script>
    $("#olho").on("click", function () {
        $("#senha").attr("type", "text");
    });
</script>
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
    <form action="/usuario-logar" method="post" id="form-cadastro" style="margin-top: 15px">
        <div class="div-form">
            <div class="form-row">
                <input class="form-control" type="text" name="usuario" placeholder="Usuario">
            </div>
            <div class="form-row">
                <input class="form-control " type="password" name="senha" placeholder="Senha">
                <button type="button"><i class="fas fa-eye"></i></button>
            </div>
            <br>
            <button class="main-btn btn-green" style="width: 291px;">login</button>
            <br>
        </div>
    </form>
    <a class="link" href="<%=request.getContextPath()%>/senha.jsp">Esqueceu sua senha ?</a><br><br>
    <a class="link" href="<%=request.getContextPath()%>/cadastro.jsp">Cadastrar-se</a>
    <div style="margin-top: 40px">
        <jsp:include page="/includes/msg.jsp"></jsp:include>
    </div>
</div>
</body>
</html>