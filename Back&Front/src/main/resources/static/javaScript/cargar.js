$(document).ready(function() {
    let listaPri = document.querySelector('#prioridad')
    listaPri.innerHTML = ''
    let listaPriAC = document.querySelector('#prioridadAC')
    listaPriAC.innerHTML = ''
    $.ajax({
        url: "http://localhost:8080/prioridades/listar",
        type: "GET",
        datatype: "JSON",
        success: function(respuesta) {
            listaPri.innerHTML += '<option selected> </option>'
            listaPriAC.innerHTML += '<option selected> </option>'
            for(i=0;i<respuesta.length;i++){
                listaPri.innerHTML += '<option value="' +respuesta[i].prioridadID +'">'
                + respuesta[i].prioridadID+'  '
                + respuesta[i].tipoPrioridad +'</option>';

                listaPriAC.innerHTML += '<option value="' +respuesta[i].prioridadID +'">'
                + respuesta[i].prioridadID+'  '
                + respuesta[i].tipoPrioridad +'</option>';
            }
        }
    });

       let listaEst = document.querySelector('#usuarioback')
       listaEst.innerHTML = ''
       let listaEstAC = document.querySelector('#estadoAC')
       listaEstAC.innerHTML = ''
       $.ajax({
           url: "http://localhost:8080/api/usuario/listar",
           type: "GET",
           datatype: "JSON",
           success: function(respuesta) {
               listaEst.innerHTML += '<option selected> </option>'
               listaEstAC.innerHTML += '<option selected> </option>'
               for(i=0;i<respuesta.length;i++){
                listaUsr.innerHTML += '<option value="' +respuesta[i].usuarioid +'">'
                + respuesta[i].usuarioid+'  '
                + respuesta[i].nombre+'  '
                + respuesta[i].apellido+'  '
                + respuesta[i].documento +'</option>';

                listaUsrAC.innerHTML += '<option value="' +respuesta[i].usuarioid +'">'
                + respuesta[i].usuarioid+'  '
                + respuesta[i].nombre+'  '
                + respuesta[i].apellido+'  '
                + respuesta[i].documento +'</option>';
            }

        }
    });


    let listaEst = document.querySelector('#estado')
    listaEst.innerHTML = ''
    let listaEstAC = document.querySelector('#estadoAC')
    listaEstAC.innerHTML = ''
    $.ajax({
        url: "http://localhost:8080/estados/listar",
        type: "GET",
        datatype: "JSON",
        success: function(respuesta) {
            listaEst.innerHTML += '<option selected> </option>'
            listaEstAC.innerHTML += '<option selected> </option>'
            for(i=0;i<respuesta.length;i++){
                listaEst.innerHTML += '<option value="' +respuesta[i].estadoID +'">'
                + respuesta[i].estadoID+'  '
                + respuesta[i].tipoEstado +'</option>';

                listaEstAC.innerHTML += '<option value="' +respuesta[i].estadoID +'">'
                + respuesta[i].estadoID+'  '
                + respuesta[i].tipoEstado +'</option>';
            }
        }
    });
}
);