<%@page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="tipologradouroController" class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Inserir novo Tipo de Logradouro"
            actionSalvar="true"
            formId="insere-form"
            actionFechar="true">
    </sge:header>
    <div class="content">
        <form action="/insere-tipologradouro" method="post" id="insere-form">
            <div class="div-form">
                <div class="form-row">
                    <div>Nome:</div>
                    <input type="text" name="nome" class="form-control"onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return false; else return true;"></div>
            </div>
        </form>
    </div>
</div>
</body>
</html>