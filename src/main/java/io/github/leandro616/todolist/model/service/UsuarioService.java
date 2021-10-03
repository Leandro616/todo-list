package io.github.leandro616.todolist.model.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.leandro616.todolist.model.dao.ListaDeTarefasDao;
import io.github.leandro616.todolist.model.dao.UsuarioDao;
import io.github.leandro616.todolist.model.entity.ListaDeTarefas;
import io.github.leandro616.todolist.model.entity.Usuario;
import io.github.leandro616.todolist.model.exception.NegocioException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UsuarioService implements UserDetailsService {

   private final UsuarioDao dao;
   private final ListaDeTarefasDao listaDao;

   @Autowired
   private PasswordEncoder encoder;

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

   public void salvar(Usuario usuario) {   
      boolean usuarioExistente = dao
            .buscarPorEmail(usuario.getEmail())
            .isPresent();

      if (usuarioExistente) {
         throw new NegocioException(
            "Usuario já cadastrado para o email " + usuario.getEmail());
      }
     /*  usuario.setSenha(
         encoder.encode(usuario.getSenha()));
      dao.salvar(usuario);
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      System.out.println(encoder.getClass().getName()); */
      for (Class<?> c : encoder.getClass().getInterfaces())
         System.out.println(c.getName());
   } 

   public Usuario usuarioAutenticado() {

      String email = SecurityContextHolder
         .getContext().getAuthentication().getName();

      return dao.buscarPorEmail(email).get();
   }

   public void criarListaDefault(String email) {
      Usuario usuario = dao.buscarPorEmail(email).get();
      ListaDeTarefas lista = new ListaDeTarefas();
      lista.setNome("Tarefas");
      lista.setPadrao(true);
      lista.setUsuario(usuario);
      listaDao.salvar(lista);
   }
   
}
