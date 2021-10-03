package io.github.leandro616.todolist.model.dao.jpa;

import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import io.github.leandro616.todolist.model.dao.UsuarioDao;
import io.github.leandro616.todolist.model.entity.Usuario;

@Repository
public class UsuarioDaoJpa 
		extends GenericDaoJpa<Usuario, Integer> implements UsuarioDao {

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {

		try {
			Usuario usuario = getManager()
				.createQuery("select u from Usuario u where u.email = :email", 
						Usuario.class)
				.setParameter("email", email)
				.getSingleResult();

			return Optional.ofNullable(usuario);
		} catch (NoResultException e) {
			return Optional.empty();
		}

	}

}
