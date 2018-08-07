<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="dashboard" scope="session" class="br.com.sonner.estagio.util.Dashboard"></jsp:useBean>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
    <nav>
        <jsp:include page="/includes/menu.jsp"></jsp:include>
    </nav>

    <div id="content">

    </div>
</body>
</html>