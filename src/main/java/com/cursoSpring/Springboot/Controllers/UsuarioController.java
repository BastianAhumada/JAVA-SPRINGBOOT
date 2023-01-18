package com.cursoSpring.Springboot.Controllers;

import com.cursoSpring.Springboot.Dao.UsuarioDao;
import com.cursoSpring.Springboot.Models.Usuario;
import com.cursoSpring.Springboot.Utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController  {
    //Con autowired el objeto queda instanciado de manera global y listo para su uso
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil ;
    @RequestMapping(value="api/usuario/{id}")
    //Pathvariable se utiliza como anotacion para pasar valores como parametros
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Bastian");
      return usuario;
    }

    @RequestMapping(value = "api/usuario", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){

        return usuarioDao.getUsuarios();
    }
    private boolean validarToken(String token){
        String usuarioid = jwtUtil.getKey(token);
        return usuarioid != null;
    }
    @RequestMapping(value = "api/usuario", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        String hash = argon.hash(1, 1024, 1, usuario.getContraseña());
        usuario.setContraseña(hash);
        usuarioDao.registrar(usuario);
    }
    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token,@PathVariable Long id){
        if (validarToken(token)){
            return;
        }
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/login", method = RequestMethod.GET)
    public void ingresar(){

        usuarioDao.ingresar();
    }
    }

