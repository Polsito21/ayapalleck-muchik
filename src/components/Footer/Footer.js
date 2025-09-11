import React from 'react';
import './Footer.css';

const Footer = () => {
  return (
    <footer>
      <div className="container">
        <div className="footer-content">
          <div className="footer-column">
            <h3>Hotel Ayapalleck Muchik</h3>
            <p>Un espacio donde la cultura Mochica se encuentra con la comodidad moderna, ofreciendo una experiencia única en el corazón de Chiclayo, Lambayeque.</p>
            <div className="social-links">
              <a href="#"><i className="fab fa-facebook-f"></i></a>
              <a href="#"><i className="fab fa-instagram"></i></a>
              <a href="#"><i className="fab fa-whatsapp"></i></a>
              <a href="#"><i className="fab fa-tripadvisor"></i></a>
            </div>
          </div>
          <div className="footer-column">
            <h3>Contacto</h3>
            <p><i className="fas fa-map-marker-alt"></i> San José 1084, Chiclayo 14001</p>
            <p><i className="fas fa-phone"></i> +51 944 123 456</p>
            <p><i className="fas fa-envelope"></i> info@ayapalleckmuchik.com</p>
            <p><i className="fas fa-clock"></i> Recepción 24 horas</p>
            <p><i className="fas fa-car"></i> Estacionamiento gratuito</p>
          </div>
          <div className="footer-column">
            <h3>Enlaces Rápidos</h3>
            <ul>
              <li><a href="#inicio">Inicio</a></li>
              <li><a href="#habitaciones">Habitaciones</a></li>
              <li><a href="#servicios">Servicios</a></li>
              <li><a href="#ubicacion">Ubicación</a></li>
              <li><a href="#reserva">Reservas</a></li>
            </ul>
          </div>
          <div className="footer-column">
            <h3>Habitaciones</h3>
            <ul>
              <li><a href="#habitaciones">Habitación Matrimonial - S/ 120</a></li>
              <li><a href="#habitaciones">Habitación Doble - S/ 100</a></li>
              <li><a href="#habitaciones">Habitación Doble Familiar - S/ 180</a></li>
            </ul>
          </div>
        </div>
        <div className="copyright">
          <p>&copy; 2025 Hotel Ayapalleck Muchik. Todos los derechos reservados. | Hotel 3 estrellas en Chiclayo, Lambayeque</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;