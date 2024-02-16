function validateBD() { // Este deja la BD con los mismos datos almacenados en OAuth0
    $.ajax({
        url: "http://localhost:8080/user",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#nombreCompleto").text(respuesta.nombreCompleto);
        },
        error: function (xhr) {
            if (xhr.status === 404) {
                $("#nombreCompleto").text("❌ El usuario no se encontró...");
                return;
            }
        }
    });
}


function loadUserInfo() { // Este muestra los datos en interface trayéndolos de BD
    validateBD();
    let correoElectronico = "juliandaguzz@gmail.com";
    let nombreCompleto = document.querySelector("#nombreCompleto");
    let fotoPerfil = document.querySelector("#fotoPerfil");
    let auth_id = document.querySelector("#auth_id");
    let rolDeUsuario = document.querySelector("#rolDeUsuario");
    $.ajax({
        url: "http://localhost:8080/user/" + correoElectronico,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#correoElectronico").text("respuesta.nombreCompleto");
        },
        error: function (xhr) {
            if (xhr.status === 404) {
                $("#correoElectronico").text("❌ El usuario no se encontró...");
                return;
            }
        }
    });
}