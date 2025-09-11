export const scrollFix = () => {
  // Esperar a que el DOM esté completamente cargado
  setTimeout(() => {
    const header = document.querySelector('.header');
    if (!header) return;
    
    const headerHeight = header.offsetHeight;
    
    // Aplicar a todas las secciones con ID
    const sections = document.querySelectorAll('section[id]');
    sections.forEach(section => {
      section.style.scrollMarginTop = `${headerHeight}px`;
    });
    
    // Aplicar al html
    document.documentElement.style.scrollPaddingTop = `${headerHeight}px`;
    
    console.log('Scroll fix aplicado. Header height:', headerHeight);
  }, 100);
};

// Ejecutar al cargar y en resize
if (typeof window !== 'undefined') {
  window.addEventListener('load', scrollFix);
  window.addEventListener('resize', scrollFix);
  window.addEventListener('orientationchange', scrollFix);
}