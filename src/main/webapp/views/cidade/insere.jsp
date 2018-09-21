<%@ page import="br.com.sonner.estagio.model.Cidade" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="estados"
             class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<%
    Cidade cidade = (Cidade) session.getAttribute("campoCidade");

    if (cidade == null) {
        cidade = new Cidade();
        cidade.setNome("");
        cidade.setCod("");
        cidade.setCep("");
        cidade.setEstado(null);
    }
%>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <script type="text/javascript" src="/assets/js/MascaraValidacao.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Inserir nova Cidade" actionSalvar="true"
                formId="insere-form" actionFechar="true">
    </sge:header>


    <div class="div-form">
        <form name="form1" action="/insere-cidade" method="post"
              id="insere-form" style="width: 100%;">
            <div class="form-row">
                <div>Nome:</div>
                <input type="text" value="<%= cidade.getNome() %>" name="nome" class="form-control" maxlength="50"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
            </div>
            <div class="form-row">
                <div>Código:</div>
                <input type="text" value="<%= cidade.getCod() %>" name="codigo" class="form-control"
                       style="width: 20.35%;" maxlength="2" onKeyPress="mascaraInteiro()">
                <div style="width: 80px">CEP:</div>
                <input type="text" value="<%= cidade.getCep() %>" name="cep" class="form-control"
                       style="width: 20.35%" onKeyPress="MascaraCep(form1.cep);"
                       maxlength="10" placeholder="Ex.: 00.000-000">
            </div>
            <div class="form-row">
                <div>Estado:</div>
                <select name="estado" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${estados.all}" var="estado">
                        <c:choose>
                            <c:when test="${estado.id == campoCidade.estado.id}">
                                <option value="${estado.id}" selected>${estado.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${estado.id}">${estado.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>

</div>
</body>
</html>

<% session.setAttribute("campoCidade", null); %>