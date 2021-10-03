package io.github.leandro616.todolist.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.leandro616.todolist.model.dao.TarefaDao;
import io.github.leandro616.todolist.model.entity.ListaDeTarefas;
import io.github.leandro616.todolist.model.entity.Tarefa;
import io.github.leandro616.todolist.model.exception.EntidadeNaoEncontradaException;
import io.github.leandro616.todolist.model.exception.NegocioException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class TarefaService {
   
   private final TarefaDao dao;
   private final ListaDeTarefasService listaService;

   public void salvar(Integer idLista, Tarefa tarefa) {       
      ListaDeTarefas lista = listaService
         .obterPorId(idLista);
      tarefa.setLista(lista);
      dao.salvar(tarefa);
   }

   public List<Tarefa> listar(Integer idLista) {
      listaService.obterPorId(idLista);
      return dao.listar(idLista);
   }

   public void atualizar(Integer id, Tarefa tarefaAtualizada) {
      Tarefa tarefa = obterPorId(id);

      tarefa.setDescricao(tarefaAtualizada.getDescricao());
      tarefa.setDtConclusao(tarefaAtualizada.getDtConclusao());
      dao.atualizar(tarefa);
   }

   public void deletar(Integer id) {
      dao.deletar(obterPorId(id));
   }

   public Tarefa obterPorId(Integer id) {
      if (dao.buscarPorId(id) == null) {
         throw new EntidadeNaoEncontradaException("Tarefa não encontrada");
      }

      return dao.buscarPorId(id);
   }

   public void finalizar(Integer id) {
      Tarefa tarefa = obterPorId(id);

      if (tarefa.getFinalizada()) {
         throw new NegocioException("Tarefa já está finalizada");
      }

      tarefa.setFinalizada(true);
      dao.salvar(tarefa);
   }

}
