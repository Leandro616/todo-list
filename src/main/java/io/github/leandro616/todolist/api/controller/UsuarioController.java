package io.github.leandro616.todolist.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.leandro616.todolist.model.entity.Usuario;
import io.github.leandro616.todolist.model.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

   private final UsuarioService service;
   
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void cadastrar(@RequestBody @Valid Usuario usuario) {
      
      service.salvar(usuario);
      service.criarListaDefault(usuario.getEmail());
   }
}
