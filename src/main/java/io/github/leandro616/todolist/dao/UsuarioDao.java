package io.github.leandro616.todolist.dao;

import io.github.leandro616.todolist.model.entity.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Integer> {
   
   Usuario buscarPorEmail(String email);
}
