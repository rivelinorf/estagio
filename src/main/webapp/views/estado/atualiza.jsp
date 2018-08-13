<%@ page import="br.com.sonner.estagio.vos.EstadoFiltroVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="estados" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<%
    EstadoFiltroVO vo = (EstadoFiltroVO) session.getAttribute("estado-para-editar");

    if (vo == null) {
        vo = new EstadoFiltroVO();
        vo.setEstado("");
        vo.setAbv("");
    }
%>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Editar estado"
            actionSalvar="true"
            formId="edit-form"
            actionFechar="true">
    </sge:header>

    <div class="content">
        <form action="/atualiza-estado?id=<%= vo.getId() %>" method="post" id="edit-form">
            <div class="div-form">
                <div class="form-row">
                    <div>Nome:</div>
                    <input type="text" name="estado" class="form-control" value="<%= vo.getEstado() %>"></div>
                <div class="form-row">
                    <div>Abreviação:</div>
                    <input type="text" name="abv" class="form-control" value="<%= vo.getAbv() %>"></div>
            </div>
        </form>
    </div>
</div>
</body>
</html>