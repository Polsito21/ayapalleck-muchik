<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Cliente" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Habitacion" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Reserva" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.enums.Metodo_pago" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Cliente> clientes;
    if (request.getAttribute("clientes") != null) {
        clientes = (List<Cliente>) request.getAttribute("clientes");
    } else {
        clientes = new ArrayList<>();
    }

    List<Habitacion> habitaciones;
    if (request.getAttribute("habitaciones") != null) {
        habitaciones = (List<Habitacion>) request.getAttribute("habitaciones");
    } else {
        habitaciones = new ArrayList<>();
    }

    Reserva reserva = new Reserva();
    if (request.getAttribute("reserva") != null) {
        reserva = (Reserva) request.getAttribute("reserva");
    } else {
        response.sendRedirect("recepcion.jsp");
    }

    List<Metodo_pago> metodo_pagos = Metodo_pago.getMetodos_pago();
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
            <h5 class="card-title fw-semibold mb-4">Actualizar reserva</h5>
            <form method="post" action="<%= request.getContextPath() %>/updateReserva">
                <input type="hidden" name="reserva_id" value="<%= reserva.getId_reserva() %>">
                <div class="mb-3">
                    <label for="cliente" class="form-label">Selecciona el cliente</label>
                    <select class="form-select" id="cliente" name="cliente" required>
                        <option value="" selected>Selecciona un cliente</option>
                        <% if (!clientes.isEmpty()) { %>
                        <% for (Cliente cliente : clientes) { %>
                        <% if (cliente.getId_cliente() == reserva.getCliente().getId_cliente()) { %>
                        <option value="<%= cliente.getId_cliente() %>" selected><%= cliente.getNombre() + " " + cliente.getApellido() +  " | " + cliente.getTipo_documento() + ": " + cliente.getNumero_documento() %></option>
                        <% } else { %>
                        <option value="<%= cliente.getId_cliente() %>"><%= cliente.getNombre() + " " + cliente.getApellido() +  " | " + cliente.getTipo_documento() + ": " + cliente.getNumero_documento() %></option>
                        <% } %>
                        <% } %>
                        <% } else { %>
                        <option disabled selected>Debes registrar un cliente antes de registrar una reserva</option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="habitacion" class="form-label">Selecciona la habitación</label>
                    <select class="form-select" id="habitacion" name="habitacion" required>
                        <option value="" selected>Selecciona una habitación</option>
                        <% if (!habitaciones.isEmpty()) { %>
                        <% for (Habitacion habitacion : habitaciones) { %>
                        <% if (habitacion.getId_habitacion() == reserva.getHabitacion().getId_habitacion()) { %>
                        <option value="<%= habitacion.getId_habitacion() %>" selected><%= habitacion.getNumero_habitacion() + " | " + habitacion.getTipo_habitacion().getNombre_habitacion() %></option>
                        <% } else { %>
                        <option value="<%= habitacion.getId_habitacion() %>"><%= habitacion.getNumero_habitacion() + " | " + habitacion.getTipo_habitacion().getNombre_habitacion() %></option>
                        <% } %>
                        <% } %>
                        <% } else { %>
                        <option disabled selected>Debes registrar una habitación antes de registrar una reserva</option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="metodo_pago" class="form-label">Selecciona el método de pago</label>
                    <select class="form-select" id="metodo_pago" name="metodo_pago" required>
                        <% for (Metodo_pago metodo_pago : metodo_pagos) { %>
                        <option value="<%= metodo_pago.toString() %>"><%= metodo_pago.getDisplayName() %></option>
                        <% } %>
                    </select>
                </div>
                <% if (clientes != null && !habitaciones.isEmpty()) { %>
                <button type="submit" class="btn btn-warning">Actualizar</button>
                <% } else { %>
                <button type="submit" class="btn btn-warning" disabled>Actualizar</button>
                <% } %>
            </form>
            <a href="<%= request.getContextPath() %>/recepcion"><button type="submit" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>