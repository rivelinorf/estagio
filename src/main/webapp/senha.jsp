<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/senha.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>

<script>
    $(document).ready(function () {
        $(".enviar").on("click", function () {
            $(".lds-ring").css({"display": "flex"})
        })
    })
</script>
<body>
<div class="lds-ring">
    <div></div>
    <div></div>
    <div></div>
    <div></div>
</div>
<div class="container borda-login">
    <div class="box-img">
        <img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
    </div>
    <h1>Resetar senha</h1>
    <form action="/esqueci-senha" method="post" id="form-cadastro" style="margin-top: 15px">
        <div class="div-form">
            <div class="form-row">
                <input class="form-control" type="text" name="user" placeholder="Insira seu usuario">
            </div>
            <br>
            <input class="main-btn btn-green enviar" type="submit" value="Enviar"
                   style="width: 296px">
            <br>
        </div>
    </form>
    <jsp:include page="/includes/msg.jsp"></jsp:include>
</div>
</body>
</html>