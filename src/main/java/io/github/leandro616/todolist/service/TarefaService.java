package io.github.leandro616.todolist.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.leandro616.todolist.dao.TarefaDao;
import io.github.leandro616.todolist.exception.ListaNaoEncontradaException;
import io.github.leandro616.todolist.exception.TarefaNaoEncontradaException;
import io.github.leandro616.todolist.model.ListaDeTarefas;
import io.github.leandro616.todolist.model.Tarefa;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class TarefaService {
   
   private final TarefaDao dao;
   private final ListaDeTarefasService listaService;

   public void salvar(Integer idLista, Tarefa tarefa) 
         throws ListaNaoEncontradaException {
      ListaDeTarefas lista = listaService.obterPorId(idLista);
      tarefa.setLista(lista);

      dao.salvar(tarefa);
   }

   public List<Tarefa> listar(Integer idLista) 
         throws ListaNaoEncontradaException {
      return dao.listar(idLista);
   }

   public void atualizar(Integer id, Tarefa tarefaAtualizada) {
      Tarefa tarefa = dao.buscarPorId(id);

      if (tarefa == null) {
         throw new TarefaNaoEncontradaException();
      }

      tarefa.setDescricao(tarefaAtualizada.getDescricao());
      tarefa.setDtConclusao(tarefaAtualizada.getDtConclusao());
      dao.atualizar(tarefa);
   }

   public void deletar(Integer id) {
      Tarefa tarefa = dao.buscarPorId(id);
      if (tarefa == null) {
         throw new TarefaNaoEncontradaException();
      }

      dao.deletar(tarefa);
   }

}
