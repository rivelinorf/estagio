<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="tipologradouroCtl" class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
<jsp:useBean id="cidadesCtl" class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Inserir novo  Logradouro"
            actionSalvar="true"
            formId="insere-form"
            actionFechar="true">
    </sge:header>

    <div class="div-form">
        <form action="/insere-logradouro" method="post" id="insere-form" style="width: 60%; margin: auto">

            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="logradouro" class="form-control">
            </div>


            <div class="form-row">
                <div>Tipo Logradouro:</div>
                <select name="tipologradouro" class="form-control" style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${tipologradouroCtl.all}" var="tipologradouro">
                        <option value="${tipologradouro.id}">${tipologradouro.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row">
                <div>Cidade:</div>
                <select name="cidade" class="form-control">
                    <option value="-1">Selecione uma opção...</option>

                    <c:forEach items="${cidadesCtl.all}" var="cidade">
                        <option value="${cidade.id}">${cidade.nome}</option>
                    </c:forEach>
                </select>
            </div>
        </form>

    </div>

</body>
</html>

