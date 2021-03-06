package io.github.leandro616.todolist.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import io.github.leandro616.todolist.api.dto.ListaDeTarefasDto;
import io.github.leandro616.todolist.api.dto.ListaDeTarefasInput;
import io.github.leandro616.todolist.model.entity.ListaDeTarefas;
import io.github.leandro616.todolist.model.service.ListaDeTarefasService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/listas")
public class ListaController {
   
   private final ListaDeTarefasService service;
   private final ModelMapper mapper;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void criar(@RequestBody @Valid ListaDeTarefasInput listaInput) {
      ListaDeTarefas lista = toEntity(listaInput);
      service.salvar(lista);
   }

   @GetMapping("/{id}")
   public ListaDeTarefasDto getLista(@PathVariable Integer id) {
      return toDto(service.obterPorId(id));
   }

   @GetMapping
   public List<ListaDeTarefasDto> listar() {
      return service.listar()
         .stream()
         .map(lista -> toDto(lista))
         .collect(Collectors.toList());
   }

   @PutMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void atualizar(@PathVariable Integer id, 
         @RequestBody @Valid ListaDeTarefasInput listaInput) {

      ListaDeTarefas lista = toEntity(listaInput);
      service.atualizar(id, lista);      
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deletar(@PathVariable Integer id) {

      service.deletar(id);
   }

   private ListaDeTarefas toEntity(ListaDeTarefasInput input) {
      return mapper.map(input, ListaDeTarefas.class);
   }

   private ListaDeTarefasDto toDto(ListaDeTarefas lista) {
      return mapper.map(lista, ListaDeTarefasDto.class);
   }
}
