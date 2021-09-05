package io.github.leandro616.todolist.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.leandro616.todolist.dao.UsuarioDao;
import io.github.leandro616.todolist.exception.UsuarioCadastradoException;
import io.github.leandro616.todolist.model.entity.Usuario;

@Service
public class UsuarioService {
   @Autowired
   private UsuarioDao dao;

   public void salvar(Usuario usuario) {
      
      if (dao.buscarPorEmail(usuario.getEmail()) != null) {
         throw new UsuarioCadastradoException(usuario.getEmail());
      }

      dao.salvar(usuario);
   }
}
