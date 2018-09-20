<%@ page import="br.com.sonner.estagio.model.Aluno" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="alunoController"
             class="br.com.sonner.estagio.controller.AlunoControllerImpl"></jsp:useBean>
<%
    Aluno campoAluno = (Aluno) session.getAttribute("campoAluno");

    if (campoAluno == null) {
        campoAluno = new Aluno();
        campoAluno.setPessoa(null);
        campoAluno.setNotas(null);
        campoAluno.setMatricula(null);
    }
%>
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Inserir novo Aluno" actionSalvar="true"
                formId="insere-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/insere-estado" method="post" id="insere-form"
              style="width: 60%; margin: auto">
            <jsp:include page="/includes/pessoaComponete.jsp"></jsp:include>
            <div class="form-row">
                <div>Número de matricula:</div>
                <input type="text"
                <%--value="<%= campoAluno.getMatricula() %>"--%>
                       name="matricula"
                       placeholder="Numero da matricula vai brota aqui" class="form-control"

                       maxlength="9">
            </div>
        </form>
    </div>
</div>
</body>
</html>
<% session.setAttribute("campoAluno", null); %>