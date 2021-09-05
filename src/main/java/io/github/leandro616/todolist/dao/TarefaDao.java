package io.github.leandro616.todolist.dao;

import java.util.List;

import io.github.leandro616.todolist.model.entity.Tarefa;

public interface TarefaDao extends GenericDao<Tarefa, Integer> {
   List<Tarefa> listar(Integer idLista);
}
