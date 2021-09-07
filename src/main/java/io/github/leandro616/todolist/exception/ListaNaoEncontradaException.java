package io.github.leandro616.todolist.exception;

public class ListaNaoEncontradaException extends RuntimeException {
   public ListaNaoEncontradaException() {
      super("Lista de tarefas não encontrada");
   }
}
