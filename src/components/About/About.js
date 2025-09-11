import React from 'react';
import './About.css';

const About = () => {
  return (
    <section className="about">
      <div className="container about-container">
        <div className="about-image">
          <img src="https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80" alt="Hotel Ayapalleck Muchik en Chiclayo" />
        </div>
        <div className="about-content">
          <h2>Nuestra Historia</h2>
          <p>El Hotel Ayapalleck Muchik rinde homenaje a la milenaria cultura Mochica en el corazón de Chiclayo. Nuestro nombre "Ayapalleck" significa "casa de descanso" en la lengua muchik, reflejando nuestro compromiso con su bienestar y comodidad.</p>
          <p>Ubicados estratégicamente en Chiclayo, Lambayeque, ofrecemos servicios de calidad con autenticidad cultural, combinando las tradiciones de la región norte del Perú con las comodidades modernas que nuestros huéspedes merecen.</p>
          <ul className="about-features">
            <li><i className="fas fa-certificate"></i> Hotel 3 estrellas en pleno centro de Chiclayo</li>
            <li><i className="fas fa-history"></i> Inspirado en la milenaria cultura Mochica lambayecana</li>
            <li><i className="fas fa-heart"></i> Atención personalizada al estilo norteño</li>
            <li><i className="fas fa-map-marker-alt"></i> Ubicación privilegiada en Chiclayo, Lambayeque</li>
          </ul>
        </div>
      </div>
    </section>
  );
};

export default About;