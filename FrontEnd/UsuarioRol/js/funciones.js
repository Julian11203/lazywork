function findById(){
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
        url: "http://localhost:8080/usuario-rol/findById/"+ idAConsultar,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#byid").val("");
            $("#tableid tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.usuarioRolID +
            '</td><td>' + respuesta.usuario +
            '</td><td>' + respuesta.rol +
            '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='deleteById(\""+respuesta.usuarioRolID+"\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\""+respuesta.usuarioRolID+"\")'> <i class='material-icons'>edit</i></a>" +
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

function findAll(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let tabla=document.querySelector("#tableid");
    $.ajax({
        url: "http://localhost:8080/usuario-rol/findAll",
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#tableid tbody").remove();
            for(i=0;i<respuesta.length;i++){
                tabla.innerHTML += '<tr><td>' + respuesta[i].usuarioRolID +
                '</td><td>' + respuesta[i].usuario.id +
                '</td><td>' + respuesta[i].rol.rolID +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='deleteById(\""+respuesta[i].usuarioRolID+"\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\""+respuesta[i].usuarioRolID+"\")'> <i class='material-icons'>edit</i></a>" +
                '</td></tr>';
            }      
        }
    })
}

function save(){
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
            $("#registrarModal").modal("hide");
            $("#usuarioRolId").val('');
            $("#usuarioId").val('');
            $("#rolId").val('');
            findAll(); 
        },
        error: function(xhr) {
            if(xhr.status===409){
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ El id "+ usuarioRolID +" ya existe, ingrese otro...");
                return;
            }
            else if(xhr.status===400){
                errorModal.classList.add('alert-danger');
                $("#errormodal").text("❌ El usuario o el rol no existe");
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