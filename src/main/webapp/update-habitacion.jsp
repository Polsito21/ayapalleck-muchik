<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Tipo_habitacion" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Habitacion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Tipo_habitacion> tipos_habitacion;
    if (request.getAttribute("tipos_habitacion") != null) {
        tipos_habitacion = (List<Tipo_habitacion>) request.getAttribute("tipos_habitacion");
    } else {
        tipos_habitacion = new ArrayList<>();
    }
    Habitacion habitacion = new Habitacion();
    if (request.getAttribute("habitacion") != null) {
        habitacion = (Habitacion) request.getAttribute("habitacion");
    } else {
        response.sendRedirect("habitaciones.jsp");
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
            <h5 class="card-title fw-semibold mb-4">Registrar nueva habitación</h5>
            <form method="post" action="<%= request.getContextPath() %>/updateHabitacion">
                <input type="hidden" name="id_habitacion" value="<%= habitacion.getId_habitacion() %>">
                <div class="mb-3">
                    <label for="numero_habitacion" class="form-label">Ingresa su número de habitación</label>
                    <input type="number" class="form-control" id="numero_habitacion" name="numero_habitacion" aria-describedby="emailHelp" value="<%= habitacion.getNumero_habitacion() %>" required>
                </div>
                <div class="mb-3">
                    <label for="tipo_habitacion" class="form-label">Selecciona su tipo de habitación</label>
                    <select class="form-select" id="tipo_habitacion" name="tipo_habitacion" required>
                        <option value="" selected>Selecciona un tipo de habitación</option>
                        <% if (!tipos_habitacion.isEmpty()) { %>
                        <% for (Tipo_habitacion tipo_habitacion : tipos_habitacion) { %>
                        <% if (tipo_habitacion.getId_tipo_habitacion() == habitacion.getTipo_habitacion().getId_tipo_habitacion()) { %>
                        <option value="<%= tipo_habitacion.getId_tipo_habitacion() %>" selected><%= tipo_habitacion.getNombre_habitacion() %></option>
                        <% } else { %>
                        <option value="<%= tipo_habitacion.getId_tipo_habitacion() %>"><%= tipo_habitacion.getNombre_habitacion() %></option>
                        <% } %>
                        <% } %>
                        <% } else { %>
                        <option disabled selected>Debes registrar un tipo de habitación antes de registrar una habitación</option>
                        <% } %>
                    </select>
                </div>
                <% if (!tipos_habitacion.isEmpty()) { %>
                <button type="submit" class="btn btn-warning">Actualizar</button>
                <% } else { %>
                <button type="submit" class="btn btn-warning" disabled>Actualizar</button>
                <% } %>
            </form>
            <a href="<%= request.getContextPath() %>/habitaciones"><button type="submit" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>