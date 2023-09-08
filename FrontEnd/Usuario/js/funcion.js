document.querySelector('#boton').addEventListener('click', guardarUsuario);

function guardarUsuario() {

    var id = document.querySelector('#id').value
    var nombre = document.querySelector('#nombre').value
    var pais = document.querySelector('#pais').value
    var fecha = document.querySelector('#fecha').value
    var correo = document.querySelector('#correo').value

    agregarUsuario(id, nombre, pais, fecha, correo)



}