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
            titulo="Inserir nova Cidade"
            actionSalvar="true"
            formId="insere-form"
            actionFechar="true">
    </sge:header>

    <div class="content">
        <form action="/insere-cidade" method="post" id="insere-form">
            <div class="div-form">
                <div class="form-row">
                    <div>Nome:</div>
                    <input type="text" name="nome" class="form-control">
                </div>
                <div class="form-row">
                    <div>Sigla:</div>
                    <input type="text" name="sigla" class="form-control">
                </div>
                <div class="form-row">
                    <div>Cep:</div>
                    <input type="text" name="cep" class="form-control">
                </div>
                <div class="form-row">
                    <div>Estado:</div>
                    <select name="estado" class="form-control"
                            style="background-color: rgb(46, 46, 46)">
                        <c:forEach items="${estados.all}" var="estado">
                            <option value="${estado.id}">${estado.nome}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>