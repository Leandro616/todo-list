package io.github.leandro616.todolist.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.leandro616.todolist.dao.TarefaDao;
import io.github.leandro616.todolist.model.Tarefa;

@Repository
public class TarefaDaoJpa 
      extends GenericDaoJpa<Tarefa, Integer> implements TarefaDao {

   @Override
   public List<Tarefa> listar(Integer idLista) {
      return getManager()
         .createQuery("select t from Tarefa t where t.lista.idLista = :id",
            Tarefa.class)
         .setParameter("id", idLista)
         .getResultList();
   }
   
   
}
