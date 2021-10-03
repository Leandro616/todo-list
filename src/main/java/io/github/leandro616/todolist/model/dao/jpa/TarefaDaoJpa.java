package io.github.leandro616.todolist.model.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.leandro616.todolist.model.dao.TarefaDao;
import io.github.leandro616.todolist.model.entity.Tarefa;

@Repository
public class TarefaDaoJpa 
      extends GenericDaoJpa<Tarefa, Integer> implements TarefaDao {

   @Override
   public List<Tarefa> listar(Integer idLista) {
      return getManager()
         .createQuery("select t from Tarefa t where t.lista.id = :id",
            Tarefa.class)
         .setParameter("id", idLista)
         .getResultList();
   }
   
   @Override
   public Tarefa buscarPorId(Integer id) {
      
      return getManager().find(Tarefa.class, id);
   }
}
