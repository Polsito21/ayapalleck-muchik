import React, { useState } from 'react';
import { useScroll } from '../../hooks/useScroll';
import './Header.css';

const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const scrolled = useScroll();

  const handleNavigation = (sectionId) => {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
    setIsMenuOpen(false);
  };

  return (
    <header className={`header ${scrolled ? 'scrolled' : ''}`}>
      <div className="container header-content">
        <div className="logo">
          <h1>Hotel <span>Ayapalleck Muchik</span></h1>
        </div>
        
        <nav className={`nav ${isMenuOpen ? 'active' : ''}`}>
          <ul className="nav-links">
            <li><button onClick={() => handleNavigation('inicio')} className="nav-link-btn">Inicio</button></li>
            <li><button onClick={() => handleNavigation('habitaciones')} className="nav-link-btn">Habitaciones</button></li>
            <li><button onClick={() => handleNavigation('servicios')} className="nav-link-btn">Servicios</button></li>
            <li><button onClick={() => handleNavigation('ubicacion')} className="nav-link-btn">Ubicación</button></li>
            <li><button onClick={() => handleNavigation('reserva')} className="btn-reserva">Reservar</button></li>
          </ul>
        </nav>

        <button 
          className="menu-toggle"
          onClick={() => setIsMenuOpen(!isMenuOpen)}
          aria-label="Toggle menu"
        >
          <span></span>
          <span></span>
          <span></span>
        </button>
      </div>
    </header>
  );
};

export default Header;