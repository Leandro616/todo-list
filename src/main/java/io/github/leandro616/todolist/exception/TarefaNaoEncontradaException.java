package io.github.leandro616.todolist.exception;

public class TarefaNaoEncontradaException extends RuntimeException {

   public TarefaNaoEncontradaException() {
      super("Tarefa não encontrada");
   }
}
