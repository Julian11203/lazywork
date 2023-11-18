function obtenerIncidenciaPorId() {
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
        url: "http://localhost:8080/incidencia/buscarporid" + idAConsultar,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#byid").val("");
            $("#tableid tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.incidenciaID +
                '</td><td>' + respuesta.nombreIncidencia +
                '</td><td>' + respuesta.ubicacion +
                '</td><td>' + respuesta.descripcion +
                '</td><td>' + respuesta.fechaRegistro +
                '</td><td>' + respuesta.usuarioback.id +
                '</td><td>' + respuesta.estado.estadoID +
                '</td><td>' + respuesta.prioridad.prioridadID +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarIncidencia(\"" + respuesta.incidenciaID + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\"" + respuesta.incidenciaID + "\")'> <i class='material-icons'>edit</i></a>" +
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

function obtenerTodasLasIncidencias() {
    let errorMensaje = document.querySelector('#errormsg');
    errorMensaje.innerHTML = '';
    errorMensaje.classList.remove('alert-danger');
    let tabla = document.querySelector("#tableid");

    $.ajax({
        url: "http://localhost:8080/incidencia/listar",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            console.log(respuesta);
            $("#tableid tbody").remove();

            for (let i = 0; i < respuesta.length; i++) {
                tabla.innerHTML += '<tr><td>' + respuesta[i].incidenciaID +
                    '</td><td>' + respuesta[i].nombreIncidencia +
                    '</td><td>' + respuesta[i].ubicacion +
                    '</td><td>' + respuesta[i].descripcion +
                    '</td><td>' + respuesta[i].fechaRegistro +
                    '</td><td>' + respuesta[i].usuarioback.id +
                    '</td><td>' + respuesta[i].estado.estadoID +
                    '</td><td>' + respuesta[i].prioridad.prioridadID +
                    '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarIncidencia(\"" + respuesta[i].incidenciaID + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\"" + respuesta[i].incidenciaID + "\")'> <i class='material-icons'>edit</i></a>" +
                    '</td></tr>';
            }
        }
    });
}

// Otras funciones y correcciones...

function save() {
    let errorModalSave = document.querySelector('#errormodal');
    errorModalSave.innerHTML = '';
    errorModalSave.classList.remove('alert-danger');

    let nombre = $("#nombre").val();
    let descripcion = $("#descripcion").val();
    let ubicacion = $("#ubicacion").val();
    let usuarioback = $("#usuarioback option:selected").val();
    let prioridad = $("#prioridad option:selected").val();
    let estado = $("#estado option:selected").val();

    if (descripcion === '' || nombre === '' || ubicacion === '' || usuarioback === '' || prioridad === '' || estado === '') {
        errorModalSave.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese todos los campos requeridos para ingresar...");
        return;
    }

    let data = {
        nombreIncidencia: nombre,
        ubicacion: ubicacion,
        descripcion: descripcion,
        usuarioback: {
            id: usuarioback
        },
        prioridad: {
            prioridadID: prioridad
        },
        estado: {
            estadoID: estado
        }
    };

    $.ajax({
        url: "http://localhost:8080/incidencia/crear",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#registrarModal").modal("hide");
            $("#nombre").val('');
            $("#ubicacion").val('');
            $("#descripcion").val('');
            $("#usuarioback").val('');
            $("#prioridad").val('');
            $("#estado").val('');
            findAll();
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                errorModalSave.classList.add('alert-danger');
                $("#errormodal").text("❌ El ID ya existe, ingrese otro...");
                return;
            } else {
                errorModalSave.classList.add('alert-danger');
                $("#errormodal").text("❌ El usuario no existe...");
                return;
            }
        }
    });
}

// Otras funciones y correcciones...


