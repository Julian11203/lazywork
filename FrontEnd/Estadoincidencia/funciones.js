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
        url: "http://localhost:8080/estados/" + idAConsultar,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#byid").val("");
            $("#tableid tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.estadoID +
                '</td><td>' + respuesta.tipoEstado +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarEstadoIncidencia(\"" + respuesta.estadoID + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\"" + respuesta.estadoID + "\")'> <i class='material-icons'>edit</i></a>" +
                '</td></tr>';
        },
        error: function (xhr) {
            if (xhr.status === 404) {
                errorMessage.classList.add('alert-danger');
                $("#errormsg").text("❌ El Id " + idAConsultar + " no se encontró...");
                return;
            }
        }
    });
}

function buscarEstadoIncidenciaIdParametro(noEstadoIncidencia) {
    let idAConsultar = $("#byid").val(noEstadoIncidencia);
    findById();
}

function findAll() {
    let errorMessage = document.querySelector('#errormsg');
    errorMessage.innerHTML = '';
    errorMessage.classList.remove('alert-danger');
    let tabla = document.querySelector("#tableid");
    $.ajax({
        url: "http://localhost:8080/estados/listar",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tableid tbody").remove();
            for (let i = 0; i < respuesta.length; i++) {
                tabla.innerHTML += '<tr><td>' + respuesta[i].estadoID +
                    '</td><td>' + respuesta[i].tipoEstado +
                    '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarEstadoIncidencia(\"" + respuesta[i].estadoID + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\"" + respuesta[i].estadoID + "\")'> <i class='material-icons'>edit</i></a>" +
                    '</td></tr>';
            }
        }
    });
}

function save() {
    let errorModal = document.querySelector('#errormodal');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');

    let tipoEstado = $("#tipoEstado").val();

    if (tipoEstado === '') {
        errorModal.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese el Tipo de Estado");
        return;
    }

    let data = {
        tipoEstado: tipoEstado
    }

    $.ajax({
        url: "http://localhost:8080/estados/crear",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#registrarModal").modal("hide");
            $("#tipoEstado").val('');
            findAll();
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ El Tipo de Estado ya existe, ingrese otro...");
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
    let tipoEstado = $("#tipoEstadoAC").val();

    if (id=== '' || tipoEstado === '') {
        errorModal.classList.add('alert-danger');
        $("#errorAc").text("❌ Ingrese todos los campos requeridos para actualizar el Tipo de Estado...");
        return;
    }

    let data = {
        estadoID: id,
        tipoEstado: tipoEstado
    }

    $.ajax({
        url: "http://localhost:8080/estados/actualizar/" + id,
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#actualizarModal").modal("hide");
            $("#idAC").val('');
            $("#tipoEstadoAC").val('');
            findAll();
        },
        error: function (xhr) {
            // Handle error here
        }
    });
}

function eliminarEstadoIncidencia(noEstadoIncidencia) {
    console.log(noEstadoIncidencia)
    $("#confirmarEliminacion").off("click").on("click", function () {
        $.ajax({
            url: "http://localhost:8080/estados/eliminar/" + noEstadoIncidencia,
            type: "DELETE",
            success: function () {
                $("#tableid tbody").find("td:contains('" + noEstadoIncidencia + "')").closest("tr").remove();
                $("#exampleModal").modal("hide");
            }
        });
    });
}

function cargarDatos(noEstadoIncidencia) {
    $.ajax({
        url: "http://localhost:8080/estados/" + noEstadoIncidencia,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#estadoIDAC").val(respuesta.estadoID);
            $("#estadoIDAC").prop("disabled", true);
            $("#tipoEstadoAC").val(respuesta.tipoEstado);
        }
    });
}

document.getElementById("byid").addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        findById();
    }
});


