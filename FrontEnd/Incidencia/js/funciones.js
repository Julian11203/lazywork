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
        url: "http://localhost:8080/api/incidencia/"+ idAConsultar,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#byid").val("");
            $("#tableid tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.noIncidencia +
            '</td><td>' + respuesta.nombre +
            '</td><td>' + respuesta.ubicacion +
            '</td><td>' + respuesta.usuario.idUser +
            '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarIncidencia(\""+respuesta.noIncidencia+"\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\""+respuesta.noIncidencia+"\")'> <i class='material-icons'>edit</i></a>" +
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

function findAll(){
    let errorMensaje = document.querySelector('#errormsg')
    errorMensaje.innerHTML='';
    errorMensaje.classList.remove('alert-danger')
    let tabla=document.querySelector("#tableid");
    $.ajax({
        url: "http://localhost:8080/api/incidencia/listar",
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#tableid tbody").remove();
            for(i=0;i<respuesta.length;i++){
                tabla.innerHTML += '<tr><td>' + respuesta[i].noIncidencia +
                '</td><td>' + respuesta[i].nombre +
                '</td><td>' + respuesta[i].ubicacion +
                '</td><td>' + respuesta[i].usuario.idUser +
                '</td><td>' + "<a href='#' class='eliminar-link' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='eliminarIncidencia(\""+respuesta[i].noIncidencia+"\")'> <i class='material-icons'>delete</i></a> <a href='#' class='editar-link' data-bs-toggle='modal' data-bs-target='#actualizarModal' onclick='cargarDatos(\""+respuesta[i].noIncidencia+"\")'> <i class='material-icons'>edit</i></a>" +
                '</td></tr>';
            }      
        }
    })
}
function save(){
    let errorModal = document.querySelector('#errormodal')
    errorModal.innerHTML='';
    errorModal.classList.remove('alert-danger')
    let noIncidencia=$("#noIncidencia").val();
    let nombre=$("#nombre").val();
    let ubicacion=$("#ubicacion").val();
    let usuario=$("#usuario").val();
    if (noIncidencia === '' || nombre === '' || ubicacion ==='' || usuario === '') {
        errorModal.classList.add('alert-danger');
        $("#errormodal").text("❌ Ingrese todos los campos requeridos para ingresar...");
        return;
    }
    data={
        noIncidencia: noIncidencia,
        nombre: nombre,
        ubicacion: ubicacion,
        usuario: usuario
    }
    $.ajax({
        url:"http://localhost:8080/api/incidencia/insertar",
        type:"POST",
        data: JSON.stringify(data),
        contentType:"application/json",
        success: function(){
            $("#registrarModal").modal("hide");
            $("#noIncidencia").val('');
            $("#nombre").val('');
            $("#ubicacion").val('');
            $("#usuario").val('');
            buscarIncidenciaIdParametro(noIncidencia) 
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
    let id=$("#noIncidenciaAC").val();
    let nombre=$("#nombreAC").val();
    let ubicacion=$("#ubicacionAC").val();
    let usuario=$("#usuarioAC").val();
    if (id === '' || nombre === '' || ubicacion ==='' || usuario === '') {
        errorModal.classList.add('alert-danger');
        $("#errorAc").text("❌ Ingrese todos los campos requeridos para actualizar...");
        return;
    }
    data={
        noIncidencia: id,
        nombre: nombre,
        ubicacion: ubicacion,
        usuario: usuario
    }
    $.ajax({
        url:"http://localhost:8080/api/incidencia/actualizar/" + id,
        type:"PUT",
        data: JSON.stringify(data),
        contentType:"application/json",
        success: function(){
            $("#actualizarModal").modal("hide");
            $('#noIncidenciaAC').val('')
            $("#nombreAC").val('');
            $("#ubicacionAC").val('');
            $("#usuarioAC").val('');
            findAll()
        },
        error: function(xhr) {
        }  
    })
}

function eliminarIncidencia(noIncidencia){
    $("#confirmarEliminacion").off("click").on("click", function(){
        $.ajax({
            url: "http://localhost:8080/api/incidencia/eliminar/"+noIncidencia,
            type: "DELETE",
            success: function(){
                $("#tableid tbody").find("td:contains('" + noIncidencia + "')").closest("tr").remove();
                $("#exampleModal").modal("hide");   
            }
        })
    })
}
function cargarDatos(noIncidencia){
    $.ajax({
        url: "http://localhost:8080/api/incidencia/"+ noIncidencia,
        type: "GET",
        dataType: "json",
        success: function(respuesta){
            $("#actu input").val('');
            $("#noIncidenciaAC").val(respuesta.noIncidencia);
            $("#noIncidenciaAC").prop('disabled', true);
            $("#nombreAC").val(respuesta.nombre);
            $("#ubicacionAC").val(respuesta.ubicacion);
            $("#usuarioAC").val(respuesta.usuario.idUser);
        }
    })
}

document.getElementById("byid").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        buscarIncidenciaId(); 
    }
});