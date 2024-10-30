<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Habitacion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Habitacion> habitaciones;
    if (request.getAttribute("habitaciones") != null) {
        habitaciones = (List<Habitacion>) request.getAttribute("habitaciones");
    } else {
        habitaciones = new ArrayList<>();
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
    <div class="card" style="max-width: none;">
        <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Recepción</h5>
            <% if (!habitaciones.isEmpty()) { %>
            <div class="carts-container">
                <% for (Habitacion habitacion : habitaciones) { %>
                    <% if (habitacion.getEstado().equals("Ocupado")) {%>
                <div class="card" style="min-width: 380px;">
                    <div class="card-header"
                         style="align-content: center; justify-content: center; align-items: center; margin: auto; background-color: white; padding: 0px;">
                        <img src="assets/images/bed-svgrepo-com.svg" alt=""
                             style="width: 90px; padding: 0px;">
                    </div>
                    <div class="card-body"
                         style="padding: 0px; margin: 0px 40px; justify-content: center;  align-content: center; align-items: center;">
                        <h5><%= habitacion.getNumero_habitacion() %></h5>
                        <div class="alert alert-danger" role="alert"
                             style="text-align: center; align-self: center; font-size: 17px;">
                            Ocupado
                        </div>
                        <article style="text-align: center; margin: 30px;">
                            <h4 style="margin-bottom: 15px;"><%= habitacion.getTipo_habitacion().getNombre_habitacion() %></h4>
                            <a href="<%= request.getContextPath() %>/updateState?habitacion_id=<%= habitacion.getId_habitacion() %>&state=<%= habitacion.getEstado() %> "><button type="button" class="btn btn-warning m-1"
                                               style="justify-self: center; margin-bottom: 5px; align-self: center; font-size: 16px;">Terminar</button></a>
                        </article>
                    </div>
                </div>
                    <% } else if (habitacion.getEstado().equals("Libre")) {%>
                <div class="card" style="min-width: 380px;">
                    <div class="card-header"
                         style="align-content: center; justify-content: center; align-items: center; margin: auto; background-color: white; padding: 0px;">
                        <img src="assets/images/bed-svgrepo-com.svg" alt=""
                             style="width: 90px; padding: 0px;">
                    </div>
                    <div class="card-body"
                         style="padding: 0px; margin: 0px 40px; justify-content: center;  align-content: center; align-items: center;">
                        <h5><%= habitacion.getNumero_habitacion() %></h5>
                        <div class="alert alert-success" role="alert"
                             style="text-align: center; align-self: center; font-size: 17px;">
                            Libre
                        </div>
                        <article style="text-align: center; margin: 30px;">
                            <h4 style="margin-bottom: 15px;"><%= habitacion.getTipo_habitacion().getNombre_habitacion() %></h4>
                            <a href="<%= request.getContextPath() %>/updateState?habitacion_id=<%= habitacion.getId_habitacion() %>&state=<%= habitacion.getEstado() %> "><button type="button" class="btn btn-success m-1"
                                               style="justify-self: center; margin-bottom: 5px; align-self: center; font-size: 16px;">Ocupar</button></a>
                        </article>
                    </div>
                </div>
                    <% } else if (habitacion.getEstado().equals("Sucia")) {%>
                <div class="card" style="min-width: 380px;">
                    <div class="card-header"
                         style="align-content: center; justify-content: center; align-items: center; margin: auto; background-color: white; padding: 0px;">
                        <img src="assets/images/bed-svgrepo-com.svg" alt=""
                             style="width: 90px; padding: 0px;">
                    </div>
                    <div class="card-body"
                         style="padding: 0px; margin: 0px 40px; justify-content: center;  align-content: center; align-items: center;">
                        <h5><%= habitacion.getNumero_habitacion() %></h5>
                        <div class="alert alert-warning" role="alert"
                             style="text-align: center; align-self: center; font-size: 17px;">
                            Sucia
                        </div>
                        <article style="text-align: center; margin: 30px;">
                            <h4 style="margin-bottom: 15px;"><%= habitacion.getTipo_habitacion().getNombre_habitacion() %></h4>
                            <a href="<%= request.getContextPath() %>/updateState?habitacion_id=<%= habitacion.getId_habitacion() %>&state=<%= habitacion.getEstado_limpieza() %>"><button type="button" class="btn btn-success m-1"
                                               style="justify-self: center; margin-bottom: 5px; align-self: center; font-size: 16px;">Limpieza</button></a>
                        </article>
                    </div>
                </div>
                    <% } %>
                <% } %>
            </div>
            <% } else { %>
            <div class="alert alert-warning" role="alert">
                No hay habitaciones registradas
            </div>
            <% } %>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>