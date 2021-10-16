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

import io.github.leandro616.todolist.api.dto.TarefaDto;
import io.github.leandro616.todolist.api.dto.TarefaInput;
import io.github.leandro616.todolist.model.entity.Tarefa;
import io.github.leandro616.todolist.model.service.TarefaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
// As tarefas serão um sub-recurso de lista de tarefas
@RequestMapping("/api/listas/{idLista}/tarefas")
public class TarefaController {

   private final TarefaService service;
   private final ModelMapper mapper;
   
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void criar(@RequestBody @Valid TarefaInput tarefaInput,
         @PathVariable Integer idLista) {
      
      Tarefa tarefa = toEntity(tarefaInput);
      service.salvar(idLista, tarefa);
   }

   @GetMapping("/{id}")
   public TarefaDto getTarefa(@PathVariable Integer id) {
      return toDto(service.obterPorId(id));
   }

   @GetMapping()
   public List<TarefaDto> listar(@PathVariable Integer idLista) {
      return service.listar(idLista)
         .stream()
         .map(tarefa -> toDto(tarefa))
         .collect(Collectors.toList());
   }

   @PutMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void atualizar(@PathVariable Integer id, 
         @RequestBody @Valid TarefaInput input) {
             
      Tarefa tarefa = toEntity(input);
      service.atualizar(id, tarefa);
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deletar(@PathVariable Integer id) {
     
      service.deletar(id);
   }

   @PutMapping("/{id}/finalizacao")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void finalizar(@PathVariable Integer id) {
      service.finalizar(id);
   }

   private TarefaDto toDto(Tarefa tarefa) {
      return mapper.map(tarefa, TarefaDto.class);
   }

   private Tarefa toEntity(TarefaInput input) {     
      return mapper.map(input, Tarefa.class);
   }
   
}
