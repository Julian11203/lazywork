function mostrarInfoAlUsuario() {
    $.get("/inicio", function(data){
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
            $("#rolActual").html(data.rolActual);
            $("#roleUser").html(data.roleUser);
            $("#roleAdmin").html(data.roleAdmin);
            if (data.rolActual == 'USER' || data.rolActual == 'ADMIN') {
                iniciarSesionBtn.style.display = 'none';
                if(data.rolActual == 'USER'){
                    gestionAdministrador.style.display = 'none';
                }
                else if(data.rolActual == 'ADMIN'){
                    gestionUsuario.style.display = 'none';
                }
            }
            else{
                GestionUsuarioLogeado.style.display = 'none';
            }
        }
    });
}