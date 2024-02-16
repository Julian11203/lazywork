function guardarDatosDeOAuth0ABaseDeDatos() { // Este deja la BD con los mismos datos almacenados en OAuth0
    $.ajax({
        url: "http://localhost:8080/user",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            mostrarInfoDelUsuario();
        },
        error: function (xhr) {
            if (xhr.status === 404) {
                $("#mensaje").text("❌ Error al guardar datos de Auth0 a base de datos");
                return;
            }
        }
    });
}


function mostrarInfoDelUsuario() { // Este muestra los datos en interface trayéndolos de BD y no de Auth0
    let correoElectronico = "prueba@gmail.com";
    let nombreCompleto = document.querySelector("#nombreCompleto");
    let rolDeUsuario = document.querySelector("#rolDeUsuario");
    $.ajax({
        url: "http://localhost:8080/user/" + correoElectronico,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#mensaje").text("Mostrando información del usuario");
            $("#correoElectronico").text(respuesta.correoElectronico);
            $("#nombreCompleto").text(respuesta.nombreCompleto);
            $("#rolDeUsuario").text(respuesta.rolDeUsuario); // respuesta.{dato} no lo trae de Auth0 sino de la BD
        },
        error: function (xhr) {
            if (xhr.status === 404) {
                $("#mensaje").text("❌ Error al mostrar la información del usuario");
                return;
            }
        }
    });
}