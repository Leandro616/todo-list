package io.github.leandro616.todolist.dao.jpa;

import org.springframework.stereotype.Repository;

import io.github.leandro616.todolist.dao.UsuarioDao;
import io.github.leandro616.todolist.model.entity.Usuario;

@Repository
public class UsuarioDaoJpa 
      extends GenericDaoJpa<Usuario, Integer> implements UsuarioDao { 
   
   @Override
   public Usuario buscarPorEmail(String email) {
      return getManager()
         .createQuery("select u from Usuario u where u.email = :email", 
               Usuario.class)
         .setParameter("email", email)
         .getSingleResult();
   }

}
