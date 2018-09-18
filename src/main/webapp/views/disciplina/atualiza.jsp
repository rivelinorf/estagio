<%@page import="br.com.sonner.estagio.vos.DisciplinaFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="disciplinaController"
             class="br.com.sonner.estagio.controller.DisciplinaControllerImpl"></jsp:useBean>

<%
    DisciplinaFiltroVO vo = (DisciplinaFiltroVO) session.getAttribute("disciplina-para-editar");
    if (vo == null) {
        vo = new DisciplinaFiltroVO();
        vo.setNome("");
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
    <sge:header
            titulo="Editar nome da disciplina"
            actionSalvar="true"
            actionLimpar="/disciplina/preenche-vo"
            formId="edit-form"
            actionFechar="true">
    </sge:header>

    <input type="hidden" value="<%=vo.getId()%>" id="id">
    <form action="/atualiza-disciplina?id=<%=vo.getId()%>" method="post" id="edit-form">
        <div class="div-form" style="width: 60%; margin: auto">
            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="disciplina" class="form-control" value="<%=vo.getNome()%>"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
            </div>
        </div>

    </form>
</div>

</body>
</html>