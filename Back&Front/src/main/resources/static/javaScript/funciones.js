function findByIdincidencia(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let idAConsultar=$("#byid").val();
    let tabla=document.querySelector("#tableid1");
    if (idAConsultar === '') {
        errorMensaje.classList.add('alert-danger');
        $("#errormsg").text("❌ Ingrese un id para realizar la consulta");
        return;
    }
    $.ajax({
        url: "http://localhost:8080/incidencia/"+ idAConsultar,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#byid").val("");
            $("#tableid1 tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.incidenciaID +
            '</td><td>' + respuesta.nombreIncidencia +
            '</td><td>' + respuesta.ubicacion +
            '</td><td>' + respuesta.descripcion +
            '</td><td>' + respuesta.fechaRegistro +
            '</td><td>' + respuesta.usuario.id+
            '</td><td>' + respuesta.estado.estadoID+
            '</td><td>' + respuesta.prioridad.prioridadID+
            '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal1' onclick='eliminarIncidencia(\""+respuesta.incidenciaID+"\")'> <i class='material-icons1'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal1' onclick='cargarDatos(\""+respuesta.incidenciaID+"\")'> <i class='material-icons1'>edit</i></a>" +
            '</td></tr>'; 
        },
        error: function(xhr) {
            if(xhr.status===404){
                errorMensaje.classList.add('alert-danger');
                $("#errormsg").text("❌ El id "+ idAConsultar +" no se encontro...");
                return;
            }
        }
    })
}
function buscarIncidenciaIdParametro(noIncidencia){
    let idAConsultar=$("#byid").val(noIncidencia);
    buscarIncidenciaId()
}

function findAllincidencias(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let tabla=document.querySelector("#tableid1");
    $.ajax({
        url: "http://localhost:8080/incidencia/listar",
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            console.log(respuesta)
            $("#tableid1 tbody").remove();
            for(i=0;i<respuesta.length;i++){
                tabla.innerHTML += '<tr><td>' + respuesta[i].incidenciaID +
                '</td><td>' + respuesta[i].nombreIncidencia +
                '</td><td>' + respuesta[i].ubicacion +
                '</td><td>' + respuesta[i].descripcion +
                '</td><td>' + respuesta[i].fechaRegistro +
                '</td><td>' + respuesta[i].usuario.id+
                '</td><td>' + respuesta[i].estado.estadoID+
                '</td><td>' + respuesta[i].prioridad.prioridadID+
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal1' onclick='eliminarIncidencia(\""+respuesta[i].incidenciaID+"\")'> <i class='material-icons1'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal1' onclick='cargarDatos(\""+respuesta[i].incidenciaID+"\")'> <i class='material-icons1'>edit</i></a>" +
                '</td></tr>';
            }      
        }
    })
}
function save(){
    let errorModal = document.querySelector('#errormodal')
    errorModal.innerHTML='';
    errorModal.classList.remove('alert-danger');
    let nombre=$("#nombre").val();
    let descripcion=$("#descripcion").val();
    let ubicacion=$("#ubicacion").val();
    let usuario=$("#usuario option:selected").val();
    let prioridad=$("#prioridad option:selected").val();
    let estado=$("#estado option:selected").val();
    if (descripcion === '' || nombre === '' || ubicacion ==='' || usuario === '' || prioridad === '' || estado === '') {
        errorModal.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese todos los campos requeridos para ingresar...");
        return;
    }
    data={
        nombreIncidencia: nombre,
        ubicacion: ubicacion,
        descripcion:descripcion,
        usuario: {
            id: usuario
        },
        prioridad: {
            prioridadID: prioridad
        },
        estado: {
            estadoID: estado
        }
    }
    $.ajax({
        url:"http://localhost:8080/incidencia/crear",
        type:"POST",
        data: JSON.stringify(data),
        contentType:"application/json",
        success: function(){
            $("#registrarModal1").modal("hide");
            $("#nombre").val('');
            $("#ubicacion").val('');
            $("#descripcion").val('');
            $("#usuario").val('');
            $("#prioridad").val('');
            $("#estado").val('');
            findAll()
        },
        error: function(xhr) {
            if(xhr.status===409){
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ El id "+ noIncidencia +" ya existe, ingrese otro...");
                return;
            }
            else{
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ El usuario "+ usuario +" no existe...");
                return; 
            }
        }
        
    })
}
function update(){
    let errorModal = document.querySelector('#errorAc')
    errorModal.innerHTML='';
    errorModal.classList.remove('alert-danger')
    let id=$("#idAC").val();
    let nombre=$("#nombreAC").val();
    let descripcion=$("#descripcionAC").val();
    let ubicacion=$("#ubicacionAC").val();
    let usuario=$("#usuarioAC option:selected").val();
    let prioridad=$("#prioridadAC option:selected").val();
    let estado=$("#estadoAC option:selected").val();
    if (id === '' || nombre === '' || ubicacion ==='' || descripcion ==='' || usuario === '' || prioridad ===''|| estado === '') {
        errorModal.classList.add('alert-danger');
        $("#errorAc").text("❌ Ingrese todos los campos requeridos para actualizar...");
        return;
    }
    data={
        incidenciaID: id,
        nombreIncidencia: nombre,
        ubicacion: ubicacion,
        descripcion:descripcion,
        usuario: {
            id: usuario
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
            $("#actualizarModal1").modal("hide");
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

function eliminarIncidencia(noIncidencia){
    console.log(noIncidencia)
    $("#confirmarEliminacion").off("click").on("click", function(){
        $.ajax({
            url: "http://localhost:8080/incidencia/delete/"+noIncidencia,
            type: "DELETE",
            success: function(){
                $("#tableid tbody").find("td:contains('" + noIncidencia + "')").closest("tr").remove();
                $("#exampleModal").modal("hide");   
            }
        })
    })
}
function cargarDatos(noIncidencia){
    console.log(noIncidencia)
    $.ajax({
        url: "http://localhost:8080/incidencia/"+ noIncidencia,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#idAC").val(respuesta.incidenciaID)
            $("#idAC").prop("disabled", true)
            $("#nombreAC").val(respuesta.nombreIncidencia);
            $("#descripcionAC").val(respuesta.descripcion);
            $("#ubicacionAC").val(respuesta.ubicacion);
        }
    })
}

document.getElementById("byid").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        buscarIncidenciaId(); 
    }
});

















