
function findById1() {
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
                '</td><td>' + usuario.correo +
                '</td><td>' + usuario.telefono +
                    '</td><td>' + usuario.tipoderol +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarUsuario(\"" + usuario.id + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\"" + usuario.id + "\")'> <i class='material-icons'>edit</i></a>" +
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

function findAll1() {
    let errorMensaje = document.querySelector('#errormsg');
    errorMensaje.innerHTML = '';
    errorMensaje.classList.remove('alert-danger');
    let tabla = $("#tableid1").DataTable({
        "pageLength": 5, // Establece la cantidad de registros por página
        "pagingType": "simple_numbers", // Tipo de paginación (puedes ajustar según tu preferencia)
    });

    $.ajax({
        url: "http://localhost:8080/api/usuario/listar",
        type: "GET",
        dataType: "json",
        success: function (usuarios) {
            tabla.clear().draw(); // Limpia la tabla antes de agregar nuevos datos

            usuarios.forEach(function (usuario) {
                tabla.row.add([
                    usuario.id,
                    usuario.nombre,
                    usuario.apellido,
                    usuario.documento,
                    usuario.correo,
                    usuario.telefono,
                    usuario.tipoderol,
                    usuario.nivelSoporte,
                    "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarUsuario(\"" + usuario.id + "\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\"" + usuario.id + "\")'> <i class='material-icons'>edit</i></a>"
                ]).draw();
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
    let correo = $("#correo").val();
    let telefono = $("#telefono").val();
    let tipoderol = $("#tipoderol").val();
    let nivelSoporte = $("#nivelSoporte").val();

    if (nombre === '' || apellido === '' || documento === '' || correo === '' || telefono === '' || tipoderol === '' || nivelSoporte === '') {
        errorModal.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese todos los campos requeridos para ingresar...");
        return;
    }
    let data = {
        nombre: nombre,
        apellido: apellido,
        documento: documento,
        correo: correo,
        telefono: telefono,
        tipoderol: tipoderol,
        nivelSoporte:nivelSoporte
    };
    $.ajax({
        url: "http://localhost:8080/api/usuario/crear",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#registrarModal1").modal("hide");
            $("#nombre").val('');
            $("#apellido").val('');
            $("#documento").val('');
            $("#correo").val('');
            $("#telefono").val('');
            $("#tipoderol").val('');
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
       let correo = $("#correoAC").val();
          let telefono = $("#telefonoAC").val();
               let tipoderol = $("#tipoderolAC").val();
    let nivelSoporte = $("#nivelSoporteAC").val();
    if (idAConsultar === '' || nombre === '' || apellido === '' || documento === '' || correo === '' || telefono === '' || tipoderol === '' || nivelSoporte === '') {
        errorModal.classList.add('alert-danger');
        $("#errorAc").text("❌ Ingrese todos los campos requeridos para actualizar...");
        return;
    }
    let data = {
        nombre: nombre,
        apellido: apellido,
        documento: documento,
        correo: correo,
       telefono: telefono,
      tipoderol: tipoderol,
        nivelSoporte: nivelSoporte
    };
    $.ajax({
        url: "http://localhost:8080/api/usuario/actualizar/" + idAConsultar,
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#actualizarModal").modal("hide");
            $('#nuevoUserId').val('');
            $("#nombreAC").val('');
            $("#apellidoAC").val('');
            $("#documentoAC").val('');
             $("#correoAC").val('');
              $("#telefonoAC").val('');
              $("#tipoderolAC").val('');

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
                $("#tableid tbody").find("td:contains('" + idUsuario + "')").closest("tr").remove();
            },
            error: function (xhr) {
                // Maneja errores si es necesario
            }
        });
    });
}


function findById2(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let idAConsultar=$("#byid").val();
    let tabla=document.querySelector("#tableid2");
    if (idAConsultar === '') {
        errorMensaje.classList.add('alert-danger');
        $("#errormsg").text("❌ Ingrese un id para realizar la consulta");
        return;
    }
    $.ajax({
        url: "http://localhost:8080/rol/findById/"+ idAConsultar,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#byid").val("");
            $("#tableid2 tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.rolID +
            '</td><td>' + respuesta.nombreRol +
            '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='deleteById(\""+respuesta.rolID+"\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\""+respuesta.rolID+"\")'> <i class='material-icons'>edit</i></a>" +
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
function buscarRolIdParametro(id){
    let idAConsultar=$("#byid").val(id);
    findById()
}

function findAll2(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let tabla=document.querySelector("#tableid2");
    $.ajax({
        url: "http://localhost:8080/rol/findAll",
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#tableid tbody").remove();
            for(i=0;i<respuesta.length;i++){
                tabla.innerHTML += '<tr><td>' + respuesta[i].rolID +
                '</td><td>' + respuesta[i].nombreRol +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='deleteById(\""+respuesta[i].rolID+"\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\""+respuesta[i].rolID+"\")'> <i class='material-icons'>edit</i></a>" +
                '</td></tr>';
            }
        }
    })
}





document.addEventListener("DOMContentLoaded", function () {
    // Llama a la función findAll para mostrar la lista despues de refrescar la página
    findAll();
});

document.getElementById("byid").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        findById();
    }
})

function findById3(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let idAConsultar=$("#byid").val();
    let tabla=document.querySelector("#tableid3");
    if (idAConsultar === '') {
        errorMensaje.classList.add('alert-danger');
        $("#errormsg").text("❌ Ingrese un id para realizar la consulta");
        return;
    }
    $.ajax({
        url: "http://localhost:8080/usuario-rol/findById/"+ idAConsultar,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#byid").val("");
            $("#tableid3 tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.usuarioRolID +
            '</td><td>' + respuesta.usuario.id +
            '</td><td>' + respuesta.rol.rolID +
            '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='deleteById(\""+respuesta.usuarioRolID+"\")'> <i class='material-icons'>delete</i></a>" +
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
function buscarUsuarioRolIdParametro(id){
    let idAConsultar=$("#byid").val(id);
    findById()
}

function findAll3(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let tabla=document.querySelector("#tableid3");
    $.ajax({
        url: "http://localhost:8080/usuario-rol/findAll",
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#tableid3 tbody").remove();
            for(i=0;i<respuesta.length;i++){
                tabla.innerHTML += '<tr><td>' + respuesta[i].usuarioRolID +
                '</td><td>' + respuesta[i].usuario.id +
                '</td><td>' + respuesta[i].rol.rolID +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='deleteById(\""+respuesta[i].usuarioRolID+"\")'> <i class='material-icons'>delete</i></a>" +
                '</td></tr>';
            }
        }
    })
}

function save2(){
    let errorModal = document.querySelector('#errormodal')
    errorModal.innerHTML='';
    errorModal.classList.remove('alert-danger')
    let usuarioRolID=$("#usuarioRolId").val();
    let usuario=$("#usuarioId").val();
    let rol=$("#rolId").val();
    if (usuarioRolID === '' || usuario === '' || rol === '') {
        errorModal.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese todos los campos requeridos para ingresar...");
        return;
    }

    data={
        usuarioRolID: usuarioRolID,
        usuario: {
            id: usuario
        },
        rol: {
            rolID: rol
        }
    }
    $.ajax({
        url:"http://localhost:8080/usuario-rol/save",
        type:"POST",
        data: JSON.stringify(data),
        contentType:"application/json",
        success: function(){
            $("#registrarModal3").modal("hide");
            $("#usuarioRolId").val('');
            $("#usuarioId").val('');
            $("#rolId").val('');
            findAll();
        },
        error: function(xhr) {
            if(xhr.status===409){
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ El Nº Registro "+ usuarioRolID +" ya existe, ingrese otro...");
                return;
            }
            else if(xhr.status===400){
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ Vuelva a verificar los campos Usuario y Rol");
                return;
            }
        }

    })
}





function deleteById(usuarioRolID) {
    $("#confirmarEliminacion").off("click").on("click", function () {
        $.ajax({
            url: "http://localhost:8080/usuario-rol/deleteById/" + usuarioRolID,
            type: "DELETE",
            success: function () {
                $("#tableid tbody").find("td:contains('" + usuarioRolID + "')").closest("tr").remove();
                $("#exampleModal").modal("hide");
            }
        });
    });
}
function cargarDatos(usuarioRolID){
    $.ajax({
        url: "http://localhost:8080/usuario-rol/findById/"+ usuarioRolID,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#actu input").val('');
            $("#usuariorolidAC").val(respuesta.usuarioRolID);
            $("#usuariorolidAC").prop('disabled', true);
            $("#usuarioidAC").val(respuesta.usuario);
            $("#rolidAC").val(respuesta.rol);
        }
    })
}


document.addEventListener("DOMContentLoaded", function () {
    // Llama a la función findAll para mostrar la lista despues de refrescar la página
    findAll();
});

document.getElementById("byid").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        findById();
    }
})
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
        url: "http://localhost:8080/api/iniciosesion/buscarporid/" + idAConsultar,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#byid").val("");
            $("#tableid tbody").empty();
            tabla.innerHTML += '<tr><td>' + respuesta.id +
            '</td><td>' + respuesta.fechaHoraInicio +
            '</td><td>' + respuesta.usuario.nombre + ' ' + respuesta.usuario.apellido +
            '</td><td>' + `<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarInicio("${respuesta.id}")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatosInicio("${respuesta.id}")'> <i class='material-icons'>edit</i></a>` +
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

function insertarInicio() {
    let errorModal = document.querySelector('#errormodal');
    errorModal.innerHTML = '';
    errorModal.classList.remove('alert-danger');

    // Obtener los datos del formulario
    let id = $("#usuarioId").val();
    let fecha_hora_fin = $("#fecha_hora_fin").val();
    let fecha_hora_inicio = $("#fecha_hora_inicio").val(); // Asegúrate de tener un campo de fechaFin en tu formulario
    let tiempodesesion = $("#tiempodesesion").val(); // Asegúrate de tener un campo de tiempoSesion en tu formulario

    // Crear un objeto con los datos
    let data = {
        usuario: {
            id: id,
            // Agregar más campos del usuario si es necesario
        },
        fecha_hora_fin: fecha_hora_fin,
        fecha_hora_inicio: fecha_hora_inicio,
        tiempodesesion: tiempodesesion,
        // Agregar más campos según la estructura de tu tabla
    };

    $.ajax({
        url: "http://localhost:8080/api/iniciosesion/insertar",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            // Limpiar los campos del formulario
            $("#usuarioId").val('');
            $("#fecha_hora_fin").val('');
            $("#fecha_hora_inicio").val('');
            $("#tiempodesesion").val('');
            $('#registrarModal').modal('hide');

            // Actualizar la tabla después de la inserción
            listarInicios();
        },
        error: function (xhr) {
            // Mostrar un mensaje de error en caso de fallo
            errorModal.classList.add('alert-danger');
            errorModal.textContent = "❌ Error al insertar el inicio.";
        },
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
function listarInicios() {
    // Obtener el elemento para mostrar mensajes de error
    let errorMensaje = document.querySelector('#errormsg');
    errorMensaje.innerHTML = '';
    errorMensaje.classList.remove('alert-danger');

    // Obtener elementos de la tabla
    let tableBody = document.querySelector("#listaIniciosSesion");

    // Realizar la solicitud AJAX utilizando la API fetch
    fetch("http://localhost:8080/api/iniciosesion/listar")
        .then(response => response.json())
        .then(data => {
            console.log(data); // Registrar los datos en la consola para inspección

            // Limpiar el cuerpo de la tabla antes de llenarla con nuevos datos
            tableBody.innerHTML = '';

            // Iterar sobre los datos y construir las filas de la tabla
            data.forEach(inicioSesion => {
                // Ajustar las propiedades según la estructura real de tus datos
                let row = `<tr>
                                <td>${inicioSesion.id}</td>
                                <td>${inicioSesion.fecha_hora_inicio}</td>
                                <td>${inicioSesion.fecha_hora_fin}</td>
                                <td>${inicioSesion.tiempodesesion}</td>
                                <td>${inicioSesion.usuarioid}</td>
                                <td>
                                    <button class="btn btn-danger" onclick="confirmarEliminar(${inicioSesion.id})">Eliminar</button>
                                </td>
                            </tr>`;

                // Agregar la fila a la tabla
                tableBody.innerHTML += row;
            });

            // Inicializar DataTables y agregar opciones, como el buscador
            $("#tableid").DataTable({
                "pageLength": 5,
                "pagingType": "simple_numbers",
                "searching": true, // Habilitar el buscador
            });
        })
        .catch(error => {
            console.error('Error al obtener datos:', error);
            // Manejar errores si es necesario
        });
}


function addComment() {
    const commentName = document.getElementById("commentName").value;
    const commentIncidences = document.getElementById("commentIncidences").value;
    const commentText = document.getElementById("commentText").value;
    const commentImage = document.getElementById("uploadedImageContainer").querySelector("img");

    if (commentText.trim() !== "" || (commentImage && commentImage.src.trim() !== "")) {
        const commentList = document.getElementById("commentList");
        const newComment = document.createElement("div");
        newComment.classList.add("comment");

        // Construye la estructura del comentario, incluyendo la imagen si está presente
        newComment.innerHTML = `
            <strong>Nombre:</strong> ${commentName}<br>
            <strong>Número de Incidencias:</strong> ${commentIncidences}<br>
            <strong>Comentario:</strong> ${commentText}<br>
            ${commentImage ? `<img src="${commentImage.src}" alt="Imagen adjunta">` : ''}
        `;
        commentList.appendChild(newComment);

        // Guardar el comentario en el localStorage
        saveComment({ name: commentName, incidences: commentIncidences, text: commentText, image: commentImage ? commentImage.src : '' });
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
 function addEmoji(emoji) {
        const commentText = document.getElementById("commentText");
        commentText.value += emoji;
    }
  function addImage() {
        const commentImage = document.getElementById("commentImage");
        const uploadedImageContainer = document.getElementById("uploadedImageContainer");

        const file = commentImage.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const imageUrl = e.target.result;
                const image = document.createElement("img");
                image.src = imageUrl;
                uploadedImageContainer.innerHTML = ""; // Limpia el contenedor antes de agregar la nueva imagen
                uploadedImageContainer.appendChild(image);

                // Descomenta la siguiente línea si deseas agregar la imagen directamente al comentario
                // addComment();
            };
            reader.readAsDataURL(file);
        }
    }


    function exportToExcel() {
        const comments = JSON.parse(localStorage.getItem("comments")) || [];
        const data = comments.map(comment => [comment.name, comment.incidences, comment.text]);

        const ws = XLSX.utils.aoa_to_sheet([["Nombre", "Número de Incidencias", "Comentario"], ...data]);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, "Comentarios");

        XLSX.writeFile(wb, "comentarios.xlsx");
    }

    // Cargar los comentarios almacenados al cargar la página
    loadComments();


 var currentImageIndex = 0;
    var images = document.querySelectorAll(".header-images img");

    function cambiarImagen(index) {
        images[currentImageIndex].style.opacity = 0;
        currentImageIndex = index !== undefined ? index : (currentImageIndex + 1) % images.length;
        images[currentImageIndex].style.opacity = 1;
    }

    setInterval(cambiarImagen, 10000); // Cambiar automáticamente cada 10 segundos