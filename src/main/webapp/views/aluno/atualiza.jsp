<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="alunoController"
             class="br.com.sonner.estagio.controller.AlunoControllerImpl"></jsp:useBean>


<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Atualizar Aluno" actionSalvar="true"
                formId="insere-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/insere-aluno" method="post" id="insere-form"
              style="width: 60%; margin: auto">
            <jsp:include page="/includes/pessoaComponete.jsp"></jsp:include>
        </form>
    </div>
</div>
</body>
</html>
<% session.setAttribute("campoAluno", null); %>