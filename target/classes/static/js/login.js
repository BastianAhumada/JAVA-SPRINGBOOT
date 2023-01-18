$(document).ready(function() {
    alert("Cargando")
});
//Una funcion asyncronica se refiere a que no se hace en tiempo real
async function iniciarsesion(){

let datos = {}
datos.email = document.getElementById('User').value;
datos.nombre = document.getElementById('Password').value;
//datos.nombreatributoModels
datos.contraseña = document.getElementById('Password').value;
console.log(datos.email);
console.log(datos.nombre);
console.log("Contra" + datos.contraseña);

/*Conexion con la url, esto se vincula cn el mapping de las response a la url usuario
Este fetch ejecuta lo modelado en el controlador*/
  const request = await fetch("api/login", {
     //Post se utilizar para insertar o crear una nueva entidad
     method: 'POST',
     headers: {
       'Accept': 'application/json',
       'Content-Type': 'application/json'
     },
    //Json.stringfy aggara cualquier objeto de js y lo transforma a un json
    body: JSON.stringify(datos)
   });
  /*Content es lo que devuelve la funcion, lo devuelve en formato json , reqest.json nos trae la informacion
  que venia desde la url*/
  //reuqest.text devuelve un texto como respuesta al servidor
     const response = await request.text();
     if(response != 'Fial'){
     //Guarda informacion en el localstorage, esto previamente se debe enviar para validar
     //El inicio de sesion
     localStorage.token = response;
     localStorage.email = datos.email;
     window.location.href = 'index.html'
     }
     if(response == "Fial"){
     alert("Fallo")
     }

}

