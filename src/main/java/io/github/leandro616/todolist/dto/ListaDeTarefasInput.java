package io.github.leandro616.todolist.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ListaDeTarefasInput {
    
   @NotBlank(message = "Campo nome obrigatório")
   @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
   private String nome;
}
