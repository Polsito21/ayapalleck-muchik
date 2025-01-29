<%@ page import="utp.edu.pe.ayapalleckmuchik.model.enums.Tipo_documento" %>
<%@ page import="java.util.List" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Tipo_documento> tipos_documento = Tipo_documento.getTipos_documento();
    Cliente cliente = new Cliente();
    if (request.getAttribute("cliente") != null) {
        cliente = (Cliente) request.getAttribute("cliente");
    } else {
        response.sendRedirect("clientes.jsp");
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
            <h5 class="card-title fw-semibold mb-4">Registrar nuevo cliente</h5>
            <form method="post" action="<%= request.getContextPath() %>/updateCliente">
                <input type="hidden" name="cliente_id" value="<%= cliente.getId_cliente() %>">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Ingresa sus nombres</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" aria-describedby="emailHelp" value="<%= cliente.getNombre() %>" required>
                </div>
                <div class="mb-3">
                    <label for="apellido" class="form-label">Ingresa sus apellidos</label>
                    <input type="text" class="form-control" id="apellido" name="apellido" aria-describedby="emailHelp" value="<%= cliente.getApellido() %>" required>
                </div>
                <div class="mb-3">
                    <label for="tipo_documento" class="form-label">Selecciona el tipo de documento</label>
                    <select class="form-select" id="tipo_documento" name="tipo_documento" required>
                        <option value="" selected>Selecciona un tipo de documento</option>
                        <% for (Tipo_documento tipo_documento : tipos_documento) { %>
                        <% if (tipo_documento.equals(cliente.getTipo_documento())) { %>
                        <option value="<%= tipo_documento %>" selected><%= tipo_documento.getDisplayName() %></option>
                        <% } else { %>
                        <option value="<%= tipo_documento %>"><%= tipo_documento.getDisplayName() %></option>
                        <% } %>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="numero_documento" class="form-label">Ingresa el número de documento</label>
                    <input type="text" class="form-control" id="numero_documento" name="numero_documento" aria-describedby="emailHelp" value="<%= cliente.getNumero_documento() %>" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Ingresa su email</label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="<%= cliente.getEmail() %>" required>
                </div>
                <div class="mb-3">
                    <label for="telefono" class="form-label">Ingresa su teléfono</label>
                    <input type="tel" class="form-control" id="telefono" name="telefono" aria-describedby="emailHelp" value="<%= cliente.getTelefono() %>" required>
                </div>
                <div class="mb-3">
                    <label for="fecha_nacimiento" class="form-label">Ingresa su fecha de nacimiento</label>
                    <input type="date" class="form-control" id="fecha_nacimiento" name="fecha_nacimiento" aria-describedby="emailHelp" value="<%= cliente.getFecha_nacimiento() %>" required>
                </div>
                <button type="submit" class="btn btn-warning">Actualizar</button>
            </form>
            <a href="<%= request.getContextPath() %>/clientes"><button type="submit" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>