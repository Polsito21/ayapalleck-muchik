<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Tipo_habitacion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Tipo_habitacion tipo_habitacion = new Tipo_habitacion();
    if (request.getAttribute("tipo_habitacion") != null) {
        tipo_habitacion = (Tipo_habitacion) request.getAttribute("tipo_habitacion");
    } else {
        response.sendRedirect("tipos_habitacion.jsp");
    }
%>
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
            <form method="post" action="<%= request.getContextPath() %>/updateTipoHabitacion">
                <input type="hidden" name="tipo_habitacion" value="<%= tipo_habitacion.getId_tipo_habitacion() %>">
                <div class="mb-3">
                    <label for="nombreHabitacion" class="form-label">Ingresa su nombre</label>
                    <input type="text" class="form-control" id="nombreHabitacion" name="nombreHabitacion" aria-describedby="emailHelp" value="<%= tipo_habitacion.getNombre_habitacion() %>" required>
                </div>
                <div class="mb-3">
                    <label for="descripcion" class="form-label">Ingresa su descripción</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" aria-describedby="emailHelp" value="<%= tipo_habitacion.getDescripcion() %>" required>
                </div>
                <div class="mb-3">
                    <label for="precio_noche" class="form-label">Ingresa su precio de noche</label>
                    <input type="number" class="form-control" id="precio_noche" name="precio_noche" aria-describedby="emailHelp" value="<%= tipo_habitacion.getPrecio_noche() %>" required>
                </div>
                <button type="submit" class="btn btn-warning">Actualizar</button>
            </form>
            <a href="<%= request.getContextPath() %>/tiposhabitacion"><button type="submit" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>