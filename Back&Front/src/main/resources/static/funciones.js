function getGivenName() {
    let nombreUsuario = document.querySelector("#nombreUsuario");
    $.ajax({
        url: "http://localhost:8080/user",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#nombreUsuario").text(respuesta.nombreUsuario);
        },
        error: function (xhr) {
            if (xhr.status === 404) {
                errorMensaje.classList.add('alert-danger');
                $("#nombreUsuario").text("❌ El usuario no se encontró...");
                return;
            }
        }
    });
}