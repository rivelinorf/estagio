<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/senha.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="container borda-login">
    <div class="box-img">
        <img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
    </div>
    <h1>Redefinir Senha</h1>
    <form action="/redefinir-senha?token=${param.token}" method="post" id="form-cadastro">
        <div class="div-form">
            <div class="form-row">
                <input class="form-control" type="password" name="newPass" placeholder="Insira nova senha">
            </div>
            <div class="form-row">
                <input class="form-control" type="password" name="confirmPass" placeholder="Confirme a nova senha">
            </div>
            <br>
            <input class="main-btn btn-green" type="submit" value="Enviar"
                   style="width: 296px">

        </div>
    </form>
    <jsp:include page="/includes/msg.jsp"></jsp:include>
</div>
</body>
</html>