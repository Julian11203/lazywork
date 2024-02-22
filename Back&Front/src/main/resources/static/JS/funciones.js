function mostrarInfoAlUsuario() {
    $.get("/user", function(data){
        var gestionUsuario = document.querySelector("#GestionUser");
        var gestionAdministrador = document.querySelector("#GestionAdmin");
        var iniciarSesionBtn = document.querySelector("#iniciarSesionBtn");
        var cerrarSesionBtn = document.querySelector("#cerrarSesionBtn");
        var nombreCompleto = document.querySelector("#nombreCompleto");

        if(data!=null){
            $("#nombreCompleto").html(data.nombreCompleto);
            $("#correoElectronico").html(data.correoElectronico);
            $("#rolDeUsuario").html(data.rolDeUsuario);
            if (data.rolDeUsuario == 'USER') {
                gestionAdministrador.style.display = 'none';
                iniciarSesionBtn.style.display = 'none';
            }
            else if (data.rolDeUsuario == 'ADMIN') {
                gestionUsuario.style.display = 'none';
                iniciarSesionBtn.style.display = 'none';
            }
            else{
                cerrarSesionBtn.style.display = 'none';
                gestionAdministrador.style.display = 'none';
                gestionUsuario.style.display = 'none';
            }
        }
    });
}