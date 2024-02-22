function mostrarInfoAlUsuario() {
    $.ajax({
        url: "/user",
        type: "GET",
        success: function(data) {
            var GestionUsuarioNoLogeado = document.querySelector("#GestionUsuarioNoLogeado");
            var gestionUsuarioLogeado = document.querySelector("#GestionUsuarioLogeado");
            var gestionUsuario = document.querySelector("#GestionUser");
            var gestionAdministrador = document.querySelector("#GestionAdmin");
            var iniciarSesionBtn = document.querySelector("#iniciarSesionBtn");
            var cerrarSesionBtn = document.querySelector("#cerrarSesionBtn");
            var nombreCompleto = document.querySelector("#nombreCompleto");

            if (data != null) {
                $("#nombreCompleto").html(data.nombreCompleto);
                $("#correoElectronico").html(data.correoElectronico);
                $("#rolDeUsuario").html(data.rolDeUsuario);
                if (data.rolDeUsuario == 'USER' || data.rolDeUsuario == 'ADMIN') {
                    iniciarSesionBtn.style.display = 'none';
                    if (data.rolDeUsuario == 'USER') {
                        gestionAdministrador.style.display = 'none';
                    } else if (data.rolDeUsuario == 'ADMIN') {
                        gestionUsuario.style.display = 'none';
                    }
                } else {
                    GestionUsuarioLogeado.style.display = 'none';
                }
            }
        },
        error: function(xhr, status, error) {
            console.error("Error en la solicitud AJAX: " + error);
        }
    });
}

function eliminarMiUsuario() {
    var correoElectronico = document.querySelector("ppp");
    var mensaje = document.querySelector("#mensaje");
    $("#mensaje").html(correoElectronico);
    $.ajax({
        url: "http://localhost:8080/user/" + correoElectronico,
        type: "DELETE",
        success: function(data) {
//            $("#mensaje").html(data.correoElectronico);
        }
    });
}