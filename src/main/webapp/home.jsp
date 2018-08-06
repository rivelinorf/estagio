<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta name="titulo" content="Home">
    <meta name="url" content="">
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
    <nav>
        <jsp:include page="/includes/menu.jsp"></jsp:include>
    </nav>
    <section id="content">
        <sge:content titulo="Home" url="/views/estado.jsp"></sge:content>
    </section>
</body>
</html>