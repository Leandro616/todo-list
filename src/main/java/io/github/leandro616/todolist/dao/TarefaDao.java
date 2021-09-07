package io.github.leandro616.todolist.dao;

import java.util.List;

import io.github.leandro616.todolist.model.Tarefa;

public interface TarefaDao extends GenericDao<Tarefa, Integer> {
   Tarefa buscarPorId(Integer id);
   List<Tarefa> listar(Integer idLista);
}
