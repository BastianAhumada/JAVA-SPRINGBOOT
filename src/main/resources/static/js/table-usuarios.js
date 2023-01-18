// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarusuario()
    actualizaruser()
  $('#table-usuarios').DataTable();


});
function actualizaruser(){
document.getElementById('txt-username').outerHTML = localStorage.email;
}

//Una funcion asyncronica se refiere a que no se hace en tiempo real
async function cargarusuario(){
//Conexion con la url, esto se vincula cn el mapping de las response a la url usuario
//Este fetch ejecuta lo modelado en el controlador
  const request = await fetch("api/usuario", {
     method: 'GET',
     headers: getheader()
   });
  /*Content es lo que devuelve la funcion, lo devuelve en formato json , reqest.json nos trae la informacion
  que venia desde la url*/
     const usuarios = await request.json();
     console.log(usuarios);
  //Creamos un listado para almacenar los valores dell foto
  let listadohtml = '';
//Ejecuta el bloque de codigo por cada elemento que exista
  for (let usuario of usuarios){
  let botonEliminar = '<a href="#" onclick= "eliminarusuario(' + usuario.id + ') " class="btn btn-danger btn-circle"> <i class="fas fa-trash"></i></a>'
    let trusuarios1 = '<tr> <td>'+ usuario.id + ' </td> <td>'+usuario.nombre  +'</td><td>'+ usuario.apellido +'</td><td>Edinburgh</td><td>61</td> <td>'+ botonEliminar +'<a class=" btn btn-info btn-circle"> <i class=" fas fa-info-circle"></i></a></td> </tr>'
    listadohtml += trusuarios1;
  }
  document.querySelector("#table-usuarios tbody").outerHTML = listadohtml;
};
function getheader(){
return {
             'Accept': 'application/json',
              'Content-Type': 'application/json',
              'Authorization': localStorage.token,
                        }
}
async function eliminarusuario(id){
//Confirm muestra una alerta en pantalla devolviendo true o false
    if(confirm('Desea eliminar este usuario?')){
        //Llamado al servidor con async await
              const request = await fetch('api/usuario/' + id, {
                 method: 'DELETE',
                 headers:getheader()
               });
               //Este metodo reload() hace que la pagina se refresque
               location.reload();
    }


}