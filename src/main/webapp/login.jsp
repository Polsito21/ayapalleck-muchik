<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Ayapalleck-muchik</title>
  <link rel="shortcut icon" type="image/png" href="img/icon.webp" />
  <link rel="stylesheet" href="assets/css/styles.min.css" />
</head>

<body>
<!--  Body Wrapper -->
<div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
     data-sidebar-position="fixed" data-header-position="fixed">
  <div
          class="position-relative overflow-hidden radial-gradient min-vh-100 d-flex align-items-center justify-content-center">
    <div class="d-flex align-items-center justify-content-center w-100">
      <div class="row justify-content-center w-100">
        <div class="col-md-8 col-lg-6 col-xxl-3">
          <div class="card mb-0">
            <div class="card-body">
              <a href="login.jsp" class="text-nowrap logo-img text-center d-block py-3 w-100">
                <img src="img/logo-sin-fondo.webp" width="150" alt="" style="margin-top: 20px; margin-left: 20px;" />
              </a>
              <form method="post" action="${pageContext.request.contextPath}/login">
                <div class="mb-3">
                  <label for="username" class="form-label">Usuario</label>
                  <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-4">
                  <label for="password" class="form-label">Contraseña</label>
                  <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block" style="width: 100%;">Ingresar</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="assets/libs/jquery/dist/jquery.min.js"></script>
<script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>