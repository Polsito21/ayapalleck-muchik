<%@ page import="utp.edu.pe.ayapalleckmuchik.model.enums.Tipo_documento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Tipo_documento> tipos_documento = Tipo_documento.getTipos_documento(); %>
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
            <form method="post" action="<%= request.getContextPath() %>/addCliente">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Ingresa sus nombres</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" aria-describedby="emailHelp" pattern="[A-Za-z\s]+" oninput="validarLetras(event)" required>
                </div>
                <div class="mb-3">
                    <label for="apellido" class="form-label">Ingresa sus apellidos</label>
                    <input type="text" class="form-control" id="apellido" name="apellido" aria-describedby="emailHelp"  pattern="[A-Za-z\s]+" oninput="validarLetras(event)" required>
                </div>
                <div class="mb-3">
                    <label for="tipo_documento" class="form-label">Selecciona el tipo de documento</label>
                    <select class="form-select" id="tipo_documento" name="tipo_documento" required>
                        <option value="" selected>Selecciona un tipo de documento</option>
                        <% for (Tipo_documento tipo_documento : tipos_documento) { %>
                            <option value="<%= tipo_documento %>"><%= tipo_documento.getDisplayName() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="numero_documento" class="form-label">Ingresa el número de documento</label>
                    <input type="number" class="form-control" id="numero_documento" name="numero_documento" aria-describedby="emailHelp" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Ingresa su email</label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required>
                </div>
                <div class="mb-3">
                    <label for="telefono" class="form-label">Ingresa su teléfono</label>
                    <input type="number" class="form-control" id="telefono" name="telefono" aria-describedby="emailHelp" required>
                </div>
                <div class="mb-3">
                    <label for="fecha_nacimiento" class="form-label">Ingresa su fecha de nacimiento</label>
                    <input type="date" class="form-control" id="fecha_nacimiento" name="fecha_nacimiento" aria-describedby="emailHelp" required>
                </div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
            <a href="<%= request.getContextPath() %>/clientes"><button type="submit" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
        </div>
    </div>
</div>
<script>
    function validarLetras(event) {
        const campo = event.target;
        campo.value = campo.value.replace(/[^A-Za-z\s]/g, '');
    }

    document.addEventListener("DOMContentLoaded", function () {
        const tipoDocumento = document.getElementById("tipo_documento");
        const numeroDocumento = document.getElementById("numero_documento");
        const telefono = document.getElementById("telefono");
        const submitBtn = document.getElementById("submitBtn");

        const numeroDocumentoError = document.getElementById("numero_documento_error");
        const telefonoError = document.getElementById("telefono_error");

        function checkErrors() {
            if (numeroDocumentoError.textContent || telefonoError.textContent) {
                submitBtn.disabled = true;
            } else {
                submitBtn.disabled = false;
            }
        }

        function clearErrors() {
            numeroDocumentoError.textContent = "";
            telefonoError.textContent = "";
        }

        function adjustNumeroDocumentoField() {
            numeroDocumento.value = "";
            numeroDocumento.disabled = tipoDocumento.value === "";
            clearErrors();

            if (tipoDocumento.value === "DNI") {
                numeroDocumento.maxLength = 8;
                numeroDocumento.setAttribute("data-minlength", "8");
                numeroDocumentoError.textContent = "Debe tener 8 dígitos.";
            } else if (tipoDocumento.value === "RUC") {
                numeroDocumento.maxLength = 11;
                numeroDocumento.setAttribute("data-minlength", "11");
                numeroDocumentoError.textContent = "Debe tener 11 dígitos.";
            } else if (tipoDocumento.value === "Pasaporte") {
                numeroDocumento.maxLength = 12;
                numeroDocumento.setAttribute("data-minlength", "8");
                numeroDocumentoError.textContent = "Debe tener entre 8 y 12 dígitos.";
            } else if (tipoDocumento.value === "Carnet de Extranjería") {
                numeroDocumento.maxLength = 9;
                numeroDocumento.setAttribute("data-minlength", "9");
                numeroDocumentoError.textContent = "Debe tener 9 dígitos.";
            }
            checkErrors();
        }

        tipoDocumento.addEventListener("change", adjustNumeroDocumentoField);

        numeroDocumento.addEventListener("input", function () {
            const docValue = numeroDocumento.value;
            clearErrors();

            // Validaciones basadas en tipo de documento
            if (tipoDocumento.value === "DNI" && docValue.length !== 8) {
                numeroDocumentoError.textContent = "El DNI debe tener exactamente 8 dígitos.";
            } else if (tipoDocumento.value === "RUC" && docValue.length !== 11) {
                numeroDocumentoError.textContent = "El RUC debe tener exactamente 11 dígitos.";
            } else if (tipoDocumento.value === "Pasaporte" && (docValue.length < 8 || docValue.length > 12)) {
                numeroDocumentoError.textContent = "El Pasaporte debe tener entre 8 y 12 dígitos.";
            } else if (tipoDocumento.value === "Carnet de Extranjería" && docValue.length !== 9) {
                numeroDocumentoError.textContent = "El Carnet de Extranjería debe tener exactamente 9 dígitos.";
            }

            checkErrors();
        });

        telefono.addEventListener("input", function () {
            const telefonoValue = telefono.value;
            telefonoError.textContent = "";

            if (telefonoValue.length !== 9 || telefonoValue[0] !== '9') {
                telefonoError.textContent = "El teléfono debe tener 9 dígitos y comenzar con '9'.";
            }
            checkErrors();
        });

        checkErrors();
        adjustNumeroDocumentoField();
    });
</script>
<jsp:include page="component/footer.jsp"/>