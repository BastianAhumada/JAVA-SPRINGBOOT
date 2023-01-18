package com.cursoSpring.Springboot.Controllers;

import com.cursoSpring.Springboot.Dao.UsuarioDao;
import com.cursoSpring.Springboot.Models.Usuario;
import com.cursoSpring.Springboot.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authcontrollers {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value = "api/login" ,method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
        Usuario usuariologeado = usuarioDao.obtenerusuarioporcredenciales(usuario);
    if(usuariologeado != null) {
    //String.vALUEOF() transforma en string
        String token = jwtUtil.create(String.valueOf(usuariologeado.getId()), usuariologeado.getEmail());
        return token;
    }
    return "Fial";
    }
}
