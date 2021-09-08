package io.github.leandro616.todolist.exception;

public class UsuarioNaoAutenticadoException extends RuntimeException {
   public UsuarioNaoAutenticadoException() {
      super("Usuario não está autenticado");
   }
}
