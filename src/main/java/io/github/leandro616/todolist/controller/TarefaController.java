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

import io.github.leandro616.todolist.dto.TarefaDto;
import io.github.leandro616.todolist.model.Tarefa;
import io.github.leandro616.todolist.service.TarefaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

   private final TarefaService service;
   
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void criar(@RequestBody @Valid TarefaDto dto) {
      
      try {

         Tarefa tarefa = new Tarefa();
         tarefa.setDescricao(dto.getDescricao());
         tarefa.setDtConclusao(dto.getDtConclusao());
   
         service.salvar(dto.getIdLista(), tarefa);
         
      } catch (Exception e) {
         throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   @GetMapping("{idLista}")
   public List<Tarefa> listar(@PathVariable Integer idLista) {
      try {
         return service.listar(idLista)
            .stream()
            .map(tarefa -> {
               Tarefa t = tarefa;
               t.getLista().setUsuario(null);
               return t;
            })
            .collect(Collectors.toList());

      } catch (Exception e) {
         throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   @PutMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void atualizar(@PathVariable Integer id, 
         @RequestBody @Valid TarefaDto dto) {
             
      try {
         Tarefa tarefaAtualizada = new Tarefa();
         tarefaAtualizada.setDescricao(dto.getDescricao());
         tarefaAtualizada.setDtConclusao(dto.getDtConclusao());
   
         service.atualizar(id, tarefaAtualizada);

      } catch (Exception e) {
         throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deletar(@PathVariable Integer id) {
      try {
         service.deletar(id);

      } catch (Exception e) {
         throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }
   
}
