<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
      <h5 class="card-title fw-semibold mb-4">Error</h5>
      <c:if test="${not empty requestScope.msg}">
        <div class="alert alert-danger" role="alert">
            ${requestScope.msg}
        </div>
      </c:if>
      <a href="<%= request.getContextPath() %>/recepcion"><button class="btn btn-dark">Regresar</button></a>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"/>