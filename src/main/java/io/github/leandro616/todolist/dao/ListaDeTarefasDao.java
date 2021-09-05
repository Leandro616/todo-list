package io.github.leandro616.todolist.dao;

import java.util.List;

import io.github.leandro616.todolist.model.entity.ListaDeTarefas;

public interface ListaDeTarefasDao extends GenericDao<ListaDeTarefas, Integer> {
   List<ListaDeTarefas> listar(Integer idUsuario);
}
