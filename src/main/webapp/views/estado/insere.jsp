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
            formId="insere-form"
            actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/insere-estado" method="post" id="insere-form" style="width: 60%; margin: auto">
            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="nome" class="form-control"></div>
            <div class="form-row">
                <div>Abreviação:</div>
                <input type="text" name="abv" class="form-control"></div>
        </form>
    </div>
</div>
</body>
</html>