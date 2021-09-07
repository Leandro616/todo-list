package io.github.leandro616.todolist.dao;

import java.util.List;

import io.github.leandro616.todolist.model.ListaDeTarefas;

public interface ListaDeTarefasDao extends GenericDao<ListaDeTarefas, Integer> {
   ListaDeTarefas buscarPorId(Integer id);
   List<ListaDeTarefas> listar(Integer idUsuario);
}
