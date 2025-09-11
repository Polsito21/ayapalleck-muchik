import React, { useEffect, useState } from 'react';
import './Booking.css';

const Booking = () => {
  const [formData, setFormData] = useState({
    checkin: '',
    checkout: '',
    adults: '2',
    children: '0',
    roomType: '',
    name: '',
    dni: '',
    email: '',
    phone: ''
  });

  useEffect(() => {
    // Scripts para el formulario
    const today = new Date();
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);
    
    const checkin = document.getElementById('checkin');
    const checkout = document.getElementById('checkout');
    
    if (checkin && checkout) {
      checkin.min = today.toISOString().split('T')[0];
      checkout.min = tomorrow.toISOString().split('T')[0];
      
      checkin.addEventListener('change', function() {
        const checkinDate = new Date(this.value);
        const newMinDate = new Date(checkinDate);
        newMinDate.setDate(newMinDate.getDate() + 1);
        checkout.min = newMinDate.toISOString().split('T')[0];
        
        if (new Date(checkout.value) < newMinDate) {
          checkout.value = '';
        }
        
        setFormData(prev => ({
          ...prev,
          checkin: this.value
        }));
      });

      checkout.addEventListener('change', function() {
        setFormData(prev => ({
          ...prev,
          checkout: this.value
        }));
      });
    }
  }, []);

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [id]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    alert('¡Gracias por su reserva! Nos pondremos en contacto con usted en un plazo de 24 horas para confirmar su solicitud.');
    // Restablecer formulario
    setFormData({
      checkin: '',
      checkout: '',
      adults: '2',
      children: '0',
      roomType: '',
      name: '',
      dni: '',
      email: '',
      phone: ''
    });
    e.target.reset();
  };

  return (
    <section className="booking" id="reserva">
      <div className="container booking-container">
        <div className="booking-info">
          <h2>Reserva tu Estancia en Chiclayo</h2>
          <p>Complete el formulario para realizar su reserva en nuestro hotel en Chiclayo. Nos pondremos en contacto con usted para confirmar la disponibilidad.</p>
          
          <div className="benefits-container">
            <h3>Beneficios Exclusivos</h3>
            <ul className="benefits-list">
              <li><i className="fas fa-check-circle"></i> Mejor precio garantizado en Chiclayo</li>
              <li><i className="fas fa-check-circle"></i> Sin cargos por reserva</li>
              <li><i className="fas fa-check-circle"></i> Cancelación gratuita</li>
              <li><i className="fas fa-check-circle"></i> Atención personalizada 24/7</li>
              <li><i className="fas fa-check-circle"></i> Precios en Soles Peruanos</li>
            </ul>
          </div>
        </div>
        <div className="booking-form-container">
          <div className="booking-form">
            <div className="form-header">
              <h3>Solicitud de Reserva</h3>
              <p>Complete todos los campos para procesar su solicitud</p>
            </div>
            <form id="reservationForm" onSubmit={handleSubmit}>
              <div className="form-row">
                <div className="form-group">
                  <label htmlFor="checkin">Fecha de Entrada</label>
                  <input 
                    type="date" 
                    id="checkin" 
                    value={formData.checkin}
                    onChange={handleInputChange}
                    required 
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="checkout">Fecha de Salida</label>
                  <input 
                    type="date" 
                    id="checkout" 
                    value={formData.checkout}
                    onChange={handleInputChange}
                    required 
                  />
                </div>
              </div>
              <div className="form-row">
                <div className="form-group">
                  <label htmlFor="adults">Adultos</label>
                  <select 
                    id="adults" 
                    value={formData.adults}
                    onChange={handleInputChange}
                    required
                  >
                    <option value="1">1 Adulto</option>
                    <option value="2">2 Adultos</option>
                    <option value="3">3 Adultos</option>
                    <option value="4">4 Adultos</option>
                  </select>
                </div>
                <div className="form-group">
                  <label htmlFor="children">Niños</label>
                  <select 
                    id="children" 
                    value={formData.children}
                    onChange={handleInputChange}
                  >
                    <option value="0">0 Niños</option>
                    <option value="1">1 Niño</option>
                    <option value="2">2 Niños</option>
                    <option value="3">3 Niños</option>
                  </select>
                </div>
              </div>
              <div className="form-group">
                <label htmlFor="roomType">Tipo de Habitación</label>
                <select 
                  id="roomType" 
                  value={formData.roomType}
                  onChange={handleInputChange}
                  required
                >
                  <option value="">Seleccione una opción</option>
                  <option value="matrimonial">Habitación Matrimonial - S/ 120</option>
                  <option value="doble">Habitación Doble - S/ 100</option>
                  <option value="familiar">Habitación Doble Familiar - S/ 180</option>
                </select>
              </div>
              <div className="form-group">
                <label htmlFor="name">Nombre Completo</label>
                <input 
                  type="text" 
                  id="name" 
                  value={formData.name}
                  onChange={handleInputChange}
                  required 
                />
              </div>
              <div className="form-group">
                <label htmlFor="dni">DNI/Pasaporte</label>
                <input 
                  type="text" 
                  id="dni" 
                  value={formData.dni}
                  onChange={handleInputChange}
                  required 
                />
              </div>
              <div className="form-group">
                <label htmlFor="email">Correo Electrónico</label>
                <input 
                  type="email" 
                  id="email" 
                  value={formData.email}
                  onChange={handleInputChange}
                  required 
                />
              </div>
              <div className="form-group">
                <label htmlFor="phone">Teléfono/Celular</label>
                <input 
                  type="tel" 
                  id="phone" 
                  value={formData.phone}
                  onChange={handleInputChange}
                  required 
                />
              </div>
              <div className="form-submit">
                <button type="submit" className="btn btn-primary">Enviar Solicitud</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Booking;