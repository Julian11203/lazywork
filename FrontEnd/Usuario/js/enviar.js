$(document).ready(function() {

    //alert("Aquiiii con jquery");

    $('#listar').on('click', function() {
        let tabla = document.querySelector('#tabla')
        tabla.innerHTML = ''
        $.ajax({
            url: "http://localhost:8080/listarUsuarios",
            type: "GET",
            datatype: "JSON",
            success: function(respuesta) {
                console.log(respuesta)
                tabla.innerHTML = '';
                for (i = 0; i <= respuesta.length; i++) {
                    tabla.innerHTML += '<tr><td>' + respuesta[i].id +
                        '</td><td>' + respuesta[i].nombre +
                        '</td><td>' + respuesta[i].pais +
                        '</td><td>' + respuesta[i].fecha +
                        '</td><td>' + respuesta[i].correo +
                        '</td></tr>';
                }
            }

        })
    });


    $('#boton').on('click', function() {

        let datos = {
            mid: parseInt($('#id').val()),
            nobre: $('#nombre').val(),
            pais: $('#pais').val(),
            fecha: $('#fecha').val(),
            correo: $('#correo').val()
        }
        let datosEnvio = JSON.stringify(datos);
        $.ajax({
            url: "http://localhost:8080/AgregarUsuario",
            type: "POST",
            data: datosEnvio,
            contentType: "application/JSON",
            datatype: JSON,
            success: function(respuesta) {
                console.log(respuesta);
                alert(respuesta)

            }
        })

    });

    $('#buscar').on('click', function() {
        let cod = parseInt($('#codigo').val());
        $.ajax({
            url: "http://localhost:8080/buscarUsuario/" + cod,
            type: "GET",
            datatype: "JSON",
            success: function(respuesta) {

                if (respuesta) {
                    alert("Bienvenido " +
                        respuesta.nombre);
                } else {
                    alert("No se encontro el usuario");
                }
            }
        })

    })



});