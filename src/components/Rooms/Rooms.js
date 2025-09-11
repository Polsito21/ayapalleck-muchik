import React from 'react';
import './Rooms.css';

const Rooms = () => {
  const handleReservation = (roomType) => {
    const element = document.getElementById('reserva');
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
      // Aquí puedes agregar lógica para pre-seleccionar la habitación
    }
  };

  return (
    <section className="rooms" id="habitaciones">
      <div className="container">
        <div className="section-title">
          <h2>Nuestras Habitaciones</h2>
          <p>Confortables espacios diseñados para su descanso en Chiclayo</p>
        </div>
        <div className="rooms-grid">
          <div className="room-card">
            <div className="room-image" style={{backgroundImage: "url('https://images.unsplash.com/photo-1584132967334-10e028bd69f7?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80')"}}></div>
            <div className="room-content">
              <h3>Habitación Matrimonial</h3>
              <ul className="room-features">
                <li><i className="fas fa-bed"></i> 1 cama king size</li>
                <li><i className="fas fa-user"></i> Capacidad: 2 personas</li>
                <li><i className="fas fa-snowflake"></i> Aire acondicionado</li>
                <li><i className="fas fa-bath"></i> Baño privado con agua caliente</li>
                <li><i className="fas fa-tv"></i> TV por cable 32"</li>
                <li><i className="fas fa-wifi"></i> Wi-Fi gratis</li>
              </ul>
              <div className="room-price">S/ 120 / noche</div>
              <button onClick={() => handleReservation('matrimonial')} className="btn">Reservar Ahora</button>
            </div>
          </div>
          
          <div className="room-card">
            <div className="room-image" style={{backgroundImage: "url('https://images.unsplash.com/photo-1631049307264-da0ec9d70304?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80')"}}></div>
            <div className="room-content">
              <h3>Habitación Doble</h3>
              <ul className="room-features">
                <li><i className="fas fa-bed"></i> 2 camas individuales</li>
                <li><i className="fas fa-user"></i> Capacidad: 2 personas</li>
                <li><i className="fas fa-snowflake"></i> Aire acondicionado</li>
                <li><i className="fas fa-bath"></i> Baño privado con agua caliente</li>
                <li><i className="fas fa-tv"></i> TV por cable 32"</li>
                <li><i className="fas fa-wifi"></i> Wi-Fi gratis</li>
              </ul>
              <div className="room-price">S/ 100 / noche</div>
              <button onClick={() => handleReservation('doble')} className="btn">Reservar Ahora</button>
            </div>
          </div>
          
          <div className="room-card">
            <div className="room-image" style={{backgroundImage: "url('https://images.unsplash.com/photo-1566665797739-1674de7a421a?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80')"}}></div>
            <div className="room-content">
              <h3>Habitación Doble Familiar</h3>
              <ul className="room-features">
                <li><i className="fas fa-bed"></i> 2 camas dobles</li>
                <li><i className="fas fa-user"></i> Capacidad: 4 personas</li>
                <li><i className="fas fa-snowflake"></i> Aire acondicionado</li>
                <li><i className="fas fa-bath"></i> Baño privado amplio</li>
                <li><i className="fas fa-tv"></i> TV por cable 40"</li>
                <li><i className="fas fa-wifi"></i> Wi-Fi gratis</li>
                <li><i className="fas fa-couch"></i> Área de estar</li>
              </ul>
              <div className="room-price">S/ 180 / noche</div>
              <button onClick={() => handleReservation('familiar')} className="btn">Reservar Ahora</button>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Rooms;