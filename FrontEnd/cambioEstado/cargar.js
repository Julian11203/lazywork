// ... (código anterior)

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
            $("#tableid tbody").empty(); // Vaciar el contenido de la tabla antes de agregar nuevos datos
            for (let i = 0; i < respuesta.length; i++) {
                tabla.innerHTML += '<tr><td>' + respuesta[i].estadoID +
                    '</td><td>' + respuesta[i].tipoEstado +
                    '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarEstadoIncidencia(" + respuesta[i].estadoID + ")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(" + respuesta[i].estadoID + ")'> <i class='material-icons'>edit</i></a>" +
                    '</td></tr>';
            }
        },
        error: function (xhr) {
            console.error("Error al cargar los estados: " + xhr.statusText);
        }
    });
}

// Resto de tu código (save, update, y otras funciones) permanece sin cambios.

document.getElementById("byid").addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        findById();
    }
});
s