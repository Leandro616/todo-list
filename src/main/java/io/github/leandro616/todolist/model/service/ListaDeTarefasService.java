package io.github.leandro616.todolist.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.leandro616.todolist.model.dao.ListaDeTarefasDao;
import io.github.leandro616.todolist.model.entity.ListaDeTarefas;
import io.github.leandro616.todolist.model.entity.Usuario;
import io.github.leandro616.todolist.model.exception.EntidadeNaoEncontradaException;
import io.github.leandro616.todolist.model.exception.NegocioException;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ListaDeTarefasService {
   
   private final UsuarioService usuarioService;
   private final ListaDeTarefasDao dao;
   
   public void salvar(ListaDeTarefas lista) {
      lista.setUsuario(getUsuario());
      dao.salvar(lista);
   }

   public List<ListaDeTarefas> listar() {
      return dao.listar(getUsuario().getId());
   }

   public void deletar(Integer id) {
      ListaDeTarefas lista = obterPorId(id);

      if (podeSerExcluidaOuModificada(lista)) {
         dao.deletar(dao.buscarPorId(id));
      }
   }

   public void atualizar(Integer id, ListaDeTarefas listaAtualizada) {
      ListaDeTarefas lista = obterPorId(id);

      if (podeSerExcluidaOuModificada(lista)) {
         listaAtualizada.setId(lista.getId());
         listaAtualizada.setUsuario(getUsuario());
         dao.atualizar(listaAtualizada);
      }
   }

   public ListaDeTarefas obterPorId(Integer id) {
      if (dao.buscarPorId(id) == null) {
         throw new EntidadeNaoEncontradaException(
            "Lista de tarefas não encontrada");
      }
      
      return dao.buscarPorId(id);
   }

   private boolean podeSerExcluidaOuModificada(ListaDeTarefas lista) {
      if (lista.getPadrao()) {
         throw new NegocioException(
            "Essa lista não pode ser excluida ou modificada");
      }

      return true;
   }

   private Usuario getUsuario() {
      return usuarioService.usuarioAutenticado();
   }

}
