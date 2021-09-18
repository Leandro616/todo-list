package io.github.leandro616.todolist.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.leandro616.todolist.dto.ListaDeTarefasDto;
import io.github.leandro616.todolist.dto.ListaDeTarefasInput;
import io.github.leandro616.todolist.exception.ListaDefaultException;
import io.github.leandro616.todolist.exception.ListaNaoEncontradaException;
import io.github.leandro616.todolist.model.ListaDeTarefas;
import io.github.leandro616.todolist.service.ListaDeTarefasService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/listas-de-tarefas")
public class ListaController {
   
   private final ListaDeTarefasService service;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void criar(@RequestBody @Valid ListaDeTarefasInput listaInput) {
      ListaDeTarefas lista = new ListaDeTarefas();
      lista.setNome(listaInput.getNome());
      service.salvar(lista);
   }

   @GetMapping
   public List<ListaDeTarefasDto> listar() {
      return service.listar()
         .stream()
         .map(lista -> new ListaDeTarefasDto(
            lista.getIdLista(), lista.getNome(), lista.isDefault()))
         .collect(Collectors.toList());
   }

   @PutMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void atualizar(@PathVariable Integer id, 
         @RequestBody @Valid ListaDeTarefasInput listaInput) {

      try {
         ListaDeTarefas lista = new ListaDeTarefas();
         lista.setNome(listaInput.getNome());
         service.atualizar(id, lista);

      } catch (ListaNaoEncontradaException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            e.getMessage());
      } catch (ListaDefaultException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            e.getMessage());
      }
      
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deletar(@PathVariable Integer id) {

      try {

         service.deletar(id);

      } catch (ListaNaoEncontradaException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
            e.getMessage());
      } catch (ListaDefaultException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            e.getMessage());
      }
      
   }
}
