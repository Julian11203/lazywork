$(document).ready(function () {
    let listaInci = document.querySelector('#incidencia')
    listaInci.innerHTML = ""
    let listaInciAC = document.querySelector('#incidenciaAC')
    listaInciAC.innerHTML = ""
    
    // Función para cargar la lista de Incidencias en el formulario de registro
    $.ajax({
        url: "http://localhost:8080/incidencia/lisar",
        type: "GET",
        dataType: "JSON",
        success: function (respuesta) {
            listaInci.innerHTML += '<option selected> </option>'
            listaInciAC.innerHTML += '<option selected> </option>'
            for (i = 0; i < respuesta.length; i++) {
                listaInci.innerHTML += '<option value="' + respuesta[i].incidenciaID + '">'
                    + respuesta[i].incidenciaID + ' '
                    + respuesta[i].NombreIncidencia + ' '
                    + respuesta[i].Descripcion + ' '
                    + respuesta[i].Ubicacion + '</option>';

                listaInciAC.innerHTML += '<option value="' + respuesta[i].incidenciaID + '">'
                    + respuesta[i].incidenciaID + ' '
                    + respuesta[i].NombreIncidencia + ' '
                    + respuesta[i].Descripcion + ' '
                    + respuesta[i].Ubicacion + '</option>';
            }
        }
    });

    // Función para cargar la lista de Estados en el formulario de registro
    let listaEst = document.querySelector('#estado')
    listaEst.innerHTML = ''
    let listaEstAC = document.querySelector('#estadoAC')
    listaEstAC.innerHTML = ''
    $.ajax({
        url: "http://localhost:8080/estados/listar",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            listaEst.innerHTML += '<option selected> </option>'
            listaEstAC.innerHTML += '<option selected> </option>'
            for (i = 0; i < respuesta.length; i++) {
                listaEst.innerHTML += '<option value="' + respuesta[i].estadoID + '">'
                    + respuesta[i].estadoID + ' '
                    + respuesta[i].tipoEstado + '</option>';

                listaEstAC.innerHTML += '<option value="' + respuesta[i].estadoID + '">'
                    + respuesta[i].estadoID + ' '
                    + respuesta[i].tipoEstado + '</option>';
            }
        }
    });
});
