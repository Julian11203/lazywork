function findById() {
    let errorMessage = document.querySelector('#errormsg');
    errorMessage.innerHTML = '';
    errorMessage.classList.remove('alert-danger');

    let idAConsultar = $("#byid").val();
    let tabla = document.querySelector("#tableid");

    if (idAConsultar === '') {
        errorMessage.classList.add('alert-danger');
        $("#errormsg").text("❌ Ingrese un ID para realizar la consulta");
        return;
    }

    $.ajax({
        url: "http://localhost:8080/cambioestado/" + idAConsultar,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#byid").val("");
            $("#tableid tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.cambioestadoID +
                '</td><td>' + respuesta.descripcion +
                '</td><td>' + respuesta.fechaRegistro +
                '</td><td>' + respuesta.estado.estadoID +
                '</td><td>' + respuesta.incidencia.incidenciaID +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarCambioEstado(\"" + respuesta.cambioEstadoID + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\"" + respuesta.cambioEstadoID + "\")'> <i class='material-icons'>edit</i></a>" +
                '</td></tr>';
        },
        error: function (xhr) {
            if (xhr.status === 404) {
                errorMessage.classList.add('alert-danger');
                $("#errormsg").text("❌ El Id " + idAConsultar + " no se encontró...");
                return;
            }
        }
    })
}

function buscarcambioEstadoIdParametro(nocambioEstado) {
    let idAConsultar = $("#byid").val(nocambioEstado);
    buscarcambioEstadoId();
}

function findAll() {
    let errorMessage = document.querySelector('#errormsg');
    errorMessage.innerHTML = '';
    errorMessage.classList.remove('alert-danger');
    let tabla = document.querySelector("#tableid");
    $.ajax({
        url: "http://localhost:8080/cambioestado/listar",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            console.log(respuesta)
            $("#tableid tbody").remove();
            for (let i = 0; i < respuesta.length; i++) {
                tabla.innerHTML += '<tr><td>' + respuesta[i].cambioEstadoID +
                    '</td><td>' + respuesta[i].descripcion +
                    '</td><td>' + respuesta[i].fechaRegistro +
                    '</td><td>' + respuesta[i].estado.estadoID +
                    '</td><td>' + respuesta[i].incidencia.incidenciaID +
                    '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarCambioEstado(\"" + respuesta[i].cambioEstadoID + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\"" + respuesta[i].cambioEstadoID + "\")'> <i class='material-icons'>edit</i></a>" +
                    '</td></tr>';
            }
        }
    });
}

function save() {
    let errorModal = document.querySelector('#errormodal');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');

    let descripcion = $("#descripcion").val();
    let incidencia = $("#incidencia option:selected").val();
    let estado = $("#estado option:selected").val();

    if (descripcion === '' || incidencia === '' || estado === '') {
        errorModal.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese todos los campos requeridos para ingresar un nuevo Cambio de Estado...");
        return;
    }

    let data = {
        descripcion: descripcion,
        incidencia: {
            incidenciaID: incidencia
        },
        estado: {
            estadoID: estado
        }
    }

    $.ajax({
        url: "http://localhost:8080/cambioestado/crear",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#registrarModal").modal("hide");
            $("#descripcion").val('');
            $("#incidencia").val('');
            $("#estado").val('');
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ El Cambio de Estado ya existe, ingrese otro...");
                return;
            } else {
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ La incidencia " + incidencia + " no existe...");
                return;
            }
        }
    });
}

function update() {
    let errorModal = document.querySelector('#errorAc');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');
    let id = $("#idAC").val();
    let descripcion = $("#descripcionAC").val();
    let incidencia = $("#incidenciaAC option:selected").val();
    let estado = $("#estadoAC option:selected").val();

    if (id === '' || descripcion === '' || incidencia === '' || estado === '') {
        errorModal.classList.add('alert-danger');
        $("#errorAc").text("❌ Ingrese todos los campos requeridos para actualizar un Cambio de Estado...");
        return;
    }

    let data = {
        cambioEstadoID: id,
        descripcion: descripcion,
        incidencia: {
            incidenciaID: incidencia
        },
        estado: {
            estadoID: estado
        }
    }

    $.ajax({
        url: "http://localhost:8080/cambioestado/actualizar/" + id,
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#actualizarModal").modal("hide");
            $("#idAC").val('');
            $("#descripcionAC").val('');
            findAll();
        },
        error: function (xhr) {
            // Handle error here
        }
    })
}

function eliminarCambioEstado(nocambioEstado) {
    console.log(nocambioEstado)
    $("#confirmarEliminacion").off("click").on("click", function () {
        $.ajax({
            url: "http://localhost:8080/cambioestado/eliminar/" + nocambioEstado,
            type: "DELETE",
            success: function () {
                $("#tableid tbody").find("td:contains('" + nocambioEstado + "')").closest("tr").remove();
                $("#exampleModal").modal("hide");
            }
        })
    })
}

function cargarDatos(noCambioEstado) {
    $.ajax({
        url: "http://localhost:8080/cambioestado/" + noCambioEstado,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#idAC").val(respuesta.cambioEstadoID);
            $("#idAC").prop("disabled", true);
            $("#descripcionAC").val(respuesta.descripcion);
        }
    })
}

document.getElementById("byid").addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        buscarcambioEstadoId();
    }
});
