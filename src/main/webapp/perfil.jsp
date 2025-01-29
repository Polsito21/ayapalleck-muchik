<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Administrador" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% HttpSession ses = request.getSession(false);
    Administrador admin = (Administrador) ses.getAttribute("admin");
%>
<%
    // Verificar la sesión
    if (ses == null || ses.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<jsp:include page="component/head.jsp"/>
<jsp:include page="component/sidebar.jsp"/>
<jsp:include page="component/navbar.jsp"/>
<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Mi perfil</h5>
            <form method="post" action="${pageContext.request.contextPath}/updateAdmin">
                <div class="mb-3">
                    <label for="full_name" class="form-label">Usuario</label>
                    <input type="text" class="form-control" id="full_name" name="full_name" value="<%= admin.getUsuario() %>" required>
                    <!-- Error message <p style="margin-left: 6px; margin-top: 2px; color: red;">El nombre no puede tener menos de 2 letras</p> -->
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <!-- Error message <p style="margin-left: 6px; margin-top: 2px; color: red;">El DNI debe tener 8 números</p> -->
                </div>
                <input type="hidden" name="admin_id" value="<%= admin.getId_admin() %>">
                <button type="submit" class="btn btn-warning">Actualizar</button>
            </form>
            <a href="<%= request.getContextPath() %>recepcion">
                <button type="button" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button>
            </a>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>