// Your JavaScript file
function obtenerIncidenciasEstado3() {
    let errorMensaje = document.querySelector('#errormsg');
    errorMensaje.innerHTML = '';
    errorMensaje.classList.remove('alert-danger');
    let tabla = document.querySelector("#tableid2");
    $.ajax({
        url: "http://localhost:8080/incidencia/estado3",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            console.log(respuesta);
            $("#tableid2 tbody").empty(); // Use .empty() to remove all child elements
            for (let i = 0; i < respuesta.length; i++) {
                tabla.innerHTML += `<tr>
                    <td>${respuesta[i].incidenciaID}</td>
                    <td>${respuesta[i].nombreIncidencia}</td>
                    <td>${respuesta[i].ubicacion}</td>
                    <td>${respuesta[i].descripcion}</td>
                    <td>${respuesta[i].fechaRegistro}</td>
                    <td>${respuesta[i].usuarioback.id}</td>
                    <td>${respuesta[i].estado.estadoID}</td>
                    <td>${respuesta[i].prioridad.prioridadID}</td>
                </tr>`;
            }
        },
        error: function (error) {
            console.error("Error fetching incidencias:", error);
        }
    });
}


function obtenerIncidenciasEstado1(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let tabla=document.querySelector("#tableid2");
    $.ajax({
        url: "http://localhost:8080/incidencia/estado1",
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            console.log(respuesta)
            $("#tableid2 tbody").remove();
            for(i=0;i<respuesta.length;i++){
                tabla.innerHTML += '<tr><td>' + respuesta[i].incidenciaID +
                '</td><td>' + respuesta[i].nombreIncidencia +
                '</td><td>' + respuesta[i].ubicacion +
                '</td><td>' + respuesta[i].descripcion +
                '</td><td>' + respuesta[i].fechaRegistro +
                '</td><td>' + respuesta[i].usuario.id+
                '</td><td>' + respuesta[i].estado.estadoID+
                '</td><td>' + respuesta[i].prioridad.prioridadID+

                '</td></tr>';
            }
        }
    })
}


function actualizarNivel2Prioridad2() {
    // Llama a la función correspondiente en tu controlador de Spring
    // Puedes utilizar AJAX para hacer la llamada.
    // Aquí hay un ejemplo de cómo hacerlo con jQuery:
    $.ajax({
        url: 'http://localhost:8080/incidencia/actualizarIncidenciasNivel2Prioridad2',
        type: 'POST',
        success: function(response) {
            alert(response);
        },
        error: function(error) {
            alert('Error al actualizar las incidencias.');
        }
    });
}

function actualizarNivel3Prioridad3() {
    // Llama a la función correspondiente en tu controlador de Spring
    // Puedes utilizar AJAX para hacer la llamada.
    // Aquí hay un ejemplo de cómo hacerlo con jQuery:
    $.ajax({
        url: 'http://localhost:8080/incidencia/actualizarIncidenciasNivel3Prioridad3',
        type: 'POST',
        success: function(response) {
            alert(response);
        },
        error: function(error) {
            alert('Error al actualizar las incidencias.');
        }
    });
}

function update(){
    let errorModal = document.querySelector('#errorAc')
    errorModal.innerHTML='';
    errorModal.classList.remove('alert-danger')
    let id=$("#idAC").val();
    let nombre=$("#nombreAC").val();
    let descripcion=$("#descripcionAC").val();
    let ubicacion=$("#ubicacionAC").val();
    let usuarioback=$("#usuariobackAC option:selected").val();
    let prioridad=$("#prioridadAC option:selected").val();
    let estado=$("#estadoAC option:selected").val();
    if (id === '' || nombre === '' || ubicacion ==='' || descripcion ==='' || usuarioback === '' || prioridad ===''|| estado === '') {
        errorModal.classList.add('alert-danger');
        $("#errorAc").text("❌ Ingrese todos los campos requeridos para actualizar...");
        return;
    }
    data={
        incidenciaID: id,
        nombreIncidencia: nombre,
        ubicacion: ubicacion,
        descripcion:descripcion,
        usuarioback: {
            id: usuarioback
        },
        prioridad: {
            prioridadID: prioridad
        },
        estado: {
            estadoID: estado
        }
    }
    $.ajax({
        url:"http://localhost:8080/incidencia/actualizar",
        type:"PUT",
        data: JSON.stringify(data),
        contentType:"application/json",
        success: function(){
            $("#actualizarModal").modal("hide");
            $('#idAC').val('')
            $("#nombreAC").val('');
            $("#ubicacionAC").val('');
            $("#descripcionAC").val('');
            findAll()
        },
        error: function(xhr) {
        }
    })
}
