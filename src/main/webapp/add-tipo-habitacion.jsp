<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Verificar la sesión
    HttpSession ses = request.getSession(false);
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
            <h5 class="card-title fw-semibold mb-4">Registrar nuevo tipo de habitación</h5>
            <form method="post" action="<%= request.getContextPath() %>/addTipoHabitacion">
                <div class="mb-3">
                    <label for="nombreHabitacion" class="form-label">Ingresa su nombre</label>
                    <input type="text" class="form-control" id="nombreHabitacion" name="nombreHabitacion" aria-describedby="emailHelp" required>
                </div>
                <div class="mb-3">
                    <label for="descripcion" class="form-label">Ingresa su descripción</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" aria-describedby="emailHelp" required>
                </div>
                <div class="mb-3">
                    <label for="precio_noche" class="form-label">Ingresa su precio de noche</label>
                    <input type="number" class="form-control" id="precio_noche" name="precio_noche" aria-describedby="emailHelp" required>
                </div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
            <a href="<%= request.getContextPath() %>/tiposhabitacion"><button type="submit" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>