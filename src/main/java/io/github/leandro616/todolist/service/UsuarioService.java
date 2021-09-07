package io.github.leandro616.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.leandro616.todolist.dao.UsuarioDao;
import io.github.leandro616.todolist.exception.UsuarioCadastradoException;
import io.github.leandro616.todolist.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService {

   @Autowired
   private UsuarioDao dao;

   // esse método busca o usuário no banco de dados 
   // e retorna um UserDatails do usuario para SecurityConfig
   @Override
   public UserDetails loadUserByUsername(String username) 
         throws UsernameNotFoundException {
      
      Usuario usuario = dao
         .buscarPorEmail(username)
         .orElseThrow(()
            -> new UsernameNotFoundException("Usuário não encontrado"));
      
      return User.builder()
         .username(usuario.getEmail())
         .password(usuario.getSenha())
         .roles("USER")
         .build();
   }

   public void salvar(Usuario usuario) throws UsuarioCadastradoException {   
      boolean usuarioExistente = dao
            .buscarPorEmail(usuario.getEmail())
            .isPresent();

      if (usuarioExistente) {
         throw new UsuarioCadastradoException(usuario.getEmail());
      }

      dao.salvar(usuario);
   }

   public Usuario usuarioAutenticado() {

      String email = SecurityContextHolder
         .getContext().getAuthentication().getName();

      return dao.buscarPorEmail(email).get();
   }

}
