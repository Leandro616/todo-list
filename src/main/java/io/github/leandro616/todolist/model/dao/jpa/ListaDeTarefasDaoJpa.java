package io.github.leandro616.todolist.model.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.leandro616.todolist.model.dao.ListaDeTarefasDao;
import io.github.leandro616.todolist.model.entity.ListaDeTarefas;

@Repository
public class ListaDeTarefasDaoJpa 
      extends GenericDaoJpa<ListaDeTarefas, Integer> 
      implements ListaDeTarefasDao {

   @Override
   public List<ListaDeTarefas> listar(Integer idUsuario) {

      return getManager()
         .createQuery(
            "select l from ListaDeTarefas l where l.usuario.id = :id", 
            ListaDeTarefas.class)
         .setParameter("id", idUsuario)
         .getResultList();
   }

   @Override
   public ListaDeTarefas buscarPorId(Integer id) {
      
      return getManager().find(ListaDeTarefas.class, id);
   }
   
   
}
