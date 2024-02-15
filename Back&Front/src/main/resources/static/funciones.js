function findOneById() {
    let nombreUsuario = document.querySelector("#nombreUsuario");
    let idAConsultar = $("#correoElectronico").val();
    if (idAConsultar === '') {
        errorMensaje.classList.add('alert-danger');
        $("#errormsg").text("❌ Ingrese un ID para realizar la consulta");
        return;
    }
    $.ajax({
        url: "http://localhost:8080/findOneById/" + id,
        type: "POST",
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