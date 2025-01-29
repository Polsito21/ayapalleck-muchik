<%@ page import="java.util.ArrayList" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Reserva" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Solicitud" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Solicitud> solicitudes;
  if (request.getAttribute("solicitudes") != null) {
    solicitudes = (List<Solicitud>) request.getAttribute("solicitudes");
    Collections.reverse(solicitudes);
  } else {
    solicitudes = new ArrayList<>();
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
        <h5 style="font-weight: 600; font-size: 18px;">Solicitudes</h5>
        </div>
      <% if (!solicitudes.isEmpty()) { %>
      <div class="table-responsive">
        <table class="table" style="text-align: center;">
          <thead>
          <tr>
            <th>Id</th>
            <th>Tipo habitación</th>
            <th>Fecha ingreso</th>
            <th>Fecha solicitud</th>
            <th>Nombre reserva</th>
            <th>Correo</th>
            <th>Duracion</th>
            <th>Estado</th>
          </tr>
          </thead>
          <tbody>
          <% for (Solicitud solicitud : solicitudes) { %>
          <tr>
            <td><%= solicitud.getId_solicitud() %></td>
            <td><%= solicitud.getTipoHabitacion().getNombre_habitacion() %></td>
            <td><%= solicitud.getFecha_solicitud() %></td>
            <td><%= solicitud.getFecha_reserva() %></td>
            <td><%= solicitud.getNombre_cliente() %></td>
            <td><%= solicitud.getCorreo() %></td>
            <td><%= solicitud.getDuracion() %></td>
            <td><%= solicitud.getEstado().getDisplayName() %></td>
          </tr>
          <% } %>
          </tbody>
        </table>
      </div>
      <% } else { %>
      <div class="alert alert-warning" role="alert">
        No hay solicitudes pendientes
      </div>
      <% } %>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"/>