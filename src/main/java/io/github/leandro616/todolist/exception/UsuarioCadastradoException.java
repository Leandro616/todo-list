package io.github.leandro616.todolist.exception;

public class UsuarioCadastradoException extends RuntimeException {

   public UsuarioCadastradoException(String email) {
      super("Usuario já cadastrado com o email: " + email);
   }
}
