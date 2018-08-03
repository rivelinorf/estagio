<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
    <nav>
        <jsp:include page="/includes/menu.jsp"></jsp:include>
    </nav>
    <section id="content">
        <header>
            <jsp:include page="/includes/header.jsp">
                <jsp:param name="titulo" value="Home"></jsp:param>
            </jsp:include>
        </header>
        <div id="table-content">

        </div>
    </section>
</body>
</html>