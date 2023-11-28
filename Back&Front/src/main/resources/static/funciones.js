var isLoggedIn = false;

    // Función para mostrar u ocultar los botones según el estado de inicio de sesión
    function toggleButtons() {
        var iniciarSesionButton = document.getElementById('iniciarSesion');
        var adminButton = document.getElementById('adminButton');
        var tecnicoButton = document.getElementById('tecnicoButton');

        if (isLoggedIn) {
            iniciarSesionButton.style.display = 'none';
            adminButton.style.display = 'inline-block';  // O 'block' según tus preferencias de estilo
            tecnicoButton.style.display = 'inline-block';  // O 'block' según tus preferencias de estilo
        } else {
            iniciarSesionButton.style.display = 'inline-block';  // O 'block' según tus preferencias de estilo
            adminButton.style.display = 'none';
            tecnicoButton.style.display = 'none';
        }
    }

    // Llama a la función al cargar la página (puedes llamarla según eventos de inicio de sesión reales)
    toggleButtons();