function findByIdusuario() {
    let errorMensaje = document.querySelector('#errormsg');
    errorMensaje.innerHTML = '';
    errorMensaje.classList.remove('alert-danger');
    let idAConsultar = $("#byid").val();
    let tabla = document.querySelector("#tableid2");
    if (idAConsultar === '') {
        errorMensaje.classList.add('alert-danger');
        $("#errormsg").text("❌ Ingrese un ID para realizar la consulta");
        return;
    }
    $.ajax({
        url: "http://localhost:8080/api/usuario/buscarporid/" + idAConsultar,
        type: "GET",
        dataType: "json",
        success: function (usuario) {
            $("#byid").val("");
            $("#tableid2 tbody").remove();
            tabla.innerHTML += '<tr><td>' + usuario.id +
                '</td><td>' + usuario.nombre +
                '</td><td>' + usuario.apellido +
                '</td><td>' + usuario.documento +
                '</td><td>' + usuario.nivelSoporte +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal2' onclick='eliminarUsuario(\"" + usuario.id + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal2' onclick='cargarDatos(\"" + usuario.id + "\")'> <i class='material-icons'>edit</i></a>" +
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

function buscarUsuarioIdParametro(idUsuario) {
    let id = $("#byid").val(idUsuario);
    findById();
}

function findAllusuario() {
    let errorMensaje = document.querySelector('#errormsg');
    errorMensaje.innerHTML = '';
    errorMensaje.classList.remove('alert-danger');
    let tabla = document.querySelector("#tableid2");

    $.ajax({
        url: "http://localhost:8080/api/usuario/listar",
        type: "GET",
        dataType: "json",
        success: function (usuarios) {
            $("#tableid2 tbody").remove();
            usuarios.forEach(function (usuario) {
                tabla.innerHTML += '<tr><td>' + usuario.id +
                    '</td><td>' + usuario.nombre +
                    '</td><td>' + usuario.apellido +
                    '</td><td>' + usuario.documento +
                    '</td><td>' + usuario.nivelSoporte +
                    '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarUsuario(\"" + usuario.id + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal2' onclick='cargarDatos(\"" + usuario.id + "\")'> <i class='material-icons'>edit</i></a>" +
                    '</td></tr>';
            });
        }
    });

}
function crear() {
    let errorModal = document.querySelector('#errormodal');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');
    let nombre = $("#nombre").val();
    let apellido = $("#apellido").val();
    let documento = $("#documento").val();
    let nivelSoporte = $("#nivelSoporte").val();
    if (nombre === '' || apellido === '' || documento === '' || nivelSoporte === '') {
        errorModal.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese todos los campos requeridos para ingresar...");
        return;
    }
    let data = {
        nombre: nombre,
        apellido: apellido,
        documento: documento,
        nivelSoporte: nivelSoporte
    };
    $.ajax({
        url: "http://localhost:8080/api/usuario/crear",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#registrarModal2").modal("hide");
            $("#nombre").val('');
            $("#apellido").val('');
            $("#documento").val('');
            $("#nivelSoporte").val('');
            findAll();
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ Ya existe un usuario con los mismos datos...");
                return;
            }
        }
    });
}

function actualizarUsuario() {
    let errorModal = document.querySelector('#errorAc');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');
    let idAConsultar = $("#nuevoUserId").val();
    let nombre = $("#nombreAC").val();
    let apellido = $("#apellidoAC").val();
    let documento = $("#documentoAC").val();
    let nivelSoporte = $("#nivelSoporteAC").val();
    if (idAConsultar === '' || nombre === '' || apellido === '' || documento === '' || nivelSoporte === '') {
        errorModal.classList.add('alert-danger');
        $("#errorAc").text("❌ Ingrese todos los campos requeridos para actualizar...");
        return;
    }
    let data = {
        nombre: nombre,
        apellido: apellido,
        documento: documento,
        nivelSoporte: nivelSoporte
    };
    $.ajax({
        url: "http://localhost:8080/api/usuario/actualizar/" + idAConsultar,
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#actualizarModal2").modal("hide");
            $('#nuevoUserId').val('');
            $("#nombreAC").val('');
            $("#apellidoAC").val('');
            $("#documentoAC").val('');
            $("#nivelSoporteAC").val('');
            findAll();
        },
        error: function (xhr) {
            // Maneja los errores si es necesario
        }
    });
}

function eliminarUsuario(idUsuario) {
    console.log("ID del usuario a eliminar: " + idUsuario);

    // Configura el ID del usuario como un atributo personalizado en el botón de confirmación
    $("#confirmarEliminacion").data("idUsuario", idUsuario);

    // Adjunta un manejador de clic una sola vez al botón de confirmación
    $("#confirmarEliminacion").off("click").on("click", function () {
        var idUsuario = $(this).data("idUsuario"); // Obtiene el ID del usuario desde el atributo personalizado

        $.ajax({
            url: "http://localhost:8080/api/usuario/eliminar/" + idUsuario,
            type: "DELETE",
            success: function () {
                $("#tableid2 tbody").find("td:contains('" + idUsuario + "')").closest("tr").remove();
            },
            error: function (xhr) {
                // Maneja errores si es necesario
            }
        });
    });
}









function buscarInicioPorId() {
    let errorMensaje = document.querySelector('#errormsg');
    errorMensaje.innerHTML = '';
    errorMensaje.classList.remove('alert-danger');
    let idAConsultar = $("#byid").val();
    let tabla = document.querySelector("#tableid3");
    if (idAConsultar === '') {
        errorMensaje.classList.add('alert-danger');
        $("#errormsg").text("❌ Ingrese un ID para realizar la consulta");
        return;
    }
    $.ajax({ 
        url: "http://localhost:8080/api/iniciosesion/buscarporid/" + idAConsultar, 
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#byid").val("");
            $("#tableid3 tbody").empty();
            tabla.innerHTML += '<tr><td>' + respuesta.id +
            '</td><td>' + respuesta.fechaHoraInicio +
            '</td><td>' + respuesta.usuario.nombre + ' ' + respuesta.usuario.apellido +
            '</td><td>' + `<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal3' onclick='eliminarInicio("${respuesta.id}")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal3' onclick='cargarDatosInicio("${respuesta.id}")'> <i class='material-icons'>edit</i></a>` +
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
    let tabla = document.querySelector("#tableid3");
    $.ajax({
        url: "http://localhost:8080/api/iniciosesion/listar", 
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tableid3 tbody").empty();
            for (let i = 0; i < respuesta.length; i++) {
                tabla.innerHTML += '<tr><td>' + respuesta[i].id +
                    '</td><td>' + respuesta[i].fechaHoraInicio +
                    '</td><td>' + respuesta[i].usuario.nombre + ' ' + respuesta[i].usuario.apellido +
                    '</td><td>' + `<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal3' onclick='eliminarInicio("${respuesta[i].id}")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatosInicio("${respuesta[i].id}")'> <i class='material-icons'>edit</i></a>` +
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
    let id = $("#usuarioId").val();
    let fecha = $("#fecha").val();

    // Crear un objeto con los datos
    let data = {
        usuario:{
            id: id,
        },
        fechaHoraInicio: fecha 
    }; 

    $.ajax({
        url: "http://localhost:8080/api/iniciosesion/insertar",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            // Limpiar los campos del formulario
            $("#usuarioId").val('');
            $("#fecha").val('');
            $('#registrarModal3').modal('hide');
            listarInicios() 
            
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
    let inicioId = $("#idInicioAC").val(); // Corregimos el ID del campo oculto

    let id = $("#usuarioIdAC").val();
    let fecha = $("#fechaAC").val();

    // Crear un objeto con los datos
    let data = {
        id: inicioId,
        usuario: {
            id: id,
        },
        fechaHoraInicio: fecha 
    }; 

    $.ajax({
        url: "http://localhost:8080/api/iniciosesion/actualizar/" + inicioId,
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            // Limpiar los campos del formulario
            $("#usuarioIdAC").val('');
            $("#fechaAC").val('');
            $('#actualizarModal3').modal('hide'); // Usamos el ID correcto del modal
            listarInicios() 
            
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
        url: "http://localhost:8080/api/iniciosesion/eliminar/" + id,
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

function addComment() {
    const commentName = document.getElementById("commentName").value;
    const commentIncidences = document.getElementById("commentIncidences").value;
    const commentText = document.getElementById("commentText").value;

    if (commentText.trim() !== "") {
        const commentList = document.getElementById("commentList");
        const newComment = document.createElement("div");
        newComment.innerHTML = `
            <strong>Nombre:</strong> ${commentName}<br>
            <strong>Número de Incidencias:</strong> ${commentIncidences}<br>
            <strong>Comentario:</strong> ${commentText}
        `;
        commentList.appendChild(newComment);

        // Guardar el comentario en el localStorage
        saveComment({ name: commentName, incidences: commentIncidences, text: commentText });
        clearFields();
    }
}

function saveComment(comment) {
    let comments = JSON.parse(localStorage.getItem("comments")) || [];
    comments.push(comment);
    localStorage.setItem("comments", JSON.stringify(comments));
}

function loadComments() {
    const comments = JSON.parse(localStorage.getItem("comments")) || [];
    const commentList = document.getElementById("commentList");

    comments.forEach((comment) => {
        const newComment = document.createElement("div");
        newComment.innerHTML = `
            <strong>Nombre:</strong> ${comment.name}<br>
            <strong>Número de Incidencias:</strong> ${comment.incidences}<br>
            <strong>Comentario:</strong> ${comment.text}
        `;
        commentList.appendChild(newComment);
    });
}

function clearFields() {
    document.getElementById("commentName").value = "";
    document.getElementById("commentIncidences").value = "";
    document.getElementById("commentText").value = "";
}

function clearComments() {
    localStorage.removeItem("comments");
    document.getElementById("commentList").innerHTML = "";
}

// Cargar los comentarios almacenados al cargar la página
loadComments();