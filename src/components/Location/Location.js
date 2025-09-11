import React from 'react';
import './Location.css';

const Location = () => {
  return (
    <section className="location" id="ubicacion">
      <div className="container">
        <div className="section-title">
          <h2>Nuestra Ubicación</h2>
          <p>En el corazón de Chiclayo</p>
        </div>
        
        <div className="location-container">
          <div className="map-container">
            <iframe 
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3969.262316496072!2d-79.84433692505985!3d-6.774096666430911!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x904ceee0c5dbeea7%3A0x4dc0fedfed7e8e1d!2sSan%20Jos%C3%A9%201084%2C%20Chiclayo%2014001!5e0!3m2!1ses!2spe!4v1700000000000!5m2!1ses!2spe"
              allowFullScreen="" 
              loading="lazy"
              title="Ubicación del Hotel Ayapalleck Muchik"
            ></iframe>
          </div>
          
          <div className="info-container">
            <div className="info-card">
              <div className="card-header">
                <i className="fas fa-map-marker-alt"></i>
                <h3>Dirección</h3>
              </div>
              <p>San José 1084, Chiclayo 14001</p>
            </div>
            
            <div className="info-card">
              <div className="card-header">
                <i className="fas fa-phone"></i>
                <h3>Teléfono</h3>
              </div>
              <p>+51 944 123 456</p>
            </div>
            
            <div className="info-card">
              <div className="card-header">
                <i className="fas fa-envelope"></i>
                <h3>Email</h3>
              </div>
              <p>reservas@ayapalleckmuchik.com</p>
            </div>
            
            <div className="info-card">
              <div className="card-header">
                <i className="fas fa-clock"></i>
                <h3>Horario</h3>
              </div>
              <p>Recepción 24/7</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Location;