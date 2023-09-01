$(document).ready(function() {
    let listaInc = document.querySelector('#incidencia')
            listaInc.innerHTML = ''
            $.ajax({
                url: "http://localhost:8080/api/incidencia/listar",
                type: "GET",
                datatype: "JSON",
                success: function(respuesta) {
                    for(i=0;i<respuesta.length;i++){
                        listaInc.innerHTML += '<option value="' +respuesta[i].noIncidencia +'">'
                        + respuesta[i].noIncidencia +'  '
                        + respuesta[i].nombre +' '+ respuesta[i].ubicacion+'</option>';
                    }     
                }
    
            });

            let listaPri = document.querySelector('#prioridad')
            listaPri.innerHTML = ''
            $.ajax({
                url: "http://localhost:8080/api/prioridad/listar",
                type: "GET",
                datatype: "JSON",
                success: function(respuesta) {
                    for(i=0;i<respuesta.length;i++){
                        listaPri.innerHTML += '<option value="' +respuesta[i].idPrioridad +'">'
                        + respuesta[i].idPrioridad+'  '
                        + respuesta[i].tipoPrioridad +'</option>';
                    }     
                }
    
            });
    }
);