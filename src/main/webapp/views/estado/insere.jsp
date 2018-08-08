<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="estados" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Inserir novo Estado"
            actionSalvar="true"
            actionLimpar="true"
            actionFechar="true">
    </sge:header>

    <div class="content">
        <div class="div-form">
            <form action="/insere-estado" method="post">
                <div class="form-div"><div>Nome:</div> <input type="text" name="nome" class="form-control"></div>
                <div class="form-div"><div>Abreviação:</div> <input type="text" name="abv" class="form-control"></div>
            </form>
        </div>
    </div>
</div>
</body>
</html>