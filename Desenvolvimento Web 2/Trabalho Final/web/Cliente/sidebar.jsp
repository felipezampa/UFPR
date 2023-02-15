<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value='/assets/css/sidebar.css'/>">
<meta charset="UTF-8">
<header class="header text-center" id="header">
    <div class="header_toggle">
        <span class="material-symbols-outlined" id="header-toggle">chevron_right</span>
    </div>
    <div></div>
</header>
<div class="l-navbar" id="nav-bar">
    <nav class="nav">
        <div>
            <a href="#" class="nav_logo"></i> <img src="<c:url value='/assets/img/logo.png'/>" width="200" alt="Logo" class="mx-2 mb-5"></a>
            <div class="nav_list">
                <c:url var="portalClienteURL" value="/ClienteServlet" context="${pageContext.request.contextPath}"></c:url>
                <a href="${portalClienteURL}" class="nav_link ${"portalCliente".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">home</span>
                    <span class="nav_name">Início</span>
                </a>
                <c:url var="novoPedidoURL" value="/ClienteServlet" context="${pageContext.request.contextPath}">
                    <c:param name="action" value="novoPedido"/>
                </c:url>
                <a href="${novoPedidoURL}" class="nav_link ${"novoPedido".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">add</span>
                    <span class="nav_name">Novo Pedido</span>
                </a>
                <c:url var="listaPedidosURL" value="/ClienteServlet" context="${pageContext.request.contextPath}">
                    <c:param name="action" value="listaPedidos"/>
                </c:url>
                <a href="${listaPedidosURL}" class="nav_link ${"listaPedidos".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">list</span>
                    <span class="nav_name">Todos os Pedidos</span>
                </a>

                <c:url var="consultarPedidoURL" value="/ClienteServlet" context="${pageContext.request.contextPath}">
                    <c:param name="action" value="consultar"/>
                </c:url>
                <a href="${consultarPedidoURL}" class="nav_link ${"consultar".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">search</span>
                    <span class="nav_name">Consultar pedido</span>
                </a>
                    
                <c:url var="orcamentosURL" value="/ClienteServlet" context="${pageContext.request.contextPath}">
                    <c:param name="action" value="orcamentos"/>
                </c:url>
                <a href="${orcamentosURL}" class="nav_link ${"orcamentos".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">paid</span>
                    <span class="nav_name">Orçamentos</span>
                </a> 
            </div>
        </div>
        <c:url var="logoutURL" value="/AuthServlet" context="${pageContext.request.contextPath}" >
            <c:param name="action" value="logout" />
        </c:url>
        <a href="${logoutURL}" class="nav_link logout">
            <span class="material-symbols-outlined">logout</span>
            <span class="nav_name text-white">Logout</span>
        </a>
    </nav>
    <script src="<c:url value='/assets/js/sidebar.js'/>"></script>
</div>