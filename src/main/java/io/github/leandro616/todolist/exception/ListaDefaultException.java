package io.github.leandro616.todolist.exception;

public class ListaDefaultException extends RuntimeException {
   public ListaDefaultException() {
      super("Essa lista não pode ser excluída ou modificada");
   }
}
