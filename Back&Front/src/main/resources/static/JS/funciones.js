function mostrarInfoAlUsuario() {
    $.get("/user", function(data){
        var GestionUsuarioNoLogeado = document.querySelector("#GestionUsuarioNoLogeado");
        var gestionUsuarioLogeado = document.querySelector("#GestionUsuarioLogeado");
        var gestionUsuario = document.querySelector("#GestionUser");
        var gestionAdministrador = document.querySelector("#GestionAdmin");
        var iniciarSesionBtn = document.querySelector("#iniciarSesionBtn");
        var cerrarSesionBtn = document.querySelector("#cerrarSesionBtn");
        var nombreCompleto = document.querySelector("#nombreCompleto");

        if(data!=null){
            $("#nombreCompleto").html(data.nombreCompleto);
            $("#correoElectronico").html(data.correoElectronico);
            $("#rolDeUsuario").html(data.rolDeUsuario);
            if (data.rolDeUsuario == 'USER' || data.rolDeUsuario == 'ADMIN') {
                if(data.rolDeUsuario == 'USER'){
                    gestionAdministrador.style.display = 'none';
                }
                else if(data.rolDeUsuario == 'ADMIN'){
                    gestionUsuario.style.display = 'none';
                }
            }
            else{
                GestionUsuarioLogeado.style.display = 'none';
            }
        }
    });
}