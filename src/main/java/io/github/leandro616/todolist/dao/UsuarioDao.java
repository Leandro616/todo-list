package io.github.leandro616.todolist.dao;

import java.util.Optional;

import io.github.leandro616.todolist.model.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Integer> {
   
   Optional<Usuario> buscarPorEmail(String email);
}
