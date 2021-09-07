package io.github.leandro616.todolist.dao.jpa;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.github.leandro616.todolist.dao.UsuarioDao;
import io.github.leandro616.todolist.model.Usuario;

@Repository
public class UsuarioDaoJpa 
		extends GenericDaoJpa<Usuario, Integer> implements UsuarioDao {

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {

		Usuario usuario = getManager()
			.createQuery("select u from Usuario u where u.email = :email", 
					Usuario.class)
			.setParameter("email", email)
			.getSingleResult();

		return Optional.ofNullable(usuario);
	}

}
