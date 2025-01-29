<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Reserva" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Reserva> reservas;
  if (request.getAttribute("reservas") != null) {
    reservas = (List<Reserva>) request.getAttribute("reservas");
    Collections.reverse(reservas);
  } else {
    reservas = new ArrayList<>();
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
      <div style="display: flex; justify-content: space-between; align-items: center; align-content: center; padding: 10px 20px;">
        <h5 style="font-weight: 600; font-size: 18px;">Historial de reservas</h5>
        <a href="exportExcel">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6" style="width: 40px; padding: 8px; color: #000;">
            <path stroke-linecap="round" stroke-linejoin="round" d="M3 16.5v2.25A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75V16.5M16.5 12 12 16.5m0 0L7.5 12m4.5 4.5V3" />
          </svg>
        </a>
      </div>
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