$(document).ready(function() {
    let listaInc = document.querySelector('#incidencia')
            listaInc.innerHTML = ''
            $.ajax({
                url: "http://localhost:8080/api/incidencia/listar",
                type: "GET",
                datatype: "JSON",
                success: function(respuesta) {
                    for(i=0;i<respuesta.length;i++){
                        listaInc.innerHTML += '<option value="' +respuesta[i].nRegistro +'">'
                        + respuesta[i].noIncidencia +'  '
                        + respuesta[i].nombre +' '+ respuesta[i].ubicacion+'</option>';
                    }     
                }
    
            });
    
});