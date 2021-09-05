package io.github.leandro616.todolist.dao;

public interface GenericDao<T, PK> {

   void salvar(T entidade);
   T obterPorId(PK id);
   void atualizar(T entidade);
   void deletar(T entidade);
}
