import React from 'react';
import './Services.css';

const Services = () => {
  const handleNavigation = (sectionId) => {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <section className="services" id="servicios">
      <div className="container">
        <div className="section-title">
          <h2>Servicios Incluidos</h2>
          <p>Todas nuestras habitaciones incluyen estos servicios premium para su comodidad</p>
        </div>
        <div className="services-grid">
          <div className="service-card">
            <div className="service-icon">
              <i className="fas fa-wifi"></i>
            </div>
            <h3>WiFi Gratuito</h3>
            <p>Conexión de alta velocidad en todas las instalaciones para que esté siempre conectado.</p>
          </div>
          <div className="service-card">
            <div className="service-icon">
              <i className="fas fa-shower"></i>
            </div>
            <h3>Agua Caliente</h3>
            <p>Disfrute de agua caliente las 24 horas del día para su máximo confort y relax.</p>
          </div>
          <div className="service-card">
            <div className="service-icon">
              <i className="fas fa-utensils"></i>
            </div>
            <h3>Desayuno Incluido</h3>
            <p>Comience su día con un delicioso desayuno buffet con productos locales y tradicionales.</p>
          </div>
          <div className="service-card">
            <div className="service-icon">
              <i className="fas fa-snowflake"></i>
            </div>
            <h3>Aire Acondicionado</h3>
            <p>Todas nuestras habitaciones cuentan con aire acondicionado para su comodidad.</p>
          </div>
          <div className="service-card">
            <div className="service-icon">
              <i className="fas fa-tv"></i>
            </div>
            <h3>Televisión por Cable</h3>
            <p>Televisores con cable en todas las habitaciones para su entretenimiento.</p>
          </div>
          <div className="service-card">
            <div className="service-icon">
              <i className="fas fa-parking"></i>
            </div>
            <h3>Estacionamiento Gratuito</h3>
            <p>Amplio estacionamiento seguro y gratuito para todos nuestros huéspedes.</p>
          </div>
        </div>
        <div className="services-cta">
          <button onClick={() => handleNavigation('reserva')} className="btn btn-primary">Reservar Ahora</button>
        </div>
      </div>
    </section>
  );
};

export default Services;