// Scripts para mostrar el contenido progresivamente
document.addEventListener('DOMContentLoaded', function() {
    const mensajeBienvenida = document.querySelector('.mensaje-bienvenida');
    const imagenBienvenida = document.querySelector('.imagen-bienvenida'); // Obtener la imagen
    mensajeBienvenida.classList.add('mostrar');
    imagenBienvenida.classList.add('mostrar'); // Agregar la clase para mostrar la imagen
});
document.addEventListener('DOMContentLoaded', function() {
    const correo = document.querySelector('.correo');
    correo.classList.add('mostrar');
});
document.addEventListener('DOMContentLoaded', function() {
    const nombre = document.querySelector('.nombre');
    nombre.classList.add('mostrar');
});
document.addEventListener('DOMContentLoaded', function() {
    const rol1 = document.querySelector('.rol1');
    rol1.classList.add('mostrar');
});
document.addEventListener('DOMContentLoaded', function() {
    const rol2 = document.querySelector('.rol2');
    rol2.classList.add('mostrar');
});
document.addEventListener('DOMContentLoaded', function() {
    const contentRole = document.querySelector('.contentRole');
    contentRole.classList.add('mostrar');
});