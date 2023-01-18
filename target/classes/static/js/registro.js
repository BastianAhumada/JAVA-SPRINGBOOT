$(document).ready(function() {
    alert("Cargando")
});
//Una funcion asyncronica se refiere a que no se hace en tiempo real
async function registrarusuario(){
let datos ={}
datos.nombre = document.getElementById('Nombre').value;
datos.apellido = document.getElementById('Apellido').value;
datos.email = document.getElementById('Email').value;
datos.telefono = 2343;
datos.contraseña = document.getElementById('Password').value;
repetir = document.getElementById('ConfirmarPass').value;

if (datos.contraseña != repetir){
alert("Password difentes")
return;
}
/*Conexion con la url, esto se vincula cn el mapping de las response a la url usuario
Este fetch ejecuta lo modelado en el controlador*/
  const request = await fetch("api/usuario", {
     //Post se utilizar para insertar o crear una nueva entidad
     method: 'POST',
     headers: {
       'Accept': 'application/json',
       'Content-Type': 'application/json'
     },
    //Json.stringfy aggara cualquier objeto de js y lo transforma a un json
    body: JSON.stringify(datos)
   });
   alert("La cuenta fue creada con exito");
   //Redireccionar a un sitio con javascript
    window.location.href = 'login.html';
  /*Content es lo que devuelve la funcion, lo devuelve en formato json , reqest.json nos trae la informacion
  que venia desde la url*/
     const usuarios = await request.json();
}

