<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/partials/meta.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/views/partials/header.jsp"%>
<div class="main_area">
    <div class="buttons_container">
        <h1>Select page</h1>
        <div class="links">
            <a href="/menu?action=get&select_all" class="button_large">
                <button type="button" class="button_large">Menu</button>
            </a>
            <a href="/admin?action=get" class="button_large">
                <button type="button" class="button_large">Manage</button>
            </a>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/partials/footer.jsp"%>
</body>

