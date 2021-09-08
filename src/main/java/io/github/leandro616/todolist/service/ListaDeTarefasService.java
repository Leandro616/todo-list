package io.github.leandro616.todolist.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.leandro616.todolist.dao.ListaDeTarefasDao;
import io.github.leandro616.todolist.exception.ListaNaoEncontradaException;
import io.github.leandro616.todolist.model.ListaDeTarefas;
import io.github.leandro616.todolist.model.Usuario;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ListaDeTarefasService {
   
   private final UsuarioService usuarioService;
   private final ListaDeTarefasDao dao;

   public void salvar(ListaDeTarefas lista) {
      Usuario usuario = usuarioService.usuarioAutenticado();
      lista.setUsuario(usuario);
      dao.salvar(lista);
   }

   public List<ListaDeTarefas> listar() {
      Usuario usuario = usuarioService.usuarioAutenticado();
      return dao.listar(usuario.getIdUsuario());
   }

   public void deletar(Integer id) throws ListaNaoEncontradaException {

      if (dao.buscarPorId(id) == null) {
         throw new ListaNaoEncontradaException();
      }
      
      dao.deletar(dao.buscarPorId(id));
   }

   public void atualizar(Integer id, ListaDeTarefas listaAtualizada)
         throws ListaNaoEncontradaException {
      ListaDeTarefas lista = dao.buscarPorId(id);

      if (lista == null) {
         throw new ListaNaoEncontradaException();
      }
      Usuario usuario = usuarioService.usuarioAutenticado();
      listaAtualizada.setIdLista(lista.getIdLista());
      listaAtualizada.setUsuario(usuario);
      dao.atualizar(listaAtualizada);
   }

   public ListaDeTarefas obterPorId(Integer id) {
      ListaDeTarefas lista = dao.buscarPorId(id);

      if (lista == null) {
         throw new ListaNaoEncontradaException();
      }
      return lista;
   }
}
