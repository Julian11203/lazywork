function buscarInicioPorId() {
    let errorMensaje = document.querySelector('#errormsg');
    errorMensaje.innerHTML = '';
    errorMensaje.classList.remove('alert-danger');
    let idAConsultar = $("#byid").val();
    let tabla = document.querySelector("#tableid");
    if (idAConsultar === '') {
        errorMensaje.classList.add('alert-danger');
        $("#errormsg").text("❌ Ingrese un ID para realizar la consulta");
        return;
    }
    $.ajax({
        url: "http://localhost:8080/api/inicio/" + idAConsultar,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#byid").val("");
            $("#tableid tbody").empty();
            tabla.innerHTML += '<tr><td>' + respuesta.idInicio +
                '</td><td>' + respuesta.hora +
                '</td><td>' + respuesta.fecha +
                '</td><td>' + respuesta.usuario.nombre + ' ' + respuesta.usuario.apellido +
                '</td><td>' + `<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarInicio("${respuesta.idInicio}")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatosInicio("${respuesta.idInicio}")'> <i class='material-icons'>edit</i></a>` +
                '</td></tr>';
        },
        error: function (xhr) {
            if (xhr.status === 404) {
                errorMensaje.classList.add('alert-danger');
                $("#errormsg").text("❌ El ID " + idAConsultar + " no se encontró...");
                return;
            }
        }
    });
}

function listarInicios() {
    let errorMensaje = document.querySelector('#errormsg');
    errorMensaje.innerHTML = '';
    errorMensaje.classList.remove('alert-danger');
    let tabla = document.querySelector("#tableid");
    $.ajax({
        url: "http://localhost:8080/api/inicio/listar",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tableid tbody").empty();
            for (let i = 0; i < respuesta.length; i++) {
                tabla.innerHTML += '<tr><td>' + respuesta[i].idInicio +
                    '</td><td>' + respuesta[i].hora +
                    '</td><td>' + respuesta[i].fecha +
                    '</td><td>' + respuesta[i].usuario.nombre + ' ' + respuesta[i].usuario.apellido +
                    '</td><td>' + `<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarInicio("${respuesta[i].idInicio}")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatosInicio("${respuesta[i].idInicio}")'> <i class='material-icons'>edit</i></a>` +
                    '</td></tr>';
            }
        },
        error: function(xhr) {
            // Handle errors if needed
        }
    });
}

function insertarInicio() {
    let errorModal = document.querySelector('#errormodal');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');

    // Obtener los datos del formulario
    let id = $("#id").val();
    let anio = $("#anio").val();
    let mes = $("#mes").val();
    let dia = $("#dia").val();
    let hora = $("#hora").val();
    let usuarioId = $("#usuarioId").val();

    // Crear un objeto con los datos
    let data = {
        idInicio: id,
        anio: anio,
        mes: mes,
        dia: dia,
        hora: hora,
        usuarioId: usuarioId
    };

    $.ajax({
        url: "http://localhost:8080/api/inicio/insertar",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            // Limpiar los campos del formulario
            $("#id").val('');
            $("#anio").val('');
            $("#mes").val('');
            $("#dia").val('');
            $("#hora").val('');
            $("#usuarioId").val('');

            // Cerrar el modal
            $('#registrarModal').modal('hide');
            
            // Puedes mostrar un mensaje de éxito si lo deseas
        },
        error: function (xhr) {
            // Mostrar un mensaje de error en caso de fallo
            errorModal.classList.add('alert-danger');
            errorModal.textContent = "❌ Error al insertar el inicio.";
        }
    });
}
function ActualizarInicio() {
    let errorModal = document.querySelector('#errormodal');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');

    // Obtener los datos del formulario
    let id = $("#idAC").val();
    let anio = $("#anioAC").val();
    let mes = $("#mesAC").val();
    let dia = $("#diaAC").val();
    let hora = $("#horaAC").val();
    let usuarioId = $("#usuarioIdAC").val();

    // Crear un objeto con los datos
    let data = {
        idInicio: id,
        anio: anio,
        mes: mes,
        dia: dia,
        hora: hora,
        usuarioId: usuarioId
    };

    $.ajax({
        url: "http://localhost:8080/api/inicio/actualizar/" + id,
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            // Limpiar los campos del formulario
            $("#idAC").val('');
            $("#anioAC").val('');
            $("#mesAC").val('');
            $("#diaAC").val('');
            $("#horaAC").val('');
            $("#usuarioIdAC").val('');

            // Cerrar el modal
            $('#actualizarModal').modal('hide');
            
            // Puedes mostrar un mensaje de éxito si lo deseas
        },
        error: function (xhr) {
            // Mostrar un mensaje de error en caso de fallo
            errorModal.classList.add('alert-danger');
            errorModal.textContent = "❌ Error al actualizar el inicio.";
        }
    });
}


function eliminarInicio(id) {
    let errorModal = document.querySelector('#errormodal');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');
    $.ajax({
        url: "http://localhost:8080/api/inicio/eliminar/" + id,
        type: "DELETE",
        success: function () {
            // Aquí puedes realizar alguna acción después de eliminar el inicio, como recargar la lista de inicios.
            listarInicios();
        },
        error: function (xhr) {
            // Manejar errores si es necesario.
            errorModal.classList.add('alert-danger');
            errorModal.textContent = "❌ Error al eliminar el inicio.";
        }
    });
}