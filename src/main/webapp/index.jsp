<%@ page import="java.util.List" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.model.Tipo_habitacion" %>
<%@ page import="utp.edu.pe.ayapalleckmuchik.dao.Tipo_habitacionDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Tipo_habitacion> tipoHabitacions = new ArrayList<>();
  try {
    Tipo_habitacionDAO tipoHabitacionDAO = new Tipo_habitacionDAO();
    tipoHabitacions = tipoHabitacionDAO.getTipoHabitaciones();
    tipoHabitacionDAO.close();
  } catch (Exception e) {
    e.printStackTrace();
  }
%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hotel Ayapalleck Muchik</title>
    <link rel="shortcut icon" type="image/png" href="img/icon.webp" />
    <link rel="stylesheet" href="styles.css" />
    <link href="https://fonts.cdnfonts.com/css/konkhmer-sleokchher" rel="stylesheet">
  </head>
  <body>
    <!-- Encabezado -->
    <header>
      <div class="header-content">
        <img src="img/LOGO_REAL.png" alt="Hotel Ayapalleck Muchik Logo" />
        <nav>
          <a href="index.jsp">Inicio</a>
          <a href="#services" class="nav-link">Servicios</a>
          <a href="#rooms" class="nav-link">Habitaciones</a>
          <a href="#ubicacion" class="nav-link">Ubicación</a>
          <a href="#sobre-nosotros" class="nav-link">Sobre Nosotros</a>
        </nav>
      </div>
    </header>

    <style>
      /* Fuente global */
      * {
        font-family: 'Playfair Display', serif;
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }

      /* Fuente específica para elementos generales */
      body {
        font-family: 'Playfair Display', serif;
        background-color: #141414;
        color: #fff;
        transition: background-color 0.3s ease-in-out;
        overflow-x: hidden;
      }

      /* Encabezado */
      .header-content {
        background-color: #ffffff;
        border-bottom: 2px solid #c9a223;
        padding: 20px 40px;
        display: flex;
        align-items: center;
        justify-content: space-between;
      }

      .header-content nav a {
        font-weight: bold;
        color: #000;
        font-size: 17px;
        text-decoration: none;
        margin: 0 15px;
        transition: color 0.3s ease;
      }

      .header-content nav a:hover {
        color: #d4af37;
      }

      .header-content img {
        width: 150px;
        height: auto;
      }

      /* Contenedor de la imagen con overlay */
      .hotel-photo {
        position: relative;
        width: 100%;
        height: 70vh;
        overflow: hidden;
      }

      .hotel-photo img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }

      .hotel-photo .overlay {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
        z-index: 1;
        padding: 0 20px;
      }

      .hotel-photo .overlay h1 {
        font-size: 4rem;
        font-weight: bold;
        color: #ffffff;
        margin-bottom: 10px;
        z-index: 2;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        font-family: 'Konkhmer Sleokchher', sans-serif; /* Aplicar fuente específica */
      }

      .hotel-photo .overlay p {
        font-size: 1.5rem;
        color: #f1c40f;
        z-index: 2;
        text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.6);
        font-family: 'Konkhmer Sleokchher', sans-serif; /* Aplicar fuente específica */
      }

      /* Botón de reserva */
      .reserve-button {
        background: rgba(255, 255, 255, 0.2);
        color: #ffffff;
        border: 2px solid #c9a223;
        padding: 15px 40px;
        border-radius: 10px;
        cursor: pointer;
        font-family: 'Konkhmer Sleokchher', sans-serif; /* Cambiar tipo de letra */
        font-size: 1.5rem;
        text-transform: uppercase;
        font-weight: bold;
        box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.3);
        transition: background 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease;
        text-decoration: none;
        display: inline-block;
        margin-top: 20px;
      }

      .reserve-button:hover {
        background: rgba(255, 255, 255, 0.7);
        transform: scale(1.05);
        box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.4);
      }

      /* Sección Servicios */
      .services {
        background-color: #141414;
        padding: 60px 20px;
        text-align: center;
        border-radius: 10px;
        color: white;
      }

      .services h2 {
        font-size: 32px;
        margin-bottom: 40px;
        color: #d4af37;
        font-family: 'Cinzel', serif;
      }

      .services-list {
        display: flex;
        justify-content: center;
        gap: 40px;
        flex-wrap: wrap;
      }

      .service-item {
        text-align: center;
        margin: 10px;
        transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
        width: 200px;
      }

      .service-item img {
        width: 80px;
        height: 80px;
        margin-bottom: 15px;
        border-radius: 50%;
        border: 2px solid #d4af37;
        transition: transform 0.3s ease-in-out, opacity 0.3s ease-in-out, filter 0.3s ease;
        opacity: 0.8;
        filter: brightness(0) saturate(100%) invert(100%);
      }

      .service-item:hover {
        transform: translateY(-10px);
        box-shadow: 0 10px 15px rgba(212, 175, 55, 0.5);
      }

      .service-item:hover img {
        transform: scale(1.1);
        opacity: 1;
        filter: brightness(0) saturate(100%) invert(100%) brightness(1.2);
      }

      .service-item h3 {
        font-size: 20px;
        margin-bottom: 10px;
        color: #d4af37;
        font-family: 'Cinzel', serif;
      }

      .service-item p {
        font-size: 16px;
        color: #ffffff;
      }

      /* Sección Habitaciones */
      .rooms {
        background-color: #141414;
        padding: 60px 20px;
        text-align: center;
        border-radius: 10px;
      }

      .rooms h2 {
        font-size: 32px;
        margin-bottom: 40px;
        color: #d4af37;
        font-family: 'Cinzel', serif;
      }

      .room-list {
        display: flex;
        justify-content: center;
        gap: 30px;
        flex-wrap: wrap;
        max-width: 1200px;
        margin: 0 auto;
      }

      .room {
        background-color: rgba(34, 33, 36, 0.9);
        padding: 20px;
        border-radius: 10px;
        width: 90%;
        max-width: 400px;
        text-align: center;
        color: white;
        box-shadow: 0px 4px 15px rgba(212, 175, 55, 0.5);
        transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
      }

      .room img {
        width: 100%;
        height: auto;
        border-radius: 10px;
        margin-bottom: 15px;
        border: 2px solid #d4af37;
        transition: transform 0.3s ease-in-out, opacity 0.3s ease-in-out;
        opacity: 0.8;
      }

      .room:hover img {
        transform: translateY(-10px);
        opacity: 1;
      }

      .room h3 {
        font-size: 24px;
        margin-bottom: 10px;
        color: #d4af37;
        font-family: 'Cinzel', serif;
      }

      .room p {
        font-size: 18px;
        margin-bottom: 15px;
        color: #ffffff;
      }

      .room button {
        background-color: #d4af37;
        color: #141414;
        border: none;
        padding: 12px 25px;
        border-radius: 5px;
        cursor: pointer;
        font-family: 'Konkhmer Sleokchher', sans-serif; /* Cambiar tipo de letra */
        font-size: 16px;
        transition: background-color 0.3s ease, transform 0.2s ease;
      }

      .room button:hover {
        background-color: #f1c40f;
        transform: scale(1.05);
      }

      /* Sección Ubicación */
      .ubicacion {
        background-color: #141414;
        padding: 60px 20px;
        text-align: center;
        border-radius: 10px;
      }

      .ubicacion h2 {
        font-size: 32px;
        margin-bottom: 40px;
        color: #d4af37;
        font-family: 'Cinzel', serif;
      }

      .ubicacion-content {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 40px;
        flex-wrap: wrap;
      }

      .ubicacion-content img {
        width: 300px;
        max-width: 100%;
        border-radius: 10px;
        box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.5);
      }

      .ubicacion-content p {
        max-width: 600px;
        font-size: 18px;
        color: #ffffff;
        line-height: 1.6;
      }

      /* Sección Sobre Nosotros */
      .sobre-nosotros {
        background-color: #141414;
        padding: 60px 20px;
        text-align: center;
        border-radius: 10px;
      }

      .sobre-nosotros h2 {
        font-size: 32px;
        margin-bottom: 40px;
        color: #d4af37;
        font-family: 'Cinzel', serif;
      }

      .sobre-nosotros-content {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 40px;
        flex-wrap: wrap;
      }

      .sobre-nosotros-content img {
        width: 300px;
        max-width: 100%;
        border-radius: 10px;
        box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.5);
      }

      .sobre-nosotros-content p {
        max-width: 600px;
        font-size: 18px;
        color: #ffffff;
        line-height: 1.6;
      }

      /* Formulario de Reserva */
      .reserve-form {
        display: none; /* Ocultar por defecto */
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: rgba(255, 255, 255, 0.6); /* Blanco semi-transparente */
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 15px rgba(212, 175, 55, 0.7); /* Sombra dorada */
        z-index: 10000;
        max-width: 400px;
        width: 90%;
        transition: all 0.3s ease;
      }

      .reserve-form h2 {
        text-align: center;
        color: #5b870e;
        margin-bottom: 20px;
        font-family: 'Cinzel', serif;
      }

      .reserve-form input[type="datetime-local"],
      .reserve-form input[type="text"],
      .reserve-form input[type="email"],
      .reserve-form select {
        width: 100%;
        padding: 12px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
        box-sizing: border-box;
        font-family: 'Playfair Display', serif; /* Mantener fuente del cuerpo para campos de entrada */
      }

      .reserve-form label {
        font-size: 16px;
        margin-bottom: 8px;
        display: block;
        color: #141414;
        font-family: 'Cinzel', serif;
      }

      .reserve-form input[type="submit"] {
        background-color: #1fbf06;
        color: white;
        cursor: pointer;
        border: none;
        border-radius: 5px;
        font-family: 'Konkhmer Sleokchher', sans-serif; /* Cambiar tipo de letra */
        font-size: 16px;
        padding: 12px;
        width: 100%;
        transition: background-color 0.3s ease, transform 0.2s ease;
      }

      .reserve-form input[type="submit"]:hover {
        background-color: #218838;
        transform: scale(1.05);
      }

      .reserve-form button.cancel {
        background-color: #ff4d4d;
        color: white;
        border: none;
        padding: 12px;
        margin-top: 10px;
        cursor: pointer;
        border-radius: 5px;
        width: 100%;
        transition: background-color 0.3s ease, transform 0.2s ease;
        font-family: 'Konkhmer Sleokchher', sans-serif; /* Cambiar tipo de letra */
      }

      .reserve-form button.cancel:hover {
        background-color: #e03e3e;
        transform: scale(1.05);
      }

      /* Overlay */
      .overlay-form {
        display: none; /* Se mantiene oculta por defecto */
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.7);
        z-index: 9999;
      }

      /* Modal de Confirmación */
      .confirmation-modal {
        display: none; /* Ocultar por defecto */
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.7);
        z-index: 10001;
        align-items: center;
        justify-content: center;
      }

      .confirmation-content {
        background-color: rgba(255, 255, 255, 0.9); /* Blanco semi-transparente */
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 15px rgba(212, 175, 55, 0.7); /* Sombra dorada */
        max-width: 400px;
        width: 90%;
        text-align: center;
        transition: all 0.3s ease;
      }

      .confirmation-content h2 {
        color: #141414;
        margin-bottom: 20px;
        font-family: 'Cinzel', serif;
      }

      .confirmation-content p {
        color: #333;
        margin-bottom: 20px;
        font-size: 18px;
        font-family: 'Playfair Display', serif; /* Mantener fuente del cuerpo */
      }

      .confirmation-content button {
        background-color: #d4af37;
        color: #141414;
        border: none;
        padding: 10px 25px;
        border-radius: 5px;
        cursor: pointer;
        font-family: 'Konkhmer Sleokchher', sans-serif; /* Cambiar tipo de letra */
        font-size: 16px;
        transition: background-color 0.3s ease, transform 0.2s ease;
      }

      .confirmation-content button:hover {
        background-color: #f1c40f;
        transform: scale(1.05);
      }

      /* Modal de Error */
      .error-modal {
        display: none; /* Ocultar por defecto */
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.7);
        z-index: 10002;
        align-items: center;
        justify-content: center;
      }

      .error-content {
        background-color: rgba(255, 255, 255, 0.9); /* Blanco semi-transparente */
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 15px rgba(255, 77, 77, 0.7); /* Sombra roja */
        max-width: 400px;
        width: 90%;
        text-align: center;
        transition: all 0.3s ease;
      }

      .error-content h2 {
        color: #141414;
        margin-bottom: 20px;
        font-family: 'Cinzel', serif;
      }

      .error-content p {
        color: #333;
        margin-bottom: 20px;
        font-size: 18px;
        font-family: 'Playfair Display', serif; /* Mantener fuente del cuerpo */
      }

      .error-content button {
        background-color: #ff4d4d;
        color: #141414;
        border: none;
        padding: 10px 25px;
        border-radius: 5px;
        cursor: pointer;
        font-family: 'Konkhmer Sleokchher', sans-serif; /* Cambiar tipo de letra */
        font-size: 16px;
        transition: background-color 0.3s ease, transform 0.2s ease;
      }

      .error-content button:hover {
        background-color: #e03e3e;
        transform: scale(1.05);
      }

      /* Responsividad */
      @media (max-width: 768px) {
        .services-list,
        .room-list,
        .ubicacion-content,
        .sobre-nosotros-content {
          flex-direction: column;
          align-items: center;
        }

        .service-item,
        .room,
        .ubicacion-content img,
        .sobre-nosotros-content img {
          width: 80%;
          max-width: 300px;
        }

        .hotel-photo .overlay h1 {
          font-size: 2.5rem;
        }

        .hotel-photo .overlay p {
          font-size: 1.2rem;
        }

        .ubicacion-content p,
        .sobre-nosotros-content p {
          font-size: 16px;
        }

      }

      /* Estilos específicos para el enlace de ubicación */
      .ubicacion-content a.ubicacion-link {
        display: inline-block; /* Asegura que el enlace se ajuste al tamaño de la imagen */
        transition: transform 0.3s ease, opacity 0.3s ease;
        text-decoration: none; /* Elimina el subrayado predeterminado */
        color: inherit; /* Hereda el color del elemento padre */
      }

      .ubicacion-content a.ubicacion-link:hover {
        transform: scale(1.05); /* Efecto de escalado al pasar el cursor */
        opacity: 0.8; /* Reducir la opacidad al pasar el cursor */
      }

      .ubicacion-content a.ubicacion-link img {
        cursor: pointer; /* Cambia el cursor a una mano al pasar sobre la imagen */
        transition: opacity 0.3s ease;
      }

      .ubicacion-content a.ubicacion-link:hover img {
        opacity: 0.8; /* Reduce la opacidad para dar feedback visual */
      }

      /* Asegura que los enlaces fuera del header no tengan subrayados */
      body a {
        text-decoration: none;
        color: inherit;
      }
    </style>

    <!-- Foto del Hotel -->
    <div class="hotel-photo">
      <img src="img/imagen_fondo.png" alt="Fondo Hotel" />
      <div class="overlay">
        <div>
          <h1>Hotel Ayapalleck Muchik</h1>
          <p>Una experiencia inolvidable</p>
          <a href="#rooms" class="reserve-button" onclick="scrollToRooms()">Reservar</a>
        </div>
      </div>
    </div>

    <!-- Servicios -->
    <section class="services" id="services">
      <h2>Servicios</h2>
      <div class="services-list">
        <div class="service-item">
          <img src="img/desayuno.png" alt="Desayuno" />
          <h3>Desayuno</h3>
          <p>Disfruta de un delicioso desayuno incluido en tu estancia.</p>
        </div>
        <div class="service-item">
          <img src="img/wifi.svg" alt="Wifi Gratis" />
          <h3>Wifi Gratuito</h3>
          <p>Conéctate a nuestro servicio de Wifi de alta velocidad en todo el hotel.</p>
        </div>
        <div class="service-item">
          <img src="img/ducha.png" alt="Agua Caliente" />
          <h3>Agua Caliente</h3>
          <p>Disfruta de duchas calientes y confortables en todas nuestras habitaciones.</p>
        </div>
        <div class="service-item">
          <img src="img/tv-con-senal-wifi.png" alt="TV Cable" />
          <h3>TV Cable</h3>
          <p>Relájate con una amplia selección de canales de televisión por cable.</p>
        </div>
      </div>
    </section>

    <!-- Habitaciones y Tarifas -->
    <section class="rooms" id="rooms">
      <h2>Habitaciones y Tarifas</h2>
      <div class="room-list">
        <% if(!tipoHabitacions.isEmpty()) { %>
          <% for(Tipo_habitacion tipoHabitacion : tipoHabitacions) { %>
          <div class="room">
            <img src="img/MATRIMONIAL.jpeg" alt="<%= tipoHabitacion.getNombre_habitacion() %>" />
            <h3><%= tipoHabitacion.getNombre_habitacion() %></h3>
            <p>s/. <%= tipoHabitacion.getPrecio_noche() %> por noche</p>
            <button class="reserve-button" onclick="openReserveForm('<%= tipoHabitacion.getId_tipo_habitacion() %>')">Reservar</button>
          </div>
        <% } %>
        <% } else { %>
          <h2>No hay habitaciones disponibles en este momento.</h2>
        <% } %>
      </div>
    </section>

    <!-- Ubicación -->
    <section class="ubicacion" id="ubicacion">
      <h2>Ubicación</h2>
      <div class="ubicacion-content">
        <a href="https://www.google.com/maps/place/San+José+1084/@-6.7716372,-79.8354914,843m/data=!3m1!1e3!4m7!3m6!1s0x904cef29f9c86b8b:0xee9e85d4bc8fc17b!4b1!8m2!3d-6.7716372!4d-79.8354914!16s%2Fg%2F11hz60z266?entry=ttu&g_ep=EgoyMDI0MTIwMS4xIKXMDSoASAFQAw%3D%3D" target="_blank" class="ubicacion-link">
        <img src="img/Ubicacion.png" alt="Mapa de Ubicación" />
          </a>
        <p>
          <!-- Personaliza este texto según tu ubicación real -->
          Nos encontramos en el corazón de la ciudad, cerca de los principales puntos de interés y con fácil acceso a transporte público. Nuestro hotel ofrece una ubicación privilegiada para que disfrutes al máximo tu estancia.
        </p>
      </div>
    </section>

    <!-- Sobre Nosotros -->
    <section class="sobre-nosotros" id="sobre-nosotros">
      <h2>Sobre Nosotros</h2>
      <div class="sobre-nosotros-content">
        <img src="img/Sobre nostros.jpg" alt="Imagen Sobre Nosotros" />
        <p>
          <!-- Personaliza este texto según la historia y valores de tu hotel -->
          En Hotel Ayapalleck Muchik, nos enorgullece ofrecer un servicio excepcional y una experiencia única a cada uno de nuestros huéspedes. Con años de experiencia en la industria hotelera, nuestro equipo está comprometido con tu comodidad y satisfacción.
        </p>
      </div>
    </section>

    <!-- Capa oscura al abrir formulario -->
    <div class="overlay-form" onclick="closeReserveForm()"></div>

    <!-- Formulario de Reserva -->
    <div class="reserve-form">
      <h2>Reserva Tu Habitación</h2>
      <form id="reservation-form" method="post" action="addSolicitud">
        <input type="hidden" id="id" name="id" placeholder="Tipo de Habitación" readonly><br>
        <input type="text" name="nombre" placeholder="Nombre completo" pattern="[A-Za-záéíóúÁÉÍÓÚ ]+" title="Solo letras y espacios" required><br>
        <input type="email" name="correo" placeholder="Correo electrónico" pattern=".+@.+" title="Debe contener un '@'" required><br>
        <input type="datetime-local" name="fechaIngreso" required>
        <select name="duracion" id="duration" required>|
          <option value="24">1 día</option>
          <option value="48">2 día</option>
        </select>
        <input type="submit" value="Confirmar Reserva">
        <button type="button" class="cancel" onclick="closeReserveForm()">Cancelar</button>
      </form>
    </div>

    <!-- Modal de Confirmación -->
    <div class="confirmation-modal" id="confirmation-modal">
      <div class="confirmation-content">
        <h2>Reserva Confirmada</h2>
        <p>Su reserva se ha realizado exitosamente. Nos pondremos en contacto con usted pronto.</p>
        <button onclick="closeConfirmation()">Aceptar</button>
      </div>
    </div>

    <!-- Modal de Error -->
    <div class="error-modal" id="error-modal">
      <div class="error-content">
        <h2>Error</h2>
        <p id="error-message">Por favor, complete todos los campos correctamente.</p>
        <button onclick="closeErrorModal()">Aceptar</button>
      </div>
    </div>

    <div style="display: flex; justify-content: center; padding-bottom: 50px;">
      <a href="login.jsp">
        <button class="reserve-button">Ingresar</button>
      </a>
    </div>

    <script>
      // Función para redirigir a la sección Habitaciones y Tarifas
      function scrollToRooms() {
        const roomsSection = document.getElementById('rooms');
        roomsSection.scrollIntoView({ behavior: 'smooth' });
      }

      // Función para abrir el formulario de reserva con el tipo de habitación seleccionada
      function openReserveForm(roomType) {
        document.querySelector('.overlay-form').style.display = 'block';
        document.querySelector('.reserve-form').style.display = 'block';
        document.getElementById('id').value = roomType;  // Establecer el tipo de habitación
      }

      // Función para cerrar el formulario de reserva
      function closeReserveForm() {
        document.querySelector('.overlay-form').style.display = 'none';
        document.querySelector('.reserve-form').style.display = 'none';
      }

      // Función para abrir el modal de confirmación
      function openConfirmation() {
        document.getElementById('confirmation-modal').style.display = 'flex';
      }

      // Función para cerrar el modal de confirmación
      function closeConfirmation() {
        document.getElementById('confirmation-modal').style.display = 'none';
      }

      // Función para abrir el modal de error
      function openErrorModal(message) {
        const errorModal = document.getElementById('error-modal');
        if (errorModal) {
          document.getElementById('error-message').innerText = message;
          errorModal.style.display = 'flex';
        }
      }

      // Función para cerrar el modal de error
      function closeErrorModal() {
        const errorModal = document.getElementById('error-modal');
        if (errorModal) {
          errorModal.style.display = 'none';
        }
      }

      // Función para validar el formulario antes de enviarlo
      function validateForm() {
        const form = document.getElementById('reservation-form');
        if (form.checkValidity()) {
          // Validación adicional para confirmar que el correo contiene '@'
          const correo = form.correo.value;
          if (!correo.includes('@')) {
            openErrorModal("El correo electrónico debe contener un '@'.");
            return false;
          }

          // Mostrar el modal de confirmación en lugar de alert
          openConfirmation();
          closeReserveForm(); // Cierra el formulario después de la confirmación
          return false; // Evita el envío real del formulario para fines de demostración
        } else {
          openErrorModal("Por favor, complete todos los campos correctamente.");
          return false; // No permite el envío del formulario
        }
      }

      // Función para manejar el efecto de deslizamiento al hacer clic en enlaces del menú
      document.querySelectorAll('.nav-link').forEach(link => {
        link.addEventListener('click', function(e) {
          e.preventDefault();
          const targetId = this.getAttribute('href').substring(1);
          const targetSection = document.getElementById(targetId);
          if (targetSection) {
            targetSection.scrollIntoView({ behavior: 'smooth' });
          }
        });
      });

      // Cerrar los modales si se hace clic fuera del área del contenido
      document.querySelector('.overlay-form').addEventListener('click', function(event) {
        if (event.target === this) {
          closeReserveForm();
        }
      });

      document.getElementById('confirmation-modal').addEventListener('click', function(event) {
        if (event.target === this) {
          closeConfirmation();
        }
      });

      document.getElementById('error-modal').addEventListener('click', function(event) {
        if (event.target === this) {
          closeErrorModal();
        }
      });
    </script>
  </body>
</html>
