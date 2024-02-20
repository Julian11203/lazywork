function mostrarInfoDelUsuario() {
    $.get("/user", function(data){
        var gestionVisitante = document.querySelector("#GestionVisitante");
        var gestionUsuario = document.querySelector("#GestionUser");
        var gestionAdministrador = document.querySelector("#GestionAdmin");

        if(data!=null){
            $("#nombreCompleto").html(data.nombreCompleto);
            $("#correoElectronico").html(data.correoElectronico);
            $("#rolDeUsuario").html(data.rolDeUsuario);
            if (data.rolDeUsuario == 'USER') {
                gestionAdministrador.style.display = 'none';
                gestionVisitante.style.display = 'none';
            }
            else if (data.rolDeUsuario == 'ADMIN') {
                gestionUsuario.style.display = 'none';
                gestionVisitante.style.display = 'none';
            }
        }
        else{
            gestionAdministrador.style.display = 'none';
            gestionUsuario.style.display = 'none';
        }
    });
}