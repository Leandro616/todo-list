package io.github.leandro616.todolist.model.dao;

public interface GenericDao<T, PK> {

   void salvar(T entidade);
   void atualizar(T entidade);
   void deletar(T entidade);
}
