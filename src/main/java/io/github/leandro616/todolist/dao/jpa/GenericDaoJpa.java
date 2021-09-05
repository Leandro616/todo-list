package io.github.leandro616.todolist.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import io.github.leandro616.todolist.dao.GenericDao;

@Repository
public class GenericDaoJpa<T, PK> implements GenericDao<T, PK> {

   @PersistenceUnit
   private EntityManager manager;
   private Class<T> classe;

   public EntityManager getManager() {
      return manager;
   }

   @Override
   public void atualizar(T entidade) {
      manager.merge(entidade);
   }

   @Override
   public void deletar(T entidade) {
      manager.remove(entidade);
   }

   @Override
   public T obterPorId(PK id) {
      manager.find(classe, id);
      return null;
   }

   @Override
   public void salvar(T entidade) {
      manager.persist(entidade);
   }
   
}
