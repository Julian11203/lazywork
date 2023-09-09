function findByIdRol(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let idAConsultar=$("#byid").val();
    let tabla=document.querySelector("#tableid");
    if (idAConsultar === '') {
        errorMensaje.classList.add('alert-danger');
        $("#errormsg").text("❌ Ingrese un id para realizar la consulta");
        return;
    }
    $.ajax({
        url: "http://localhost:8080/api/usuario/"+ idAConsultar,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#byid").val("");
            $("#tableid tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.idUser +
            '</td><td>' + respuesta.nombre +
            '</td><td>' + respuesta.apellido +
            '</td><td>' + respuesta.documento +
            '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarUsuario(\""+respuesta.idUser+"\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\""+respuesta.idUser+"\")'> <i class='material-icons'>edit</i></a>" +
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
function buscarUsuarioIdParametro(id){
    let idAConsultar=$("#byid").val(id);
    buscarUsuarioId()
}

function findAll(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let tabla=document.querySelector("#tableid");
    $.ajax({
        url: "http://localhost:8080/api/usuario/listar",
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#tableid tbody").remove();
            for(i=0;i<respuesta.length;i++){
                tabla.innerHTML += '<tr><td>' + respuesta[i].idUser +
                '</td><td>' + respuesta[i].nombre +
                '</td><td>' + respuesta[i].apellido +
                '</td><td>' + respuesta[i].documento +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarUsuario(\""+respuesta[i].idUser+"\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\""+respuesta[i].idUser+"\")'> <i class='material-icons'>edit</i></a>" +
                '</td></tr>';
            }      
        }
    })
}

function save() {
    let errorModal = document.querySelector('#errormodal')
    errorModal.innerHTML='';
    errorModal.classList.remove('alert-danger')
    let idInicio = $("#idInicio").val();
    
    let hora = $("#" + tipo + "hora").val(); // Nuevo campo para "inicio"
    let fecha = $("#" + tipo + "fecha").val(); // Nuevo campo para "inicio"
    let nombre = $("#" + tipo + "nombre").val();
    let apellido = $("#" + tipo + "apellido").val();
    let documento = $("#" + tipo + "documento").val();

    if (idInicio === '' || isNaN(idInicio)) {
        errorModal.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese un ID de inicio válido...");
        return;
    }
     let data = {
        idInicio: idInicio,
        hora: hora, // Reemplaza 'hora' y 'fecha' con tus valores correctos
        fecha: fecha
    }
    
    $.ajax({
        url: "http://localhost:8080/api/inicio/insertar",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#registrarModal").modal("hide");
            $("#" + tipo + "nombre").val('');
            $("#" + tipo + "apellido").val('');
            $("#" + tipo + "documento").val('');
            $("#" + tipo + "id").val('');
            
            // Llamar a la función correspondiente para listar (listarUsuarios o listarInicios)
            if (tipo === "usuario") {
                listarUsuarios();
            } else if (tipo === "inicio") {
                listarInicios();
            }
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ El id " + id + " ya existe, ingrese otro...");
                return;
            }
        }
    })
}

function re_save(){
    let errorModal = document.querySelector('#errorAc')
    errorModal.innerHTML='';
    errorModal.classList.remove('alert-danger')
    let id=$("#idAC").val();
    let nombre=$("#nombreAC").val();
    let apellido=$("#apellidoAC").val();
    let documento=$("#documentoAC").val();
    if (id === '' || nombre === '' || apellido ==='' || documento === '') {
        errorModal.classList.add('alert-danger');
        $("#errorAc").text("❌ Ingrese todos los campos requeridos para actualizar...");
        return;
    }
    data={
        idUser: id,
        nombre: nombre,
        apellido: apellido,
        documento: documento
    }
    $.ajax({
        url:"http://localhost:8080/api/usuario/actualizar/" + id,
        type:"PUT",
        data: JSON.stringify(data),
        contentType:"application/json",
        success: function(){
            $("#actualizarModal").modal("hide");
            $("#nombreAC").val('');
            $("#apellidoAC").val('');
            $("#documentoAC").val('');
            $("#idAC").val('');    
        },
        error: function(xhr) {
        }  
    })
}



function deleteById(idUser) {
    $("#confirmarEliminacion").off("click").on("click", function () {
        $.ajax({
            url: "http://localhost:8080/api/usuario/eliminar/" + idUser,
            type: "DELETE",
            success: function () {
                $("#tableid tbody").find("td:contains('" + idUser + "')").closest("tr").remove();
                $("#exampleModal").modal("hide");
            }
        });
    });
}
function cargarDatos(idUser){
    $.ajax({
        url: "http://localhost:8080/api/usuario/"+ idUser,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#actu input").val('');
            $("#idAC").val(respuesta.idUser);
            $("#idAC").prop('disabled', true);
            $("#nombreAC").val(respuesta.nombre);
            $("#apellidoAC").val(respuesta.apellido);
            $("#documentoAC").val(respuesta.documento);
        }
    })
}

document.getElementById("byid").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        buscarUsuarioId(); 
    }
})