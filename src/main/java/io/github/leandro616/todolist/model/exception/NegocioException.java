package io.github.leandro616.todolist.model.exception;

public class NegocioException extends RuntimeException {
   public NegocioException(String message) {
      super(message);
   }
}
