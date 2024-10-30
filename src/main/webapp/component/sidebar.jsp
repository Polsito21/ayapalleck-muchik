<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="sidebar-nav scroll-sidebar" data-simplebar="">
  <ul id="sidebarnav">
    <li class="nav-small-cap">
      <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
      <span class="hide-menu">Inicio</span>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%=request.getContextPath()%>/recepcion" aria-expanded="false">
                              <span>
                                  <i class="ti ti-hotel-service"></i>
                              </span>
        <span class="hide-menu">Recepción</span>
      </a>
    </li>
    <li class="nav-small-cap">
      <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
      <span class="hide-menu">Administrar</span>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%=request.getContextPath()%>/reservas" aria-expanded="false">
                              <span>
                                  <i class="ti ti-logout"></i>
                              </span>
        <span class="hide-menu">Reservas</span>
      </a>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%=request.getContextPath()%>/clientes" aria-expanded="false">
                              <span>
                                  <i class="ti ti-users"></i>
                              </span>
        <span class="hide-menu">Clientes</span>
      </a>
    </li>
    <li class="nav-small-cap">
      <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
      <span class="hide-menu">Configuración</span>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%=request.getContextPath()%>/tiposhabitacion" aria-expanded="false">
                              <span>
                                  <i class="ti ti-coin"></i>
                              </span>
        <span class="hide-menu">Tipos de habitaciones</span>
      </a>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%=request.getContextPath()%>/habitaciones" aria-expanded="false">
                              <span>
                                  <i class="ti ti-door"></i>
                              </span>
        <span class="hide-menu">Habitaciones</span>
      </a>
    </li>
    <li class="nav-small-cap">
      <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
      <span class="hide-menu">Reporte</span>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%=request.getContextPath()%>/historial" aria-expanded="false">
                              <span>
                                  <i class="ti ti-history"></i>
                              </span>
        <span class="hide-menu">Historial</span>
      </a>
    </li>
  </ul>
</nav>
<!-- End Sidebar navigation -->
</div>
<!-- End Sidebar scroll-->
</aside>