<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value='/assets/css/sidebar.css'/>">
<meta charset="UTF-8">
<header class="header text-center" id="header">
    <div class="header_toggle">
        <span class="material-symbols-outlined" id="header-toggle">chevron_right</span>
    </div>
</header>
<div class="l-navbar" id="nav-bar">
    <nav class="nav">
        <div>
            <a href="#" class="nav_logo"><img src="<c:url value='/assets/img/logo.png'/>" width="200" alt="Logo" class="mx-2 mb-5"></a>
            <div class="nav_list">
                <c:url var="portalFuncionarioURL" value="/FuncionarioServlet" context="${pageContext.request.contextPath}"></c:url>
                <a href="${portalFuncionarioURL}" class="nav_link ${"portalFuncionario".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">home</span>
                    <span class="nav_name">Iní­cio</span>
                </a>
                <c:url var="pedidosURL" value="/FuncionarioServlet" context="${pageContext.request.contextPath}">
                    <c:param name="action" value="pedidos"/>
                </c:url>
                <a href="${pedidosURL}" class="nav_link ${"pedidos".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">list</span>
                    <span class="nav_name">Pedidos</span>
                </a>
                <c:url var="manterFuncionariosURL" value="/FuncionarioServlet" context="${pageContext.request.contextPath}">
                    <c:param name="action" value="manterFuncionarios"/>
                </c:url>
                <a href="${manterFuncionariosURL}" class="nav_link ${"manterFuncionarios".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">groups_2</span>
                    <span class="nav_name">Funcionarios</span>
                </a>
                <c:url var="roupasURL" value="/RoupaServlet" context="${pageContext.request.contextPath}">
                    <c:param name="action" value="listarRoupas"/>
                </c:url>
                <a href="${roupasURL}" class="nav_link ${"listarRoupas".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">checkroom</span>
                    <span class="nav_name">Roupas</span>
                </a>
                <c:url var="relatoriosURL" value="/RelatorioServlet" context="${pageContext.request.contextPath}">
                    <c:param name="action" value="indexRelatorios"/>
                </c:url>
                <a href="${relatoriosURL}" class="nav_link ${"indexRelatorios".equals(sidebarAction) ? "active" : ""}">
                    <span class="material-symbols-outlined">summarize</span>
                    <span class="nav_name">Relatórios</span>
                </a>
            </div>
        </div>
        <c:url var="logoutURL" value="/AuthServlet" context="${pageContext.request.contextPath}">
            <c:param name="action" value="logout"/>
        </c:url>
        <a href="${logoutURL}" class="nav_link logout">
            <span class="material-symbols-outlined">logout</span>
            <span class="nav_name text-white">Logout</span>
        </a>
    </nav>
    <script src="<c:url value='/assets/js/sidebar.js'/>"></script>
</div>