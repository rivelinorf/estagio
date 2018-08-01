<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="titulo">
        <h3><%= request.getParameter("titulo") %></h3>
    </div>
    <div class="botoes">
        <i class="fas fa-eraser"></i>
        <i class="fas fa-save"></i>
        <%--<i class="fas fa-window-close"></i>--%>
    </div>
</header>