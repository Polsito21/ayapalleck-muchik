import React from 'react';
import './Hero.css';

const Hero = () => {
  const handleNavigation = (sectionId) => {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <section className="hero" id="inicio">
      <div className="hero-content">
        <h2>Hotel Ayapalleck Muchik</h2>
        <div className="hero-subtitle">Bienvenido a Tierra Mochica</div>
        <p>Disfrute de una experiencia única en nuestro hotel 3 estrellas en Chiclayo, donde la cultura Mochica se encuentra con la comodidad moderna</p>
        <div className="hero-buttons">
          <button onClick={() => handleNavigation('reserva')} className="btn btn-primary">Reservar Ahora</button>
          <button onClick={() => handleNavigation('habitaciones')} className="btn btn-secondary">Ver Habitaciones</button>
        </div>
      </div>
    </section>
  );
};

export default Hero;