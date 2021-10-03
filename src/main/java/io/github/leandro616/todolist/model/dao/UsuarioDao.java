package io.github.leandro616.todolist.model.dao;

import java.util.Optional;

import io.github.leandro616.todolist.model.entity.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Integer> {
   
   Optional<Usuario> buscarPorEmail(String email);
}
