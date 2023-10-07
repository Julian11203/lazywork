function findPrioridadById() {
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
        url: "http://localhost:8080/prioridades/" + idAConsultar,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#byid").val("");
            $("#tableid tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.prioridadID +
                '</td><td>' + respuesta.tipoPrioridad +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarPrioridad(\"" + respuesta.prioridadID + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatosPrioridad(\"" + respuesta.prioridadID + "\")'> <i class='material-icons'>edit</i></a>" +
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

function buscarPrioridadByIdParametro(noPrioridad) {
    let idAConsultar = $("#byid").val(noPrioridad);
    findPrioridadById();
}

function findAllPrioridades() {
    let errorMessage = document.querySelector('#errormsg');
    errorMessage.innerHTML = '';
    errorMessage.classList.remove('alert-danger');
    let tabla = document.querySelector("#tableid");
    $.ajax({
        url: "http://localhost:8080/prioridades/listar",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tableid tbody").remove();
            for (let i = 0; i < respuesta.length; i++) {
                tabla.innerHTML += '<tr><td>' + respuesta[i].prioridadID +
                    '</td><td>' + respuesta[i].tipoPrioridad +
                    '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarPrioridad(\"" + respuesta[i].prioridadID + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatosPrioridad(\"" + respuesta[i].prioridadID + "\")'> <i class='material-icons'>edit</i></a>" +
                    '</td></tr>';
            }
        }
    });
}

function savePrioridad() {
    let errorModal = document.querySelector('#errormodal');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');

    let tipoPrioridad = $("#tipoPrioridad").val();

    if (tipoPrioridad === '') {
        errorModal.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese el Tipo de Prioridad");
        return;
    }

    let data = {
        tipoPrioridad: tipoPrioridad
    }

    $.ajax({
        url: "http://localhost:8080/prioridades/crear",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#registrarModal").modal("hide");
            $("#tipoPrioridad").val('');
            findAllPrioridades();
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ El Tipo de Prioridad ya existe, ingrese otro...");
                return;
            }
        }
    });
}

function updatePrioridad() {
    let errorModal = document.querySelector('#errorAc');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');
    let id = $("#idAC").val();
    let tipoPrioridad = $("#tipoPrioridadAC").val();

    if (id === '' || tipoPrioridad === '') {
        errorModal.classList.add('alert-danger');
        $("#errorAc").text("❌ Ingrese todos los campos requeridos para actualizar el Tipo de Prioridad...");
        return;
    }

    let data = {
        prioridadID: id,
        tipoPrioridad: tipoPrioridad
    }

    $.ajax({
        url: "http://localhost:8080/prioridades/actualizar/" + id,
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#actualizarModal").modal("hide");
            $("#idAC").val('');
            $("#tipoPrioridadAC").val('');
            findAllPrioridades();
        },
        error: function (xhr) {
            // Handle error here
        }
    });
}

function eliminarPrioridad(noPrioridad) {
    console.log(noPrioridad)
    $("#confirmarEliminacion").off("click").on("click", function () {
        $.ajax({
            url: "http://localhost:8080/prioridades/eliminar/" + noPrioridad,
            type: "DELETE",
            success: function () {
                $("#tableid tbody").find("td:contains('" + noPrioridad + "')").closest("tr").remove();
                $("#exampleModal").modal("hide");
            }
        });
    });
}

function cargarDatosPrioridad(noPrioridad) {
    $.ajax({
        url: "http://localhost:8080/prioridades/" + noPrioridad,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#idAC").val(respuesta.prioridadID);
            $("#prioridadIDAC").prop("disabled", true);
            $("#tipoPrioridadAC").val(respuesta.tipoPrioridad);
        }
    });
}

document.getElementById("byid").addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        findPrioridadById();
    }
});
