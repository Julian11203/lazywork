//function mostrarInfoDelUsuario() { // Este muestra los datos en interface trayéndolos de BD y no de Auth0
//    let correoElectronico = $("#correoElectronico").val();
//    $.ajax({
//        url: "http://localhost:8080/user/" + correoElectronico,
//        type: "GET",
//        dataType: "json",
//        success: function (respuesta) {
//            $("#mensaje").text("Mostrando información del usuario");
//            $("#correoElectronico").text(respuesta.correoElectronico);
//            $("#nombreCompleto").text(respuesta.nombreCompleto);
//            $("#rolDeUsuario").text(respuesta.rolDeUsuario); // respuesta.{dato} no lo trae de Auth0 sino de la BD
//        },
//        error: function (xhr) {
//            if (xhr.status === 404) {
//                $("#mensaje").text("❌ Error al mostrar la información del usuario");
//                return;
//            }
//        }
//    });
//}