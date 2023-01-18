package com.cursoSpring.Springboot.Dao;

import com.cursoSpring.Springboot.Models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    void ingresar();

    Usuario obtenerusuarioporcredenciales(Usuario usuario);
}
