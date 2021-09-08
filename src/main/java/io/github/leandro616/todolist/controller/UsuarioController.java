package io.github.leandro616.todolist.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.leandro616.todolist.exception.UsuarioCadastradoException;
import io.github.leandro616.todolist.model.Usuario;
import io.github.leandro616.todolist.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

   private final UsuarioService service;
   
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void cadastrar(@RequestBody @Valid Usuario usuario) {

      try {
         service.salvar(usuario);
         
      } catch (UsuarioCadastradoException e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }
}
