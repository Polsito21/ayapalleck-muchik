<%@ page import="java.util.ArrayList" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Reserva" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
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
                <h5 style="font-weight: 600; font-size: 18px;">Reservas</h5>
                <a href="<%= request.getContextPath() %>/addReservaRedirect"><button type="button" class="btn btn-success m-1">Registrar nueva reserva</button></a>
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
                        <th>Acción</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Reserva reserva : reservas) { %>
                    <tr>
                        <td><%= reserva.getId_reserva() %></td>
                        <td><%= reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido() %></td>
                        <td><%= reserva.getHabitacion().getNumero_habitacion() %></td>
                        <td><%= reserva.getFecha_ingreso() %></td>
                        <td><%= reserva.getFecha_salida() %></td>
                        <td>S/<%= reserva.getMonto_total() %></td>
                        <td><%= reserva.getMetodo_pago() %></td>
                        <td><%= reserva.getEstado_reserva() %></td>
                        <td class="icons" style="display: flex; justify-content: center; align-items: center; align-content: center;">
                            <a href="<%= request.getContextPath() %>/redirectReserva?reserva_id=<%= reserva.getId_reserva() %>">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="icon" style="width: 21px; margin: 2px;">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                                </svg>
                            </a>
                            <a href=<%= request.getContextPath() %>/deleteReserva?reserva_id=<%= reserva.getId_reserva() %>>
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="icon" style="width: 21px; margin: 2px;">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                                </svg>
                            </a>
                        </td>
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