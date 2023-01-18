package com.cursoSpring.Springboot.Dao;

import com.cursoSpring.Springboot.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List <Usuario> getUsuarios() {
        String query = "FROM Usuario";
        List<Usuario> Listusers = entityManager.createQuery(query).getResultList();
        return Listusers;
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        //Ingresar un datos a la base de datos previamente configuado en js y llamado desde el controller
    entityManager.merge(usuario);
    }

    @Override
    public void ingresar() {

    }
    @Override
    public Usuario obtenerusuarioporcredenciales(Usuario usuario) {
        System.out.println(usuario.getContraseña());
        System.out.println(usuario.getEmail());
        String query = "FROM Usuario where email = :email";
         List<Usuario> list = entityManager.createQuery(query)
                 .setParameter("email", usuario.getEmail())
                 .getResultList();
          System.out.println(usuario.getEmail());
          if(list.isEmpty()){
              return null;
          }
         //Obtener la contraseña de la lista el cual solo trae un dato
         String passwordhash = list.get(0).getContraseña();
         //Verificar contraseña con Argon
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        //Verificar que las contraseñas coincidadn con Argon
       if (argon.verify(passwordhash,usuario.getContraseña())){
           return list.get(0);

       }
       //Credenciales incorrectas
       return null;






    }

}
