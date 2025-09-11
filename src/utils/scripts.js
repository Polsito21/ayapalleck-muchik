export const initFormScripts = () => {
  // Establecer fechas mínimas para los inputs de fecha
  const today = new Date();
  const tomorrow = new Date(today);
  tomorrow.setDate(tomorrow.getDate() + 1);
  
  const checkin = document.getElementById('checkin');
  const checkout = document.getElementById('checkout');
  
  if (checkin && checkout) {
    checkin.min = today.toISOString().split('T')[0];
    checkout.min = tomorrow.toISOString().split('T')[0];
    
    // Validar que la fecha de salida sea posterior a la de entrada
    checkin.addEventListener('change', function() {
      const checkinDate = new Date(this.value);
      const newMinDate = new Date(checkinDate);
      newMinDate.setDate(newMinDate.getDate() + 1);
      checkout.min = newMinDate.toISOString().split('T')[0];
      
      if (new Date(checkout.value) < newMinDate) {
        checkout.value = '';
      }
    });
  }
  
  // Efecto de desplazamiento suave para los enlaces
  document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
      e.preventDefault();
      
      const targetId = this.getAttribute('href');
      if (targetId === '#') return;
      
      const targetElement = document.querySelector(targetId);
      if (targetElement) {
        window.scrollTo({
          top: targetElement.offsetTop - 100,
          behavior: 'smooth'
        });
      }
    });
  });
};

export const handleFormSubmit = (e) => {
  e.preventDefault();
  alert('¡Gracias por su reserva! Nos pondremos en contacto con usted en un plazo de 24 horas para confirmar su solicitud.');
  e.target.reset();
};