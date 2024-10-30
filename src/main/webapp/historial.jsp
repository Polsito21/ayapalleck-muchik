<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Reserva" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Reserva> reservas;
  if (request.getAttribute("reservas") != null) {
    reservas = (List<Reserva>) request.getAttribute("reservas");
  } else {
    reservas = new ArrayList<>();
  }
%>
<%
  // Verificar la sesión
  HttpSession ses = request.getSession(false);
  if (ses == null || ses.getAttribute("admin") == null) {
    response.sendRedirect("index.jsp");
    return;
  }
%>
<jsp:include page="component/head.jsp"/>
<jsp:include page="component/sidebar.jsp"/>
<jsp:include page="component/navbar.jsp"/>
<div class="container-fluid">
  <div class="card">
    <div class="card-body">
      <h5 class="card-title fw-semibold mb-4">Historial de reservas</h5>
      <% if (!reservas.isEmpty()) { %>
      <div class="table-responsive">
        <table class="table" style="text-align: center;">
          <thead>
          <tr>
            <th>Id</th>
            <th>Cliente</th>
            <th>Habitación</th>
            <th>Fecha de ingreso</th>
            <th>Fecha de salida</th>
            <th>Monto pagado</th>
            <th>Método de pago</th>
            <th>Estado</th>
          </tr>
          </thead>
          <tbody>
          <%  for (Reserva reserva : reservas) { %>
            <tr>
                <td><%= reserva.getId_reserva() %></td>
                <td><%= reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido() %></td>
                <td><%= reserva.getHabitacion().getNumero_habitacion() %></td>
                <td><%= reserva.getFecha_ingreso() %></td>
                <td><%= reserva.getFecha_salida() %></td>
                <td>S/<%= reserva.getMonto_total() %></td>
                <td><%= reserva.getMetodo_pago() %></td>
                <td><%= reserva.getEstado_reserva() %></td>
            </tr>
            <% } %>
          </tbody>
        </table>
      </div>
        <% } else { %>
        <div class="alert alert-warning" role="alert">
          No hay reservas registradas
        </div>
        <% } %>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"/>